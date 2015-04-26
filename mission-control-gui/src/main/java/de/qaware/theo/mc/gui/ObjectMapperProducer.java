package de.qaware.theo.mc.gui;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.inject.Produces;

/**
 * @author andreas.janning
 */
public class ObjectMapperProducer {


    @Produces
    public ObjectMapper produceObjectMapper() {
        return new ObjectMapper();
    }
}
