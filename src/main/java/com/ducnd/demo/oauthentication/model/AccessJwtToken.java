package com.ducnd.demo.oauthentication.model;

import io.jsonwebtoken.Claims;

/**
 * Created by ducnd on 6/12/17.
 */
public class AccessJwtToken implements JwtToken {
    private final String rawToken;
    private Claims claims;

    protected AccessJwtToken(final String token, Claims claims) {
        this.rawToken = token;
        this.claims = claims;
    }

    public String getToken() {
        return this.rawToken;
    }

    public Claims getClaims() {
        return claims;
    }
}
