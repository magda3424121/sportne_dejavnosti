package com.magdalena.rezervacije.repository;

import com.magdalena.rezervacije.model.Kraj;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KrajRepository extends CrudRepository<Kraj, Long> {
    // CrudRepository avtomatsko doda findAll(), save(), findById(), itd.
}