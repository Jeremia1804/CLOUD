/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.controllers;

import com.codex.tomobilina.models.Resultat;
import com.codex.tomobilina.models.Voiture;
import com.codex.tomobilina.services.VoitureService;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Tohy
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("tomobilina/voiture")
public class VoitureController {
    @Autowired
    private VoitureService voitureService;

    @GetMapping
    public ResponseEntity<Resultat> getAllVoiture() {
        try {
            List<Voiture> listV = voitureService.getAllVoiture();
            Resultat resultat = new Resultat("OK", null, listV);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resultat> getVoitureById(@PathVariable String id) {
        try {
            Optional<Voiture> voiture = voitureService.getVoitureById(id);
            if (voiture.isEmpty()) {
                Resultat resultat = new Resultat("NOT FOUND", "Voiture Not Found", null);
                return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
            }
            Resultat resultat = new Resultat("OK", null, voiture.get());
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/recherche")
    public ResponseEntity<Resultat> recherche(
            @RequestParam(required = false) String nomModele,
            @RequestParam(required = false) List<String> idMarques,
            @RequestParam(required = false) List<String> idCategories,
            @RequestParam(required = false) Integer minNbrPlaces,
            @RequestParam(required = false) Integer maxNbrPlaces,
            @RequestParam(required = false) Integer minNbrPortes,
            @RequestParam(required = false) Integer maxNbrPortes,
            @RequestParam(required = false) Integer minAnnee,
            @RequestParam(required = false) Integer maxAnnee,
            @RequestParam(required = false) double minKilometrage,
            @RequestParam(required = false) double maxKilometrage,
            @RequestParam(required = false) List<String> idEnergies,
            @RequestParam(required = false) List<String> idBoiteVitesses,
            @RequestParam(required = false) List<String> idCouleurs,
            @RequestParam(required = false) double minConso,
            @RequestParam(required = false) double maxConso
    ) {
        try {
            List<Voiture> allVoiture = voitureService.filtreRecherche(nomModele, idMarques, idCategories, minNbrPlaces, maxNbrPlaces, minNbrPortes, maxNbrPortes,
                minAnnee, maxAnnee, minKilometrage, maxKilometrage, idEnergies, idBoiteVitesses, idCouleurs, minConso, maxConso);
            if (allVoiture.isEmpty()) {
                Resultat resultat = new Resultat("NOT FOUND", "Voiture Not Found", null);
                return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
            }
            Resultat resultat = new Resultat("OK", null, allVoiture);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/add")
    public ResponseEntity<Resultat> add(@RequestBody Voiture voiture) {
        try {
            Resultat resultat = new Resultat("CREATED", null, voitureService.saveVoiture(voiture));
            return new ResponseEntity<>(resultat, HttpStatus.CREATED);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT CREATED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }     
    
    @PutMapping("/{id}")
    public ResponseEntity<Resultat> updateVoiture(@PathVariable String id, @RequestBody Voiture vaika) {
        try {
            Resultat resultat = new Resultat("Voiture UPDATED", null, voitureService.updateVoiture(id, vaika));
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("Voiture NOT UPDATED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }
    
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Resultat> deleteVoiture(@PathVariable String id) {
        try {
            Voiture vaika = voitureService.getVoitureById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Entité Voiture non trouvée"));
            
            vaika.setEtat(0);

            voitureService.saveVoiture(vaika);
            Resultat resultat = new Resultat("DELETED", null, vaika);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT DELETED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }
}
