package com.ducnd.demo;

import com.ducnd.demo.bean.ConfigGreatingsBean;
import com.ducnd.demo.bean.Greatings;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ducnd on 5/6/17.
 */
@RestController
public class TestControler {
    private static final Logger LOG = Logger.getLogger(TestControler.class);
    @Autowired
    private MyBean mBin;

    private PersonBoot personBoot;

    @Autowired
    public void setPersonBoot(PersonBoot personBoot) {
        this.personBoot = personBoot;
    }

    @GetMapping("/hello")
    public String hello() {
        LOG.info("repsonse hello method");
        personBoot.print();

        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigGreatingsBean.class);

        Greatings greatings = (Greatings) context.getBean(Greatings.class);
        LOG.info("greating message: " + greatings.getMessage());

        return "hello android" + " " + mBin.getServer() + " " + mBin.getScreat();
    }
}
