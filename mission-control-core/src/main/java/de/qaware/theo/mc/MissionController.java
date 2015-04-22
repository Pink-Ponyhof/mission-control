package de.qaware.theo.mc;

import de.qaware.theo.mc.model.Metadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author andreas.janning
 */
public class MissionController {

    private Map<String, Metadata> metadataList = new HashMap<>();

    public MissionController() {
    }

    public void addMetadata(Metadata metadata) {
        metadataList.put(metadata.getName(), metadata);
    }

    public List<Metadata> allConfigMetadata() {
        return new ArrayList<>(metadataList.values());
    }

    public Map<String, String> getConfigValues(String configName) {
        Metadata metadata = metadataList.get(configName);

        throw new UnsupportedOperationException("not yet implemented");
    }

    public void setConfigValues(String configName, Map<String, String> newValues) {
        throw new UnsupportedOperationException("not yet implemented");
    }

}
