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
@Table(name = "equipement")
public class Equipement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idequipement")
    private String idEquipement;
    @Column(name = "nomequipement")
    private String nomEquipement;
    private int etat;

    public Equipement(String nomEquipement, int etat) {
        this.nomEquipement = nomEquipement;
        this.etat = etat;
    }

    public Equipement() {
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getIdEquipement() {
        return idEquipement;
    }

    public void setIdEquipement(String idEquipement) {
        this.idEquipement = idEquipement;
    }

    public String getNomEquipement() {
        return nomEquipement;
    }

    public void setNomEquipement(String nomEquipement) {
        this.nomEquipement = nomEquipement;
    }
}
