package com.brewdogger.beer.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class EntityPropertyHelper {

    private static final Logger logger = LoggerFactory.getLogger(EntityPropertyHelper.class);

    /**
     * Entity mapping that validates only fields required to be updated.
     *
     * @param objectEntity Original entity contained in the database
     * @param objectUpdates Request entity with updated values
     * @throws IllegalAccessException Throws if property is not accessible
     */
    public void updateEntity(Object objectEntity, Object objectUpdates) throws IllegalAccessException {

        var updatedProperties = getFields(objectUpdates);

        setFields(objectEntity, updatedProperties);
    }

    public HashMap<String, Object> getFields(Object objectToMapFrom) throws IllegalStateException {

        var properties = new HashMap<String, Object>();

        // Get BeerRequest populated field values
        for (var field : objectToMapFrom.getClass().getDeclaredFields()) {

            // Make private property accessible
            field.setAccessible(true);
            Object value = null;

            try {
                value = field.get(objectToMapFrom);
            } catch (IllegalAccessException e) {
                logger.error("MapperImpl::map - Could not retrieve " + objectToMapFrom.getClass().getName() + " property");
            }

            if (value != null) {
                properties.put(field.getName(), value);
                logger.info("MapperImpl::map - Mapped field " + field.getName() + " with value " + value + " from request successfully");
            }
        }

        return properties;
    }

    public void setFields(Object objectToMapTo, HashMap<String, Object> map) throws IllegalAccessException {

        for (var field : objectToMapTo.getClass().getDeclaredFields()) {

            field.setAccessible(true);

            if (map.containsKey(field.getName())) {
                field.set(objectToMapTo, map.get(field.getName()));
            }
        }
    }

    public boolean isValidEntity(Object objectEntity) throws IllegalAccessException {

        for (var objectEntityField : objectEntity.getClass().getDeclaredFields()) {
            objectEntityField.setAccessible(true);

            if (objectEntityField.get(objectEntity) != null)
                return true;
        }

        return false;
    }
}
