package com.codex.tomobilina.repository;

import com.codex.tomobilina.models.Annonce;
import com.codex.tomobilina.models.NbAnnonceLastWeek;
import com.codex.tomobilina.models.NbAnnonceWeek;
import com.codex.tomobilina.models.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface AnnonceRepository extends JpaRepository<Annonce, String> {
    @Query("select an from Annonce an where an.user.id = :id ")
    List<Annonce> findByUser_Id(@Param("id") int id);

    @Query("select an from Annonce an where an.etat = :state ")
    List<Annonce> findByEtat(@Param("state") int state);

    List<Annonce> findByVoiture_IdVoitureInAndPrixBetween(
            List<String> idVoitures, double prixMin, double prixMax
    );

    @Query("select a from Annonce a where month(a.dateheure) = month(:date) and year(a.dateheure) = year (:date)")
    List<Vente> findByDateheure(@Param("date") Timestamp date);
    
    @Query("select n from NbAnnonceLastWeek n")
    NbAnnonceLastWeek findNbAnnonceLastWeek();
    
    @Query("select n from NbAnnonceWeek n")
    NbAnnonceWeek findNbAnnonceWeek();
}
