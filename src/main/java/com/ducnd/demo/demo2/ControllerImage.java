package com.ducnd.demo.demo2;

import com.ducnd.demo.demo2.firebase.image.FirebaseStoreManager;
import com.ducnd.demo.response.ResponseUtils;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by ducnd on 8/22/17.
 */
@RestController
@RequestMapping(value = "/image")
public class ControllerImage {
    @Autowired
    private FirebaseStoreManager firebaseStoreManager;


    @GetMapping(value = "image")
    @ResponseBody
    public ResponseEntity getImage(@RequestParam(required = true, value = "nameImage") String nameImage) {
        try {
            File file = new File("/Users/ducnd/Desktop/" + nameImage);
            InputStream inputStreaminputStream = new FileInputStream(file);
            InputStreamResource inputStreamResource = new InputStreamResource(inputStreaminputStream);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .lastModified(file.lastModified())
                    .contentLength(file.length())
                    .body(inputStreamResource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.accepted()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ResponseUtils.getBaseResponse(404, "find not found"));
        }
    }

    @PostMapping(value = "/postImage")
    public String postImage(
            @RequestParam(value = "image") MultipartFile image
    ) {
        try {
            return firebaseStoreManager.uploadFile(image);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping(
            value = "/getImage", produces = MediaType.IMAGE_PNG_VALUE
    )
    public @ResponseBody
    byte[] getImageWithMediaType(
            @RequestParam(value = "image") String image
    ) {
        return firebaseStoreManager.getImage(image);

    }
}
