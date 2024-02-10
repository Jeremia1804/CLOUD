package com.codex.tomobilina.models;

import java.sql.Timestamp;
import jakarta.persistence.*;

@Entity
public class Favori {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfavori")
    private String idFavori;

    @ManyToOne
    @JoinColumn(name = "iduser", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idannonce", referencedColumnName = "idAnnonce")
    private Annonce annonce;

    private Timestamp dateheure;
    
    private int etat;

    public Favori(User user, Annonce annonce, Timestamp dateheure, int etat) {
        this.user = user;
        this.annonce = annonce;
        this.dateheure = dateheure;
        this.etat = etat;
    }

    public Favori() {
    }

    public String getIdFavori() {
        return idFavori;
    }

    public void setIdFavori(String idFavori) {
        this.idFavori = idFavori;
    }

    public User getUser() {
        return user;
    }

    public void setUtilisateur(User utilisateur) {
        this.user = utilisateur;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public Timestamp getDateheure() {
        return dateheure;
    }

    public void setDateheure(Timestamp dateheure) {
        this.dateheure = dateheure;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
}
