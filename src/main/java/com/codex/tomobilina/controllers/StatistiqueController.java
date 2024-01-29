/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.controllers;

import com.codex.tomobilina.models.BESTVENTE;
import com.codex.tomobilina.models.CommissionLastWeek;
import com.codex.tomobilina.models.CommissionWeek;
import com.codex.tomobilina.models.NbAnnonceLastWeek;
import com.codex.tomobilina.models.NbAnnonceWeek;
import com.codex.tomobilina.models.NbUsersWeek;
import com.codex.tomobilina.models.Resultat;
import com.codex.tomobilina.models.Stat_AnnonceUser;
import com.codex.tomobilina.models.Stat_PrixCommission;
import com.codex.tomobilina.models.V_vente_Commission;
import com.codex.tomobilina.models.VenteLastWeek;
import com.codex.tomobilina.models.VenteWeek;
import com.codex.tomobilina.services.CommissionService;
import com.codex.tomobilina.services.StatistiqueService;
import com.codex.tomobilina.services.VenteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tohy
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tomobilina/stat")
public class StatistiqueController {
  @Autowired
  StatistiqueService statistiqueService;
  
  @Autowired
  VenteService venteService;
  
  @Autowired
  CommissionService commissionService;
  
    @GetMapping("/nbUsersLastWeek")
    public ResponseEntity<Resultat> getNbUsersLastWeek() {
        try {
            int nb = statistiqueService.getNbUsersLastWeek();
            Resultat resultat = new Resultat("OK", null, nb);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/nbUsersWeek")
    public ResponseEntity<Resultat> getNbUsersWeek() {
        try {
            NbUsersWeek nb = statistiqueService.getNbUsersWeek();
            Resultat resultat = new Resultat("OK", null, nb);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/venteLastWeek")
    public ResponseEntity<Resultat> getVenteLastWeek() {
        try {
            VenteLastWeek v = statistiqueService.getVenteLastWeek();
            Resultat resultat = new Resultat("OK", null, v);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/venteWeek")
    public ResponseEntity<Resultat> getVenteWeek() {
        try {
            VenteWeek v = statistiqueService.getVenteWeek();
            Resultat resultat = new Resultat("OK", null, v);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/nbAnnonceLastWeek")
    public ResponseEntity<Resultat> getNbAnnonceLastWeek() {
        try {
            NbAnnonceLastWeek nb = statistiqueService.getNbAnnonceLastWeek();
            Resultat resultat = new Resultat("OK", null, nb);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/nbAnnonceWeek")
    public ResponseEntity<Resultat> getNbAnnonceWeek() {
        try {
            NbAnnonceWeek nb = statistiqueService.getNbAnnonceWeek();
            Resultat resultat = new Resultat("OK", null, nb);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/v_vente_commission")
    public ResponseEntity<Resultat> getV_vente_Commission() {
        try {
            List<V_vente_Commission> vc = venteService.getAllV_vente_Commission();
            Resultat resultat = new Resultat("OK", null, vc);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/v_vente_commission/{id}")
    public ResponseEntity<Resultat> getV_vente_Commission(@PathVariable String id) {
        try {
            V_vente_Commission vc = venteService.getAllV_vente_CommissionByIdVente(id);
            Resultat resultat = new Resultat("OK", null, vc);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/CommissionLastWeek")
    public ResponseEntity<Resultat> getCommissionLastWeek() {
        try {
            CommissionLastWeek cw = commissionService.getCommissionLastWeek();
            Resultat resultat = new Resultat("OK", null, cw);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/CommissionWeek")
    public ResponseEntity<Resultat> getCommissionWeek() {
        try {
            CommissionWeek cw = commissionService.getCommissionWeek();
            Resultat resultat = new Resultat("OK", null, cw);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/bestvente")
    public ResponseEntity<Resultat> getBestVente() {
        try {
            List<BESTVENTE> b = venteService.getBESTVENTE();
            Resultat resultat = new Resultat("OK", null, b);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/stat_PrixCommission")
    public ResponseEntity<Resultat> getStat_PrixCommission() {
        try {
            List<Stat_PrixCommission> b = statistiqueService.getAllStat_PrixCommission();
            Resultat resultat = new Resultat("OK", null, b);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/stat_PrixCommission/{mois}")
    public ResponseEntity<Resultat> getStat_PrixCommissionByMois(@PathVariable int mois) {
        try {
            Stat_PrixCommission b = statistiqueService.getAllStat_PrixCommissionByMois(mois);
            Resultat resultat = new Resultat("OK", null, b);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/Stat_AnnonceUser")
    public ResponseEntity<Resultat> getStat_AnnonceUser() {
        try {
            List<Stat_AnnonceUser> stat = statistiqueService.getStat_AnnonceUser();
            Resultat resultat = new Resultat("OK", null, stat);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }
}
