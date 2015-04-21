package de.qaware.theo.mc.gui.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
 * @author andreas.janning
 */
@XmlRootElement(name = "configuration")
public class DataModel {

    @XmlElement
    private String name;

    @XmlElement
    private Map<String, String> keyValueMap;


    public DataModel() {
    }

    public DataModel(String name, Map<String, String> keyValueMap) {
        this.name = name;
        this.keyValueMap = keyValueMap;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getKeyValueMap() {
        return keyValueMap;
    }
}
