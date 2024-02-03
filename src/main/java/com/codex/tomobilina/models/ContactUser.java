package com.codex.tomobilina.models;

import java.sql.Timestamp;
import jakarta.persistence.*;


@Entity
@Table(name = "contact_user")
public class ContactUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcontact_user")
    private String idContactUser;

    private String contact;
    
    @ManyToOne
    @JoinColumn(name = "iduser", referencedColumnName = "id")
    private User user;

    private String type;

    private Timestamp dateheure;

    public ContactUser() {
    }

    public String getIdContactUser() {
        return idContactUser;
    }

    public void setIdContactUser(String idContactUser) {
        this.idContactUser = idContactUser;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getDateheure() {
        return dateheure;
    }

    public void setDateheure(Timestamp dateheure) {
        this.dateheure = dateheure;
    }
}
