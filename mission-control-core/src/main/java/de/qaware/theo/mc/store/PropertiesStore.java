package de.qaware.theo.mc.store;

import de.qaware.theo.mc.ConfigStore;

import java.util.Map;

/**
 * @author andreas.janning
 */
public class PropertiesStore implements ConfigStore {
    @Override
    public Map<String, String> getConfigValues(String configName) {
        return null;
    }

    @Override
    public void setConfigValues(String configName, Map<String, String> newValues) {

    }
}
