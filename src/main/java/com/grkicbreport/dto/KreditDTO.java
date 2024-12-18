package com.grkicbreport.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class KreditDTO {

    private String kod;
    private String numdog;
    private LocalDate datadog;
    private BigDecimal summa;
    private String grkiClaimId;
    private String grkiContractId;
    private LocalDate dats_zakr;

    public KreditDTO() {
    }

    public String getKod() {
        return this.kod;
    }

    public String getNumdog() {
        return this.numdog;
    }

    public LocalDate getDatadog() {
        return this.datadog;
    }

    public BigDecimal getSumma() {
        return this.summa;
    }

    public String getGrkiClaimId() {
        return this.grkiClaimId;
    }

    public String getGrkiContractId() {
        return this.grkiContractId;
    }

    public LocalDate getDats_zakr() {
        return this.dats_zakr;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public void setNumdog(String numdog) {
        this.numdog = numdog;
    }

    public void setDatadog(LocalDate datadog) {
        this.datadog = datadog;
    }

    public void setSumma(BigDecimal summa) {
        this.summa = summa;
    }

    public void setGrkiClaimId(String grkiClaimId) {
        this.grkiClaimId = grkiClaimId;
    }

    public void setGrkiContractId(String grkiContractId) {
        this.grkiContractId = grkiContractId;
    }

    public void setDats_zakr(LocalDate dats_zakr) {
        this.dats_zakr = dats_zakr;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof KreditDTO)) return false;
        final KreditDTO other = (KreditDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$kod = this.getKod();
        final Object other$kod = other.getKod();
        if (this$kod == null ? other$kod != null : !this$kod.equals(other$kod)) return false;
        final Object this$numdog = this.getNumdog();
        final Object other$numdog = other.getNumdog();
        if (this$numdog == null ? other$numdog != null : !this$numdog.equals(other$numdog)) return false;
        final Object this$datadog = this.getDatadog();
        final Object other$datadog = other.getDatadog();
        if (this$datadog == null ? other$datadog != null : !this$datadog.equals(other$datadog)) return false;
        final Object this$summa = this.getSumma();
        final Object other$summa = other.getSumma();
        if (this$summa == null ? other$summa != null : !this$summa.equals(other$summa)) return false;
        final Object this$grkiClaimId = this.getGrkiClaimId();
        final Object other$grkiClaimId = other.getGrkiClaimId();
        if (this$grkiClaimId == null ? other$grkiClaimId != null : !this$grkiClaimId.equals(other$grkiClaimId))
            return false;
        final Object this$grkiContractId = this.getGrkiContractId();
        final Object other$grkiContractId = other.getGrkiContractId();
        if (this$grkiContractId == null ? other$grkiContractId != null : !this$grkiContractId.equals(other$grkiContractId))
            return false;
        final Object this$dats_zakr = this.getDats_zakr();
        final Object other$dats_zakr = other.getDats_zakr();
        if (this$dats_zakr == null ? other$dats_zakr != null : !this$dats_zakr.equals(other$dats_zakr)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof KreditDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $kod = this.getKod();
        result = result * PRIME + ($kod == null ? 43 : $kod.hashCode());
        final Object $numdog = this.getNumdog();
        result = result * PRIME + ($numdog == null ? 43 : $numdog.hashCode());
        final Object $datadog = this.getDatadog();
        result = result * PRIME + ($datadog == null ? 43 : $datadog.hashCode());
        final Object $summa = this.getSumma();
        result = result * PRIME + ($summa == null ? 43 : $summa.hashCode());
        final Object $grkiClaimId = this.getGrkiClaimId();
        result = result * PRIME + ($grkiClaimId == null ? 43 : $grkiClaimId.hashCode());
        final Object $grkiContractId = this.getGrkiContractId();
        result = result * PRIME + ($grkiContractId == null ? 43 : $grkiContractId.hashCode());
        final Object $dats_zakr = this.getDats_zakr();
        result = result * PRIME + ($dats_zakr == null ? 43 : $dats_zakr.hashCode());
        return result;
    }

    public String toString() {
        return "KreditDTO(kod=" + this.getKod() + ", numdog=" + this.getNumdog() + ", datadog=" + this.getDatadog() + ", summa=" + this.getSumma() + ", grkiClaimId=" + this.getGrkiClaimId() + ", grkiContractId=" + this.getGrkiContractId() + ", dats_zakr=" + this.getDats_zakr() + ")";
    }
}
