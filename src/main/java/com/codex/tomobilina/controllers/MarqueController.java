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
import jakarta.persistence.EntityNotFoundException;
import com.codex.tomobilina.models.Marque;
import com.codex.tomobilina.models.PaysMarque;
import com.codex.tomobilina.models.Resultat;
import com.codex.tomobilina.services.ImageUploadingService;
import com.codex.tomobilina.services.MarqueService;
import com.codex.tomobilina.services.PaysMarqueService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tohy
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("tomobilina/marques")
public class MarqueController {

    @Autowired
    private MarqueService marqueService;

    @Autowired
    private PaysMarqueService paysMarqueService;

    @Autowired
    private ImageUploadingService imageService;

    @GetMapping
    public ResponseEntity<Resultat> getAllMarques() {
        try {
            List<Marque> marques = marqueService.getAllMarques();
            Resultat resultat = new Resultat("OK", null, marques);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pays/{id}")
    public ResponseEntity<Resultat> getMarqueByPays(@PathVariable String id) {
        try {
            List<Marque> marq = marqueService.getMarquePays(id);
            Resultat resultat = new Resultat("OK", null, marq);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resultat> getMarqueById(@PathVariable String id) {
        Resultat res = new Resultat();
        try {
            Optional<Marque> marque = marqueService.getMarqueById(id);
            if (!marque.isEmpty()) {
                res.setEtat("OK");
                res.setData(marque.get());
            } else {
                res.setEtat("NOT FOUND");
                res.setError("Marque non trouvé");
                res.setData(null);
            }
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.setEtat("NOT FOUND");
            res.setError(e.getMessage());
            res.setData(null);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Resultat> createMarque(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("nomMarque") String nomMarque,
            @RequestParam("paysMarque") String paysMarque,
            @RequestParam(required = false) int etat) {
        try {
            etat = 1;
            Optional<PaysMarque> pays = paysMarqueService.getPaysMarqueById(paysMarque);
            if (pays.isEmpty()) {
                Resultat resultat = new Resultat("NOT FOUND", "Id Pays Not Found", null);
                return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
            }

            String image = imageService.upload(multipartFile);
            Marque marque = new Marque(nomMarque, pays.get(), image, etat);
            Marque createdMarque = marqueService.saveMarque(marque);

            Resultat resultat = new Resultat("CREATED", null, createdMarque);
            return new ResponseEntity<>(resultat, HttpStatus.CREATED);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT CREATED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resultat> updateMarque(@PathVariable String id, @RequestBody Marque m) {
        try {
            Resultat resultat = new Resultat("Couleurs UPDATED", null, marqueService.updateMarque(id, m));
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("Couleurs NOT UPDATED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarque(@PathVariable String id) {
        marqueService.deleteMarque(id);
        return ResponseEntity.noContent().build();
    }
}
