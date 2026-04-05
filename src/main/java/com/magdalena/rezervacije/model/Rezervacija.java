package com.magdalena.rezervacije.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("rezervacije")
public class Rezervacija {

    @Id
    @Column("r_id")
    private Long rId;

    @Column("datum_rezervacije")
    private LocalDate datumRezervacije;

    @Column("u_id")
    private Long uId; // ID uporabnika

    @Column("sd_id")
    private Long sdId; // ID dvorane

    // --- Getters in Setters ---

    public Long getRId() {
        return rId;
    }

    public void setRId(Long rId) {
        this.rId = rId;
    }

    public LocalDate getDatumRezervacije() {
        return datumRezervacije;
    }

    public void setDatumRezervacije(LocalDate datumRezervacije) {
        this.datumRezervacije = datumRezervacije;
    }

    public Long getUId() {
        return uId;
    }

    public void setUId(Long uId) {
        this.uId = uId;
    }

    public Long getSdId() {
        return sdId;
    }

    public void setSdId(Long sdId) {
        this.sdId = sdId;
    }

}