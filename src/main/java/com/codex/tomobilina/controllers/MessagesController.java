package com.codex.tomobilina.controllers;

import com.codex.tomobilina.models.Chat;
import com.codex.tomobilina.models.Resultat;
import com.codex.tomobilina.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/messenger")
public class MessagesController {
    @Autowired
    private MessageService messageService;
    
    @GetMapping("/messages")
    public ResponseEntity<List<String>> messages() {
        return ResponseEntity.ok(Arrays.asList("first", "second"));
    }
    
        @PostMapping
    public ResponseEntity<Resultat> sendMessage(@RequestBody Chat message) {
        // Enregistrez le message dans la base de données MongoDB
        messageService.saveMessage(message);
        Resultat resultat = new Resultat("OK", null, null);
        return new ResponseEntity<>(resultat, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Resultat> getAllMessages() {
        // Récupérez tous les messages de la base de données MongoDB;
        Resultat resultat = new Resultat("OK", null, messageService.getAllMessages());
        return new ResponseEntity<>(resultat, HttpStatus.OK);
    }

    @GetMapping("/{idSender}/{idReceiver}")
    public ResponseEntity<Resultat> getMessagesBetweenUsers(@PathVariable String idSender, @PathVariable String idReceiver) {
        // Récupérez les messages entre les utilisateurs spécifiés
        Resultat resultat = new Resultat("OK", null, messageService.getMessagesBetweenUsers(idSender, idReceiver));
        return new ResponseEntity<>(resultat, HttpStatus.OK);
    }
}
