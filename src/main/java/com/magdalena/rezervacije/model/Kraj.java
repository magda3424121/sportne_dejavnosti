package com.magdalena.rezervacije.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("kraji")
public class Kraj {
    @Id
    @Column("k_id")
    private Long kId;
    
    private String ime;
    
    @Column("postna_st")
    private Integer postnaStevilka;

    public Kraj() {}

    // Getterji in Setterji
    public Long getKId() { return kId; }
    public void setKId(Long kId) { this.kId = kId; }
    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }
    public Integer getPostnaStevilka() { return postnaStevilka; }
    public void setPostnaStevilka(Integer postnaStevilka) { this.postnaStevilka = postnaStevilka; }
}