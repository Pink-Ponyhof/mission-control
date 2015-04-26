package de.qaware.theo.mc.gui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.qaware.theo.mc.MissionController;
import de.qaware.theo.mc.gui.model.MetadataModel;
import de.qaware.theo.mc.model.Metadata;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author andreas.janning
 */
@Path("/")
@Singleton
public class OverviewResource {

    private List<Metadata> allMetadata;

    @Inject
    private MissionController missionController;

    @Inject
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init(){
        allMetadata = missionController.allConfigMetadata();
    }

    @GET
    @Produces({"application/json"})
    public byte[] getAllConfigurations(@Context UriInfo uriInfo) throws JsonProcessingException {
        List<MetadataModel> result = new ArrayList<>(allMetadata.size());

        for (Metadata metadata : allMetadata) {
            URI path = uriInfo.getRequestUriBuilder().path(metadata.getName()).build();
            result.add(new MetadataModel(metadata, path));
        }
        return objectMapper.writeValueAsBytes(result);
    }

}
