package com.magdalena.rezervacije.repository;

import com.magdalena.rezervacije.model.Rezervacija;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface RezervacijaRepository extends CrudRepository<Rezervacija, Long> {

    // Popravljeno ime metode, da ustreza polju 'uId' v modelu
    List<Rezervacija> findByuId(Long uId);

    List<Rezervacija> findBySdId(Long sdId);
    List<Rezervacija> findByDatumRezervacije(LocalDate datumRezervacije);
}