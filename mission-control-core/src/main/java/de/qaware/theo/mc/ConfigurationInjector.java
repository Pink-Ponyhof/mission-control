package de.qaware.theo.mc;

import de.qaware.theo.mc.annotation.Configuration;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.*;
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
 * @author andreas.janning
 */
public class ConfigurationInjector implements Extension {

    public static final Logger LOGGER = Logger.getLogger(ConfigurationInjector.class.getName());

    private AnnotatedType<?> annotatedType;

    <T> void processAnnotatedType(@Observes ProcessAnnotatedType<T> pat) {
        LOGGER.info("scanning type: " + pat.getAnnotatedType().getJavaClass().getName());

        if (pat.getAnnotatedType().getAnnotation(Configuration.class) != null) {
            LOGGER.info("Found it!");
            annotatedType = pat.getAnnotatedType();
        }

    }

    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) {
        abd.addBean(new ConfigurationProxy(annotatedType));
    }

    private static class ConfigurationProxy implements Bean {

        private final AnnotatedType type;

        private ConfigurationProxy(AnnotatedType type) {
            this.type = type;
        }

        @Override
        public Set<Type> getTypes() {
            return type.getTypeClosure();
        }

        @Override
        public Set<Annotation> getQualifiers() {
            Set<Annotation> qualifiers = new HashSet<Annotation>();
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
            return "config";
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
                    return "Hello World!";
                }
            });
        }

        @Override
        public void destroy(Object instance, CreationalContext creationalContext) {

        }
    }
}