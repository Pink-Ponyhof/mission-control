package de.qaware.theo.mc.cdi;

import de.qaware.theo.mc.ConfigStore;
import de.qaware.theo.mc.ConfigurationNotAccessibleException;
import de.qaware.theo.mc.MissionController;
import de.qaware.theo.mc.annotation.ConfigKey;
import de.qaware.theo.mc.annotation.Configuration;
import de.qaware.theo.mc.model.Metadata;
import de.qaware.theo.mc.store.PropertiesStore;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.*;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author andreas.janning
 * @see <a href="https://docs.jboss.org/weld/reference/latest/en-US/html/extend.html">Extension reference</a>
 */
public class ConfigurationInjector implements Extension {

    public static final Logger LOGGER = Logger.getLogger(ConfigurationInjector.class.getName());

    private Map<AnnotatedType<?>,Metadata> configs = new HashMap<>();
    private MissionController missionController = new MissionController();

    /**
     * @param pat
     * @param <T>
     */
    <T> void processAnnotatedType(@Observes ProcessAnnotatedType<T> pat) {
        AnnotatedType<T> annotatedType = pat.getAnnotatedType();

        LOGGER.info("scanning type: " + annotatedType.getJavaClass().getName());

        Configuration configuration = annotatedType.getAnnotation(Configuration.class);
        if (configuration != null) {
            LOGGER.info("Found configuration annotation!");

            List<String> keys = new ArrayList<>();
            String name = configuration.name();
            String file = configuration.file();

            LOGGER.info("Configuration has name " + name + " and refers to file " + file);

            Set<AnnotatedMethod<? super T>> methods = annotatedType.getMethods();
            for (AnnotatedMethod method : methods) {
                ConfigKey configKey = method.getAnnotation(ConfigKey.class);
                if (configKey != null) {
                    keys.add(configKey.key());
                    LOGGER.info("added key " + configKey.key() + " to list of keys for configuration " + name);
                }
            }

            Metadata metadata = new Metadata(name, file, keys);
            configs.put(annotatedType, metadata);
            missionController.addMetadata(metadata);
        }
    }

    /**
     * Add all config beans and the mission controller bean s.th. they can be injected in the application
     */
    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) throws ConfigurationNotAccessibleException {
        for (Map.Entry<AnnotatedType<?>, Metadata> entry : configs.entrySet()) {
            Metadata metadata = entry.getValue();
            ConfigStore configStore = new PropertiesStore(metadata);
            missionController.addConfigStore(metadata.getName(), configStore);

            abd.addBean(new ConfigurationProxy(entry.getKey(), metadata, configStore));
            LOGGER.info("Added bean as proxy for " + entry.getKey());
        }
        abd.addBean(new MissionControlBean(missionController));
        LOGGER.info("Added mission controller bean");
    }

}
