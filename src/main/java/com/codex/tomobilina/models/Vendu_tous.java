/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Tohy
 */
@Entity
@Table(name = "vendu_tous")
public class Vendu_tous {
    @Id
    
    @ManyToOne
    @JoinColumn(name = "idmodele", referencedColumnName = "idModele")
    private Modele model;
    @ManyToOne
    @JoinColumn(name = "idcategorie", referencedColumnName = "idCategorie")
    private Categorie categorie;
    @ManyToOne
    @JoinColumn(name = "idmarque", referencedColumnName = "idMarque")
    private Marque marque;
    private double prix;

    public Modele getModel() {
        return model;
    }

    public void setModel(Modele model) {
        this.model = model;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
