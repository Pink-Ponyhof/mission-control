package de.qaware.theo.mc.test;

import de.qaware.theo.mc.annotation.ConfigKey;
import de.qaware.theo.mc.annotation.Configuration;

/**
 * @author andreas.janning
 */
@Configuration(name = "fancy", file = "O:/tmpWork/testfile.properties")
public interface ConfigInterface {

    @ConfigKey(key="first.value")
    public String getValue();

    @ConfigKey(key="second.value")
    public String getAnotherValue();

}
