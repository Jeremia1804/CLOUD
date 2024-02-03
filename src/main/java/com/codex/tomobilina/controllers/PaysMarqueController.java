/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import com.codex.tomobilina.models.PaysMarque;
import com.codex.tomobilina.models.Resultat;
import com.codex.tomobilina.services.PaysMarqueService;
/**
 *
 * @author Tohy
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tomobilina/paysmarques")
public class PaysMarqueController {

    @Autowired
    private PaysMarqueService paysMarqueService;

    @GetMapping
    public ResponseEntity<Resultat> getAllPaysMarques() {
        try {
            List<PaysMarque> an = paysMarqueService.getAllPaysMarques();
            Resultat resultat = new Resultat("OK", null, an);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/etat/{etat}")
    public ResponseEntity<Resultat> getPaysMarqueEtat(@PathVariable int etat) {
        try {
            List<PaysMarque> an = paysMarqueService.getPaysMarqueByEtat(etat);
            Resultat resultat = new Resultat("OK", null, an);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resultat> getPaysMarqueById(@PathVariable String id) {
        try {
            Optional<PaysMarque> paysM = paysMarqueService.getPaysMarqueById(id);
            if (paysM.isEmpty()) {
                Resultat resultat = new Resultat("NOT FOUND", "Pays Marque null ou Id Pays Marque NOT FOUND", null);
                return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
            }
            Resultat resultat = new Resultat("OK", null, paysM.get());
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Resultat> createPaysMarque(@RequestBody PaysMarque paysMarque) {
        try {
            Resultat resultat = new Resultat("CREATED", null, paysMarqueService.savePaysMarque(paysMarque));
            return new ResponseEntity<>(resultat, HttpStatus.CREATED);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT CREATED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaysMarque(@PathVariable String id) {
        paysMarqueService.deletePaysMarque(id);
        return ResponseEntity.noContent().build();
    }
}
