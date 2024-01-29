/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.services;

import com.codex.tomobilina.models.Annonce;
import com.codex.tomobilina.models.Voiture;
import com.codex.tomobilina.repository.AnnonceRepository;
import java.util.ArrayList;
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
public class AnnonceService {
    @Autowired
    private AnnonceRepository annonceRepository;

    @Autowired
    private VoitureService voitureService;

    public List<Annonce> getAllAnnonce() {
        return annonceRepository.findAll();
    }
    
    public List<Annonce> getAllAnnonceUser(int idUser) {
        return annonceRepository.findByUser_Id(idUser);
    }

    public List<Annonce> getAllAnnonceByEtat(int etat) {
        return annonceRepository.findByEtat(etat);
    }
    
    public Optional<Annonce> getAnnonceById(String id) { return annonceRepository.findById(id); }
        
    public Annonce saveAnnonce(Annonce an) { return annonceRepository.save(an); }
    
    @Transactional
    public void updateAnnonce(String id, Annonce newAnnonce) { 
        Annonce annonce = annonceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entité Annonce non trouvée"));
        annonce.setDateheure(newAnnonce.getDateheure());
        annonce.setDescriptions(newAnnonce.getDescriptions());
        annonce.setEtat(newAnnonce.getEtat());
        annonce.setUtilisateur(newAnnonce.getUser());
        annonce.setVoiture(newAnnonce.getVoiture());
        annonce.setPrix(newAnnonce.getPrix());
        annonce.setTitre(newAnnonce.getTitre());
        
        annonceRepository.save(annonce);
    }

    public List<Annonce> filtreRecherche(
            String nomModele, List<String> marques, List<String> categories, Integer minNbrPlaces, Integer maxNbrPlaces,
            Integer minNbrPortes, Integer maxNbrPortes, Integer minAnnee, Integer maxAnnee,
            double minKilometrage, double maxKilometrage, List<String> allEnergies, List<String> allBtV, List<String> allCouleurs,
            double minConso, double maxConso, double minPrix, double maxPrix
    ) {
        List<Voiture> allVoiture = voitureService.filtreRecherche(nomModele, marques, categories, minNbrPlaces, maxNbrPlaces, minNbrPortes, maxNbrPortes,
                minAnnee, maxAnnee, minKilometrage, maxKilometrage, allEnergies, allBtV, allCouleurs, minConso, maxConso);

        List<String> idVoitures = new ArrayList<>();
        for (Voiture vt : allVoiture) {
            idVoitures.add(vt.getIdVoiture());
        }

        return  annonceRepository.findByVoiture_IdVoitureInAndPrixBetween(idVoitures, minPrix, maxPrix);
    }
}
