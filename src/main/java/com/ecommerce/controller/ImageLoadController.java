package com.ecommerce.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;

@Controller
public class ImageLoadController {

    @Cacheable("images")
    @RequestMapping(value = "/images/{name}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody
    ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {
        String myExternalFilePath = System.getProperty("user.dir") + "/images/" + name;
        byte[] image = new byte[0];
        try {
            System.out.println("girdi");
            image = FileUtils.readFileToByteArray(new File(myExternalFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }
}
