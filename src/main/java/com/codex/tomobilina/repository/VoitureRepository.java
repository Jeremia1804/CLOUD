/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.codex.tomobilina.repository;

import com.codex.tomobilina.models.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author Tohy
 */
public interface VoitureRepository extends JpaRepository<Voiture, String> {
    List<Voiture> findByModele_IdModeleInAndKilometrageBetweenAndEnergie_IdEnergieInAndBoiteVitesse_IdBoiteVitesseInAndCouleur_IdCouleurInAndConsommationBetween(
        List<String> idModeles, double minKilometrage, double maxKilometrage, List<String> idEnergies, List<String> idBoite_vitesse, List<String> idCouleurs, double minConsommation, double maxConsommation
    );
}
