/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.codex.tomobilina.repository;

import com.codex.tomobilina.models.Commission;
import com.codex.tomobilina.models.CommissionLastWeek;
import com.codex.tomobilina.models.CommissionWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Tohy
 */
public interface CommissionRepository extends JpaRepository<Commission, String> {
    @Query("select c from CommissionLastWeek c")
    CommissionLastWeek findCommissionLastWeek();
    
    @Query("select c from CommissionWeek c")
    CommissionWeek findCommissionWeek();
}
