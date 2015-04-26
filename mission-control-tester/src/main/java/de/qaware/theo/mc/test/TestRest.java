package de.qaware.theo.mc.test;

import de.qaware.theo.mc.MissionController;
import de.qaware.theo.mc.model.Metadata;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * @author andreas.janning
 */
@SuppressWarnings("ALL")
@Path("/test")
@Singleton
public class TestRest {

    @Inject
    private ConfigInterface configuration;

    @Inject
    private AnotherInterface anotherConfig;

    @Inject
    private MissionController missionController;

    @GET
    public String getConfig(){
        return configuration.getValue();
    }

    @GET
    @Path("/config/ConfigInterface/value")
    public String getValue() {
        return configuration.getValue();
    }

    @GET
    @Path("/config/ConfigInterface/anotherValue")
    public String getAnotherValue() {
        return configuration.getAnotherValue();
    }

    @GET
    @Path("/config/AnotherConfig/value")
    public String getValueForOtherConfig() {
        return anotherConfig.getValue();
    }

    @GET
    @Path("/allConfigs")
    public String getMissionController() {
        List<Metadata> metadata = missionController.allConfigMetadata();
        return metadata.toString();
    }

    @GET
    @Path("/config/{configName}")
    public String getConfigValue(@PathParam("configName") String configName) {
        //TODO: map?
        List<Metadata> metadataList = missionController.allConfigMetadata();
        for (Metadata metadata : metadataList) {
            if (metadata.getName().equals(configName)) {
                return metadata.toString();
            }
        }
        return "No config with name " + configName + " found.";
    }


}
