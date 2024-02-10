/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.services;

import com.codex.tomobilina.models.Chat;
import com.codex.tomobilina.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void saveMessage(Chat message) {
        messageRepository.save(message);
    }

    public List<Chat> getMessagesBetweenUsers(String user1, String user2) {
        List<Chat> messages = messageRepository.findMessagesByUsers(user1, user2);
        messages.forEach(message -> {
            if (message.getIdSender().equals(user1)) {
                message.setEmplacement("droite");
            } else {
                message.setEmplacement("gauche");
            }
        });

        return messages;
    }

    public List<Chat> getAllMessages() {
        return messageRepository.findAll();
    }

    // Ajoutez d'autres méthodes si nécessaire
}

