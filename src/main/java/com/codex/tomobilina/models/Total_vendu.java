package com.codex.tomobilina.models;

import jakarta.persistence.*;

@Entity
@Table(name = "total_vendu")
public class Total_vendu {
    @Id
    
    private double total;
    private double totalprix;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotalprix() {
        return totalprix;
    }

    public void setTotalprix(double totalprix) {
        this.totalprix = totalprix;
    }
}
