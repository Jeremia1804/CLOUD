/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.controllers;

import com.codex.tomobilina.models.BoiteVitesse;
import com.codex.tomobilina.models.Resultat;
import com.codex.tomobilina.services.BoiteVitesseService;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tohy
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("tomobilina/boite_vitesse")
public class BoiteVitesseController {
    @Autowired
    private BoiteVitesseService boite_vitesseService;

    @GetMapping
    public ResponseEntity<Resultat> getAllBoite_vitesse() {
        try {
            Resultat resultat = new Resultat("OK", null, boite_vitesseService.getAllBoiteVitesse());
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resultat> getBoite_vitesseById(@PathVariable String id) {
        try {
            Optional<BoiteVitesse> bv = boite_vitesseService.getBoiteVitesseById(id);
            if(bv.isEmpty()) {
                Resultat resultat = new Resultat("NOT FOUND", "Id Boite Vitesse Not Found", null);
                return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
            }
            Resultat resultat = new Resultat("OK", null, bv.get());
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/add")
    public ResponseEntity<Resultat> add(@RequestBody BoiteVitesse bt) {
        try {
            Resultat resultat = new Resultat("CREATED", null, boite_vitesseService.saveBoite_vitesse(bt));
            return new ResponseEntity<>(resultat, HttpStatus.CREATED);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT CREATED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    } 
    
    @PutMapping("/{id}")
    public ResponseEntity<Resultat> updateBoite_vitesse(@PathVariable String id, @RequestBody BoiteVitesse bt) {
        try {
            Resultat resultat = new Resultat("Boite Vitesse UPDATED", null, boite_vitesseService.updateBoiteVitesse(id, bt));
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT UPDATED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoite_vitesse(@PathVariable String id) {
        boite_vitesseService.deleteBoite_vitesse(id);
        return ResponseEntity.noContent().build();
    }
}
