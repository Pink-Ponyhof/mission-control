package de.qaware.theo.mc.gui;

import de.qaware.theo.mc.gui.model.MetadataModel;
import de.qaware.theo.mc.model.Metadata;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.InputStream;
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
    public List<MetadataModel> getAllConfigurations() {
        List<MetadataModel> result = new ArrayList<>(allMetadata.size());

        for (Metadata metadata : allMetadata) {
            result.add(new MetadataModel(metadata));
        }
        return result;
    }

    @GET
    @Produces("text/html")
    public InputStream getOverviewHtml() {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        return cl.getResourceAsStream("html/Overview.html");
    }
}
