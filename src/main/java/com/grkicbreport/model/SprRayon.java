package com.grkicbreport.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "spr_rayon")
public class SprRayon {

    @Id
    private String kod;  // nchar(3)

    private String kodcb;  // nchar(2)
    private String namer;  // varchar(30)
    private String dopol;  // nchar(2)
    private Date datsaktiv;  // date
    private Date datsdeaktiv;  // date
    private String sost;  // nchar(10)
    private String nameu;  // varchar(30)

    public SprRayon() {
    }

    public String getKod() {
        return this.kod;
    }

    public String getKodcb() {
        return this.kodcb;
    }

    public String getNamer() {
        return this.namer;
    }

    public String getDopol() {
        return this.dopol;
    }

    public Date getDatsaktiv() {
        return this.datsaktiv;
    }

    public Date getDatsdeaktiv() {
        return this.datsdeaktiv;
    }

    public String getSost() {
        return this.sost;
    }

    public String getNameu() {
        return this.nameu;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public void setKodcb(String kodcb) {
        this.kodcb = kodcb;
    }

    public void setNamer(String namer) {
        this.namer = namer;
    }

    public void setDopol(String dopol) {
        this.dopol = dopol;
    }

    public void setDatsaktiv(Date datsaktiv) {
        this.datsaktiv = datsaktiv;
    }

    public void setDatsdeaktiv(Date datsdeaktiv) {
        this.datsdeaktiv = datsdeaktiv;
    }

    public void setSost(String sost) {
        this.sost = sost;
    }

    public void setNameu(String nameu) {
        this.nameu = nameu;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SprRayon)) return false;
        final SprRayon other = (SprRayon) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$kod = this.getKod();
        final Object other$kod = other.getKod();
        if (this$kod == null ? other$kod != null : !this$kod.equals(other$kod)) return false;
        final Object this$kodcb = this.getKodcb();
        final Object other$kodcb = other.getKodcb();
        if (this$kodcb == null ? other$kodcb != null : !this$kodcb.equals(other$kodcb)) return false;
        final Object this$namer = this.getNamer();
        final Object other$namer = other.getNamer();
        if (this$namer == null ? other$namer != null : !this$namer.equals(other$namer)) return false;
        final Object this$dopol = this.getDopol();
        final Object other$dopol = other.getDopol();
        if (this$dopol == null ? other$dopol != null : !this$dopol.equals(other$dopol)) return false;
        final Object this$datsaktiv = this.getDatsaktiv();
        final Object other$datsaktiv = other.getDatsaktiv();
        if (this$datsaktiv == null ? other$datsaktiv != null : !this$datsaktiv.equals(other$datsaktiv)) return false;
        final Object this$datsdeaktiv = this.getDatsdeaktiv();
        final Object other$datsdeaktiv = other.getDatsdeaktiv();
        if (this$datsdeaktiv == null ? other$datsdeaktiv != null : !this$datsdeaktiv.equals(other$datsdeaktiv))
            return false;
        final Object this$sost = this.getSost();
        final Object other$sost = other.getSost();
        if (this$sost == null ? other$sost != null : !this$sost.equals(other$sost)) return false;
        final Object this$nameu = this.getNameu();
        final Object other$nameu = other.getNameu();
        if (this$nameu == null ? other$nameu != null : !this$nameu.equals(other$nameu)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SprRayon;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $kod = this.getKod();
        result = result * PRIME + ($kod == null ? 43 : $kod.hashCode());
        final Object $kodcb = this.getKodcb();
        result = result * PRIME + ($kodcb == null ? 43 : $kodcb.hashCode());
        final Object $namer = this.getNamer();
        result = result * PRIME + ($namer == null ? 43 : $namer.hashCode());
        final Object $dopol = this.getDopol();
        result = result * PRIME + ($dopol == null ? 43 : $dopol.hashCode());
        final Object $datsaktiv = this.getDatsaktiv();
        result = result * PRIME + ($datsaktiv == null ? 43 : $datsaktiv.hashCode());
        final Object $datsdeaktiv = this.getDatsdeaktiv();
        result = result * PRIME + ($datsdeaktiv == null ? 43 : $datsdeaktiv.hashCode());
        final Object $sost = this.getSost();
        result = result * PRIME + ($sost == null ? 43 : $sost.hashCode());
        final Object $nameu = this.getNameu();
        result = result * PRIME + ($nameu == null ? 43 : $nameu.hashCode());
        return result;
    }

    public String toString() {
        return "SprRayon(kod=" + this.getKod() + ", kodcb=" + this.getKodcb() + ", namer=" + this.getNamer() + ", dopol=" + this.getDopol() + ", datsaktiv=" + this.getDatsaktiv() + ", datsdeaktiv=" + this.getDatsdeaktiv() + ", sost=" + this.getSost() + ", nameu=" + this.getNameu() + ")";
    }
}
