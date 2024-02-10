package com.codex.tomobilina.models;

import jakarta.persistence.*;

@Entity
@Table(name = "venteweek")
public class VenteWeek {
    @Id
    
    @Column(name = "totalvente")
    private double totalvente;
    
    @Column(name = "last")
    private Double last;


    public double getTotalvente() {
        return totalvente;
    }

    public void setTotalvente(Double totalvente) {
        this.totalvente = totalvente;
    }

    public double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }
}
