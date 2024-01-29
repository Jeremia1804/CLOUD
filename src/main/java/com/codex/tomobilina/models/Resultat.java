/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.models;

/**
 *
 * @author Tohy
 */
public class Resultat {
    private String etat;
    private String error;
    private Object data;

    public Resultat() {
    }

    public Resultat(String etat, String error, Object data) {
        this.etat = etat;
        this.error = error;
        this.data = data;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
