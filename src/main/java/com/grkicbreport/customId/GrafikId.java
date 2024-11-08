package com.grkicbreport.customId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class GrafikId implements Serializable {
    private LocalDate dats;
    private BigDecimal pogKred;
    private BigDecimal pogProc;
    private BigDecimal ostatok;
    private Byte mes;
    private Byte sost;
    private String numdog;

    public GrafikId() {
    }

    public LocalDate getDats() {
        return this.dats;
    }

    public BigDecimal getPogKred() {
        return this.pogKred;
    }

    public BigDecimal getPogProc() {
        return this.pogProc;
    }

    public BigDecimal getOstatok() {
        return this.ostatok;
    }

    public Byte getMes() {
        return this.mes;
    }

    public Byte getSost() {
        return this.sost;
    }

    public String getNumdog() {
        return this.numdog;
    }

    public void setDats(LocalDate dats) {
        this.dats = dats;
    }

    public void setPogKred(BigDecimal pogKred) {
        this.pogKred = pogKred;
    }

    public void setPogProc(BigDecimal pogProc) {
        this.pogProc = pogProc;
    }

    public void setOstatok(BigDecimal ostatok) {
        this.ostatok = ostatok;
    }

    public void setMes(Byte mes) {
        this.mes = mes;
    }

    public void setSost(Byte sost) {
        this.sost = sost;
    }

    public void setNumdog(String numdog) {
        this.numdog = numdog;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof GrafikId)) return false;
        final GrafikId other = (GrafikId) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$dats = this.getDats();
        final Object other$dats = other.getDats();
        if (this$dats == null ? other$dats != null : !this$dats.equals(other$dats)) return false;
        final Object this$pogKred = this.getPogKred();
        final Object other$pogKred = other.getPogKred();
        if (this$pogKred == null ? other$pogKred != null : !this$pogKred.equals(other$pogKred)) return false;
        final Object this$pogProc = this.getPogProc();
        final Object other$pogProc = other.getPogProc();
        if (this$pogProc == null ? other$pogProc != null : !this$pogProc.equals(other$pogProc)) return false;
        final Object this$ostatok = this.getOstatok();
        final Object other$ostatok = other.getOstatok();
        if (this$ostatok == null ? other$ostatok != null : !this$ostatok.equals(other$ostatok)) return false;
        final Object this$mes = this.getMes();
        final Object other$mes = other.getMes();
        if (this$mes == null ? other$mes != null : !this$mes.equals(other$mes)) return false;
        final Object this$sost = this.getSost();
        final Object other$sost = other.getSost();
        if (this$sost == null ? other$sost != null : !this$sost.equals(other$sost)) return false;
        final Object this$numdog = this.getNumdog();
        final Object other$numdog = other.getNumdog();
        if (this$numdog == null ? other$numdog != null : !this$numdog.equals(other$numdog)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof GrafikId;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $dats = this.getDats();
        result = result * PRIME + ($dats == null ? 43 : $dats.hashCode());
        final Object $pogKred = this.getPogKred();
        result = result * PRIME + ($pogKred == null ? 43 : $pogKred.hashCode());
        final Object $pogProc = this.getPogProc();
        result = result * PRIME + ($pogProc == null ? 43 : $pogProc.hashCode());
        final Object $ostatok = this.getOstatok();
        result = result * PRIME + ($ostatok == null ? 43 : $ostatok.hashCode());
        final Object $mes = this.getMes();
        result = result * PRIME + ($mes == null ? 43 : $mes.hashCode());
        final Object $sost = this.getSost();
        result = result * PRIME + ($sost == null ? 43 : $sost.hashCode());
        final Object $numdog = this.getNumdog();
        result = result * PRIME + ($numdog == null ? 43 : $numdog.hashCode());
        return result;
    }

    public String toString() {
        return "GrafikId(dats=" + this.getDats() + ", pogKred=" + this.getPogKred() + ", pogProc=" + this.getPogProc() + ", ostatok=" + this.getOstatok() + ", mes=" + this.getMes() + ", sost=" + this.getSost() + ", numdog=" + this.getNumdog() + ")";
    }
}

