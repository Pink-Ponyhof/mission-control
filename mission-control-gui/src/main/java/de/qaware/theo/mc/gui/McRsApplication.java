package de.qaware.theo.mc.gui;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * @author andreas.janning
 */
public class McRsApplication extends Application {

    /**
     * Explicitly set classes so we do not accidentally pick up resources of our host application.
     *
     * @return the resource classes of the mission control gui
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(OverviewResource.class);
        classes.add(ConfigurationResource.class);
        classes.add(StaticContentResource.class);
        return classes;
    }
}
