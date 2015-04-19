package de.qaware.theo.mc.test;

import de.qaware.theo.mc.MissionController;
import de.qaware.theo.mc.model.Metadata;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
    @Path("/allConfigs")
    public String getMissionController() {
        List<Metadata> metadata = missionController.allConfigMetadata();
        return metadata.toString();
    }


}
