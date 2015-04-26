package de.qaware.theo.mc.gui;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.Calendar;

/**
 * @author andreas.janning
 */
@Path("/static")
@Singleton
public class StaticContentResource {

    @Path("{path:js/.*}")
    @GET
    public Response serveJavascript(@PathParam("path") String path) {
        return serveStaticResource(path, "application/javascript");
    }

    @Path("{path:html/.*}")
    @GET
    public Response serveHtml(@PathParam("path") String path) {
        return serveStaticResource(path, "text/html");
    }

    private Response serveStaticResource(@PathParam("path") String path, String contentTyope) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream is = cl.getResourceAsStream("static/" + path);
        if (is == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Calendar expires = Calendar.getInstance();
        expires.add(Calendar.YEAR, 1);
        return Response.ok(is, contentTyope)
//                .expires(expires.getTime())
                .build();
    }
}
