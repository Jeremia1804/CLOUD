/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.codex.tomobilina.repository;

import com.codex.tomobilina.models.Marque;
import com.codex.tomobilina.models.V_Statmarque;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Tohy
 */
public interface MarqueRepository extends JpaRepository<Marque, String> {
    @Query("select m from Marque m where m.paysMarque.idPays = :idP ")
    List<Marque> findMarqueByIdPays(@Param("idP") String idpays);
    
    @Query("select v from V_Statmarque v")
    List<V_Statmarque> findStatmarque();
}
