package com.magdalena.rezervacije.controller;

import com.magdalena.rezervacije.model.SportnaDvorana;
import com.magdalena.rezervacije.repository.SportnaDvoranaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/dvorane")
public class SportnaDvoranaController {

    private final SportnaDvoranaRepository repo;

    public SportnaDvoranaController(SportnaDvoranaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<SportnaDvorana> vse(@RequestParam(required = false) Long sc_id) {
        if (sc_id != null) return repo.findByScId(sc_id);
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SportnaDvorana> ena(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SportnaDvorana dodaj(@RequestBody SportnaDvorana sd) {
        return repo.save(sd);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SportnaDvorana> posodobi(@PathVariable Long id, @RequestBody SportnaDvorana novi) {
        return repo.findById(id)
                .map(stari -> {
                    stari.setIme(novi.getIme());
                    stari.setKapaciteta(novi.getKapaciteta());
                    stari.setScId(novi.getScId());
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