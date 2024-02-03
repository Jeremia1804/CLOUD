package com.codex.tomobilina.models;

import jakarta.persistence.*;

@Entity
@Table(name = "nbusersweek")
public class NbUsersWeek {
    @Id
    
    @Column(name = "nombre_inscrits")
    private int nombre_inscrits;
    private int last;

    public NbUsersWeek(int nombre_inscrits, int last) {
        this.nombre_inscrits = nombre_inscrits;
        this.last = last;
    }

    public NbUsersWeek() {
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getNombre_inscrits() {
        return nombre_inscrits;
    }

    public void setNombre_inscrits(int nombre_inscrits) {
        this.nombre_inscrits = nombre_inscrits;
    }
}
