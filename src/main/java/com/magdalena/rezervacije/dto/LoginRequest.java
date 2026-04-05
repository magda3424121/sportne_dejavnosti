package com.magdalena.rezervacije.dto;

public class LoginRequest {
    private String email;
    private String geslo;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGeslo() { return geslo; }
    public void setGeslo(String geslo) { this.geslo = geslo; }
}