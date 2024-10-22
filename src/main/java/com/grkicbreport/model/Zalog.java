package com.grkicbreport.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "zalog")
public class Zalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kod", nullable = false)
    private Byte kod;

    @Column(name = "sums")
    private BigDecimal sums;

    @Column(name = "prim")
    private String prim;

    @Column(name = "dtime")
    private Date dtime;

    @Column(name = "numdog", nullable = false, columnDefinition = "char(12)")
    private String numdog;

    @Column(name = "sost")
    private Byte sost;

    @Column(name = "kod_zalog")
    private Integer kodZalog;

    @Column(name = "dats")
    private LocalDate dats;

    @Column(name = "dats_snyat")
    private LocalDate datsSnyat;

    @Column(name = "ls", nullable = false, columnDefinition = "nchar(20)")
    private String ls;

    @Column(name = "kod_cb")
    private String kodCb;

    @Column(name = "dopinfo", columnDefinition = "TEXT")
    private String dopinfo;

    @ManyToOne
    @JoinColumn(name = "numdog", referencedColumnName = "numdog", insertable = false, updatable = false)
    private Kredit kredit;

    public Zalog(Byte kod, BigDecimal sums, String prim, Date dtime, String numdog, Byte sost, Integer kodZalog, LocalDate dats, LocalDate datsSnyat, String ls, String kodCb, String dopinfo, Kredit kredit) {
        this.kod = kod;
        this.sums = sums;
        this.prim = prim;
        this.dtime = dtime;
        this.numdog = numdog;
        this.sost = sost;
        this.kodZalog = kodZalog;
        this.dats = dats;
        this.datsSnyat = datsSnyat;
        this.ls = ls;
        this.kodCb = kodCb;
        this.dopinfo = dopinfo;
        this.kredit = kredit;
    }

    public Zalog() {
    }

    public Byte getKod() {
        return this.kod;
    }

    public BigDecimal getSums() {
        return this.sums;
    }

    public String getPrim() {
        return this.prim;
    }

    public Date getDtime() {
        return this.dtime;
    }

    public String getNumdog() {
        return this.numdog;
    }

    public Byte getSost() {
        return this.sost;
    }

    public Integer getKodZalog() {
        return this.kodZalog;
    }

    public LocalDate getDats() {
        return this.dats;
    }

    public LocalDate getDatsSnyat() {
        return this.datsSnyat;
    }

    public String getLs() {
        return this.ls;
    }

    public String getKodCb() {
        return this.kodCb;
    }

    public String getDopinfo() {
        return this.dopinfo;
    }

    public Kredit getKredit() {
        return this.kredit;
    }

    public void setKod(Byte kod) {
        this.kod = kod;
    }

    public void setSums(BigDecimal sums) {
        this.sums = sums;
    }

    public void setPrim(String prim) {
        this.prim = prim;
    }

    public void setDtime(Date dtime) {
        this.dtime = dtime;
    }

    public void setNumdog(String numdog) {
        this.numdog = numdog;
    }

    public void setSost(Byte sost) {
        this.sost = sost;
    }

    public void setKodZalog(Integer kodZalog) {
        this.kodZalog = kodZalog;
    }

    public void setDats(LocalDate dats) {
        this.dats = dats;
    }

    public void setDatsSnyat(LocalDate datsSnyat) {
        this.datsSnyat = datsSnyat;
    }

    public void setLs(String ls) {
        this.ls = ls;
    }

    public void setKodCb(String kodCb) {
        this.kodCb = kodCb;
    }

    public void setDopinfo(String dopinfo) {
        this.dopinfo = dopinfo;
    }

    public void setKredit(Kredit kredit) {
        this.kredit = kredit;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Zalog)) return false;
        final Zalog other = (Zalog) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$kod = this.getKod();
        final Object other$kod = other.getKod();
        if (this$kod == null ? other$kod != null : !this$kod.equals(other$kod)) return false;
        final Object this$sums = this.getSums();
        final Object other$sums = other.getSums();
        if (this$sums == null ? other$sums != null : !this$sums.equals(other$sums)) return false;
        final Object this$prim = this.getPrim();
        final Object other$prim = other.getPrim();
        if (this$prim == null ? other$prim != null : !this$prim.equals(other$prim)) return false;
        final Object this$dtime = this.getDtime();
        final Object other$dtime = other.getDtime();
        if (this$dtime == null ? other$dtime != null : !this$dtime.equals(other$dtime)) return false;
        final Object this$numdog = this.getNumdog();
        final Object other$numdog = other.getNumdog();
        if (this$numdog == null ? other$numdog != null : !this$numdog.equals(other$numdog)) return false;
        final Object this$sost = this.getSost();
        final Object other$sost = other.getSost();
        if (this$sost == null ? other$sost != null : !this$sost.equals(other$sost)) return false;
        final Object this$kodZalog = this.getKodZalog();
        final Object other$kodZalog = other.getKodZalog();
        if (this$kodZalog == null ? other$kodZalog != null : !this$kodZalog.equals(other$kodZalog)) return false;
        final Object this$dats = this.getDats();
        final Object other$dats = other.getDats();
        if (this$dats == null ? other$dats != null : !this$dats.equals(other$dats)) return false;
        final Object this$datsSnyat = this.getDatsSnyat();
        final Object other$datsSnyat = other.getDatsSnyat();
        if (this$datsSnyat == null ? other$datsSnyat != null : !this$datsSnyat.equals(other$datsSnyat)) return false;
        final Object this$ls = this.getLs();
        final Object other$ls = other.getLs();
        if (this$ls == null ? other$ls != null : !this$ls.equals(other$ls)) return false;
        final Object this$kodCb = this.getKodCb();
        final Object other$kodCb = other.getKodCb();
        if (this$kodCb == null ? other$kodCb != null : !this$kodCb.equals(other$kodCb)) return false;
        final Object this$dopinfo = this.getDopinfo();
        final Object other$dopinfo = other.getDopinfo();
        if (this$dopinfo == null ? other$dopinfo != null : !this$dopinfo.equals(other$dopinfo)) return false;
        final Object this$kredit = this.getKredit();
        final Object other$kredit = other.getKredit();
        if (this$kredit == null ? other$kredit != null : !this$kredit.equals(other$kredit)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Zalog;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $kod = this.getKod();
        result = result * PRIME + ($kod == null ? 43 : $kod.hashCode());
        final Object $sums = this.getSums();
        result = result * PRIME + ($sums == null ? 43 : $sums.hashCode());
        final Object $prim = this.getPrim();
        result = result * PRIME + ($prim == null ? 43 : $prim.hashCode());
        final Object $dtime = this.getDtime();
        result = result * PRIME + ($dtime == null ? 43 : $dtime.hashCode());
        final Object $numdog = this.getNumdog();
        result = result * PRIME + ($numdog == null ? 43 : $numdog.hashCode());
        final Object $sost = this.getSost();
        result = result * PRIME + ($sost == null ? 43 : $sost.hashCode());
        final Object $kodZalog = this.getKodZalog();
        result = result * PRIME + ($kodZalog == null ? 43 : $kodZalog.hashCode());
        final Object $dats = this.getDats();
        result = result * PRIME + ($dats == null ? 43 : $dats.hashCode());
        final Object $datsSnyat = this.getDatsSnyat();
        result = result * PRIME + ($datsSnyat == null ? 43 : $datsSnyat.hashCode());
        final Object $ls = this.getLs();
        result = result * PRIME + ($ls == null ? 43 : $ls.hashCode());
        final Object $kodCb = this.getKodCb();
        result = result * PRIME + ($kodCb == null ? 43 : $kodCb.hashCode());
        final Object $dopinfo = this.getDopinfo();
        result = result * PRIME + ($dopinfo == null ? 43 : $dopinfo.hashCode());
        final Object $kredit = this.getKredit();
        result = result * PRIME + ($kredit == null ? 43 : $kredit.hashCode());
        return result;
    }

    public String toString() {
        return "Zalog(kod=" + this.getKod() + ", sums=" + this.getSums() + ", prim=" + this.getPrim() + ", dtime=" + this.getDtime() + ", numdog=" + this.getNumdog() + ", sost=" + this.getSost() + ", kodZalog=" + this.getKodZalog() + ", dats=" + this.getDats() + ", datsSnyat=" + this.getDatsSnyat() + ", ls=" + this.getLs() + ", kodCb=" + this.getKodCb() + ", dopinfo=" + this.getDopinfo() + ", kredit=" + this.getKredit() + ")";
    }
}
