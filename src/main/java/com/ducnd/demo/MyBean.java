package com.ducnd.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by ducnd on 5/19/17.
 */
@Component
public class MyBean implements CommandLineRunner {

    @Value("${server.port}")
    private String name;

    @Value("${tasks.server}")
    private String server;

    @Value("${my.screat}")
    private int screat;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("MyBean strings: " + strings);
        System.out.println("MyBean name: " + name);
        System.out.println("MyBean server: " + server);
        System.out.println("MyBean server: " + new Config().server);

    }

    public String getServer() {
        return server;
    }

    public int getScreat() {
        return screat;
    }
}
