package com.brewdogger.beer.controller;

import com.brewdogger.beer.mapper.Mapper;
import com.brewdogger.beer.model.BeerRequest;
import com.brewdogger.beer.model.BeerStyle;
import com.brewdogger.beer.service.BeerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/beer")
public class BeerController {

    private static final Logger logger = LoggerFactory.getLogger(BeerController.class);

    @Autowired
    private BeerService beerService;

    @Autowired
    private Mapper mapper;

    @GetMapping
    public ResponseEntity<?> getAllBeers() {
        var beers = beerService.getAllBeers();
        logger.info(String.format("BeerController::getAllBeers - found %d beers", beers.size()));

        return ResponseEntity.ok(beers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBeerById(@PathVariable("id") Long id) {
        var beer = beerService.getBeerById(id);
        logger.info("BeerController::getBeerById - Found beer with id [" + id + "]");

        return ResponseEntity.ok(beer);
    }

    @GetMapping("/style/{beerStyle}")
    public ResponseEntity<?> getBeerByBeerStyle(@PathVariable("beerStyle") BeerStyle beerStyle) {
        var beers = beerService.getBeersByStyle(beerStyle);
        logger.info("BeerController::getBeerById - Found beer with beer style [" + beerStyle + "]");

        return ResponseEntity.ok(beers);
    }

    @PostMapping
    public ResponseEntity<?> createBeer(@RequestBody BeerRequest beerRequest) {
        var newBeer = mapper.mapBeerRequestToBeer(beerRequest);

        beerService.saveBeer(newBeer);

        return ResponseEntity.ok("Beer created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBeer(@PathVariable("id") Long id, @RequestBody BeerRequest beerRequest) {
        beerService.updateBeer(id, beerRequest);

        return ResponseEntity.ok("Beer updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBeer(@PathVariable("id") Long id) {
        beerService.deleteBeer(id);

        return ResponseEntity.ok("Beer deleted");
    }
}
