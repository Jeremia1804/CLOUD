package com.codex.tomobilina.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ventelastweek")
public class VenteLastWeek {
    @Id
    
    @Column(name = "totalvente")
    private double totalvente;

    public double getTotalvente() {
        return totalvente;
    }

    public void setTotalvente(double totalvente) {
        this.totalvente = totalvente;
    }
}
