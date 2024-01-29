/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.services;

import com.codex.tomobilina.models.EquipementVoiture;
import com.codex.tomobilina.repository.EquipementVoitureRepository;
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
public class EquipementVoitureService {
    @Autowired
    private EquipementVoitureRepository equipementVoitureRepository;

    public List<EquipementVoiture> getAllEquipementVoiture() {
        return equipementVoitureRepository.findAll();
    }
    
    public Optional<EquipementVoiture> getEquipementVoitureById(String id) { return equipementVoitureRepository.findById(id); }
    
    public EquipementVoiture saveEquipementVoiture(EquipementVoiture equipementVoiture) { return equipementVoitureRepository.save(equipementVoiture); }
    
    public EquipementVoiture updateEquipementVoiture(String id, EquipementVoiture newEV) { 
        EquipementVoiture ev = equipementVoitureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entité EquipementVoiture non trouvée"));
        ev.setEtat(newEV.getEtat());
        ev.setVoiture(newEV.getVoiture());
        ev.setEquipement(newEV.getEquipement());
        
        return equipementVoitureRepository.save(ev);
    }
    
    public void deleteEquipementVoiture(String id) {
        equipementVoitureRepository.deleteById(id);
    }
}
