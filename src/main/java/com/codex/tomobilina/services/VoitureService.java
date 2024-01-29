/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.services;

import com.codex.tomobilina.models.BoiteVitesse;
import com.codex.tomobilina.models.Couleurs;
import com.codex.tomobilina.models.Energie;
import com.codex.tomobilina.models.Modele;
import com.codex.tomobilina.models.Voiture;
import com.codex.tomobilina.repository.BoiteVitesseRepository;
import com.codex.tomobilina.repository.CouleursRepository;
import com.codex.tomobilina.repository.EnergieRepository;
import com.codex.tomobilina.repository.VoitureRepository;
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
public class VoitureService {
    @Autowired
    private VoitureRepository voitureRepository;

    @Autowired
    private ModeleService modeleService;

    @Autowired
    private EnergieRepository energieRepository;

    @Autowired
    private BoiteVitesseRepository boiteVitesseRepository;

    @Autowired
    private CouleursRepository couleursRepository;

    public List<Voiture> getAllVoiture() {
        return voitureRepository.findAll();
    }
    
    public Optional<Voiture> getVoitureById(String id) { return voitureRepository.findById(id); }
    
    public Voiture saveVoiture(Voiture voiture) { return voitureRepository.save(voiture); }
    
    @Transactional
    public Voiture updateVoiture(String id, Voiture newVoiture) { 
        Voiture voiture = voitureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entité Voiture non trouvée"));
        voiture.setEtat(newVoiture.getEtat());
        voiture.setConsommation(newVoiture.getConsommation());
        voiture.setBoiteVitesse(newVoiture.getBoiteVitesse());
        voiture.setCouleur(newVoiture.getCouleur());
        voiture.setEnergie(newVoiture.getEnergie());
        voiture.setModele(newVoiture.getModele());
        voiture.setKilometrage(newVoiture.getKilometrage());
        
        return voitureRepository.save(voiture);
    }

    public List<Voiture> filtreRecherche(
            String nomModele, List<String> marques, List<String> categories, Integer minNbrPlaces, Integer maxNbrPlaces,
            Integer minNbrPortes, Integer maxNbrPortes, Integer minAnnee, Integer maxAnnee,
            double minKilometrage, double maxKilometrage, List<String> allEnergies, List<String> allBtV, List<String> allCouleurs,
            double minConso, double maxConso
            ) {
        List<Modele> allModele = modeleService.filtreRecherche(nomModele, marques, categories, minNbrPlaces, maxNbrPlaces, minNbrPortes,
                maxNbrPortes, minAnnee, maxAnnee);

        List<String> idmodeles = new ArrayList<>();

        for (Modele mod: allModele) {
            idmodeles.add(mod.getIdModele());
        }

        List<String> energies = Energie.getListIds(energieRepository.findAll(), allEnergies);
        List<String> boiteVitesses = BoiteVitesse.getListIds(boiteVitesseRepository.findAll(), allBtV);
        List<String> couleurs = Couleurs.getListIds(couleursRepository.findAll(), allCouleurs);

        List<Voiture> voitures = voitureRepository.findByModele_IdModeleInAndKilometrageBetweenAndEnergie_IdEnergieInAndBoiteVitesse_IdBoiteVitesseInAndCouleur_IdCouleurInAndConsommationBetween(
                idmodeles, minKilometrage, maxKilometrage, energies, boiteVitesses, couleurs, minConso, maxConso);

        return voitures;
    }
}
