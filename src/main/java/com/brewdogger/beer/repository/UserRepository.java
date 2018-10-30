package com.brewdogger.beer.repository;

import com.brewdogger.beer.entity.BrewdoggerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<BrewdoggerUser, Long> {
}
