package com.brewdogger.beer.service.impl;

import com.brewdogger.beer.entity.Brewery;
import com.brewdogger.beer.exception.BrewdoggerException;
import com.brewdogger.beer.exception.BreweryNotFoundException;
import com.brewdogger.beer.helper.EntityPropertyHelper;
import com.brewdogger.beer.helper.EntityUpdateHelper;
import com.brewdogger.beer.model.BreweryRequest;
import com.brewdogger.beer.repository.BreweryRepository;
import com.brewdogger.beer.service.BreweryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreweryServiceImpl implements BreweryService {

    private static final Logger logger = LoggerFactory.getLogger(BreweryServiceImpl.class);

    @Autowired
    private BreweryRepository breweryRepository;

    @Autowired
    private EntityUpdateHelper entityUpdateHelper;

    @Autowired
    private EntityPropertyHelper entityPropertyHelper;

    @Override
    public Brewery getBreweryById(Long id) {

        var brewery = breweryRepository.findById(id);

        return brewery.orElseThrow(() -> new BreweryNotFoundException("Could not find brewery"));
    }

    @Override
    public Brewery getBreweryByName(String name) {

        var brewery = breweryRepository.findBreweryByBreweryName(name);

        return brewery.orElseThrow(() -> new BreweryNotFoundException("Could not find any breweries by name " + name));
    }

    @Override
    public List<Brewery> getAllBreweries() {

        var breweries = breweryRepository.findAll();

        if (breweries.size() == 0)
            throw new BreweryNotFoundException("Could not find any breweries");

        return breweries;
    }

    @Override
    public List<Brewery> getBreweriesByCity(String city) {

        var breweries = breweryRepository.findBreweriesByCity(city);

        if (!breweries.isPresent())
            throw new BreweryNotFoundException("Could not find any breweries in city " + city);

        return breweries.get();
    }

    @Override
    public List<Brewery> getBreweriesByState(String state) {

        var breweries = breweryRepository.findBreweriesByState(state);

        if (!breweries.isPresent())
            throw new BreweryNotFoundException("Could not find any breweries in state " + state);

        return breweries.get();
    }

    @Override
    public void saveBrewery(Brewery brewery) {

        try {

            if (entityPropertyHelper.isValidEntity(brewery))
                breweryRepository.save(brewery);
            else
                throw new BrewdoggerException("Not a valid brewery");

        } catch (Exception e) {
            throw new BrewdoggerException("Could not create brewery");
        }
    }

    @Override
    public void deleteBrewery(Long id) {

        var brewery = getBreweryById(id);

        breweryRepository.delete(brewery);
    }

    @Override
    public void updateBrewery(Long id, BreweryRequest updatedBrewery) {

        var breweryToUpdate = getBreweryById(id);

        try {
            entityUpdateHelper.updateEntity(breweryToUpdate, updatedBrewery);
        } catch (IllegalAccessException e) {
            logger.error("BreweryServiceImpl::updateBrewery - Could not update properties for brewery with id " + id);
        }

        breweryRepository.save(breweryToUpdate);
    }
}
