package com.ducnd.demo.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ducnd on 6/3/17.
 */
@Configuration
public class ConfigGreatingsBean {

    @Bean
    public Greatings getGreating() {
        Greatings greatings = new Greatings("hello");
        return greatings;
    }
}
