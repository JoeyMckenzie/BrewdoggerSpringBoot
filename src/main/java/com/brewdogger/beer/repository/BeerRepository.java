package com.brewdogger.beer.repository;

import com.brewdogger.beer.entity.Beer;
import com.brewdogger.beer.model.BeerStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {

    @Query("select b from Beer as b where b.beerStyle = :beerStyle")
    Optional<List<Beer>> findByBeerStyle(@Param("beerStyle") BeerStyle beerStyle);
}
