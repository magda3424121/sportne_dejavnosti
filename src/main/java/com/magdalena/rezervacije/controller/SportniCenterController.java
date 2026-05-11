package com.magdalena.rezervacije.controller;

import com.magdalena.rezervacije.model.SportniCenter;
import com.magdalena.rezervacije.repository.SportniCenterRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sportni-centri")
public class SportniCenterController {

    private final SportniCenterRepository repo;

    public SportniCenterController(SportniCenterRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<SportniCenter> vsi() {
        return repo.findAllWithKraji();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SportniCenter> en(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
public SportniCenter dodaj(@RequestBody SportniCenter sc) {
    sc.setScId(null);
    return repo.save(sc);
}

    @PutMapping("/{id}")
    public ResponseEntity<SportniCenter> posodobi(@PathVariable Long id, @RequestBody SportniCenter novi) {
        return repo.findById(id)
                .map(stari -> {
                    stari.setIme(novi.getIme());
                    stari.setNaslov(novi.getNaslov());
                    stari.setKontakt(novi.getKontakt());
                    stari.setKid(novi.getKid());
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