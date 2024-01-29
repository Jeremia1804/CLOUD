package com.codex.tomobilina.repository;

import com.codex.tomobilina.models.Favori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FavoriRepository extends JpaRepository<Favori, String> {
    @Query("select fav from Favori fav where fav.user.id = :id ")
    List<Favori> findByUser_Id(@Param("id") int id);

    List<Favori> findByEtat(int etat);

    List<Favori> findByUser_IdAndEtat(int user_id, int etat);
}
