/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tohy
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/upload")
public class ImageUploadController {
    private static String imageDirectory = System.getProperty("user.dir") + "/images/";
    private static String ext = "jpeg";
    
    @RequestMapping(value = "/image", produces = {MediaType.IMAGE_PNG_VALUE, "application/json"})
    public ResponseEntity<?> uploadImage(@RequestParam("imageFile")MultipartFile file, @RequestParam("imageName")String name) {
        makeDirectoryIfNotExist(imageDirectory);
        Path fileNamePath = Paths.get(imageDirectory, 
                name.concat(".").concat(ext));
        try {
            Files.write(fileNamePath, file.getBytes());
            return new ResponseEntity<>(name, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Image is not uploaded", HttpStatus.OK);
        }
    }
    
    private void makeDirectoryIfNotExist(String imageDirectory) {
        File directory = new File(imageDirectory);
        if(!directory.exists()) {
            directory.mkdir();
        }
    }
}
