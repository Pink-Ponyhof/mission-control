package de.qaware.theo.mc;

import de.qaware.theo.mc.model.Metadata;
import de.qaware.theo.mc.store.PropertiesStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author andreas.janning
 */
public class MissionController {

    private Map<String, Metadata> metadataMap = new HashMap<>();
    private Map<String, ConfigStore> configStoreMap = new HashMap<>();

    public MissionController() {
    }

    public void addMetadata(Metadata metadata) {
        metadataMap.put(metadata.getName(), metadata);
    }

    public List<Metadata> allConfigMetadata() {
        return new ArrayList<>(metadataMap.values());
    }

    public Map<String, String> getConfigValues(String configName) {
        ConfigStore configStore = configStoreMap.get(configName);
        if (configStore != null) {
            return configStore.getConfigValues();
        } else {
            throw new IllegalArgumentException("No config with name " + configName + " found.");
        }
    }

    public void setConfigValues(String configName, Map<String, String> newValues) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public void addConfigStore(String configName, ConfigStore store) {
        configStoreMap.put(configName, store);
    }

}
