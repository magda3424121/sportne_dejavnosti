package com.magdalena.rezervacije.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("sportni_centri")
public class SportniCenter {

    @Id
    @Column("sc_id")
    private Long scId;
    private String ime;
    private String naslov;
    private Integer kontakt;
    @Column("k_id")
    private Long kId;

    // Getters & setters
    public Long getScId() { return scId; }
    public void setScId(Long scId) { this.scId = scId; }
    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }
    public String getNaslov() { return naslov; }
    public void setNaslov(String naslov) { this.naslov = naslov; }
    public Integer getKontakt() { return kontakt; }
    public void setKontakt(Integer kontakt) { this.kontakt = kontakt; }
    public Long getKId() { return kId; }
    public void setKId(Long kId) { this.kId = kId; }
}