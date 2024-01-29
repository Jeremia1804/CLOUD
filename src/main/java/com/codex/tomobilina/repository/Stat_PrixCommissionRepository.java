/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.codex.tomobilina.repository;

import com.codex.tomobilina.models.Stat_PrixCommission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Tohy
 */
public interface Stat_PrixCommissionRepository extends JpaRepository<Stat_PrixCommission, Long> {
    Stat_PrixCommission findByLibelle(String libelle);
    Stat_PrixCommission findByMois(int mois);
}
