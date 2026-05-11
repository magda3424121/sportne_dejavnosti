package com.magdalena.rezervacije.controller;

import com.magdalena.rezervacije.model.Rezervacija;
import com.magdalena.rezervacije.repository.RezervacijaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/rezervacije")
public class RezervacijaController {

    // Uporabljamo ime 'repo', kot si ga definirala zgoraj
    private final RezervacijaRepository repo;

    public RezervacijaController(RezervacijaRepository repo) {
        this.repo = repo;
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
}