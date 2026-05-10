package com.magdalena.rezervacije.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("sporti")
public class Sport {

    @Id
    @Column("s_id")
    private Long sId;
    private String ime;
    private String opis;
    @Column("sd_id")
    private Long sdId;

    // Getters & setters
    public Long getSId() { return sId; }
    public void setSId(Long sId) { this.sId = sId; }
    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }
    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }
    public Long getSdId() { return sdId; }
    public void setSdId(Long sdId) { this.sdId = sdId; }
}