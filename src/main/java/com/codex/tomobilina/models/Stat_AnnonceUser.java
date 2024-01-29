/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Tohy
 */

@Entity
@Table(name = "stat_annonceuser")
public class Stat_AnnonceUser {
    @Id
    
    private String libelle;
    private int mois;
    private int user;
    private int annonce;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getAnnonce() {
        return annonce;
    }

    public void setAnnonce(int annonce) {
        this.annonce = annonce;
    }
}
