package de.qaware.theo.mc;

import java.util.Map;

/**
 * The config store used by the {@@link MissionController}.
 * A config store always belongs to exactly one configuration.
 * The mission controller accesses all config values through the config store.
 * A store implementation can do some caching, or it might read the data whenever it is requested.
 * @author andreas.janning
 */
public interface ConfigStore {

    /**
     * Get all key value pairs of the configuration where this config store belongs to.
     * @return the map of key value pairs
     * @throws ConfigurationNotAccessibleException if some error occured when requesting the key value pairs.
     */
    Map<String, String> getConfigValues() throws ConfigurationNotAccessibleException;

    /**
     * Get the value for the given key in the corresponding configuration
     * @param key the key to get the value for
     * @return the value
     */
    String getConfigValue(String key) throws ConfigurationNotAccessibleException;

    /**
     * Set the configurations config values to the given map.
     * If the map does not contain a key - value pair for a key given in the
     * configuration's {@link de.qaware.theo.mc.model.Metadata} key list,
     * the value is set to an empty string.
     * If the map contains a key not given in the configuration's {@link de.qaware.theo.mc.model.Metadata} key list,
     * this key-value pair will be ignored.
     * @param newValues the new key value pairs of the config.
     */
    void setConfigValues(Map<String, String> newValues) throws ConfigurationNotAccessibleException;

}
