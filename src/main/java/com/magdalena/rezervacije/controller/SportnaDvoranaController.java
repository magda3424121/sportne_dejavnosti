package com.magdalena.rezervacije.controller;

import com.magdalena.rezervacije.model.SportnaDvorana;
import com.magdalena.rezervacije.repository.SportnaDvoranaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sportne-dvorane")
public class SportnaDvoranaController {

    private final SportnaDvoranaRepository repository;
    private final JdbcTemplate jdbcTemplate; // Dodano za prisilno mapiranje

    // Posodobljen konstruktor
    public SportnaDvoranaController(SportnaDvoranaRepository repository, JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<SportnaDvorana> vse() {
        return repository.findAllWithNames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SportnaDvorana> en(@PathVariable Long id) {
        // SQL poizvedba, ki združi dvorane in centre
        String sql = "SELECT sd.*, sc.ime AS ime_centra, sc.naslov AS naslov_centra, sc.kontakt AS kontakt_centra " +
                     "FROM sportne_dvorane sd " +
                     "JOIN sportni_centri sc ON sd.sc_id = sc.sc_id " +
                     "WHERE sd.sd_id = ?";

        try {
            // Prisilno ročno mapiranje rezultatov iz baze v Java objekt
            SportnaDvorana dvorana = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                SportnaDvorana sd = new SportnaDvorana();
                // Osnovna polja iz tabele sportne_dvorane
                sd.setSdId(rs.getLong("sd_id"));
                sd.setIme(rs.getString("ime"));
                sd.setKapaciteta(rs.getInt("kapaciteta"));
                sd.setScId(rs.getLong("sc_id"));
                sd.setSporti(rs.getString("sporti"));

                // PRISILNO MAPIRANJE TRANSIENT POLJ (iz tabele sportni_centri)
                sd.setImeCentra(rs.getString("ime_centra"));
                sd.setNaslovCentra(rs.getString("naslov_centra"));
                sd.setKontaktCentra(rs.getString("kontakt_centra"));
                
                return sd;
            }, id);

            return ResponseEntity.ok(dvorana);
        } catch (Exception e) {
            // Če dvorane ni ali pride do napake, vrnemo 404
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public SportnaDvorana dodaj(@RequestBody SportnaDvorana nova) {
        return repository.save(nova);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SportnaDvorana> posodobi(@PathVariable Long id, @RequestBody SportnaDvorana nova) {
        return repository.findById(id)
                .map(stara -> {
                    stara.setIme(nova.getIme());
                    stara.setKapaciteta(nova.getKapaciteta());
                    stara.setScId(nova.getScId());
                    stara.setSporti(nova.getSporti());
                    return ResponseEntity.ok(repository.save(stara));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void izbrisi(@PathVariable Long id) {
        repository.deleteById(id);
    }
}