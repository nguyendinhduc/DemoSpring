package com.ducnd.demo.demo2.firebase;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class AndroidPushNotificationsService {
    private static final String FIREBASE_SERVER_KEY = "AAAAml3uH-s:APA91bHbN2gcxipT1NpjC5EUI4A1HnJxqAE80Fqs4mf2hUVpXDRh-S6C1pPMFeKu9smYu0BD4I2cvX3ZU0fhaZ8c3pWjCo3cUrvHvNMG-D9h5h-APp2l09EtowrVZh0VjwidJsh8jkMR";

    @Async
    public CompletableFuture<FirebaseResponse> send(HttpEntity<String> entity) {

        RestTemplate restTemplate = new RestTemplate();

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);

        FirebaseResponse firebaseResponse = restTemplate.postForObject("https://fcm.googleapis.com/fcm/send", entity, FirebaseResponse.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }
}
