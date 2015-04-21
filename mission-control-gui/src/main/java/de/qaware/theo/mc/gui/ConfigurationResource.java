package de.qaware.theo.mc.gui;

import de.qaware.theo.mc.gui.model.DataModel;
import de.qaware.theo.mc.model.Metadata;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author andreas.janning
 */
@Path("/{configurationName}")
public class ConfigurationResource {


    private Map<String, Map<String, String>> allConfigurations;

    public ConfigurationResource() {
        allConfigurations = new HashMap<>(2);

        Map<String, String> oneValues = new HashMap<>(2);
        oneValues.put("TheStuff", "is tough");
        oneValues.put("MoreStuff", "more that enough stuff");
        Map<String, String> twoValues = new HashMap<>(2);
        twoValues.put("numberOfPonies", "7");
        twoValues.put("PonyHappiness", "100%");

        allConfigurations.put("numberOne", oneValues);
        allConfigurations.put("numberTwo", twoValues);
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
