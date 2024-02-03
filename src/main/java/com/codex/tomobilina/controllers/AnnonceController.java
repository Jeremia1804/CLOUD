/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.codex.tomobilina.controllers;

import com.codex.tomobilina.models.Annonce;
import com.codex.tomobilina.models.Favori;
import com.codex.tomobilina.models.Resultat;
import com.codex.tomobilina.models.User;
import com.codex.tomobilina.models.Vente;
import com.codex.tomobilina.models.Voiture;
import com.codex.tomobilina.repository.UserRepository;
import com.codex.tomobilina.services.AnnonceService;
import com.codex.tomobilina.services.FavoriService;
import com.codex.tomobilina.services.ImageUploadingService;
import com.codex.tomobilina.services.VenteService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tohy
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tomobilina/annonce")
public class AnnonceController {
    @Autowired
    private AnnonceService annonceService;
    @Autowired
    private FavoriService favoriService;
    @Autowired
    private VenteService venteService;
    @Autowired
    private ImageUploadingService imageUploadingService;
    
    @Autowired
    UserRepository userRepository;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Resultat> getAllAnnonce() {
        try {
            List<Annonce> an = annonceService.getAllAnnonce();
            Resultat resultat = new Resultat("OK", null, an);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/valid")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Resultat> getAllAnnonceValid() {
        try {
            Resultat resultat = new Resultat("OK", null, annonceService.getAllAnnonceByEtat(5));
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/invalid")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Resultat> getAllAnnonceInValid() {
        try {
            Resultat resultat = new Resultat("OK", null, annonceService.getAllAnnonceByEtat(3));
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/vendu")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Resultat> getAllAnnonceVendu() {
        try {
            Resultat resultat = new Resultat("OK", null, annonceService.getAllAnnonceByEtat(10));
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/recherche")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Resultat> recherche(
            @RequestParam(required = false) String nomModele,
            @RequestParam(required = false) List<String> idMarques,
            @RequestParam(required = false) List<String> idCategories,
            @RequestParam(required = false) Integer minNbrPlaces,
            @RequestParam(required = false) Integer maxNbrPlaces,
            @RequestParam(required = false) Integer minNbrPortes,
            @RequestParam(required = false) Integer maxNbrPortes,
            @RequestParam(required = false) Integer minAnnee,
            @RequestParam(required = false) Integer maxAnnee,
            @RequestParam(required = false) double minKilometrage,
            @RequestParam(required = false) double maxKilometrage,
            @RequestParam(required = false) List<String> idEnergies,
            @RequestParam(required = false) List<String> idBoiteVitesses,
            @RequestParam(required = false) List<String> idCouleurs,
            @RequestParam(required = false) double minConso,
            @RequestParam(required = false) double maxConso,
            @RequestParam(required = false) double minPrix,
            @RequestParam(required = false) double maxPrix) {
        try {
            Resultat resultat = new Resultat("OK", null, annonceService.filtreRecherche(nomModele, idMarques,
                    idCategories, minNbrPlaces, maxNbrPlaces, minNbrPortes, maxNbrPortes,
                    minAnnee, maxAnnee, minKilometrage, maxKilometrage, idEnergies, idBoiteVitesses,
                    idCouleurs, minConso, maxConso, minPrix, maxPrix));
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/vente/{id}")
    public ResponseEntity<Resultat> VendrerAnnonce(@PathVariable String id) {
        try {
            Optional<Annonce> an = annonceService.getAnnonceById(id);
            if (an.isEmpty()) {
                Resultat resultat = new Resultat("NOT FOUND", "Id Annonce Not Found", null);
                return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
            }
            Annonce ano = an.get();
            ano.setEtat(10);
            Annonce annonc = annonceService.saveAnnonce(ano);
            
            Date currentDate = new Date();
            // Convertir la date en timestamp
            Timestamp dateheure = new Timestamp(currentDate.getTime());
            
            Vente vente = new Vente(annonc, dateheure);
            venteService.saveVente(vente);
            
            Resultat resultat = new Resultat("Annonce VENDU", null, vente);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/validate/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Resultat> ValiderAnnonce(@PathVariable String id) {
        try {
            Optional<Annonce> an = annonceService.getAnnonceById(id);
            if (an.isEmpty()) {
                Resultat resultat = new Resultat("NOT FOUND", "Id Annonce Not Found", null);
                return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
            }
            Annonce ano = an.get();
            ano.setEtat(5);
            Annonce annonc = annonceService.saveAnnonce(ano);
            Resultat resultat = new Resultat("VALIDATE", null, annonc);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Resultat> getAllAnnonceUser(@PathVariable int id) {
        try {
            List<Annonce> anonceUser = annonceService.getAllAnnonceUser(id);
            if (anonceUser.isEmpty()) {
                Resultat resultat = new Resultat("NOT FOUND", "Anonce utilisateur null ou Id Utilisateur NOT FOUND",
                        null);
                return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
            }
            Resultat resultat = new Resultat("OK", null, anonceUser);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/favori/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Annonce> getAllAnnonceFavoriUser(@PathVariable int id) {
        List<Favori> allFav = favoriService.getAllFavoriUser(id);
        List<Annonce> favUser = new ArrayList<>();
        for (Favori fav : allFav) {
            favUser.add(annonceService.getAnnonceById(fav.getAnnonce().getIdAnnonce()).get());
        }
        return favUser;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Resultat> getAnnonceById(@PathVariable String id) {
        try {
            Optional<Annonce> anon = annonceService.getAnnonceById(id);
            if (anon.isEmpty()) {
                Resultat resultat = new Resultat("NOT FOUND", "Id Annonce Not Found", null);
                return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
            }
            Resultat resultat = new Resultat("OK", null, anon);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT FOUND", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Resultat> add(
            @RequestParam("titre") String titre,
            @RequestParam("descriptions") String descriptions,
            @RequestParam("idUser") Long idUser,
            @RequestParam("idVoiture") String idVoiture,
            @RequestParam("prix") double prix,
            @RequestParam("photo") MultipartFile photo) {
        try {
            int etat = 3;
            Date currentDate = new Date();
            // Convertir la date en timestamp
            Timestamp dateheure = new Timestamp(currentDate.getTime());
            
            String image = imageUploadingService.upload(photo);
            
            Annonce anon = new Annonce(titre, descriptions, new User(idUser), new Voiture(idVoiture), prix, image, dateheure, etat);
            Resultat resultat = new Resultat("CREATED", null, annonceService.saveAnnonce(anon));
            return new ResponseEntity<>(resultat, HttpStatus.CREATED);
        } catch (Exception e) {
            Resultat resultat = new Resultat("NOT CREATED", e.getMessage(), null);
            return new ResponseEntity<>(resultat, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> updateAnnonce(@PathVariable String id, @RequestBody Annonce anon) {
        annonceService.updateAnnonce(id, anon);
        return ResponseEntity.ok("Annonce Update");
    }
}
