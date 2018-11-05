package com.brewdogger.beer.mapper;

import com.brewdogger.beer.model.BeerRequest;
import com.brewdogger.beer.model.BeerStyle;
import com.brewdogger.beer.model.BreweryRequest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Mapper.class)
public class MapperTest {

    private BeerRequest beerRequest;
    private BreweryRequest breweryRequest;

    @Autowired
    private Mapper mapper;

    @Before
    public void setUp() {
        beerRequest = new BeerRequest();
        beerRequest.setName("Pliny the Elder");
        beerRequest.setAbv(BigDecimal.valueOf(8.0));
        beerRequest.setBeerStyle(BeerStyle.DOUBLE_IPA);
        beerRequest.setIbu(110);

        breweryRequest = new BreweryRequest();
        breweryRequest.setBreweryName("Fall River Brewery");
        breweryRequest.setCity("Redding");
        breweryRequest.setState("CA");
    }

    @After
    public void tearDown() {
        beerRequest = null;
        breweryRequest = null;
    }

    @Test
    public void mapBeerRequestToBeer_givenBeerRequestWithPopulatedFields_mapsAllFieldsToBeer() throws IllegalAccessException {

        // Act
        var beer = mapper.mapBeerRequestToBeer(beerRequest);

        // Assert
        Assert.assertNotNull("Beer object is created from mapper", beer);
        Assert.assertTrue("Beer request is mapped to beer", verifyPopulatedFields(beerRequest, beer));
    }

    @Test
    public void mapBreweryRequestToBrewery_givenBreweryRequestWithPopulatedFields_mapsAllFieldsToBrewery() throws IllegalAccessException {

        // Act
        var brewery = mapper.mapBreweryRequestToBrewery(breweryRequest);

        // Assert
        Assert.assertNotNull("Beer object is created from mapper", brewery);
        Assert.assertTrue("Beer request is mapped to beer", verifyPopulatedFields(breweryRequest, brewery));
    }

    private boolean verifyPopulatedFields(Object requestObject, Object object) throws IllegalAccessException {

        var objectRequestMap = buildObjectMap(requestObject);
        var objectMap = buildObjectMap(object);

        for (var key : objectMap.keySet()) {
            if (!objectRequestMap.containsKey(key) ||
                    (objectRequestMap.containsKey(key) &&
                            objectRequestMap.get(key) != objectMap.get(key))) {
                return false;
            }
        }

        return true;
    }

    private HashMap<String, Object> buildObjectMap(Object object) throws IllegalAccessException {

        var objectMap = new HashMap<String, Object>();

        for (var objectField : object.getClass().getDeclaredFields()) {
            objectField.setAccessible(true);
            var objectFieldValue = objectField.get(object);

            if (objectFieldValue != null)
                objectMap.put(objectField.getName(), objectFieldValue);
        }

        return objectMap;
    }
}