package com.brewdogger.beer.repository;

import com.brewdogger.beer.entity.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BreweryRepository extends JpaRepository<Brewery, Long> {

    @Query("select b from Brewery as b where b.state like %:state%")
    Optional<List<Brewery>> findBreweriesByState(@Param("state") String state);

    @Query("select b from Brewery as b where b.city like %:city%")
    Optional<List<Brewery>> findBreweriesByCity(@Param("city") String city);

    Optional<Brewery> findBreweryByBreweryName(@Param("breweryName") String breweryName);
}
