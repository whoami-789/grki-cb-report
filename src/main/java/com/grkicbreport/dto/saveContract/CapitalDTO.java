package com.grkicbreport.dto.saveContract;

public class CapitalDTO {
    private String amount_uzs;
    private String amount_usd;
    private String amount_eur;

    public CapitalDTO(String amount_uzs, String amount_usd, String amount_eur) {
        this.amount_uzs = amount_uzs;
        this.amount_usd = amount_usd;
        this.amount_eur = amount_eur;
    }

    public String getAmount_uzs() {
        return this.amount_uzs;
    }

    public String getAmount_usd() {
        return this.amount_usd;
    }

    public String getAmount_eur() {
        return this.amount_eur;
    }

    public void setAmount_uzs(String amount_uzs) {
        this.amount_uzs = amount_uzs;
    }

    public void setAmount_usd(String amount_usd) {
        this.amount_usd = amount_usd;
    }

    public void setAmount_eur(String amount_eur) {
        this.amount_eur = amount_eur;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CapitalDTO)) return false;
        final CapitalDTO other = (CapitalDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$amount_uzs = this.getAmount_uzs();
        final Object other$amount_uzs = other.getAmount_uzs();
        if (this$amount_uzs == null ? other$amount_uzs != null : !this$amount_uzs.equals(other$amount_uzs))
            return false;
        final Object this$amount_usd = this.getAmount_usd();
        final Object other$amount_usd = other.getAmount_usd();
        if (this$amount_usd == null ? other$amount_usd != null : !this$amount_usd.equals(other$amount_usd))
            return false;
        final Object this$amount_eur = this.getAmount_eur();
        final Object other$amount_eur = other.getAmount_eur();
        if (this$amount_eur == null ? other$amount_eur != null : !this$amount_eur.equals(other$amount_eur))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CapitalDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $amount_uzs = this.getAmount_uzs();
        result = result * PRIME + ($amount_uzs == null ? 43 : $amount_uzs.hashCode());
        final Object $amount_usd = this.getAmount_usd();
        result = result * PRIME + ($amount_usd == null ? 43 : $amount_usd.hashCode());
        final Object $amount_eur = this.getAmount_eur();
        result = result * PRIME + ($amount_eur == null ? 43 : $amount_eur.hashCode());
        return result;
    }

    public String toString() {
        return "CapitalDTO(amount_uzs=" + this.getAmount_uzs() + ", amount_usd=" + this.getAmount_usd() + ", amount_eur=" + this.getAmount_eur() + ")";
    }
}
