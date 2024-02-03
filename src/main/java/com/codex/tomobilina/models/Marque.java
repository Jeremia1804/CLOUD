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
@Table(name = "marque")
public class Marque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmarque", length = 15)
    private String idMarque;

    @Column(name = "nommarque", length = 100, nullable = false)
    private String nomMarque;

    @ManyToOne
    @JoinColumn(name = "idpays", referencedColumnName = "idPays")
    private PaysMarque paysMarque;
    
    @Column(name = "photo")
    private String photo;

    @Column(name = "etat", columnDefinition = "INT DEFAULT 1")
    private int etat;

    public Marque(String idMarque) {
        this.idMarque = idMarque;
    }

    public Marque(String nomMarque, PaysMarque paysMarque, String photo, int etat) {
        this.nomMarque = nomMarque;
        this.paysMarque = paysMarque;
        this.photo = photo;
        this.etat = etat;
    }

    public Marque() {
    }

    public String getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(String idMarque) {
        this.idMarque = idMarque;
    }

    public String getNomMarque() {
        return nomMarque;
    }

    public void setNomMarque(String nomMarque) {
        this.nomMarque = nomMarque;
    }

    public PaysMarque getPaysMarque() {
        return paysMarque;
    }

    public void setPaysMarque(PaysMarque paysMarque) {
        this.paysMarque = paysMarque;
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