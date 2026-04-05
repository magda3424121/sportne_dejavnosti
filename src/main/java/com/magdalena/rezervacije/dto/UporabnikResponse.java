package com.magdalena.rezervacije.dto;

public record UporabnikResponse(
        Long uId,
        String ime,
        String priimek,
        String email,
        Integer telefon,
        String vrstaUporabnika
) {}