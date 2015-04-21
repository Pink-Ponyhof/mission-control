package de.qaware.theo.mc;

import de.qaware.theo.mc.model.Metadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author andreas.janning
 */
public class MissionController {

    private List<Metadata> metadataList = new ArrayList<>();

    public MissionController() {
    }

    public void addMetadata(Metadata metadata) {
        metadataList.add(metadata);
    }

    public List<Metadata> allConfigMetadata() {
        return metadataList;
    }

    public Map<String, String> getConfigValues(String configName) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public void setConfigValues(String configName, Map<String, String> newValues) {
        throw new UnsupportedOperationException("not yet implemented");
    }

}
