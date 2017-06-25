package com.ducnd.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by ducnd on 5/19/17.
 */
@ConfigurationProperties(prefix = "tasks")
public class Config {
    @Value("${server}")
    public String server;
}
