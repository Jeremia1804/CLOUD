/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.services;

import com.codex.tomobilina.models.Couleurs;
import com.codex.tomobilina.repository.CouleursRepository;
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
public class CouleursService {
    @Autowired
    private CouleursRepository couleursRepository;

    public List<Couleurs> getAllCouleurs() {
        return couleursRepository.findAll();
    }
    
    public Optional<Couleurs> getCouleursById(String id) { return couleursRepository.findById(id); }
    
    public Couleurs saveCouleurs(Couleurs col) { return couleursRepository.save(col); }
    
    public Couleurs updateCouleurs(String id, Couleurs newCol) { 
        Couleurs c = couleursRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entité Couleurs non trouvée"));
        c.setEtat(newCol.getEtat());
        c.setNomCouleur(newCol.getNomCouleur());
        
        return couleursRepository.save(c);
    }
    
    public void deleteCouleur(String id) {
        couleursRepository.deleteById(id);
    }
}
