package com.magdalena.rezervacije.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("sportne_dvorane")
public class SportnaDvorana {

    @Id
    @Column("sd_id")
    private Long sdId;
    private String ime;
    private Integer kapaciteta;
    @Column("sc_id")
    private Long scId;

    // Getters & setters
    public Long getSdId() { return sdId; }
    public void setSdId(Long sdId) { this.sdId = sdId; }
    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }
    public Integer getKapaciteta() { return kapaciteta; }
    public void setKapaciteta(Integer kapaciteta) { this.kapaciteta = kapaciteta; }
    public Long getScId() { return scId; }
    public void setScId(Long scId) { this.scId = scId; }
}