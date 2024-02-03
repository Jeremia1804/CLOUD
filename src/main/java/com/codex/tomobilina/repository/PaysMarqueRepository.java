/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.codex.tomobilina.repository;

import com.codex.tomobilina.models.PaysMarque;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Tohy
 */

public interface PaysMarqueRepository extends JpaRepository<PaysMarque, String> {
    @Query("select pm from PaysMarque pm where pm.etat = :etat ")
    List<PaysMarque> findPaysMarqueByEtat(@Param("etat") int etat);
}