package com.brewdogger.beer.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityUpdateHelper {

    private static final Logger logger = LoggerFactory.getLogger(EntityUpdateHelper.class);

    @Autowired
    private EntityPropertyHelper entityPropertyHelper;

    public void updateEntity(Object objectEntity, Object objectUpdates) throws IllegalAccessException {

        var updatedProperties = entityPropertyHelper.getFields(objectUpdates);

        entityPropertyHelper.setFields(objectEntity, updatedProperties);
    }
}
