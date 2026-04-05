package com.magdalena.rezervacije.dto;

public class AuthResponse {
    private Long uId;
    private String email;
    private String vrstaUporabnika;

    public AuthResponse(Long uId, String email, String vrstaUporabnika) {
        this.uId = uId;
        this.email = email;
        this.vrstaUporabnika = vrstaUporabnika;
    }

    public Long getUId() { return uId; }
    public String getEmail() { return email; }
    public String getVrstaUporabnika() { return vrstaUporabnika; }
}