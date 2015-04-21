package de.qaware.theo.mc.gui;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.Calendar;

/**
 * @author andreas.janning
 */
@Path("/static")
public class StaticContentResource {

    @Path("{path:.*}")
    @GET
    public Response serveStaticResource(@PathParam("path") String path) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream is = cl.getResourceAsStream("static/" + path);
        if (is == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Calendar expires = Calendar.getInstance();
        expires.add(Calendar.YEAR, 1);
        return Response.ok(is, "application/javascript").expires(expires.getTime()).build();
    }
}
