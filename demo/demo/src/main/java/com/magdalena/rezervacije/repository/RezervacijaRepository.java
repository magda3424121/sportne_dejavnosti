package com.magdalena.rezervacije.repository;

import com.magdalena.rezervacije.model.Rezervacija;
import org.springframework.data.repository.CrudRepository;

public interface RezervacijaRepository extends CrudRepository<Rezervacija, Long> {
}
