/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.services;

import com.codex.tomobilina.models.Marque;
import com.codex.tomobilina.repository.MarqueRepository;
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
public class MarqueService {

    @Autowired
    private MarqueRepository marqueRepository;

    public List<Marque> getAllMarques() {
        return marqueRepository.findAll();
    }

    public Optional<Marque> getMarqueById(String id) {
        return marqueRepository.findById(id);
    }
    
    public List<Marque> getMarquePays(String idpays) {
        return marqueRepository.findMarqueByIdPays(idpays);
    }

    public Marque saveMarque(Marque marque) {
        return marqueRepository.save(marque);
    }

    public void deleteMarque(String id) {
        marqueRepository.deleteById(id);
    }
    
    @Transactional
    public Marque updateMarque(String id, Marque m) { 
        Marque marq = marqueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entité Marque non trouvée"));
        marq.setEtat(m.getEtat());
        marq.setNomMarque(m.getNomMarque());
        marq.setPaysMarque(m.getPaysMarque());
        
        return marqueRepository.save(marq);
    }
}
