package com.ducnd.demo;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by ducnd on 5/7/17.
 */
@Service
public class TopicService {
    private List<Topic> topics = Arrays.asList(
            new Topic("java", "hello java"),
            new Topic("android", "hello android"),
            new Topic("ios", "hello ios"),
            new Topic("swipe", "hello swpie"),
            new Topic("node", "hello node"),
            new Topic("nodejs", "hello nodejs"),
            new Topic("javascripe", "hello javascripe")
    );

    public List<Topic> getTopics(){
        return topics;
    }

    public Topic getTopic(String id) {
        return topics.stream().filter(topic -> topic.getId().equals(id)).findFirst().get();
    }
}
