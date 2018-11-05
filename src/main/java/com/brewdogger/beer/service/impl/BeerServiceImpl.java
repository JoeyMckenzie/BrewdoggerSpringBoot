package com.brewdogger.beer.service.impl;

import com.brewdogger.beer.entity.Beer;
import com.brewdogger.beer.exception.BeerNotFoundException;
import com.brewdogger.beer.exception.BrewdoggerException;
import com.brewdogger.beer.helper.EntityPropertyHelper;
import com.brewdogger.beer.model.BeerRequest;
import com.brewdogger.beer.model.BeerStyle;
import com.brewdogger.beer.repository.BeerRepository;
import com.brewdogger.beer.service.BeerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Beer service implementation
 */

@Service
public class BeerServiceImpl implements BeerService {

    private static final Logger logger = LoggerFactory.getLogger(BeerServiceImpl.class);

    @Autowired
    private BeerRepository beerRepository;

    @Autowired EntityPropertyHelper entityPropertyHelper;

    @Override
    public Beer getBeerById(Long id) {
        var beer = beerRepository.findById(id);

        return beer.orElseThrow(() -> new BeerNotFoundException("Could not find beer with id [" + id + "]"));
    }

    @Override
    public List<Beer> getAllBeers() {
        var beers = beerRepository.findAll();

        if (beers.size() == 0)
            throw new BeerNotFoundException("No beers found");

        return beers;
    }

    @Override
    public List<Beer> getBeersByStyle(BeerStyle beerStyle) {
        var beers = beerRepository.findByBeerStyle(beerStyle);

        return beers.orElseThrow(() -> new BeerNotFoundException("Could not find any beers with style of " + beerStyle));
    }

    @Override
    public void saveBeer(Beer beer) {
        try {
            if (entityPropertyHelper.isValidEntity(beer))
                beerRepository.save(beer);
            else
                throw new BrewdoggerException("Could not create beer");
        } catch (Exception e) {
            throw new BrewdoggerException("Could not create beer");
        }
    }

    @Override
    public void deleteBeer(Long id) {
        var beerToDelete = getBeerById(id);

        beerRepository.delete(beerToDelete);
    }

    @Override
    public void updateBeer(Long id, BeerRequest updatedBeer) {
        var beerToUpdate = getBeerById(id);

        try {
            entityPropertyHelper.updateEntity(beerToUpdate, updatedBeer);
        } catch (IllegalAccessException e) {
            logger.error("BeerServiceImpl::updateBrewery - Could not update properties for beer with id " + id);
        }

        beerRepository.save(beerToUpdate);
    }
}
