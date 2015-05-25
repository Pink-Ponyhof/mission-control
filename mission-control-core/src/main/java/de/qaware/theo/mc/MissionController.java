package de.qaware.theo.mc;

import de.qaware.theo.mc.model.Metadata;
import de.qaware.theo.mc.store.PropertiesStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The information source for application.
 * The {@link MissionController} can be injected and given access to all requested information in the application.
 *
 * @author andreas.janning
 */
public class MissionController {

    private Map<String, Metadata> metadataMap = new HashMap<>();
    private Map<String, ConfigStore> configStoreMap = new HashMap<>();

    public MissionController() {
    }

    /**
     * Add the given configuration metadata to the metadata map.
     *
     * @param metadata the metadata to be added
     */
    public void addMetadata(Metadata metadata) {
        metadataMap.put(metadata.getName(), metadata);
    }

    /**
     * @return a list of all metadatas accessible by the controller.
     */
    public List<Metadata> allConfigMetadata() {
        return new ArrayList<>(metadataMap.values());
    }

    /**
     * Get the key value pairs for the configuration with the given name
     *
     * @param configName the name annotation of the configuration for whicht the key value pairs are requested.
     * @return the key value map for the given config
     * @throws ConfigurationNotAccessibleException if the values could not be retrieved
     */
    public Map<String, String> getConfigValues(String configName) throws ConfigurationNotAccessibleException {
        ConfigStore configStore = configStoreMap.get(configName);
        if (configStore != null) {
            return configStore.getConfigValues();
        }
        else {
            throw new IllegalArgumentException("No config with name " + configName + " found.");
        }
    }

    public void setConfigValues(String configName, Map<String, String> newValues) throws ConfigurationNotAccessibleException {
        ConfigStore configStore = configStoreMap.get(configName);
        if (configStore != null) {
            configStore.setConfigValues(newValues);
        }
        else {
            throw new IllegalArgumentException("No config with name " + configName + " found.");
        }
    }

    /**
     * Add a config store to the controller.
     *
     * @param configName the name of the config for which the config store holds the values
     * @param store      the config store to be added to the ones handled by the controller
     */
    public void addConfigStore(String configName, ConfigStore store) {
        configStoreMap.put(configName, store);
    }

}
