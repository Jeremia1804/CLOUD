/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.services;

import com.codex.tomobilina.models.Equipement;
import com.codex.tomobilina.repository.EquipementRepository;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tohy
 */

@Service
public class EquipementService {
    @Autowired
    private EquipementRepository equipementRepository;

    public List<Equipement> getAllEquipement() {
        return equipementRepository.findAll();
    }
    
    public Optional<Equipement> getEquipementById(String id) { return equipementRepository.findById(id); }
    
    public Equipement saveEquipement(Equipement equi) { return equipementRepository.save(equi); }
    
    public Equipement updateEquipement(String id, Equipement newEqui) { 
        Equipement eq = equipementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entité Equipement non trouvée"));
        eq.setEtat(newEqui.getEtat());
        eq.setNomEquipement(newEqui.getNomEquipement());
        
        return equipementRepository.save(eq);
    }
    
    public void deleteEquipement(String id) {
        equipementRepository.deleteById(id);
    }
}
