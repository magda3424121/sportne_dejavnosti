package com.magdalena.rezervacije.repository;

import com.magdalena.rezervacije.model.SportnaDvorana;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SportnaDvoranaRepository extends CrudRepository<SportnaDvorana, Long> {

    @Query("SELECT sd.*, sc.ime AS ime_centra, sc.naslov AS naslov_centra, sc.kontakt AS kontakt_centra " +
           "FROM sportne_dvorane sd " +
           "JOIN sportni_centri sc ON sd.sc_id = sc.sc_id")
    List<SportnaDvorana> findAllWithNames();

    @Query("SELECT sd.*, sc.ime AS \"ime_centra\", sc.naslov AS \"naslov_centra\", sc.kontakt AS \"kontakt_centra\" " +
       "FROM sportne_dvorane sd " +
       "JOIN sportni_centri sc ON sd.sc_id = sc.sc_id " +
       "WHERE sd.sd_id = :id")
SportnaDvorana findByIdWithDetails(@Param("id") Long id);
}