package com.magdalena.rezervacije.controller;

import com.magdalena.rezervacije.model.Rezervacija;
import com.magdalena.rezervacije.repository.RezervacijaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/rezervacije")
public class RezervacijaController {

    private final RezervacijaRepository repo;

    public RezervacijaController(RezervacijaRepository repo) {
        this.repo = repo;
    }

    // GET /rezervacije
    // GET /rezervacije?uId=...
    // GET /rezervacije?sdId=...
    // GET /rezervacije?datum=2026-02-24
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
                    stari.setuId(novi.getuId());   // popravljen getter/setter
                    stari.setSdId(novi.getSdId());
                    return ResponseEntity.ok(repo.save(stari));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> brisi(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}