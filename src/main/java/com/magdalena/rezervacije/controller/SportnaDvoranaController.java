package com.magdalena.rezervacije.controller;

import com.magdalena.rezervacije.model.SportnaDvorana;
import com.magdalena.rezervacije.repository.SportnaDvoranaRepository;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sportne-dvorane")
public class SportnaDvoranaController {

    private final SportnaDvoranaRepository repo;

    public SportnaDvoranaController(SportnaDvoranaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
public List<SportnaDvorana> vse() {
    return repo.findAllWithNames();
}
}