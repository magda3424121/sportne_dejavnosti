package com.magdalena.rezervacije.controller;
import org.springframework.http.ResponseEntity;
import com.magdalena.rezervacije.model.Uporabnik;
import com.magdalena.rezervacije.repository.UporabnikRepository;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/uporabniki")
public class UporabnikController {

    private final UporabnikRepository uporabnikRepository;

    public UporabnikController(UporabnikRepository uporabnikRepository) {
        this.uporabnikRepository = uporabnikRepository;
    }

    
    @GetMapping
    public Iterable<Uporabnik> vsi() {
        return uporabnikRepository.findAll();
    }

    
    @GetMapping("/{id}")
public ResponseEntity<Uporabnik> en(@PathVariable Long id) {
    return uporabnikRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}


    
    @PostMapping
    public Uporabnik dodaj(@RequestBody Uporabnik u) {
        return uporabnikRepository.save(u);
    }

    @PutMapping("/{id}")
public ResponseEntity<Uporabnik> posodobi(@PathVariable Long id, @RequestBody Uporabnik u) {

    return uporabnikRepository.findById(id)
            .map(stari -> {
                stari.setIme(u.getIme());
                stari.setPriimek(u.getPriimek());
                stari.setEmail(u.getEmail());
                stari.setGeslo(u.getGeslo());
                stari.setTelefon(u.getTelefon());
                stari.setVrsta_uporabnika(u.getVrsta_uporabnika());
                return ResponseEntity.ok(uporabnikRepository.save(stari));
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

}


