package de.qaware.theo.mc.test;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author andreas.janning
 */
@Path("/test")
@Singleton
public class TestRest {

    @Inject
    private ConfigInterface configuration;

    @GET
    public String getConfig(){
        return configuration.getValue();
    }
}
