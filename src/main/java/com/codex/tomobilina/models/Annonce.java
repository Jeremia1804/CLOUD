package com.codex.tomobilina.models;

import java.sql.Timestamp;
import jakarta.persistence.*;


@Entity
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idannonce")
    private String idAnnonce;

    private String titre;

    private String descriptions;

    @ManyToOne
    @JoinColumn(name = "iduser", referencedColumnName = "id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "idvoiture", referencedColumnName = "idVoiture")
    private Voiture voiture;

    private Double prix;
    
    @Column(name = "photo")
    private String photo;
    
    private Timestamp dateheure;
    private int etat;

    public Annonce(String titre, String descriptions, User user, Voiture voiture, Double prix, String photo, Timestamp dateheure, int etat) {
        this.titre = titre;
        this.descriptions = descriptions;
        this.user = user;
        this.voiture = voiture;
        this.prix = prix;
        this.photo = photo;
        this.dateheure = dateheure;
        this.etat = etat;
    }

    public Annonce() {
    }

    public String getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(String idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public User getUser() {
        return user;
    }

    public void setUtilisateur(User utilisateur) {
        this.user = utilisateur;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
