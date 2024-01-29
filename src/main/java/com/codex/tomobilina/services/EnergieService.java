/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.services;

import com.codex.tomobilina.models.Energie;
import com.codex.tomobilina.repository.EnergieRepository;
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
public class EnergieService {
    @Autowired
    private EnergieRepository energieRepository;

    public List<Energie> getAllEnergie() {
        return energieRepository.findAll();
    }
    
    public Optional<Energie> getEnergieById(String id) { return energieRepository.findById(id); }
    
    public Energie saveEnergie(Energie energie) { return energieRepository.save(energie); }
    
    public Energie updateEnergie(String id, Energie newEne) { 
        Energie ene = energieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entité Energie non trouvée"));
        ene.setEtat(newEne.getEtat());
        ene.setNomEnergie(newEne.getNomEnergie());
        
        return energieRepository.save(ene);
    }
    
    public void deleteEnergie(String id) {
        energieRepository.deleteById(id);
    }
}
