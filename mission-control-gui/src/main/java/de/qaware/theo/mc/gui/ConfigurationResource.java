package de.qaware.theo.mc.gui;

import de.qaware.theo.mc.ConfigurationNotAccessibleException;
import de.qaware.theo.mc.MissionController;
import de.qaware.theo.mc.gui.model.DataModel;
import de.qaware.theo.mc.model.Metadata;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author andreas.janning
 */
@Path("/{configurationName}")
@Singleton
public class ConfigurationResource {


    @Inject
    private MissionController missionController;


    private Map<String, Map<String, String>> allConfigurations;

    @PostConstruct
    public void init() throws ConfigurationNotAccessibleException {
        List<Metadata> metadatas = missionController.allConfigMetadata();

        allConfigurations = new HashMap<>(metadatas.size());

        for (Metadata metadata : metadatas) {
            String name = metadata.getName();
            Map<String, String> configValues = missionController.getConfigValues(name);
            allConfigurations.put(name, configValues);
        }
    }


    @GET
    @Produces({"application/json", "application/xml"})
    public Response getConfiguration(@PathParam("configurationName") String name) {
        Map<String, String> configValues = allConfigurations.get(name);
        if (configValues == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new DataModel(name, configValues)).build();
    }
}
