package de.qaware.theo.mc.annotation;

import java.lang.annotation.*;

/**
 * The annotation for keys of key-value pairs in {@link de.qaware.theo.mc.annotation.Configuration}
 * @author s.wittke
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConfigKey {

    /**
     * @return the key identifier
     */
    String key();
}
