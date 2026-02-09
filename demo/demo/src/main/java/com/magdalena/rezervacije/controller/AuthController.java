package com.magdalena.rezervacije.controller;

import com.magdalena.rezervacije.model.Uporabnik;
import com.magdalena.rezervacije.repository.UporabnikRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UporabnikRepository uporabnikRepository;

    public AuthController(UporabnikRepository uporabnikRepository) {
        this.uporabnikRepository = uporabnikRepository;
    }

    // POST http://localhost:8080/auth/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {

        var uporabnikOpt = uporabnikRepository.findByEmail(req.email());

        if (uporabnikOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Napačen email ali geslo");
        }

        Uporabnik u = uporabnikOpt.get();

        if (!u.getGeslo().equals(req.geslo())) {
            return ResponseEntity.status(401).body("Napačen email ali geslo");
        }

        // vrnemo userja (lahko tudi brez gesla, spodaj dodam boljšo verzijo)
        return ResponseEntity.ok(u);
    }

    public record LoginRequest(String email, String geslo) {}
}
