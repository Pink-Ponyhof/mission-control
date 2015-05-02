package de.qaware.theo.mc.gui.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author andreas.janning
 */
public class DataModel {

    private String name;

    private List<ConfigEntryModel> configValues;


    public DataModel() {
    }

    public DataModel(String name, Map<String, String> configValues) {
        this.name = name;
        this.configValues = new ArrayList<>(configValues.size());
        for(Map.Entry<String, String> entry : configValues.entrySet()){
            this.configValues.add(new ConfigEntryModel(entry.getKey(), entry.getValue()));
        }
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public List<ConfigEntryModel> getConfigValues() {
        return configValues;
    }

    public void setConfigValues(List<ConfigEntryModel> configValues) {
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
