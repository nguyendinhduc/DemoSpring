package com.ducnd.demo.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ducnd on 6/10/17.
 */
@Service
public class ServiceFoo {
    private List<Foo> foos;

    @Autowired
    public ServiceFoo() {
        foos = new ArrayList<>();
        foos.add(new Foo("Nguyen van A", 0));
    }

    public List<Foo> getFoos() {
        return foos;
    }

    public void addFoo(Foo foo) {
        foos.add(foo);
    }
}
