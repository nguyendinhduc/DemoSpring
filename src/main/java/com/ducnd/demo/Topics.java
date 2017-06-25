package com.ducnd.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ducnd on 5/7/17.
 */
@RestController
public class Topics {
    @Autowired
    private TopicService topicService;


    @GetMapping("/getTopics")
    public List<Topic> getListTopic(){
        return topicService.getTopics();
    }

    @GetMapping("/getTopic/{id}")
    public Topic getTopic(@PathVariable("id") String id){
        return topicService.getTopic(id);
    }
}
