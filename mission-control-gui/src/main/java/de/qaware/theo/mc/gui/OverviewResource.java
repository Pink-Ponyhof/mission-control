package de.qaware.theo.mc.gui;

import de.qaware.theo.mc.gui.model.MetadataModel;
import de.qaware.theo.mc.model.Metadata;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author andreas.janning
 */
@Path("/")
public class OverviewResource {

    private List<Metadata> allMetadata;

    public OverviewResource() {
        allMetadata = new ArrayList<>();
        allMetadata.add(new Metadata("numberOne", "O:/config/numberOne.properties", Arrays.asList("TheStuff", "MoreStuff")));
        allMetadata.add(new Metadata("numberTwo", "O:/config/numberTwo.properties", Arrays.asList("numberOfPonies", "PonyHappiness")));
    }

    @GET
    @Produces({"application/json", "application/xml"})
    public List<MetadataModel> getAllConfigurations(@Context UriInfo uriInfo) {
        List<MetadataModel> result = new ArrayList<>(allMetadata.size());

        for (Metadata metadata : allMetadata) {
            URI path = uriInfo.getRequestUriBuilder().path(metadata.getName()).build();
            result.add(new MetadataModel(metadata, path));
        }
        return result;
    }

}
