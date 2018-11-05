package com.brewdogger.beer.service;

import com.brewdogger.beer.entity.Beer;
import com.brewdogger.beer.model.BeerRequest;
import com.brewdogger.beer.model.BeerStyle;

import java.util.List;

/**
 * A beer service for handling CRUD operations
 */
public interface BeerService {

    Beer getBeerById(Long id);

    List<Beer> getAllBeers();

    List<Beer> getBeersByStyle(BeerStyle beerStyle);

    void saveBeer(Beer beer);

    void deleteBeer(Long id);

    void updateBeer(Long id, BeerRequest beer);
}
