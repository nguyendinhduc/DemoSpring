package com.ducnd.demo;

/**
 * Created by ducnd on 5/7/17.
 */
public class Topic {
    private String id;
    private String name;

    public Topic(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
