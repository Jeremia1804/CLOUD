package com.codex.tomobilina.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.Immutable;

@Entity
@Immutable
@Table(name = "nbuserslastweek")
public class NbUsersLastWeek {
    private Long id;
    @Column(name = "nombre_inscrits")
    private int nombre_inscrits;

    public int getNombre_inscrits() {
        return nombre_inscrits;
    }

    public void setNombre_inscrits(int nombre_inscrits) {
        this.nombre_inscrits = nombre_inscrits;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
