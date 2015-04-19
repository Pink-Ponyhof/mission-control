package de.qaware.theo.mc.model;

import java.util.List;

/**
 * @author andreas.janning
 */
public class Metadata {

    private final String name;

    private final String fileName;

    private final List<String> keys;

    public Metadata(String name, String fileName, List<String> keys) {
        this.name = name;
        this.fileName = fileName;
        this.keys = keys;
    }

    public String getName() {
        return name;
    }

    public String getFileName() {
        return fileName;
    }

    public List<String> getKeys() {
        return keys;
    }
}
