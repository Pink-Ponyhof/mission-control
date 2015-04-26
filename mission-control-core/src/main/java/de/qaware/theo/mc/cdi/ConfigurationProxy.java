package de.qaware.theo.mc.cdi;

import de.qaware.theo.mc.ConfigStore;
import de.qaware.theo.mc.annotation.ConfigKey;
import de.qaware.theo.mc.model.Metadata;
import de.qaware.theo.mc.store.PropertiesStore;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Singleton;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * The bean implementation that can be injected.
 * Proxies all actions to a config proxy.
* @author s.wittke
*/
public class ConfigurationProxy implements Bean {

    public static final Logger LOGGER = Logger.getLogger(ConfigurationProxy.class.getName());

    private final AnnotatedType type;

    private final Metadata metadata;
    private ConfigStore store;

    ConfigurationProxy(AnnotatedType type, Metadata metadata, ConfigStore store) {
        this.type = type;
        this.metadata = metadata;
        this.store = store;
    }

    @Override
    public Set<Type> getTypes() {
        return type.getTypeClosure();
    }

    @Override
    public Set<Annotation> getQualifiers() {
        Set<Annotation> qualifiers = new HashSet<>();
        qualifiers.add( new AnnotationLiteral<Default>() {} );
        qualifiers.add( new AnnotationLiteral<Any>() {} );

        return qualifiers;
    }

    @Override
    public Class<? extends Annotation> getScope() {
        return Singleton.class;
    }

    @Override
    public String getName() {
        return "MC_" + metadata.getName();
    }

    @Override
    public Set<Class<? extends Annotation>> getStereotypes() {
        return Collections.emptySet();
    }

    @Override
    public Class<?> getBeanClass() {
        return type.getJavaClass();
    }

    @Override
    public boolean isAlternative() {
        return false;
    }

    @Override
    public boolean isNullable() {
        return false;
    }

    @Override
    public Set<InjectionPoint> getInjectionPoints() {
        return Collections.emptySet();
    }

    @Override
    public Object create(CreationalContext creationalContext) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{type.getJavaClass()}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                //check which method was invoked
                ConfigKey configKey = method.getAnnotation(ConfigKey.class);
                if (configKey != null) {
                    LOGGER.info("Found config key annotation on invoked method.");

                    String requestedKey = configKey.key();
                    return store.getConfigValue(requestedKey);

                } else {
                    throw new IllegalStateException("Invoked method " + method + "  does not have a config key annotation.");
                }
            }
        });
    }

    @Override
    public void destroy(Object instance, CreationalContext creationalContext) {

    }
}
