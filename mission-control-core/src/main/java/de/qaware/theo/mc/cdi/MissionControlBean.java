package de.qaware.theo.mc.cdi;


import de.qaware.theo.mc.MissionController;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Singleton;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author s.wittke
 */
public class MissionControlBean implements Bean {

    private MissionController missionController;

    public MissionControlBean(MissionController missionController) {
        this.missionController = missionController;
    }

    @Override
    public Set<Type> getTypes() {
        Set<Type> types = new HashSet<>();
        types.add(MissionController.class);
        return types;
    }

    /**
     * @return default qualifiers
     */
    @Override
    public Set<Annotation> getQualifiers() {
        Set<Annotation> qualifiers = new HashSet<>();
        qualifiers.add( new AnnotationLiteral<Default>() {} );
        qualifiers.add( new AnnotationLiteral<Any>() {} );

        return qualifiers;
    }

    /**
     * @return Singleton.class
     */
    @Override
    public Class<? extends Annotation> getScope() {
        return Singleton.class;
    }

    @Override
    public String getName() {
        return "MC_MissionControl";
    }

    /**
     * @return no stereotypes necessary
     */
    @Override
    public Set<Class<? extends Annotation>> getStereotypes() {
        return Collections.emptySet();
    }

    @Override
    public Class<?> getBeanClass() {
        return MissionController.class;
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

    /**
     * @return the {@link de.qaware.theo.mc.MissionController} object to call methods on it
     */
    @Override
    public Object create(CreationalContext creationalContext) {
        return missionController;
    }

    @Override
    public void destroy(Object instance, CreationalContext creationalContext) {

    }
}
