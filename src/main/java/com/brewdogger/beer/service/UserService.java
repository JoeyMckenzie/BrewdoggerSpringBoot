package com.brewdogger.beer.service;

import com.brewdogger.beer.entity.BrewdoggerUser;
import com.brewdogger.beer.exception.BrewdoggerUserCreationException;
import com.brewdogger.beer.helper.EntityPropertyHelper;
import com.brewdogger.beer.model.BrewdoggerUserRequest;
import com.brewdogger.beer.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Value("${spring.jpa.salt}")
    private String salt;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityPropertyHelper entityPropertyHelper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var brewdoggerUser = userRepository.findBrewdoggerUserByUsername(username);

        if (!brewdoggerUser.isPresent())
            throw new UsernameNotFoundException("User " + username + " was not found");

        var verifiedBrewdoggerUser = brewdoggerUser.get();
        var authorities = Collections.singletonList(new SimpleGrantedAuthority("user"));

        return new User(verifiedBrewdoggerUser.getUsername(), verifiedBrewdoggerUser.getPassword(), authorities);
    }

    public void saveBrewdoggerUser(BrewdoggerUser brewdoggerUser) {
        var brewdoggerUserFields = entityPropertyHelper.getFields(brewdoggerUser);


        if (brewdoggerUserFields.containsValue(null))
            throw new BrewdoggerUserCreationException("User contains null fields");

        // Since were using tokens now, no real need to salt
        var originalPassword = brewdoggerUser.getPassword();
        brewdoggerUser.setPassword(hashPassword(originalPassword));


        if (brewdoggerUser.getPassword().equals(originalPassword))
            throw new BrewdoggerUserCreationException("Password was not hashed properly");

        userRepository.save(brewdoggerUser);
        logger.info("UserService::saveBrewdoggerUser - User " + brewdoggerUser.getUsername() + " saved successfully");
    }

    public boolean validateBrewdoggerUserRequest(BrewdoggerUserRequest brewdoggerUserRequest) {
        var fields = entityPropertyHelper.getFields(brewdoggerUserRequest);

        if (fields.containsValue(null))
            throw new BrewdoggerUserCreationException("User request contains null fields");

        if (!fields.containsKey("username") || !fields.containsKey("password") ||
                !fields.containsKey("firstName") || !fields.containsKey("lastName"))
            throw new BrewdoggerUserCreationException("User request does not contain required fields");

        return true;
    }

    // For development purposes
    public List<BrewdoggerUser> getAllBrewdoggerUsers() {
        return userRepository.findAll();
    }

    private String hashPassword(String password) {
        var saltedPassword = password.concat(salt);
        return passwordEncoder.encode(saltedPassword);
    }
}
