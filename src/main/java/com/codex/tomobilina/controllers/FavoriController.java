/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.controllers;

import java.util.List;

import com.codex.tomobilina.models.Favori;
import com.codex.tomobilina.models.Resultat;
import com.codex.tomobilina.models.V_Favori;
import com.codex.tomobilina.services.FavoriService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Tohy
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tomobilina/favori")
public class FavoriController {
    @Autowired
    private FavoriService favoriService;

    @GetMapping("user/{id}")
    public ResponseEntity<Resultat> getAllFavoriUser(@PathVariable int id) {
        try {
            List<V_Favori> allFav = favoriService.getAnnonceFavUser(id);
            Resultat resultat = new Resultat("OK", null, allFav);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favori> getFavoriById(@PathVariable String id) {
        return favoriService.getFavoriById(id)
                .map(fav -> new ResponseEntity<>(fav, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping("/add")
    public ResponseEntity<Resultat> add(@RequestBody Favori fav) {
        try {
            Date currentDate = new Date();
            // Convertir la date en timestamp
            Timestamp dateheure = new Timestamp(currentDate.getTime());
            fav.setDateheure(dateheure);
            fav.setEtat(1);
            Favori favori = favoriService.saveFavori(fav);
            Resultat resultat = new Resultat("CREATED", null, favori);
            return new ResponseEntity<>(resultat, HttpStatus.CREATED);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT CREATED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }     
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Resultat> deleteEquipementVoiture(@PathVariable String id) {
        try {
            Date currentDate = new Date();
            // Convertir la date en timestamp
            Timestamp dateheure = new Timestamp(currentDate.getTime());
            
            Optional<Favori> fav = favoriService.getFavoriById(id);
            if (fav.isEmpty()) {
                Resultat resultat = new Resultat("NOT DELETED", "id Favori not found", null);
                return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
            }
            Favori f = fav.get();
            
            Favori newFav = new Favori(f.getUser(), f.getAnnonce(), dateheure, -1);
            Resultat resultat = new Resultat("DELETED", null, favoriService.saveFavori(newFav));
            return new ResponseEntity<>(resultat, HttpStatus.CREATED);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT DELETED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }
}
