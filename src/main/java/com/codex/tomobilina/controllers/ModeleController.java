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
import com.codex.tomobilina.models.Modele;
import com.codex.tomobilina.models.Marque;
import com.codex.tomobilina.models.Categorie;
import com.codex.tomobilina.models.Resultat;
import com.codex.tomobilina.services.ImageUploadingService;
import com.codex.tomobilina.services.ModeleService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tohy
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tomobilina/modeles")
public class ModeleController {

    @Autowired
    private ModeleService modeleService;
    
    @Autowired
    private ImageUploadingService imageService;

    @GetMapping
    public ResponseEntity<Resultat> getAllModeles() {
        try {
            List<Modele> mod = modeleService.getAllModeles();
            Resultat resultat = new Resultat("OK", null, mod);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resultat> getModeleById(@PathVariable String id) {
        try {
            Optional<Modele> mod = modeleService.getModeleById(id);
            if (mod.isEmpty()) {
                Resultat resultat = new Resultat("NOT FOUND", "Modele Not Found", null);
                return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
            }
            Resultat resultat = new Resultat("OK", null, mod.get());
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
            @RequestParam(required = false) Integer maxAnnee
    ) {
        try {
            List<Modele> allMod = modeleService.filtreRecherche(nomModele, idMarques, idCategories, minNbrPlaces, maxNbrPlaces, minNbrPortes, maxNbrPortes, minAnnee, maxAnnee);
            if (allMod.isEmpty()) {
                Resultat resultat = new Resultat("NOT FOUND", "Modele Not Found", null);
                return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
            }
            Resultat resultat = new Resultat("OK", null, allMod);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }
    
    /*@PostMapping("/add")
    public ResponseEntity<Resultat> createModele(@RequestBody Modele modele) {
        try {
            Resultat resultat = new Resultat("CREATED", null, modeleService.saveModele(modele));
            return new ResponseEntity<>(resultat, HttpStatus.CREATED);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT CREATED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }*/
    
    @PostMapping("/add")
    public ResponseEntity<Resultat> createModele(
            @RequestParam("idmarque") String idmarque,
            @RequestParam("idcategorie") String idcategorie,
            @RequestParam("nommodele") String nommodele,
            @RequestParam("annee") int annee,
            @RequestParam("nbrplaces") int nbrplaces,
            @RequestParam("nbrportes") int nbrportes,
            @RequestParam("photo") MultipartFile photo) {
        try {
            String image = imageService.upload(photo);
            Modele modele = new Modele(new Marque(idmarque), new Categorie(idcategorie), nommodele, annee, nbrplaces, nbrportes, image, 1);
            Resultat resultat = new Resultat("CREATED", null, modeleService.saveModele(modele));
            return new ResponseEntity<>(resultat, HttpStatus.CREATED);
        } catch (Exception e) {
            
            Resultat resultat = new Resultat("NOT CREATED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateModele(@PathVariable String id, @RequestBody Modele mod) {
        modeleService.updateModele(id, mod);
        return ResponseEntity.ok("Modele Update");
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModele(@PathVariable String id) {
        modeleService.deleteModele(id);
        return ResponseEntity.noContent().build();
    }
}
