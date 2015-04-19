package de.qaware.theo.mc.test;

import de.qaware.theo.mc.annotation.ConfigKey;
import de.qaware.theo.mc.annotation.Configuration;

/**
 * @author s.wittke
 */
@Configuration(name = "ponyConfig", file = "O:/tmpWork/moreproperties.txt")
public interface AnotherInterface {

    @ConfigKey(key="first.value")
    public String getValue();

    @ConfigKey(key="distinct.key.from.other.config")
    public String getAnotherValue();
}
