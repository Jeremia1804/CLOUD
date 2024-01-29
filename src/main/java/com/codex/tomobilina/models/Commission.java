/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.models;

import jakarta.persistence.*;
import java.sql.Timestamp;

/**
 *
 * @author Tohy
 */

@Entity
@Table(name = "commission")
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcommission")
    private String idCommission;

    private double commission;
    
    private Timestamp dateheure;

    public Commission(double commission, Timestamp dateheure) {
        this.commission = commission;
        this.dateheure = dateheure;
    }

    public Commission() {
    }

    public String getIdCommission() {
        return idCommission;
    }

    public void setIdCommission(String idCommission) {
        this.idCommission = idCommission;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public Timestamp getDateheure() {
        return dateheure;
    }

    public void setDateheure(Timestamp dateheure) {
        this.dateheure = dateheure;
    }
}
