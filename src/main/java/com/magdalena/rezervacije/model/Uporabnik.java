package com.magdalena.rezervacije.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("uporabniki")
public class Uporabnik {

    @Id
    private Long u_id;
    private String ime;
    private String priimek;
    private String email;
    private String geslo;
    private Integer telefon;
    private String vrsta_uporabnika; // "USER" ali "ADMIN"

    // Getters & setters
    public Long getU_id() { return u_id; }
    public void setU_id(Long u_id) { this.u_id = u_id; }
    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }
    public String getPriimek() { return priimek; }
    public void setPriimek(String priimek) { this.priimek = priimek; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getGeslo() { return geslo; }
    public void setGeslo(String geslo) { this.geslo = geslo; }
    public Integer getTelefon() { return telefon; }
    public void setTelefon(Integer telefon) { this.telefon = telefon; }
    public String getVrsta_uporabnika() { return vrsta_uporabnika; }
    public void setVrsta_uporabnika(String vrsta_uporabnika) { this.vrsta_uporabnika = vrsta_uporabnika; }
}