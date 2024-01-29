package com.codex.tomobilina.models;

import jakarta.persistence.*;

@Entity
@Table(name = "stat_prixcommission")
public class Stat_PrixCommission {
    @Id
    
    private String libelle;
    private int mois;
    private double prixvente;
    private double commission;
    private int rank_num;

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

    public double getPrixvente() {
        return prixvente;
    }

    public void setPrixvente(double prixvente) {
        this.prixvente = prixvente;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public int getRank_num() {
        return rank_num;
    }

    public void setRank_num(int rank_num) {
        this.rank_num = rank_num;
    }
}
