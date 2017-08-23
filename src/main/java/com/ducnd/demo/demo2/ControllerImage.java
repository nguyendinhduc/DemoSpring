package com.ducnd.demo.demo2;

import com.ducnd.demo.response.ResponseUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by ducnd on 8/22/17.
 */
@RestController
@RequestMapping(value = "/image")
public class ControllerImage {
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
}
