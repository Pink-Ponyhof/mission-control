package de.qaware.theo.mc.gui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.qaware.theo.mc.ConfigurationNotAccessibleException;
import de.qaware.theo.mc.MissionController;
import de.qaware.theo.mc.gui.model.ConfigEntryModel;
import de.qaware.theo.mc.gui.model.DataModel;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
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

    @Inject
    private ObjectMapper objectMapper;

    @GET
    @Produces({"application/json"})
    public Response getConfiguration(@PathParam("configurationName") String name) throws JsonProcessingException, ConfigurationNotAccessibleException {
        if (missionController.configExists(name)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Map<String, String> configValues;
        configValues = missionController.getConfigValues(name);

        DataModel entity = new DataModel(name, configValues);
        return Response.ok(objectMapper.writeValueAsBytes(entity)).build();
    }

    @POST
    @Consumes({"application/json"})
    public Response setConfiguration(@PathParam("configurationName") String name, String jsonString) throws IOException, ConfigurationNotAccessibleException {
        List<ConfigEntryModel> configModel = objectMapper.readValue(jsonString, new TypeReference<List<ConfigEntryModel>>() {
        });

        if (missionController.configExists(name)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Map<String, String> configuration = new HashMap<>();
        for (ConfigEntryModel entry : configModel) {
            configuration.put(entry.getKey(), entry.getValue());
        }

        missionController.setConfigValues(name, configuration);

        DataModel entity = new DataModel(name, configuration);
        return Response.ok(objectMapper.writeValueAsBytes(entity)).build();
    }
}
