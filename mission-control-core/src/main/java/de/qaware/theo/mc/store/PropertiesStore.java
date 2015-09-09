package de.qaware.theo.mc.store;

import de.qaware.theo.mc.ConfigStore;
import de.qaware.theo.mc.ConfigurationNotAccessibleException;
import de.qaware.theo.mc.data.PropertiesFileOperator;
import de.qaware.theo.mc.model.Metadata;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * The {@link ConfigStore} implementation when the config is given as properties file.
 * It stores the config's metadata and provides methods to get and set the config values.
 *
 * @author andreas.janning
 */
public class PropertiesStore implements ConfigStore {

    private static final Logger LOGGER = Logger.getLogger(PropertiesStore.class.getName());

    public static final String NO_VALUE_FOUND = "";
    private PropertiesFileOperator operator;
    private Metadata metadata;
    private volatile Map<String, String> cachedConfig;
    private ScheduledExecutorService executorService;

    public PropertiesStore(Metadata metadata) throws ConfigurationNotAccessibleException {
        this.operator = new PropertiesFileOperator(metadata);
        this.metadata = metadata;
        this.executorService = Executors.newSingleThreadScheduledExecutor();

        init();
    }


    /**
     * Protected constructor for tests only.
     */
    PropertiesStore(Metadata metadata, PropertiesFileOperator operator) throws ConfigurationNotAccessibleException {
        this.operator = operator;
        this.metadata = metadata;
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        init();
    }

    private void init() throws ConfigurationNotAccessibleException {
        long lastChange = initialRead();
        schedule(lastChange);
    }

    private void schedule(long lastChange) {
        executorService.scheduleWithFixedDelay(new Watchdog(lastChange), 5, 5, TimeUnit.SECONDS);
    }

    private long initialRead() throws ConfigurationNotAccessibleException {
        try {
            long lastChange = operator.lastChange();
            cachedConfig = operator.read();
            return lastChange;
        } catch (IOException e ){
            throw new ConfigurationNotAccessibleException("An error occured when reading properties file", e);
        }
    }

    /**
     * Reads the properties file and retrieves a map of key value pairs.
     * If the file does not exist, the file is created with empty values.
     *
     * @return the map of key value pairs in the config's properties file.
     * @throws ConfigurationNotAccessibleException if an error occured when reading the file
     */
    @Override
    public Map<String, String> getConfigValues() throws ConfigurationNotAccessibleException {
        return cachedConfig;
    }

    /**
     * Check whether the given key is a valid key in the configuration.
     * If it is, read the properties file and get the value for the given key.
     * If the file does not specify a value for the given key, an empty String is returned.
     *
     * @param key the key to get the value for
     * @return the value corresponding to the key.
     */
    @Override
    public String getConfigValue(String key) throws ConfigurationNotAccessibleException {
        if (!metadata.getKeys().contains(key)) {
            throw new IllegalArgumentException("Given key " + key + " is not allowed in config " + metadata.getName());
        }
        if (cachedConfig.containsKey(key)) {
            return cachedConfig.get(key);
        } else {
            return NO_VALUE_FOUND;
        }
    }

    @Override
    public void setConfigValues(Map<String, String> newValues) throws ConfigurationNotAccessibleException {
        try {
            operator.write(newValues);
            cachedConfig = newValues;
        } catch (IOException e) {
            throw new ConfigurationNotAccessibleException("Could not write properties");
        }
    }

    /**
     * ByPass Watchdog and reread configuration for testing purposes
     *
     * @throws IOException on errors reading the config file
     */
    void reReadConfiguration() throws IOException {
        cachedConfig = operator.read();
    }

    private class Watchdog implements Runnable {

        private long lastChange;

        public Watchdog(long lastChange) {
            this.lastChange = lastChange;
        }

        @Override
        public void run() {
            try {
                long currentLastChange = operator.lastChange();
                if (currentLastChange > lastChange) {
                    cachedConfig = operator.read();
                    lastChange = currentLastChange;
                }
            } catch (IOException e) {
                LOGGER.severe("Unable to read config file " + metadata.getFileName());
            }
        }
    }
}
