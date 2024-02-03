/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.services;

import com.codex.tomobilina.models.Commission;
import com.codex.tomobilina.models.CommissionLastWeek;
import com.codex.tomobilina.models.CommissionWeek;
import com.codex.tomobilina.repository.CommissionRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tohy
 */
@Service
public class CommissionService {
    @Autowired
    private CommissionRepository commissionRepository;

    public List<Commission> getAllCommission() {
        return commissionRepository.findAll();
    }
    
    public Optional<Commission> getCommissionById(String id) { return commissionRepository.findById(id); }
    
    public Commission saveCommission(Commission com) { return commissionRepository.save(com); }
    
    @Transactional
    public Commission updateCommission(String id, Commission newCom) { 
        Commission c = commissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entité Commission non trouvée"));
        c.setCommission(newCom.getCommission());
        c.setDateheure(newCom.getDateheure());
        
        return commissionRepository.save(c);
    }
    public void deleteCommission(String id) {
        commissionRepository.deleteById(id);
    }
    
    
    public CommissionLastWeek getCommissionLastWeek() {
        return commissionRepository.findCommissionLastWeek();
    }
    
    public CommissionWeek getCommissionWeek() {
        return commissionRepository.findCommissionWeek();
    }
}
