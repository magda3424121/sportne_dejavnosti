package com.magdalena.rezervacije.controller;

import com.magdalena.rezervacije.model.Rezervacija;
import com.magdalena.rezervacije.repository.RezervacijaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rezervacije")
public class RezervacijaController {

    private final RezervacijaRepository repo;
    private final JdbcTemplate jdbcTemplate; // Dodaj tole

    public RezervacijaController(RezervacijaRepository repo, JdbcTemplate jdbcTemplate) {
        this.repo = repo;
        this.jdbcTemplate = jdbcTemplate;
    }
    @GetMapping
    public Iterable<Rezervacija> vse(
            @RequestParam(required = false) Long uId,
            @RequestParam(required = false) Long sdId,
            @RequestParam(required = false) String datum
    ) {
        if (uId != null) return repo.findByuId(uId);
        if (sdId != null) return repo.findBySdId(sdId);
        if (datum != null) return repo.findByDatumRezervacije(LocalDate.parse(datum));
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rezervacija> ena(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Rezervacija dodaj(@RequestBody Rezervacija r) {
        return repo.save(r);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rezervacija> posodobi(@PathVariable Long id, @RequestBody Rezervacija novi) {
        return repo.findById(id)
                .map(stari -> {
                    stari.setDatumRezervacije(novi.getDatumRezervacije());
                    stari.setUId(novi.getUId());
                    stari.setSdId(novi.getSdId());
                    return ResponseEntity.ok(repo.save(stari));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ENA metoda za brisanje/preklic (odstranil sem dvojnik)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> brisi(@PathVariable Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/admin")
    public List<Rezervacija> zaAdmina() {
        String sql = "SELECT r.*, u.ime AS ime_uporabnika, sc.ime AS ime_dvorane " +
                     "FROM rezervacije r " +
                     "JOIN uporabniki u ON r.u_id = u.u_id " +
                     "JOIN sportni_centri sc ON r.sd_id = sc.sc_id";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Rezervacija r = new Rezervacija();
            r.setRId(rs.getLong("r_id"));
            r.setDatumRezervacije(rs.getDate("datum_rezervacije").toLocalDate());
            r.setUId(rs.getLong("u_id"));
            r.setSdId(rs.getLong("sd_id"));
            // ROČNO NASTAVIMO IMENA
            r.setImeUporabnika(rs.getString("ime_uporabnika"));
            r.setImeDvorane(rs.getString("ime_dvorane"));
            return r;
        });
    }
}