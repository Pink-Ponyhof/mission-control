package de.qaware.theo.mc.annotation;

import javax.inject.Singleton;
import java.lang.annotation.*;

/**
 * The annotation for the config interface.
 * @author andreas.janning
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Singleton
public @interface Configuration {

    /**
     * @return the config name. used to identify the config
     */
    String name();

    /**
     * @return the path to the file containing the config values
     */
    String file();
}
