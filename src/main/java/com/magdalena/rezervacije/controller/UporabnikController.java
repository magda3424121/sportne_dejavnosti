package com.magdalena.rezervacije.controller;

import com.magdalena.rezervacije.model.Uporabnik;
import com.magdalena.rezervacije.repository.UporabnikRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/uporabniki")
public class UporabnikController {

    private final UporabnikRepository repo;

    public UporabnikController(UporabnikRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Uporabnik> vsi(@RequestParam(required = false) String email) {
        List<Uporabnik> vsi = StreamSupport.stream(repo.findAll().spliterator(), false)
                .collect(Collectors.toList());
        
        if (email != null) {
            return vsi.stream()
                    .filter(u -> u.getEmail().equalsIgnoreCase(email))
                    .collect(Collectors.toList());
        }
        return vsi;
    }

    @PostMapping
public Uporabnik registracija(@RequestBody Uporabnik u) {
    // Zdaj bo getVrstaUporabnika() prepoznan!
    if (u.getVrstaUporabnika() == null || u.getVrstaUporabnika().isEmpty()) {
        u.setVrstaUporabnika("uporabnik");
    }
    return repo.save(u);
}
}