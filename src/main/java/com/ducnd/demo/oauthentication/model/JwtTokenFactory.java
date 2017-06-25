//package com.ducnd.demo.oauthentication.model;
//
//import com.ducnd.demo.StringUtils;
//import com.ducnd.demo.oauthentication.config.JwtSettings;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
///**
// * Created by ducnd on 6/12/17.
// */
//@Component
//public class JwtTokenFactory {
//    private final JwtSettings settings;
//
//    @Autowired
//    public JwtTokenFactory(JwtSettings settings) {
//        this.settings = settings;
//    }
//
//    public AccessJwtToken createAccessJwtToken(UserContext userContext) {
//        if (StringUtils.isBlank(userContext.getUsername()))
//            throw new IllegalArgumentException("Cannot create JWT Token without username");
//
//        if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty())
//            throw new IllegalArgumentException("User doesn't have any privileges");
//
//        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
//        claims.put("scopes", userContext.getAuthorities().stream().match(s -> s.toString()).collect(Collectors.toList()));
//
//        LocalDateTime currentTime = LocalDateTime.now();
//
//        String token = Jwts.builder()
//                .setClaims(claims)
//                .setIssuer(settings.getTokenIssuer())
//                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
//                .setExpiration(Date.from(currentTime
//                        .plusMinutes(settings.getTokenExpirationTime())
//                        .atZone(ZoneId.systemDefault()).toInstant()))
//                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
//                .compact();
//
//        return new AccessJwtToken(token, claims);
//    }
//
//    public JwtToken createRefreshToken(UserContext userContext) {
//        if (StringUtils.isBlank(userContext.getUsername())) {
//            throw new IllegalArgumentException("Cannot create JWT Token without username");
//        }
//
//        LocalDateTime currentTime = LocalDateTime.now();
//
//        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
//        claims.put("scopes", userContext.getAuthorities());
//
//        String token = Jwts.builder()
//                .setClaims(claims)
//                .setIssuer(settings.getTokenIssuer())
//                .setId(UUID.randomUUID().toString())
//                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
//                .setExpiration(Date.from(currentTime
//                        .plusMinutes(settings.getRefreshTokenExpTime())
//                        .atZone(ZoneId.systemDefault()).toInstant()))
//                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
//                .compact();
//
//        return new AccessJwtToken(token, claims);
//    }
//}
