/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.codex.tomobilina.repository;

import com.codex.tomobilina.models.Energie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Tohy
 */
public interface EnergieRepository extends JpaRepository<Energie, String> {
    
}
