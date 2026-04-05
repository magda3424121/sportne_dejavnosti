package com.magdalena.rezervacije.controller;

import com.magdalena.rezervacije.model.Uporabnik;
import com.magdalena.rezervacije.repository.UporabnikRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UporabnikRepository uporabnikRepository;

    public AuthController(UporabnikRepository uporabnikRepository) {
        this.uporabnikRepository = uporabnikRepository;
    }

    public static class LoginRequest {
        public String email;
        public String geslo;
    }

    public static class RegisterRequest {
        public String ime;
        public String priimek;
        public String email;
        public String geslo;
        public Integer telefon;
        public String vrsta_uporabnika;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        if (req == null || req.email == null || req.geslo == null) {
            return ResponseEntity.badRequest().body("Manjka email ali geslo.");
        }

        return uporabnikRepository.findByEmail(req.email)
                .map(u -> {
                    if (!req.geslo.equals(u.getGeslo())) {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Napačno geslo.");
                    }
                    return ResponseEntity.ok(u);
                })
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Uporabnik ne obstaja."));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (req == null || req.email == null || req.geslo == null || req.ime == null) {
            return ResponseEntity.badRequest().body("Manjkajo obvezna polja (ime, email, geslo).");
        }

        if (uporabnikRepository.existsByEmail(req.email)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email je že v uporabi.");
        }

        Uporabnik u = new Uporabnik();
        u.setIme(req.ime);
        u.setPriimek(req.priimek);
        u.setEmail(req.email);
        u.setGeslo(req.geslo);
        u.setTelefon(req.telefon);
        u.setVrsta_uporabnika(req.vrsta_uporabnika != null ? req.vrsta_uporabnika : "stranka");

        return ResponseEntity.status(HttpStatus.CREATED).body(uporabnikRepository.save(u));
    }
}