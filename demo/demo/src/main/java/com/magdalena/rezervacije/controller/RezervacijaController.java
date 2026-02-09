package com.magdalena.rezervacije.controller;

import com.magdalena.rezervacije.model.Rezervacija;
import com.magdalena.rezervacije.repository.RezervacijaRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rezervacije")
public class RezervacijaController {

    private final RezervacijaRepository rezervacijaRepository;

    public RezervacijaController(RezervacijaRepository rezervacijaRepository) {
        this.rezervacijaRepository = rezervacijaRepository;
    }

    // GET http://localhost:8080/rezervacije
    @GetMapping
    public Iterable<Rezervacija> vse() {
        return rezervacijaRepository.findAll();
    }

    // GET http://localhost:8080/rezervacije/{id}
    @GetMapping("/{id}")
    public Rezervacija ena(@PathVariable Long id) {
        return rezervacijaRepository.findById(id).orElse(null);
    }

    // POST http://localhost:8080/rezervacije
    @PostMapping
    public Rezervacija dodaj(@RequestBody Rezervacija r) {
        return rezervacijaRepository.save(r);
    }
}
