package com.brewdogger.beer.helper;

import com.brewdogger.beer.entity.Beer;
import com.brewdogger.beer.entity.Brewery;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EntityPropertyHelper.class)
public class EntityPropertyHelperTest {

    private BeerRequest beerRequest;
    private BreweryRequest breweryRequest;
    private Brewery brewery;
    private Beer beer;

    @Autowired
    private EntityPropertyHelper entityPropertyHelper;

    @Before
    public void setUp() {
        /*
            Entities
         */
        beer = new Beer();
        beer.setName("Test Beer Request");
        beer.setIbu(100);
        beer.setBeerStyle(BeerStyle.PALE_ALE);
        beer.setAbv(BigDecimal.valueOf(4.5));

        brewery = new Brewery();
        brewery.setBreweryName("Test Brewery Request");
        brewery.setState("CA");
        brewery.setCity("Sacramento");

        /*
            Request DTOs
         */
        beerRequest = new BeerRequest();
        beerRequest.setName("Test Beer Request");
        beerRequest.setIbu(90);
        beerRequest.setAbv(BigDecimal.valueOf(2.4));

        breweryRequest = new BreweryRequest();
        breweryRequest.setBreweryName("Test Brewery Request");
        breweryRequest.setCity("Redding");
    }

    @After
    public void tearDown() {
        beer = null;
        brewery = null;
        breweryRequest = null;
        beerRequest = null;
    }

    @Test
    public void getFields_givenRequestDto_returnsAllPopulatedFields() {
        // Act
        var beerRequestMap = entityPropertyHelper.getFields(beerRequest);
        var breweryRequestMap = entityPropertyHelper.getFields(breweryRequest);

        // Assert
        Assert.assertNotNull("Beer request DTO map is populated", beerRequestMap);
        Assert.assertNotNull("Brewery request DTO map is populated", breweryRequestMap);
        Assert.assertEquals("Beer request DTO map contains all populated fields", 3, beerRequestMap.size());
        Assert.assertEquals("Brewery request DTO map contains all populated fields", 2, breweryRequestMap.size());
    }

    @Test
    public void getFields_givenEntities_returnsAllPopulatedFields() {
        // Act
        var beerMap = entityPropertyHelper.getFields(beer);
        var breweryMap = entityPropertyHelper.getFields(brewery);

        // Assert
        Assert.assertNotNull("Beer entity map is populated", beerMap);
        Assert.assertNotNull("Brewery entity map is populated", breweryMap);
        Assert.assertEquals("Beer entity map contains all populated fields", 4, beerMap.size());
        Assert.assertEquals("Brewery entity map contains all populated fields", 3, breweryMap.size());
    }

    @Test
    public void setFields_givenBeerDtoAndEntity_updatesEntityFields() throws IllegalAccessException {
        // Act
        var beerRequestMap = entityPropertyHelper.getFields(beerRequest);
        entityPropertyHelper.setFields(beer, beerRequestMap);

        Assert.assertEquals("Beer ABV has been updated", beer.getAbv(), beerRequest.getAbv());
        Assert.assertEquals("Beer IBU has been updated", beer.getIbu(), beerRequest.getIbu());
    }

    @Test
    public void setFields_givenBreweryDtoAndEntity_updatesEntityFields() throws IllegalAccessException {
        // Act
        var breweryRequestMap = entityPropertyHelper.getFields(breweryRequest);
        entityPropertyHelper.setFields(brewery, breweryRequestMap);

        Assert.assertEquals("Brewery city has been updated", brewery.getCity(), breweryRequest.getCity());
    }
}