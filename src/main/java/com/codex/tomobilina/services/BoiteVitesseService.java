/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.services;

import com.codex.tomobilina.models.BoiteVitesse;
import com.codex.tomobilina.repository.BoiteVitesseRepository;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tohy
 */

@Service
public class BoiteVitesseService {
    @Autowired
    private BoiteVitesseRepository boiteVitesseRepository;

    public List<BoiteVitesse> getAllBoiteVitesse() {
        return boiteVitesseRepository.findAll();
    }
    
    public Optional<BoiteVitesse> getBoiteVitesseById(String id) { return boiteVitesseRepository.findById(id); }
    
    public BoiteVitesse saveBoite_vitesse(BoiteVitesse bv) { return boiteVitesseRepository.save(bv); }
    
    public BoiteVitesse updateBoiteVitesse(String id, BoiteVitesse newBt) { 
        BoiteVitesse bt = boiteVitesseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entité Boite Vitesse non trouvée"));
        bt.setEtat(newBt.getEtat());
        bt.setNomBoiteVitesse(newBt.getNomBoiteVitesse());
        
        return boiteVitesseRepository.save(bt);
    }
    
    public void deleteBoite_vitesse(String id) {
        boiteVitesseRepository.deleteById(id);
    }
}
