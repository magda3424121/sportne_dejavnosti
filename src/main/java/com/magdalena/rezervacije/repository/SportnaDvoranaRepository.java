package com.magdalena.rezervacije.repository;

import com.magdalena.rezervacije.model.SportnaDvorana;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface SportnaDvoranaRepository extends CrudRepository<SportnaDvorana, Long> {

    // Ta poizvedba poveže (JOIN) tabeli, da dobimo ime centra
   @Query("SELECT d.*, c.ime AS ime_centra, c.naslov AS naslov_centra, c.kontakt AS kontakt_centra " +
       "FROM sportne_dvorane d " +
       "LEFT JOIN sportni_centri c ON d.sc_id = c.k_id")
List<SportnaDvorana> findAllWithNames();
}