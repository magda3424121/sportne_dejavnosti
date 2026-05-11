package com.magdalena.rezervacije.controller;

import com.magdalena.rezervacije.model.Kraj;
import com.magdalena.rezervacije.repository.KrajRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kraji")
public class KrajiController {

    private final KrajRepository repo;

    public KrajiController(KrajRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<Kraj> vsi() {
        return repo.findAll(); // Zdaj bi moralo delovati brez napake!
    }
}