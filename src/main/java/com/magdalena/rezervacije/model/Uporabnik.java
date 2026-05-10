package com.magdalena.rezervacije.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("uporabniki")
public class Uporabnik {

    @Id
    @Column("u_id")
    private Long uid; // Bolje je uporabljati uid namesto u_id v Javi

    private String ime;
    private String priimek;
    private String email;
    private String geslo;
    private Integer telefon;

    @Column("vrsta_uporabnika")
    private String vrstaUporabnika;

    // Getters & Setters
    public Long getUid() { return uid; }
    public void setUid(Long uid) { this.uid = uid; }

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

    public String getVrstaUporabnika() { return vrstaUporabnika; }
    public void setVrstaUporabnika(String vrstaUporabnika) { this.vrstaUporabnika = vrstaUporabnika; }
}