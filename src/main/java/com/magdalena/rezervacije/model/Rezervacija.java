package com.magdalena.rezervacije.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;
import org.springframework.data.annotation.Transient;

@Table("rezervacije")
public class Rezervacija {
    @Id
    @Column("r_id")
    private Long rId;

    @Column("datum_rezervacije")
    private LocalDate datumRezervacije;

    @Column("u_id")
    private Long uId;

    @Column("sd_id")
    private Long sdId;

   
    @Transient
    private String imeUporabnika;
    
    @Transient
    private String imeDvorane;

    
    public String getImeUporabnika() { return imeUporabnika; }
    public void setImeUporabnika(String imeUporabnika) { this.imeUporabnika = imeUporabnika; }

    public String getImeDvorane() { return imeDvorane; }
    public void setImeDvorane(String imeDvorane) { this.imeDvorane = imeDvorane; }

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