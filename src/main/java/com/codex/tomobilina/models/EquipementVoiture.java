/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.models;

import jakarta.persistence.*;

/**
 *
 * @author Tohy
 */

@Entity
@Table(name = "equipementvoiture")
public class EquipementVoiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idequipementvoiture", length = 15)
    private String idequipementvoiture;
    
    @ManyToOne
    @JoinColumn(name = "idvoiture", referencedColumnName = "idVoiture")
    private Voiture voiture;
    
    @ManyToOne
    @JoinColumn(name = "idequipement", referencedColumnName = "idEquipement")
    private Equipement equipement;
    
    private int etat;

    public EquipementVoiture() {
    }

    public String getIdequipementvoiture() {
        return idequipementvoiture;
    }

    public void setIdequipementvoiture(String idequipementvoiture) {
        this.idequipementvoiture = idequipementvoiture;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public Equipement getEquipement() {
        return equipement;
    }

    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
}
