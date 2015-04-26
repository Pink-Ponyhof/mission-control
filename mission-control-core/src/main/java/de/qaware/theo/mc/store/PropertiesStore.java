package de.qaware.theo.mc.store;

import de.qaware.theo.mc.ConfigStore;
import de.qaware.theo.mc.data.PropertiesReader;
import de.qaware.theo.mc.model.Metadata;

import java.io.IOException;
import java.util.Map;

/**
 * The {@link ConfigStore} implementation when the config is given as properties file.
 * It stores the config's metadata and provides methods to get and set the config values.
 * @author andreas.janning
 */
public class PropertiesStore implements ConfigStore {

    private PropertiesReader reader;
    private Metadata metadata;

    public PropertiesStore(Metadata metadata) {
        this.reader = new PropertiesReader(metadata);
        this.metadata = metadata;
    }

    @Override
    public Map<String, String> getConfigValues() {
        return null;
    }

    @Override
    public String getConfigValue(String key) {
        try {
            Map<String, String> configValues = reader.read();
            if (configValues.containsKey(key)) {
                return configValues.get(key);
            } else {
                return "No value set.";
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return "IO Exception occured.";
        }
    }

    @Override
    public void setConfigValues(Map<String, String> newValues) {

    }
}
