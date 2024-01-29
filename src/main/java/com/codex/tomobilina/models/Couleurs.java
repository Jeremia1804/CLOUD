/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tohy
 */

@Entity
@Table(name = "couleurs")
public class Couleurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcouleur")
    private String idCouleur;
    @Column(name = "nomcouleur")
    private String nomCouleur;
    private int etat;

    public Couleurs(String nomcouleur, int etat) {
        this.nomCouleur = nomcouleur;
        this.etat = etat;
    }

    public Couleurs() {
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getIdCouleur() {
        return idCouleur;
    }

    public void setIdCouleur(String idcouleur) {
        this.idCouleur = idcouleur;
    }

    public String getNomCouleur() {
        return nomCouleur;
    }

    public void setNomCouleur(String nomcouleur) {
        this.nomCouleur = nomcouleur;
    }

    public static List<String> getListIds(List<Couleurs> allCouleurs, List<String> coul) {
        List<String> allColors = new ArrayList<>();
        if (coul == null || coul.isEmpty()) {
            System.out.println("Couleurs Null All");
            List<Couleurs> allC =allCouleurs;
            for (Couleurs c: allC) {
                allColors.add(c.getIdCouleur());
            }
        } else {
            allColors = coul;
        }

        return  allColors;
    }
}
