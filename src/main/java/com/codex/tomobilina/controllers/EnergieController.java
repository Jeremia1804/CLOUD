/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.controllers;

import java.util.List;
import java.util.Optional;
import com.codex.tomobilina.models.Energie;
import com.codex.tomobilina.models.Resultat;
import com.codex.tomobilina.services.EnergieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/tomobilina/energie")
public class EnergieController {
    @Autowired
    private EnergieService energieService;

    @GetMapping
    public ResponseEntity<Resultat> getAllEnergie() {
        try {
            List<Energie> an = energieService.getAllEnergie();;
            Resultat resultat = new Resultat("OK", null, an);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resultat> getEnergieById(@PathVariable String id) {
        try {
            Optional<Energie> ene = energieService.getEnergieById(id);
            if(ene.isEmpty()) {
                Resultat resultat = new Resultat("NOT FOUND", "Energie Not Found", null);
                return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
            }
            Resultat resultat = new Resultat("OK", null, ene.get());
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/add")
    public ResponseEntity<Resultat> add(@RequestBody Energie energie) {
        try {
            Resultat resultat = new Resultat("CREATED", null, energieService.saveEnergie(energie));
            return new ResponseEntity<>(resultat, HttpStatus.CREATED);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT CREATED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Resultat> updateEnergie(@PathVariable String id, @RequestBody Energie ene) {
        try {
            Resultat resultat = new Resultat("Energie UPDATED", null, energieService.updateEnergie(id, ene));
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("Energie NOT UPDATED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnergie(@PathVariable String id) {
        energieService.deleteEnergie(id);
        return ResponseEntity.noContent().build();
    }
}
