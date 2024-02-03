/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.services;

import com.codex.tomobilina.models.Categorie;
import com.codex.tomobilina.models.Marque;
import com.codex.tomobilina.models.Modele;
import com.codex.tomobilina.repository.CategorieRepository;
import com.codex.tomobilina.repository.MarqueRepository;
import com.codex.tomobilina.repository.ModeleRepository;
import java.util.ArrayList;
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
public class ModeleService {

    @Autowired
    private ModeleRepository modeleRepository;

    @Autowired
    private MarqueRepository marqueRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    public List<Modele> getAllModeles() {
        return modeleRepository.findAll();
    }

    public Optional<Modele> getModeleById(String id) {
        return modeleRepository.findById(id);
    }

    public Modele saveModele(Modele modele) {
        return modeleRepository.save(modele);
    }

    public void deleteModele(String id) {
        modeleRepository.deleteById(id);
    }
    
    @Transactional
    public void updateModele(String id, Modele newMod) { 
        Modele modele = modeleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entité Modele non trouvée"));
        modele.setEtat(newMod.getEtat());
        modele.setCategorie(newMod.getCategorie());
        modele.setMarque(newMod.getMarque());
        modele.setNbrPlaces(newMod.getNbrPlaces());
        modele.setNbrPortes(newMod.getNbrPortes());
        modele.setNomModele(newMod.getNomModele());
        modele.setAnnee(newMod.getAnnee());
        
        modeleRepository.save(modele);
    }
    
    public List<Modele> filtreRecherche(String nomModele, List<String> marques, List<String> categories, Integer minNbrPlaces, Integer maxNbrPlaces, Integer minNbrPortes, Integer maxNbrPortes, Integer minAnnee, Integer maxAnnee) {
        List<String> allMarques = new ArrayList<>();
        List<String> allCategories = new ArrayList<>();
        if (marques == null || marques.isEmpty()) {
            System.out.println("Marques Null All");
            List<Marque> allM = marqueRepository.findAll();
            for (Marque m: allM) {
                allMarques.add(m.getIdMarque());
            }
        } else {
            allMarques = marques;
        }

        if (categories == null || categories.isEmpty()) {
            System.out.println("Categories Null All");
            List<Categorie> allC = categorieRepository.findAll();
            for (Categorie c: allC) {
                allCategories.add(c.getIdCategorie());
            }
        } else {
            allCategories = categories;
        }
        return modeleRepository.findByNomModeleContainingIgnoreCaseAndMarque_IdMarqueInAndCategorie_IdCategorieInAndNbrPlacesBetweenAndNbrPortesBetweenAndAnneeBetween(nomModele, allMarques, allCategories, minNbrPlaces, maxNbrPlaces, minNbrPortes, maxNbrPortes, minAnnee, maxAnnee);
    }
}
