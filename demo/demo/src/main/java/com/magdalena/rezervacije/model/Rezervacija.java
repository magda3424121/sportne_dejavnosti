package com.magdalena.rezervacije.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("rezervacije")
public class Rezervacija {

    @Id
    private Long rId;

    private LocalDate datumRezervacije;
    private Long uId;
    private Long sdId;

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
