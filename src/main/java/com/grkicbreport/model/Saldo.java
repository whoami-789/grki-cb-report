package com.grkicbreport.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "saldo")
public class Saldo {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;

    @Id
    @Column(name = "ls", nullable = false)
    private String ls;

    @Column(name = "dats", nullable = false)
    private LocalDate dats;

    @Column(name = "sums", nullable = false)
    private BigDecimal sums;

    @Column(name = "activate")
    private Byte activate;

    public Saldo(String ls, LocalDate dats, BigDecimal sums, Byte activate) {
        this.ls = ls;
        this.dats = dats;
        this.sums = sums;
        this.activate = activate;
    }

    public Saldo() {
    }

    public String getLs() {
        return this.ls;
    }

    public LocalDate getDats() {
        return this.dats;
    }

    public BigDecimal getSums() {
        return this.sums;
    }

    public Byte getActivate() {
        return this.activate;
    }

    public void setLs(String ls) {
        this.ls = ls;
    }

    public void setDats(LocalDate dats) {
        this.dats = dats;
    }

    public void setSums(BigDecimal sums) {
        this.sums = sums;
    }

    public void setActivate(Byte activate) {
        this.activate = activate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Saldo)) return false;
        final Saldo other = (Saldo) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$ls = this.getLs();
        final Object other$ls = other.getLs();
        if (this$ls == null ? other$ls != null : !this$ls.equals(other$ls)) return false;
        final Object this$dats = this.getDats();
        final Object other$dats = other.getDats();
        if (this$dats == null ? other$dats != null : !this$dats.equals(other$dats)) return false;
        final Object this$sums = this.getSums();
        final Object other$sums = other.getSums();
        if (this$sums == null ? other$sums != null : !this$sums.equals(other$sums)) return false;
        final Object this$activate = this.getActivate();
        final Object other$activate = other.getActivate();
        if (this$activate == null ? other$activate != null : !this$activate.equals(other$activate)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Saldo;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $ls = this.getLs();
        result = result * PRIME + ($ls == null ? 43 : $ls.hashCode());
        final Object $dats = this.getDats();
        result = result * PRIME + ($dats == null ? 43 : $dats.hashCode());
        final Object $sums = this.getSums();
        result = result * PRIME + ($sums == null ? 43 : $sums.hashCode());
        final Object $activate = this.getActivate();
        result = result * PRIME + ($activate == null ? 43 : $activate.hashCode());
        return result;
    }

    public String toString() {
        return "Saldo(ls=" + this.getLs() + ", dats=" + this.getDats() + ", sums=" + this.getSums() + ", activate=" + this.getActivate() + ")";
    }
}
