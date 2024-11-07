package com.imperior.lifts.cdn_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/cdn")
public class UploadImagesController {

    private final String FILE_DIRECTORY = "/app/userProfileImages";
    private static final Logger logger = LoggerFactory.getLogger(UploadImagesController.class);
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image){
        try{
            if (image.isEmpty()){
                logger.error("The file is invalid");
                return new ResponseEntity<>("Invalid image file.", HttpStatus.BAD_REQUEST);
            }

            File uploadDir = new File(FILE_DIRECTORY);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            Path filePath = Paths.get(FILE_DIRECTORY, fileName);
            Files.write(filePath, image.getBytes());

            String publicUrl = "http://localhost:9090/userProfileImages/" + fileName;
            logger.debug("The file was uploaded successfully look " + fileName + filePath + publicUrl);
            return ResponseEntity.ok(publicUrl);
        } catch (IOException e) {
            return new ResponseEntity<>("Error occurred while uploading image.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    }