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
@Table(name = "boite_vitesse")
public class BoiteVitesse  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idboitevitesse", length = 15)
    private String idBoiteVitesse;

    @Column(name = "nomboite_vitesse", nullable = false)
    private String nomBoiteVitesse;

    @Column(name = "etat", columnDefinition = "INT DEFAULT 1")
    private int etat;

    public BoiteVitesse(String nomBoiteVitesse, int etat) {
        this.nomBoiteVitesse = nomBoiteVitesse;
        this.etat = etat;
    }

    public BoiteVitesse() {
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getIdBoiteVitesse() {
        return idBoiteVitesse;
    }

    public void setIdBoiteVitesse(String idBoiteVitesse) {
        this.idBoiteVitesse = idBoiteVitesse;
    }

    public String getNomBoiteVitesse() {
        return nomBoiteVitesse;
    }

    public void setNomBoiteVitesse(String nomBoiteVitesse) {
        this.nomBoiteVitesse = nomBoiteVitesse;
    }

    public static List<String> getListIds(List<BoiteVitesse> allBtV, List<String> bt) {
        List<String> allBoiteV = new ArrayList<>();
        if (bt == null || bt.isEmpty()) {
            System.out.println("Boite Vitesse Null All");
            List<BoiteVitesse> allB = allBtV;
            for (BoiteVitesse btvi: allB) {
                allBoiteV.add(btvi.getIdBoiteVitesse());
            }
        } else {
            allBoiteV = bt;
        }

        return  allBoiteV;
    }
}
