package com.magdalena.rezervacije.repository;

import com.magdalena.rezervacije.model.Rezervacija;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RezervacijaRepository extends CrudRepository<Rezervacija, Long> {

 @Query("SELECT r.*, u.ime AS ime_uporabnika, sc.ime AS ime_dvorane " +
       "FROM rezervacije r " +
       "JOIN uporabniki u ON r.u_id = u.u_id " +
       "JOIN sportni_centri sc ON r.sd_id = sc.sc_id " +
       "ORDER BY r.datum_rezervacije DESC")
List<Rezervacija> findAllDetailed();

    List<Rezervacija> findByuId(Long uId);
    List<Rezervacija> findBySdId(Long sdId);
    List<Rezervacija> findByDatumRezervacije(LocalDate datumRezervacije);

    // To bo preverilo zasedenost dvorane na specifičen datum
    boolean existsBySdIdAndDatumRezervacije(Long sdId, LocalDate datumRezervacije);
}