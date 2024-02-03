/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.codex.tomobilina.repository;

import com.codex.tomobilina.models.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Tohy
 */
public interface EquipementRepository extends JpaRepository<Equipement, String> {
    
}
