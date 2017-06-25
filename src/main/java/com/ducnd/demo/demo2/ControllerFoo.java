package com.ducnd.demo.demo2;

import com.ducnd.demo.DemoApplication;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ducnd on 6/10/17.
 */
@Controller
@RequestMapping(value = "/foos")
public class ControllerFoo {
    private static final Logger LOGGER = Logger.getLogger(ControllerFoo.class);
    @Autowired
    private ServiceFoo service;

    @GetMapping(value = "getAll")
    @ResponseBody
    public List<Foo> getAllMap() {
        return service.getFoos();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Foo getFoo(@PathVariable(value = "id") int id) {
        for (Foo foo : service.getFoos()) {
            if (foo.getId() == id) {
                return foo;
            }
        }
        return null;
    }

    @PostMapping(value = "postFoo")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BaseResponse postFoo(@RequestBody Foo foo) {
        LOGGER.info(foo.toString());
        service.getFoos().add(foo);
        return new BaseResponse(0, "ok");
    }




}
