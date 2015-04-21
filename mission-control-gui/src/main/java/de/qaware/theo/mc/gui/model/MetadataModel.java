package de.qaware.theo.mc.gui.model;

import de.qaware.theo.mc.model.Metadata;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author andreas.janning
 */
@XmlRootElement(name = "configuration")
public class MetadataModel {

    @XmlElement
    private String name;

    @XmlElement
    private String fileName;

    @XmlElement
    private URI href;

    @XmlElementWrapper
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
