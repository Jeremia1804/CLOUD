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
@Table(name = "bestvente")
public class BESTVENTE {
    @Id
    
    private String idvente;
    private String idannonce;
    private String username;
    private String nommodele;
    private double prix;
    private int rank_num;

    public String getIdvente() {
        return idvente;
    }

    public void setIdvente(String idvente) {
        this.idvente = idvente;
    }

    public String getIdannonce() {
        return idannonce;
    }

    public void setIdannonce(String idannonce) {
        this.idannonce = idannonce;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNommodele() {
        return nommodele;
    }

    public void setNommodele(String nommodele) {
        this.nommodele = nommodele;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getRank_num() {
        return rank_num;
    }

    public void setRank_num(int rank_num) {
        this.rank_num = rank_num;
    }
}
