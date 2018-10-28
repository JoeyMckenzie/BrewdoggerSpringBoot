package com.brewdogger.beer.controller;

import com.brewdogger.beer.helper.EntityPropertyHelper;
import com.brewdogger.beer.mapper.Mapper;
import com.brewdogger.beer.model.BreweryRequest;
import com.brewdogger.beer.service.BreweryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brewery")
public class BreweryController {

    private static final Logger logger = LoggerFactory.getLogger(BreweryController.class);

    @Autowired
    private BreweryService breweryService;

    @Autowired
    private EntityPropertyHelper entityPropertyHelper;

    @Autowired
    private Mapper mapper;

    @GetMapping
    public ResponseEntity<?> getAllBreweries() {

        var breweries = breweryService.getAllBreweries();

        return ResponseEntity.ok(breweries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBreweryById(@PathVariable("id") Long id) {

        var brewery = breweryService.getBreweryById(id);

        return ResponseEntity.ok(brewery);
    }

    @PostMapping
    public ResponseEntity<?> createBrewery(@RequestBody BreweryRequest breweryRequest) {

        var newBrewery = mapper.mapBreweryRequestToBrewery(breweryRequest);

        breweryService.saveBrewery(newBrewery);

        return ResponseEntity.ok("Brewery has been created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBrewery(@PathVariable("id") Long id, @RequestBody BreweryRequest breweryRequest) {

        breweryService.updateBrewery(id, breweryRequest);

        return ResponseEntity.ok("Brewery with id " + id + " has been update");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrewery(@PathVariable("id") Long id) {

        breweryService.deleteBrewery(id);

        return ResponseEntity.ok("Brewery with id " + id + " has been deleted");
    }
}
