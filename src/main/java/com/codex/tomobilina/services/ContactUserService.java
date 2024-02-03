/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.services;

import com.codex.tomobilina.models.ContactUser;
import com.codex.tomobilina.repository.ContactUserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tohy
 */

@Service
public class ContactUserService {
    @Autowired
    private ContactUserRepository contactUserRepository;
    
    public List<ContactUser> getAllContactUser(int idUser) {
        return contactUserRepository.findByUser_Id(idUser);
    }
    
    public ContactUser saveContactUser(ContactUser cont) { return contactUserRepository.save(cont); }
}
