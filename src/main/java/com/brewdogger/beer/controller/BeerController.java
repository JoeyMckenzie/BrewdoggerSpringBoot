package com.brewdogger.beer.controller;

import com.brewdogger.beer.mapper.Mapper;
import com.brewdogger.beer.model.BeerRequest;
import com.brewdogger.beer.model.BeerStyle;
import com.brewdogger.beer.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/beer")
public class BeerController {

    @Autowired
    private BeerService beerService;

    @Autowired
    private Mapper mapper;

    @GetMapping
    public ResponseEntity<?> getAllBeers() {

        var beers = beerService.getAllBeers();

        return ResponseEntity.ok(beers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBeerById(@PathVariable("id") Long id) {

        var beer = beerService.getBeerById(id);

        return ResponseEntity.ok(beer);
    }

    @GetMapping("/style/{beerStyle}")
    public ResponseEntity<?> getBeerByBeerStyle(@PathVariable("beerStyle") BeerStyle beerStyle) {

        var beers = beerService.getBeersByStyle(beerStyle);

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
