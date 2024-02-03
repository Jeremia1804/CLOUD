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
@Table(name = "energie")
public class Energie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idenergie")
    private String idEnergie;
    @Column(name = "nomenergie")
    private String nomEnergie;
    private int etat;

    public Energie(String nomenergie, int etat) {
        this.nomEnergie = nomenergie;
        this.etat = etat;
    }

    public Energie() {
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getIdEnergie() {
        return idEnergie;
    }

    public void setIdEnergie(String idenergie) {
        this.idEnergie = idenergie;
    }

    public String getNomEnergie() {
        return nomEnergie;
    }

    public void setNomEnergie(String nomenergie) {
        this.nomEnergie = nomenergie;
    }

    public static List<String> getListIds(List<Energie> allEne, List<String> ene) {
        List<String> allEnergies = new ArrayList<>();
        if (ene == null || ene.isEmpty()) {
            System.out.println("Energie Null All");
            List<Energie> allE =allEne;
            for (Energie e: allE) {
                allEnergies.add(e.getIdEnergie());
            }
        } else {
            allEnergies = ene;
        }

        return  allEnergies;
    }
}
