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
@Table(name = "voiture")
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvoiture")
    private String idVoiture;
    
    @ManyToOne
    @JoinColumn(name = "idmodele", referencedColumnName = "idModele")
    private Modele modele;
    
    private double kilometrage;
    
    @ManyToOne
    @JoinColumn(name = "idenergie", referencedColumnName = "idEnergie")
    private Energie energie;
    
    @ManyToOne
    @JoinColumn(name = "idboitevitesse", referencedColumnName = "idBoiteVitesse")
    private BoiteVitesse boiteVitesse;
    
    @ManyToOne
    @JoinColumn(name = "idcouleur", referencedColumnName = "idCouleur")
    private Couleurs couleur;
    
    private double consommation;
    
    private int etat;

    public Voiture(String idVoiture) {
        this.idVoiture = idVoiture;
    }

    public Voiture() {
    }

    public String getIdVoiture() {
        return idVoiture;
    }

    public void setIdVoiture(String idVoiture) {
        this.idVoiture = idVoiture;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public double getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(double kilometrage) {
        this.kilometrage = kilometrage;
    }

    public Energie getEnergie() {
        return energie;
    }

    public void setEnergie(Energie energie) {
        this.energie = energie;
    }

    public BoiteVitesse getBoiteVitesse() {
        return boiteVitesse;
    }

    public void setBoiteVitesse(BoiteVitesse boite_vitesse) {
        this.boiteVitesse = boite_vitesse;
    }

    public Couleurs getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleurs couleur) {
        this.couleur = couleur;
    }

    public double getConsommation() {
        return consommation;
    }

    public void setConsommation(double consommation) {
        this.consommation = consommation;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
}
