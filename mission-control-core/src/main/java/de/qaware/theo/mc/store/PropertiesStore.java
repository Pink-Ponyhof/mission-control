package de.qaware.theo.mc.store;

import de.qaware.theo.mc.ConfigStore;
import de.qaware.theo.mc.ConfigurationNotAccessibleException;
import de.qaware.theo.mc.data.PropertiesReader;
import de.qaware.theo.mc.model.Metadata;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;

/**
 * The {@link ConfigStore} implementation when the config is given as properties file.
 * It stores the config's metadata and provides methods to get and set the config values.
 *
 * @author andreas.janning
 */
public class PropertiesStore implements ConfigStore {

    public static final String NO_VALUE_FOUND = "";
    private PropertiesReader reader;
    private Metadata metadata;

    public PropertiesStore(Metadata metadata) {
        this.reader = new PropertiesReader(metadata);
        this.metadata = metadata;
    }

    /**
     * Protected constructor for tests only.
     */
    PropertiesStore(Metadata metadata, PropertiesReader reader) {
        this.reader = reader;
        this.metadata = metadata;
    }

    /**
     * Reads the properties file and retrieves a map of key value pairs.
     * If the file does not exist, the file is created with empty values.
     * @return the map of key value pairs in the config's properties file.
     * @throws ConfigurationNotAccessibleException if an error occured when reading the file
     */
    @Override
    public Map<String, String> getConfigValues() throws ConfigurationNotAccessibleException {
        Map<String, String> configValues;

        try {
            configValues = reader.read();
        }
        catch (IOException e) {
            throw new ConfigurationNotAccessibleException("An error occured when reading properties file", e);
        }

        return configValues;
    }

    /**
     * Check whether the given key is a valid key in the configuration.
     * If it is, read the properties file and get the value for the given key.
     * If the file does not specify a value for the given key, an empty String is returned.
     * @param key the key to get the value for
     * @return the value corresponding to the key.
     */
    @Override
    public String getConfigValue(String key) throws ConfigurationNotAccessibleException {
        if (!metadata.getKeys().contains(key)) {
            throw new IllegalArgumentException("Given key " + key + " is not allowed in config " + metadata.getName());
        }
        try {
            Map<String, String> configValues = reader.read();
            if (configValues.containsKey(key)) {
                return configValues.get(key);
            }
            else {
                return NO_VALUE_FOUND;
            }
        }
        catch (IOException e) {
            throw new ConfigurationNotAccessibleException("Could not read or write properties file", e);
        }
    }

    @Override
    public void setConfigValues(Map<String, String> newValues) {
        throw new UnsupportedOperationException();
    }
}
