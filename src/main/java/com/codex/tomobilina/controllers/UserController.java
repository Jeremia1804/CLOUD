package com.codex.tomobilina.controllers;

import com.codex.tomobilina.models.ContactUser;
import com.codex.tomobilina.models.Resultat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codex.tomobilina.models.User;
import com.codex.tomobilina.repository.UserRepository;
import com.codex.tomobilina.security.jwt.JwtUtils;
import com.codex.tomobilina.security.services.UserDetailsImpl;
import com.codex.tomobilina.services.ContactUserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tomobilina/user")
public class UserController {
  @Autowired
  UserRepository userRepository;
  
  @Autowired
  ContactUserService contactUserService;

  @Autowired
  JwtUtils jwtUtils;

    @GetMapping("/getuser")
    public UserDetailsImpl getUser(@RequestParam("token") String token) {
        String username = jwtUtils.getUserNameFromJwtToken(token);

        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(user);
    }
  
    @GetMapping("/contact/{id}")
    public ResponseEntity<Resultat> getContactUser(@PathVariable int id) {
        try {
            List<ContactUser> listC = contactUserService.getAllContactUser(id);
            Resultat resultat = new Resultat("OK", null, listC);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }
}
