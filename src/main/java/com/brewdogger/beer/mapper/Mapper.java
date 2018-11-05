package com.brewdogger.beer.mapper;

import com.brewdogger.beer.entity.Beer;
import com.brewdogger.beer.entity.BrewdoggerUser;
import com.brewdogger.beer.entity.Brewery;
import com.brewdogger.beer.model.BeerRequest;
import com.brewdogger.beer.model.BrewdoggerUserRequest;
import com.brewdogger.beer.model.BreweryRequest;

public interface Mapper {

    Beer mapBeerRequestToBeer(BeerRequest beerRequest);

    Brewery mapBreweryRequestToBrewery(BreweryRequest breweryRequest);

    BrewdoggerUser mapBrewdoggerUserRequestToBrewdoggerUser(BrewdoggerUserRequest brewdoggerUserRequest);

}
