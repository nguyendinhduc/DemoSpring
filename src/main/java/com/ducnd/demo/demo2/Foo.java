package com.ducnd.demo.demo2;

/**
 * Created by ducnd on 6/10/17.
 */
public class Foo {
    private String name;
    private int id;

    public Foo() {
    }

    public Foo(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
