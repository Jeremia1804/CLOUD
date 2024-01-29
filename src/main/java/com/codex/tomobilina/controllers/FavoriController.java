/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.controllers;

import java.util.List;

import com.codex.tomobilina.models.Favori;
import com.codex.tomobilina.services.FavoriService;
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
@RequestMapping("tomobilina/favori")
public class FavoriController {
    @Autowired
    private FavoriService favoriService;

    @GetMapping("user/{id}")
    public List<Favori> getAllFavoriUser(@PathVariable int id) {
        return favoriService.getAllFavoriUser(id);
    }
    
    @GetMapping("userfav/{id}")
    public List<Favori> getCurrentAllFavoriUser(@PathVariable int id) {
        return favoriService.getAllFavoriUserEtat(id, 1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favori> getFavoriById(@PathVariable String id) {
        return favoriService.getFavoriById(id)
                .map(fav -> new ResponseEntity<>(fav, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping("/add")
    public ResponseEntity<Favori> add(@RequestBody Favori fav) {
        Favori resultat = favoriService.saveFavori(fav);
        return new ResponseEntity<>(resultat, HttpStatus.CREATED);
    }     
}
