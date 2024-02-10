/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.models;

import jakarta.persistence.*;

/**
 *
 * @author Tohy
 */

@Entity
public class PhotoAnnonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idannonce;
    private String image;

    public PhotoAnnonce(String idannonce, String image) {
        this.idannonce = idannonce;
        this.image = image;
    }

    public PhotoAnnonce() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdannonce() {
        return idannonce;
    }

    public void setIdannonce(String idannonce) {
        this.idannonce = idannonce;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
