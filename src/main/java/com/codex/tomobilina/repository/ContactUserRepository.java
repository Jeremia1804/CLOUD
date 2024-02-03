package com.codex.tomobilina.repository;

import com.codex.tomobilina.models.ContactUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ContactUserRepository extends JpaRepository<ContactUser, String> {
    @Query("select cont from ContactUser cont where cont.user.id = :id ")
    List<ContactUser> findByUser_Id(@Param("id") int id);
}
