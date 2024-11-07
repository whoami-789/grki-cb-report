package com.grkicbreport.dto.saveContract;

public class SourcesDTO {
    private String type;
    private String foreign_org;
    private String currency;
    private String amount;

    public SourcesDTO() {
    }

    public String getType() {
        return this.type;
    }

    public String getForeign_org() {
        return this.foreign_org;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setForeign_org(String foreign_org) {
        this.foreign_org = foreign_org;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SourcesDTO)) return false;
        final SourcesDTO other = (SourcesDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$foreign_org = this.getForeign_org();
        final Object other$foreign_org = other.getForeign_org();
        if (this$foreign_org == null ? other$foreign_org != null : !this$foreign_org.equals(other$foreign_org))
            return false;
        final Object this$currency = this.getCurrency();
        final Object other$currency = other.getCurrency();
        if (this$currency == null ? other$currency != null : !this$currency.equals(other$currency)) return false;
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !this$amount.equals(other$amount)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SourcesDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $foreign_org = this.getForeign_org();
        result = result * PRIME + ($foreign_org == null ? 43 : $foreign_org.hashCode());
        final Object $currency = this.getCurrency();
        result = result * PRIME + ($currency == null ? 43 : $currency.hashCode());
        final Object $amount = this.getAmount();
        result = result * PRIME + ($amount == null ? 43 : $amount.hashCode());
        return result;
    }

    public String toString() {
        return "SourcesDTO(type=" + this.getType() + ", foreign_org=" + this.getForeign_org() + ", currency=" + this.getCurrency() + ", amount=" + this.getAmount() + ")";
    }
}
