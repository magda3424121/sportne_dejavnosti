package com.magdalena.rezervacije.controller;

import com.magdalena.rezervacije.model.SportnaDvorana;
import com.magdalena.rezervacije.repository.SportnaDvoranaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sportne-dvorane")
public class SportnaDvoranaController {

    private final SportnaDvoranaRepository repository;

    public SportnaDvoranaController(SportnaDvoranaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<SportnaDvorana> vse() {
        return repository.findAllWithNames();
    }

    @GetMapping("/{id}")
public ResponseEntity<SportnaDvorana> en(@PathVariable Long id) {
    return repository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}
    @PostMapping
    public SportnaDvorana dodaj(@RequestBody SportnaDvorana nova) {
        return repository.save(nova);
    }

    // TA METODA PREPREČI 404 NAPAKO PRI UREJANJU
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