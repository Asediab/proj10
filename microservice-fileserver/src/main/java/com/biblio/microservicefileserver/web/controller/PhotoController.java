package com.biblio.microservicefileserver.web.controller;

import com.biblio.microservicefileserver.ApplicationPropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class PhotoController {

    @Autowired
    ApplicationPropertiesConfiguration applicationPropertiesConfiguration;

    @GetMapping(value = "/image")
    ResponseEntity<Resource> read(@RequestParam String name) {
        File file = new File(applicationPropertiesConfiguration.getPhoto_dir() + name + applicationPropertiesConfiguration.getPhoto_postfix());
        if (file.isFile()) {
            Resource fileSystemResource = new FileSystemResource(file);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(fileSystemResource);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

}
