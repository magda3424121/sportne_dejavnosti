package com.magdalena.rezervacije.controller;

import com.magdalena.rezervacije.dto.UporabnikResponse;
import com.magdalena.rezervacije.model.Uporabnik;
import com.magdalena.rezervacije.repository.UporabnikRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.StreamSupport;
import java.util.List;

@RestController
@RequestMapping("/uporabniki")
public class UporabnikController {

    private final UporabnikRepository repo;

    public UporabnikController(UporabnikRepository repo) { this.repo = repo; }

    @GetMapping
    public List<UporabnikResponse> vsi() {
        return StreamSupport.stream(repo.findAll().spliterator(), false)
                .map(this::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UporabnikResponse> en(@PathVariable Long id) {
        return repo.findById(id)
                .map(u -> ResponseEntity.ok(toDto(u)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UporabnikResponse dodaj(@RequestBody Uporabnik u) {
        return toDto(repo.save(u));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UporabnikResponse> posodobi(@PathVariable Long id, @RequestBody Uporabnik u) {
        return repo.findById(id)
                .map(stari -> {
                    stari.setIme(u.getIme());
                    stari.setPriimek(u.getPriimek());
                    stari.setEmail(u.getEmail());
                    stari.setGeslo(u.getGeslo());
                    stari.setTelefon(u.getTelefon());
                    stari.setVrsta_uporabnika(u.getVrsta_uporabnika());
                    return ResponseEntity.ok(toDto(repo.save(stari)));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> brisi(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private UporabnikResponse toDto(Uporabnik u) {
        return new UporabnikResponse(
                u.getU_id(), u.getIme(), u.getPriimek(), u.getEmail(),
                u.getTelefon(), u.getVrsta_uporabnika()
        );
    }
}