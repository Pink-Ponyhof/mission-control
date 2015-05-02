package de.qaware.theo.mc.gui;

import de.qaware.theo.mc.ConfigurationNotAccessibleException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.qaware.theo.mc.MissionController;
import de.qaware.theo.mc.gui.model.ConfigEntryModel;
import de.qaware.theo.mc.gui.model.DataModel;
import de.qaware.theo.mc.model.Metadata;

import javax.annotation.PostConstruct;
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
    @Produces({"application/json"})
    public Response getConfiguration(@PathParam("configurationName") String name) throws JsonProcessingException {
        Map<String, String> configValues = allConfigurations.get(name);
        if (configValues == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        DataModel entity = new DataModel(name, configValues);
        return Response.ok(objectMapper.writeValueAsBytes(entity)).build();
    }

    @POST
    @Consumes({"application/json"})
    public Response setConfiguration(@PathParam("configurationName") String name, String jsonString) throws IOException {
        List<ConfigEntryModel> configModel = objectMapper.readValue(jsonString, new TypeReference<List<ConfigEntryModel>>() {
        });

        if (!allConfigurations.containsKey(name)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Map<String, String> configuration = new HashMap<>();
        for(ConfigEntryModel entry : configModel){
            configuration.put(entry.getKey(), entry.getValue());
        }

        allConfigurations.put(name, configuration);

        DataModel entity = new DataModel(name, configuration);
        return Response.ok(objectMapper.writeValueAsBytes(entity)).build();
    }
}
