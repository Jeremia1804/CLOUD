/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.codex.tomobilina.repository;

import com.codex.tomobilina.models.Modele;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Tohy
 */
public interface ModeleRepository extends JpaRepository<Modele, String> {
    @Query("select m from Modele m where m.marque.idMarque = :idM ")
    List<Modele> findModeleByMarque_IdMarque(@Param("idM") String idMarque);
    
    @Query("select m from Modele m where m.categorie.idCategorie = :idC ")
    List<Modele> findModeleByCategorie_IdCategorie(@Param("idC") String idCategorie);

    @Query("select m from Modele m where m.marque.idMarque = :idM AND m.categorie.idCategorie = :idC ")
    List<Modele> findModeleByMarque_IdMarqueAndCategorie_IdCategorie(@Param("idM") String idMarque, @Param("idC") String idCategorie);

    List<Modele> findByNomModeleContainingIgnoreCaseAndMarque_IdMarqueInAndCategorie_IdCategorieInAndNbrPlacesBetweenAndNbrPortesBetweenAndAnneeBetween(
            String nomModele, List<String> marques, List<String> categories, Integer minNbrPlaces, Integer maxNbrPlaces, Integer minNbrPortes, Integer maxNbrPortes, Integer minAnnee, Integer maxAnnee
    );
}
