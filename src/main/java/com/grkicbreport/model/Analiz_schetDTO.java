package com.grkicbreport.model;

import java.math.BigDecimal;

public class Analiz_schetDTO {
    private String bal;
    private String namer;
    private BigDecimal deb;
    private BigDecimal kred;

    public Analiz_schetDTO() {
    }

    public String getBal() {
        return this.bal;
    }

    public String getNamer() {
        return this.namer;
    }

    public BigDecimal getDeb() {
        return this.deb;
    }

    public BigDecimal getKred() {
        return this.kred;
    }

    public void setBal(String bal) {
        this.bal = bal;
    }

    public void setNamer(String namer) {
        this.namer = namer;
    }

    public void setDeb(BigDecimal deb) {
        this.deb = deb;
    }

    public void setKred(BigDecimal kred) {
        this.kred = kred;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Analiz_schetDTO)) return false;
        final Analiz_schetDTO other = (Analiz_schetDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$bal = this.getBal();
        final Object other$bal = other.getBal();
        if (this$bal == null ? other$bal != null : !this$bal.equals(other$bal)) return false;
        final Object this$namer = this.getNamer();
        final Object other$namer = other.getNamer();
        if (this$namer == null ? other$namer != null : !this$namer.equals(other$namer)) return false;
        final Object this$deb = this.getDeb();
        final Object other$deb = other.getDeb();
        if (this$deb == null ? other$deb != null : !this$deb.equals(other$deb)) return false;
        final Object this$kred = this.getKred();
        final Object other$kred = other.getKred();
        if (this$kred == null ? other$kred != null : !this$kred.equals(other$kred)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Analiz_schetDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $bal = this.getBal();
        result = result * PRIME + ($bal == null ? 43 : $bal.hashCode());
        final Object $namer = this.getNamer();
        result = result * PRIME + ($namer == null ? 43 : $namer.hashCode());
        final Object $deb = this.getDeb();
        result = result * PRIME + ($deb == null ? 43 : $deb.hashCode());
        final Object $kred = this.getKred();
        result = result * PRIME + ($kred == null ? 43 : $kred.hashCode());
        return result;
    }

    public String toString() {
        return "Analiz_schetDTO(bal=" + this.getBal() + ", namer=" + this.getNamer() + ", deb=" + this.getDeb() + ", kred=" + this.getKred() + ")";
    }
}
