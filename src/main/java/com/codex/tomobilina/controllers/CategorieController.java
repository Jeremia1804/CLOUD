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
import com.codex.tomobilina.models.Categorie;
import com.codex.tomobilina.models.Resultat;
import com.codex.tomobilina.services.CategorieService;
/**
 *
 * @author Tohy
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tomobilina/categories")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    @GetMapping
    public ResponseEntity<Resultat> getAllCategories() {
        try {
            List<Categorie> an = categorieService.getAllCategories();
            Resultat resultat = new Resultat("OK", null, an);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resultat> getCategorieById(@PathVariable String id) {
        try {
            Optional<Categorie> cat = categorieService.getCategorieById(id);
            if(cat.isEmpty()) {
                Resultat resultat = new Resultat("NOT FOUND", "Categorie Not Found", null);
                return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
            }
            Resultat resultat = new Resultat("OK", null, cat.get());
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Resultat> createCategorie(@RequestBody Categorie categorie) {
        try {
            categorie.setEtat(1);
            Resultat resultat = new Resultat("CREATED", null, categorieService.saveCategorie(categorie));
            return new ResponseEntity<>(resultat, HttpStatus.CREATED);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT CREATED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Resultat> updateCategorie(@PathVariable String id, @RequestBody Categorie cat) {
        try {
            Resultat resultat = new Resultat("Categorie UPDATED", null, categorieService.updateCategorie(id, cat));
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("Categorie NOT UPDATED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable String id) {
        categorieService.deleteCategorie(id);
        return ResponseEntity.noContent().build();
    }
}
