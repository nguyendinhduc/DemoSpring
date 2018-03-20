package com.ducnd.demo.demo2.firebase.image;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
public class FirebaseStoreManager {
    private static final Logger LOG = LoggerFactory.getLogger(FirebaseStoreManager.class);

    public FirebaseStoreManager() {
        try {
            File file = new ClassPathResource("service_account_key.json").getFile();
            InputStream inputStream = new FileInputStream(file);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(inputStream))
                    .setDatabaseUrl("https://fir-firebase-5b455.firebaseio.com")
                    .setStorageBucket("fir-firebase-5b455.appspot.com")
                    .build();
            FirebaseApp.initializeApp(options);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        Bucket bucket = StorageClient.getInstance().bucket();
        LOG.info("uploadFile info bucket: " + bucket.getName());
        LOG.debug("uploadFile debug bucket: " + bucket.getName());
        bucket.create("hello", multipartFile.getInputStream(), multipartFile.getContentType());
        return "hello";
    }

    public byte[] getImage(String name) {
        Bucket bucket = StorageClient.getInstance().bucket();
        return bucket.get(name).getContent();
    }
}
