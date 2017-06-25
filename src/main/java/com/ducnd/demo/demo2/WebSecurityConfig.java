//package com.ducnd.demo.demo2;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.csrf.CsrfTokenRepository;
//import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
//
///**
// * Created by ducnd on 6/11/17.
// */
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    private static final Logger LOGGER = Logger.getLogger(WebSecurityConfig.class);
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        LOGGER.info("configure...............");
//        InMemoryUserDetailsManager detailsManager = new InMemoryUserDetailsManager();
//        detailsManager.createUser(User.withUsername("username").password("password").roles("USER").build());
//        return detailsManager;
//    }
//
//    @Qualifier("userDetailsService")
//    @Autowired
//    private UserDetailsService details;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        LOGGER.info("configure...............");
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and().formLogin()
//                .loginPage("/login").permitAll()
//                .and()
//                .csrf().disable();
////                .csrfTokenRepository(csrfTokenRepository()).and().userDetailsService(details);
//    }
//
//    private CsrfTokenRepository csrfTokenRepository() {
//        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
////        repository.setSessionAttributeName("_csrf");
//        return repository;
//    }
//}
