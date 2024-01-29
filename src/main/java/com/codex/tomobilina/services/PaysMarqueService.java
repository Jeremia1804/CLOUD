/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.services;

import com.codex.tomobilina.models.PaysMarque;
import com.codex.tomobilina.repository.PaysMarqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Tohy
 */
@Service
public class PaysMarqueService {

    @Autowired
    private PaysMarqueRepository paysMarqueRepository;

    public List<PaysMarque> getAllPaysMarques() {
        return paysMarqueRepository.findAll();
    }

    public Optional<PaysMarque> getPaysMarqueById(String id) {
        return paysMarqueRepository.findById(id);
    }
    
    public List<PaysMarque> getPaysMarqueByEtat(int etat) {
        return paysMarqueRepository.findPaysMarqueByEtat(etat);
    }

    public PaysMarque savePaysMarque(PaysMarque paysMarque) {
        return paysMarqueRepository.save(paysMarque);
    }

    public void deletePaysMarque(String id) {
        paysMarqueRepository.deleteById(id);
    }
    
    @Transactional
    public void updatePaysMarque(String id, PaysMarque pm) { 
        PaysMarque paysM = paysMarqueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entité non trouvée"));
        paysM.setEtat(pm.getEtat());
        paysM.setNomPays(pm.getNomPays());
        
        paysMarqueRepository.save(paysM);
    }
}
