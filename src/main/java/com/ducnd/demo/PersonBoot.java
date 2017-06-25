package com.ducnd.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by ducnd on 6/3/17.
 */
@Component
@ConfigurationProperties("person")
public class PersonBoot {
    private String config;
    private String say;
    private String name;
    private String age;
    private String firstName;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getSay() {
        return say;
    }

    public void setSay(String say) {
        this.say = say;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void print() {
        System.out.println("config: " + config);
        System.out.println("say: " + say);
        System.out.println("name: " + name);
        System.out.println("age: " + age);
        System.out.println("first name: " + firstName);
    }
}
