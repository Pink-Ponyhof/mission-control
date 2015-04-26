package de.qaware.theo.mc.gui.model;

import de.qaware.theo.mc.model.Metadata;

import java.net.URI;
import java.util.Collections;
import java.util.List;

/**
 * @author andreas.janning
 */
public class MetadataModel {

    private String name;

    private String fileName;

    private URI href;

    private List<String> keys;

    /**
     * @XmlType must have public noargs constructor
     */
    public MetadataModel() {
    }

    public MetadataModel(Metadata metadata, URI path) {
        this.name = metadata.getName();
        this.fileName = metadata.getFileName();
        this.keys = Collections.unmodifiableList(metadata.getKeys());
        this.href = path;
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

    public URI getHref() {
        return href;
    }
}
