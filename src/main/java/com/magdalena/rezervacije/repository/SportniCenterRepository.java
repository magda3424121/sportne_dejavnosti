package com.magdalena.rezervacije.repository;

import com.magdalena.rezervacije.model.SportniCenter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SportniCenterRepository extends CrudRepository<SportniCenter, Long> {

    // ime metode mora sovpadati z imenom polja v modelu: kId
    List<SportniCenter> findBykId(Long kId);

    List<SportniCenter> findByImeContainingIgnoreCase(String ime);
}