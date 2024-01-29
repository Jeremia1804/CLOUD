package com.codex.tomobilina.repository;

import com.codex.tomobilina.models.NbUsersWeek;
import com.codex.tomobilina.models.Stat_AnnonceUser;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codex.tomobilina.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Optional<User> findById(int id);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  @Query("select u from User u where month(u.dateheure) = month(:date) and year(u.dateheure) = year (:date)")
  List<User> findByDateheure(@Param("date") Timestamp date);

  @Query("select n.nombre_inscrits from NbUsersLastWeek n")
  int findNbrUserLastWeek();

  @Query("select n from NbUsersWeek n")
  NbUsersWeek findNbrUserWeek();
  
    @Query("select sa from Stat_AnnonceUser sa")
    List<Stat_AnnonceUser> findStat_AnnonceUser();
}
