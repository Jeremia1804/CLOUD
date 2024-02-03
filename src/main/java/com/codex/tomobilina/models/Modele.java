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
@Table(name = "modele")
public class Modele {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmodele", length = 15)
    private String idModele;

    @ManyToOne
    @JoinColumn(name = "idmarque", referencedColumnName = "idMarque")
    private Marque marque;

    @ManyToOne
    @JoinColumn(name = "idcategorie", referencedColumnName = "idCategorie")
    private Categorie categorie;

    @Column(name = "nommodele", length = 100, nullable = false)
    private String nomModele;

    @Column(name = "annee", nullable = false)
    private int annee;

    @Column(name = "nbrplaces", nullable = false)
    private int nbrPlaces;

    @Column(name = "nbrportes", nullable = false)
    private int nbrPortes;
    
    @Column(name = "photo", length = 300, nullable = false)
    private String photo;

    @Column(name = "etat", columnDefinition = "INT DEFAULT 1")
    private int etat;

    public Modele() {
    }

    public Modele(Marque marque, Categorie categorie, String nomModele, int annee, int nbrPlaces, int nbrPortes, String photo, int etat) {
        this.marque = marque;
        this.categorie = categorie;
        this.nomModele = nomModele;
        this.annee = annee;
        this.nbrPlaces = nbrPlaces;
        this.nbrPortes = nbrPortes;
        this.photo = photo;
        this.etat = etat;
    }

    public String getIdModele() {
        return idModele;
    }

    public void setIdModele(String idModele) {
        this.idModele = idModele;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getNomModele() {
        return nomModele;
    }

    public void setNomModele(String nomModele) {
        this.nomModele = nomModele;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getNbrPlaces() {
        return nbrPlaces;
    }

    public void setNbrPlaces(int nbrPlaces) {
        this.nbrPlaces = nbrPlaces;
    }

    public int getNbrPortes() {
        return nbrPortes;
    }

    public void setNbrPortes(int nbrPortes) {
        this.nbrPortes = nbrPortes;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
}
