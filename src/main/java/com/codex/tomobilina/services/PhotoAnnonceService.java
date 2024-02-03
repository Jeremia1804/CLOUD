/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codex.tomobilina.services;

import com.codex.tomobilina.models.PhotoAnnonce;
import com.codex.tomobilina.repository.PhotoAnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tohy
 */

@Service
public class PhotoAnnonceService {
    @Autowired
    private PhotoAnnonceRepository photoAnnonceRepo;
    @Autowired
    private ImageUploadingService imageUploadingService;
    
    public PhotoAnnonce savePhotoAnnonce(MultipartFile file, String idAnnonce) {
        PhotoAnnonce photo = new PhotoAnnonce();
        photo.setIdannonce(idAnnonce);
        String img = imageUploadingService.upload(file);
        photo.setImage(img);
        return photoAnnonceRepo.save(photo);
    }
}
