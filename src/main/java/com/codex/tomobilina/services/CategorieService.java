/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.services;

import com.codex.tomobilina.models.Categorie;
import com.codex.tomobilina.repository.CategorieRepository;
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
public class CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Optional<Categorie> getCategorieById(String id) {
        return categorieRepository.findById(id);
    }

    public Categorie saveCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public void deleteCategorie(String id) {
        categorieRepository.deleteById(id);
    }
    
    @Transactional
    public Categorie updateCategorie(String id, Categorie cat) { 
        Categorie c = categorieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entité Categorie non trouvée"));
        c.setEtat(cat.getEtat());
        c.setNomCategorie(cat.getNomCategorie());
        
        return categorieRepository.save(c);
    }
}

