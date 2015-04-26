package de.qaware.theo.mc;

import java.util.Map;

/**
 * @author andreas.janning
 */
public interface ConfigStore {

    Map<String, String> getConfigValues();

    String getConfigValue(String key);

    void setConfigValues(Map<String, String> newValues);

}
