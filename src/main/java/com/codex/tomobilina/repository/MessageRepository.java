/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.codex.tomobilina.repository;

import com.codex.tomobilina.models.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import org.springframework.data.mongodb.repository.Query;

public interface MessageRepository extends MongoRepository<Chat, String> {

   @Query("{ $or: [ { 'idSender': ?0, 'idReceiver': ?1 }, { 'idSender': ?1, 'idReceiver': ?0 } ] }")
    List<Chat> findMessagesByUsers(String user1, String user2);

    // Ajoutez d'autres méthodes personnalisées si nécessaire
}

