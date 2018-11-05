package com.brewdogger.beer.controller;

import com.brewdogger.beer.mapper.Mapper;
import com.brewdogger.beer.model.BrewdoggerUserRequest;
import com.brewdogger.beer.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private Mapper mapper;

    @PostMapping
    public ResponseEntity<?> createBrewdoggerUser(@RequestBody BrewdoggerUserRequest brewdoggerUserRequest) {
        // Validate user
        if (userService.validateBrewdoggerUserRequest(brewdoggerUserRequest)) {
            // Check for existing username
            try {
                userService.loadUserByUsername(brewdoggerUserRequest.getUsername());
            } catch (UsernameNotFoundException unfe) {
                logger.info("Creating user " + brewdoggerUserRequest.getUsername());

                var newBrewdoggerUser = mapper.mapBrewdoggerUserRequestToBrewdoggerUser(brewdoggerUserRequest);
                userService.saveBrewdoggerUser(newBrewdoggerUser);

                return ResponseEntity.ok("User " + newBrewdoggerUser.getUsername() + " created");
            }
        }

        return ResponseEntity.ok("Username already exists");
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        var brewdoggerUsers = userService.getAllBrewdoggerUsers();

        return ResponseEntity.ok(brewdoggerUsers);
    }
}
