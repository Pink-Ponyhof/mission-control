package de.qaware.theo.mc;

import java.util.Map;

/**
 * @author andreas.janning
 */
public interface ConfigStore {

    Map<String, String> getConfigValues(String configName);

    void setConfigValues(String configName, Map<String, String> newValues);

}
