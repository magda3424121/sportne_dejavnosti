package com.magdalena.rezervacije.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("rezervacije")  // ime tabele v bazi
public class Rezervacija {

    @Id
    @Column("r_id")
    private Long rId;

    @Column("datum_rezervacije")
    private LocalDate datumRezervacije;

    @Column("u_id")
    private Long uId;   // uporabniški ID

    @Column("sd_id")
    private Long sdId;  // športna dvorana ID

    // -------- Getters & Setters --------
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

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public Long getSdId() {
        return sdId;
    }

    public void setSdId(Long sdId) {
        this.sdId = sdId;
    }
}