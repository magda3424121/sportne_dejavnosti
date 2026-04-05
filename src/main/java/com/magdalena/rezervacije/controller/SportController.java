package com.magdalena.rezervacije.controller;

import com.magdalena.rezervacije.model.Sport;
import com.magdalena.rezervacije.repository.SportRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sporti")
public class SportController {

    private final SportRepository repo;

    public SportController(SportRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<Sport> vsi(@RequestParam(required = false) Long sd_id) {
        if (sd_id != null) return repo.findBySdId(sd_id);
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sport> en(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sport dodaj(@RequestBody Sport sport) {
        return repo.save(sport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sport> posodobi(@PathVariable Long id, @RequestBody Sport novi) {
        return repo.findById(id)
                .map(stari -> {
                    stari.setIme(novi.getIme());
                    stari.setOpis(novi.getOpis());
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