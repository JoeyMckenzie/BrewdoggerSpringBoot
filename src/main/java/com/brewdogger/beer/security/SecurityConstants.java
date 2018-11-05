package com.brewdogger.beer.security;

class SecurityConstants {
    static final String SECRET = "SecretKeyToGenJWTs";
    static final String SALT = "brewdogger";
    static final long EXPIRATION_TIME = 864000000L; // 10 days
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
}
