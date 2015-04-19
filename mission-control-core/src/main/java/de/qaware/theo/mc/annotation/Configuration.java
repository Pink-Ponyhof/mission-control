package de.qaware.theo.mc.annotation;

import javax.inject.Singleton;
import java.lang.annotation.*;

/**
 * @author andreas.janning
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Singleton
public @interface Configuration {

    String file();
}
