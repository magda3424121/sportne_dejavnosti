package com.magdalena.rezervacije.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Transient;

@Table("sportne_dvorane")
public class SportnaDvorana {
    @Id
    @Column("sd_id")
    private Long sdId;
    
    private String ime;
    private Integer kapaciteta;
    
    @Column("sc_id")
    private Long scId;

   
    private String sporti; 

    @Transient
    private String imeCentra; 

    @Transient
    private String naslovCentra; 
    @Transient
    private String kontaktCentra; 

    public SportnaDvorana() {}

    // Getterji in Setterji
    public Long getSdId() { return sdId; }
    public void setSdId(Long sdId) { this.sdId = sdId; }

    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }

    public Integer getKapaciteta() { return kapaciteta; }
    public void setKapaciteta(Integer kapaciteta) { this.kapaciteta = kapaciteta; }

    public Long getScId() { return scId; }
    public void setScId(Long scId) { this.scId = scId; }

    public String getSporti() { return sporti; }
    public void setSporti(String sporti) { this.sporti = sporti; }

    public String getImeCentra() { return imeCentra; }
    public void setImeCentra(String imeCentra) { this.imeCentra = imeCentra; }

    public String getNaslovCentra() { return naslovCentra; }
    public void setNaslovCentra(String naslovCentra) { this.naslovCentra = naslovCentra; }

    public String getKontaktCentra() { return kontaktCentra; }
    public void setKontaktCentra(String kontaktCentra) { this.kontaktCentra = kontaktCentra; }
}