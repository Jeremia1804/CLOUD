/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.controllers;

import com.codex.tomobilina.models.Resultat;
import com.codex.tomobilina.services.PhotoAnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("tomobilina/photo_annonce")
public class PhotoAnnonceController {
    @Autowired
    private PhotoAnnonceService photoService;
    
    @PostMapping("/add")
    public ResponseEntity<Resultat> savePhoto(@RequestParam("file") MultipartFile file,
            @RequestParam("idannonce") String idAnnonce) {
        try {
            Resultat resultat = new Resultat("CREATED", null, photoService.savePhotoAnnonce(file, idAnnonce));
            return new ResponseEntity<>(resultat, HttpStatus.CREATED);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT CREATED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }
}
