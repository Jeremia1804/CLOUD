/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.services;

import com.codex.tomobilina.models.BESTVENTE;
import com.codex.tomobilina.models.V_vente_Commission;
import com.codex.tomobilina.models.Vente;
import com.codex.tomobilina.repository.VenteRepository;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tohy
 */

@Service
public class VenteService {
    @Autowired
    private VenteRepository venteRepository;
    
    public List<Vente> getAllVente() {
        return venteRepository.findAll();
    }
    
    public List<V_vente_Commission> getAllV_vente_Commission() {
        return venteRepository.findV_vente_Commission();
    }
    
    public V_vente_Commission getAllV_vente_CommissionByIdVente(String id) {
        return venteRepository.findV_vente_CommissionByIdVente(id);
    }
    
    public List<BESTVENTE> getBESTVENTE() {
        return venteRepository.findBESTVENTE();
    }
    
    public Vente saveVente(Vente vente) { return venteRepository.save(vente); }
}
