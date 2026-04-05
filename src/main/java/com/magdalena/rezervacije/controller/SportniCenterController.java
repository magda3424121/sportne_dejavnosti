package com.magdalena.rezervacije.controller;

import com.magdalena.rezervacije.model.SportniCenter;
import com.magdalena.rezervacije.repository.SportniCenterRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sportni-centri")
public class SportniCenterController {

    private final SportniCenterRepository repo;

    public SportniCenterController(SportniCenterRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<SportniCenter> vsi(
            @RequestParam(required = false) Long kId,
            @RequestParam(required = false) String ime
    ) {
        if (kId != null) return repo.findBykId(kId);
        if (ime != null && !ime.isBlank()) return repo.findByImeContainingIgnoreCase(ime);
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SportniCenter> en(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SportniCenter dodaj(@RequestBody SportniCenter sc) {
        return repo.save(sc);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SportniCenter> posodobi(@PathVariable Long id, @RequestBody SportniCenter novi) {
        return repo.findById(id)
                .map(stari -> {
                    stari.setIme(novi.getIme());
                    stari.setNaslov(novi.getNaslov());
                    stari.setKontakt(novi.getKontakt());
                    stari.setKId(novi.getKId());
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