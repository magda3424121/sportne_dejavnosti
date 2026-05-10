package com.magdalena.rezervacije.repository;

import com.magdalena.rezervacije.model.Rezervacija;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RezervacijaRepository extends CrudRepository<Rezervacija, Long> {

    List<Rezervacija> findByuId(Long uId);
    List<Rezervacija> findBySdId(Long sdId);
    List<Rezervacija> findByDatumRezervacije(LocalDate datumRezervacije);

    // To bo preverilo zasedenost dvorane na specifičen datum
    boolean existsBySdIdAndDatumRezervacije(Long sdId, LocalDate datumRezervacije);
}