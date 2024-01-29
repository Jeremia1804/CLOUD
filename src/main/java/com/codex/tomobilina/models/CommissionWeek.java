/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Tohy
 */

@Entity
@Table(name = "commissionweek")
public class CommissionWeek {
    @Id
    
    private double benefice;
    private double last;
    

    public double getBenefice() {
        return benefice;
    }

    public void setBenefice(double benefice) {
        this.benefice = benefice;
    }

    public double getLast() {
        return last;
    }

    public void setLast(double last) {
        this.last = last;
    }
}
