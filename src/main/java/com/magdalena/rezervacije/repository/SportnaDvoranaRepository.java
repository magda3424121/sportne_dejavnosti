package com.magdalena.rezervacije.repository;

import com.magdalena.rezervacije.model.SportnaDvorana;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface SportnaDvoranaRepository extends CrudRepository<SportnaDvorana, Long> {

    @Query("SELECT d.*, c.ime AS ime_centra " +
       "FROM sportne_dvorane d " +
       "JOIN sportni_centri c ON d.sc_id = c.sc_id")
List<SportnaDvorana> findAllWithNames();
}