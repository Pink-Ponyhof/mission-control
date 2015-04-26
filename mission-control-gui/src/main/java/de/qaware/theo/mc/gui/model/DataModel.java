package de.qaware.theo.mc.gui.model;

import java.util.Map;

/**
 * @author andreas.janning
 */
public class DataModel {

    private String name;

    private Map<String, String> configValues;


    public DataModel() {
    }

    public DataModel(String name, Map<String, String> configValues) {
        this.name = name;
        this.configValues = configValues;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getConfigValues() {
        return configValues;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConfigValues(Map<String, String> configValues) {
        this.configValues = configValues;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "name='" + name + '\'' +
                ", configValues=" + configValues +
                '}';
    }
}
