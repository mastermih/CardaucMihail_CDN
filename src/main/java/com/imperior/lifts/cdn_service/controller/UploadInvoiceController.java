package com.imperior.lifts.cdn_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/cdn/invoices")
public class UploadInvoiceController {

    private static final String INVOICE_DIRECTORY = "/app/Invoices";
    private static final Logger logger = LoggerFactory.getLogger(UploadImagesController.class);

    @PostMapping("/upload/invoice")
    public ResponseEntity<String> uploadInvoice(@RequestParam("Invoice") MultipartFile invoice){
        try {
            if (invoice.isEmpty()) {
                logger.error("The file is invalid");
                return new ResponseEntity<>("Invalid invoice file.", HttpStatus.BAD_REQUEST);
        }
            File uploadDir = new File(INVOICE_DIRECTORY);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + invoice.getOriginalFilename();
            Path filePath = Paths.get(INVOICE_DIRECTORY, fileName);
            Files.write(filePath, invoice.getBytes());


            String publicUrl = "http://localhost:9090/Invoices/" + fileName;
            logger.debug("The file was uploaded successfully look " + fileName + filePath + publicUrl);
            return ResponseEntity.ok(publicUrl);
        } catch (IOException e) {
            return new ResponseEntity<>("Error occurred while uploading image.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
