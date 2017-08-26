package com.ducnd.demo.demo2.firebase;

import com.ducnd.demo.demo2.BaseManager;
import com.ducnd.mysql.tables.User;
import com.ducnd.mysql.tables.records.UserRecord;
import org.jooq.Condition;
import org.jooq.Result;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class FcmClientIntegrationTest {
    private static final Logger log = LoggerFactory.getLogger(FcmClientIntegrationTest.class);
    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    @Autowired
    private BaseManager baseManager;

//    @Scheduled(fixedRate = 150000)
    public void sendNotification() {
        Condition condition = User.USER.DEVICETOKEN.isNotNull();
        Result<UserRecord> users = baseManager.getDslContext().selectFrom((User.USER)).where(condition).fetch();
        if (users == null || users.size() == 0) {
            return;
        }
        for (UserRecord user : users) {
            log.info("sendNotification token: " + user.getDevicetoken());
            JSONObject body = new JSONObject();
            try {
                body.put("to", user.getDevicetoken());
                body.put("priority", "high");

                JSONObject notification = new JSONObject();
                notification.put("body", "body string here");
                notification.put("title", "title string here");

                JSONObject data = new JSONObject();
                data.put("key1", "value1");
                data.put("key2", "value2");

                body.put("notification", notification);
                body.put("data", data);

                HttpEntity<String> request = new HttpEntity<>(body.toString());

                CompletableFuture<FirebaseResponse> pushNotification = androidPushNotificationsService.send(request);
                CompletableFuture.allOf(pushNotification).join();
                try {
                    FirebaseResponse firebaseResponse = pushNotification.get();
                    if (firebaseResponse.getSuccess() == 1) {
                        log.info("push notification sent ok!");
                    } else {
                        log.error("error sending push notifications: " + firebaseResponse.toString());
                    }
                    log.info("sendNotification..........");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
