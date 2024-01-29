package com.codex.tomobilina.repository;

import com.codex.tomobilina.models.BESTVENTE;
import com.codex.tomobilina.models.V_vente_Commission;
import com.codex.tomobilina.models.Vente;
import com.codex.tomobilina.models.VenteLastWeek;
import com.codex.tomobilina.models.VenteWeek;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface VenteRepository  extends JpaRepository<Vente, String> {
    @Query("select v from VenteLastWeek v")
    VenteLastWeek findVenteLastWeek();
    
    @Query("select v from VenteWeek v")
    VenteWeek findVenteWeek();
    
    @Query("select v from V_vente_Commission v")
    List<V_vente_Commission> findV_vente_Commission();
    
    @Query("select v from V_vente_Commission v where v.idVente = :id")
    V_vente_Commission findV_vente_CommissionByIdVente(@Param("id") String id);
    
    @Query("select v from BESTVENTE v")
    List<BESTVENTE> findBESTVENTE();
}
