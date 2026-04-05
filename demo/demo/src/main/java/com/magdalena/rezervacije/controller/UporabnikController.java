package com.magdalena.rezervacije.controller;
import java.util.stream.StreamSupport;
import java.util.List;
import com.magdalena.rezervacije.dto.UporabnikResponse;
import com.magdalena.rezervacije.model.Uporabnik;
import com.magdalena.rezervacije.repository.UporabnikRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import java.util.List;

@RestController
@RequestMapping("/uporabniki")
public class UporabnikController {

    private final UporabnikRepository uporabnikRepository;

    public UporabnikController(UporabnikRepository uporabnikRepository) {
        this.uporabnikRepository = uporabnikRepository;
    }

    @GetMapping
    public List<UporabnikResponse> vsi() {
        
                 return StreamSupport.stream(uporabnikRepository.findAll().spliterator(), false)
            .map(this::toDto)
            .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UporabnikResponse> en(@PathVariable Long id) {
        return uporabnikRepository.findById(id)
                .map(u -> ResponseEntity.ok(toDto(u)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UporabnikResponse dodaj(@RequestBody Uporabnik u) {
        // če nočeš dovoliti da se geslo vrača nazaj, vrni DTO
        Uporabnik saved = uporabnikRepository.save(u);
        return toDto(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UporabnikResponse> posodobi(@PathVariable Long id, @RequestBody Uporabnik u) {

        return uporabnikRepository.findById(id)
                .map(stari -> {
                    stari.setIme(u.getIme());
                    stari.setPriimek(u.getPriimek());
                    stari.setEmail(u.getEmail());
                    stari.setGeslo(u.getGeslo());
                    stari.setTelefon(u.getTelefon());
                    // ⚠️ tukaj popravi getter/setter ime glede na tvoj model:
                    stari.setVrsta_uporabnika(u.getVrsta_uporabnika());

                    Uporabnik updated = uporabnikRepository.save(stari);
                    return ResponseEntity.ok(toDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> brisi(@PathVariable Long id) {
        if (!uporabnikRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        uporabnikRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private UporabnikResponse toDto(Uporabnik u) {
    return new UporabnikResponse(
            u.getU_id(),
            u.getIme(),
            u.getPriimek(),
            u.getEmail(),
            u.getTelefon(),
            u.getVrsta_uporabnika()
    );
}
}