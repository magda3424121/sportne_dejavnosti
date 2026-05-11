package com.magdalena.rezervacije.repository;

import com.magdalena.rezervacije.model.SportniCenter;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface SportniCenterRepository extends CrudRepository<SportniCenter, Long> {
    
    @Query("SELECT sc.*, k.ime AS ime_kraja " +
       "FROM sportni_centri sc " +
       "LEFT JOIN kraji k ON sc.k_id = k.k_id")
List<SportniCenter> findAllWithKraji();
}