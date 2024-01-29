/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.controllers;

import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;
import com.codex.tomobilina.models.EquipementVoiture;
import com.codex.tomobilina.models.Resultat;
import com.codex.tomobilina.services.EquipementVoitureService;
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
@RequestMapping("tomobilina/equipementvoiture")
public class EquipementVoitureController {
    @Autowired
    private EquipementVoitureService equipementVoitureService;

    @GetMapping
    public ResponseEntity<Resultat> getAllEquipementVoiture() {
        try {
            Resultat resultat = new Resultat("OK", null, equipementVoitureService.getAllEquipementVoiture());
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resultat> getEquipementVoitureById(@PathVariable String id) {
        try {
            Optional<EquipementVoiture> eq = equipementVoitureService.getEquipementVoitureById(id);
            if (eq.isEmpty()) {
                Resultat resultat = new Resultat("NOT FOUND", "Equipement Voiture NOT FOUND", null);
                return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
            }
            Resultat resultat = new Resultat("OK", null, eq.get());
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/add")
    public ResponseEntity<Resultat> add(@RequestBody EquipementVoiture eq) {
        try {
            EquipementVoiture eqV = equipementVoitureService.saveEquipementVoiture(eq);
            Resultat resultat = new Resultat("CREATED", null, eqV);
            return new ResponseEntity<>(resultat, HttpStatus.CREATED);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT CREATED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Resultat> updateEquipementVoiture(@PathVariable String id, @RequestBody EquipementVoiture equiV) {
        try {
            EquipementVoiture eqV = equipementVoitureService.updateEquipementVoiture(id, equiV);
            Resultat resultat = new Resultat("UPDATED", null, eqV);
            return new ResponseEntity<>(resultat, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT UPDATED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipementVoiture(@PathVariable String id) {
        equipementVoitureService.deleteEquipementVoiture(id);
        return ResponseEntity.noContent().build();
    }
}
