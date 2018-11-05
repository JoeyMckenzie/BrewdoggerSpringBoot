package com.brewdogger.beer.repository;

import com.brewdogger.beer.entity.BrewdoggerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<BrewdoggerUser, String> {

    Optional<BrewdoggerUser> findBrewdoggerUserByUsername(String username);

}
