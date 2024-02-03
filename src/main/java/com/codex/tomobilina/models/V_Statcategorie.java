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
@Table(name = "v_statcategorie")
public class V_Statcategorie {
    @Id
    
    private String idcategorie;
    private String nomcategorie;
    private int nombre;
    private double prix;
    private int total;
    private double totalprix;
    private double taux_nombre;
    private double taux_prix;

    public String getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(String idcategorie) {
        this.idcategorie = idcategorie;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getTotalprix() {
        return totalprix;
    }

    public void setTotalprix(double totalprix) {
        this.totalprix = totalprix;
    }

    public double getTaux_nombre() {
        return taux_nombre;
    }

    public void setTaux_nombre(double taux_nombre) {
        this.taux_nombre = taux_nombre;
    }

    public double getTaux_prix() {
        return taux_prix;
    }

    public void setTaux_prix(double taux_prix) {
        this.taux_prix = taux_prix;
    }
}
