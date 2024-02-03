/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.controllers;

import com.codex.tomobilina.models.Commission;
import com.codex.tomobilina.models.Resultat;
import com.codex.tomobilina.services.CommissionService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
@RequestMapping("/tomobilina/commission")
public class CommissionController {
    @Autowired
    private CommissionService commissionService;

    @GetMapping
    public ResponseEntity<Resultat> getAllCommission() {
        try {
            List<Commission> an = commissionService.getAllCommission();
            Resultat resultat = new Resultat("OK", null, an);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resultat> getCommissionById(@PathVariable String id) {
        try {
            Optional<Commission> com = commissionService.getCommissionById(id);
            if(com.isEmpty()) {
                Resultat resultat = new Resultat("NOT FOUND", "Commission Not Found", null);
                return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
            }
            Resultat resultat = new Resultat("OK", null, com.get());
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Resultat> createCommission(@RequestBody Commission com) {
        try {
            Date currentDate = new Date();
            // Convertir la date en timestamp
            Timestamp currentTimestamp = new Timestamp(currentDate.getTime());
            com.setDateheure(currentTimestamp);
            Resultat resultat = new Resultat("CREATED", null, commissionService.saveCommission(com));
            return new ResponseEntity<>(resultat, HttpStatus.CREATED);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT CREATED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Resultat> updateCommission(@PathVariable String id, @RequestBody Commission com) {
        try {
            Date currentDate = new Date();
            // Convertir la date en timestamp
            Timestamp currentTimestamp = new Timestamp(currentDate.getTime());
            com.setDateheure(currentTimestamp);
            Resultat resultat = new Resultat("Commission UPDATED", null, commissionService.updateCommission(id, com));
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("Commission NOT UPDATED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommission(@PathVariable String id) {
        commissionService.deleteCommission(id);
        return ResponseEntity.noContent().build();
    }
}
