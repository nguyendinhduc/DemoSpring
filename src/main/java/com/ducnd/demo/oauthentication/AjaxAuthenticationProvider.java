//package com.ducnd.demo.oauthentication;
//
//import com.ducnd.demo.oauthentication.model.UserContext;
//import io.jsonwebtoken.lang.Assert;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * Created by ducnd on 6/12/17.
// */
//@Component
//public class AjaxAuthenticationProvider implements AuthenticationProvider {
////    private final BCryptPasswordEncoder encoder;
////    private final DatabaseUserService userService;
//
//    @Autowired
//    public AjaxAuthenticationProvider( ) {
////        this.userService = userService;
////        this.encoder = encoder;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        Assert.notNull(authentication, "No authentication data provided");
//
//        String username = (String) authentication.getPrincipal();
//        String password = (String) authentication.getCredentials();
//
////        User user = userService.getByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
////
////        if (!encoder.matches(password, user.getPassword())) {
////            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
////        }
////
////        if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
////
////        List<GrantedAuthority> authorities = user.getRoles().stream()
////                .match(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
////                .collect(Collectors.toList());
////
////        UserContext userContext = UserContext.create(user.getUsername(), authorities);
////        List<GrantedAuthority> authorities = user.getRoles().stream()
//        List<String> roles = new ArrayList<>();
//        roles.add("USER");
//        roles.add("ADDMIN");
//        List<GrantedAuthority> authorities = roles.stream()
//                .match(role -> {
//                    return new SimpleGrantedAuthority(role);
//                }).collect(Collectors.toList());
//        return new UsernamePasswordAuthenticationToken(new UserContext(username, password, authorities), null, authorities);
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
//    }
//}
