package com.magdalena.rezervacije.repository;

import com.magdalena.rezervacije.model.Uporabnik;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UporabnikRepository extends CrudRepository<Uporabnik, Long> {
    Optional<Uporabnik> findByEmail(String email);
}
