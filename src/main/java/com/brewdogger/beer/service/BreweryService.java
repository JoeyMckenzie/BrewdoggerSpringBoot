package com.brewdogger.beer.service;

import com.brewdogger.beer.entity.Beer;
import com.brewdogger.beer.entity.Brewery;
import com.brewdogger.beer.model.BeerRequest;
import com.brewdogger.beer.model.BeerStyle;
import com.brewdogger.beer.model.BreweryRequest;

import java.util.List;

public interface BreweryService {

    Brewery getBreweryById(Long id);

    Brewery getBreweryByName(String name);

    List<Brewery> getAllBreweries();

    List<Brewery> getBreweriesByCity(String city);

    List<Brewery> getBreweriesByState(String state);

    void saveBrewery(Brewery beer);

    void deleteBrewery(Long id);

    void updateBrewery(Long id, BreweryRequest beer);
}
