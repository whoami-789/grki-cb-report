package com.grkicbreport.dto;

public class CbOtchDTO {
    private String account;
    private String prev_amount;
    private String deb;
    private String kred;
    private String current_amount;

    public CbOtchDTO() {
    }

    public String getAccount() {
        return this.account;
    }

    public String getPrev_amount() {
        return this.prev_amount;
    }

    public String getDeb() {
        return this.deb;
    }

    public String getKred() {
        return this.kred;
    }

    public String getCurrent_amount() {
        return this.current_amount;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPrev_amount(String prev_amount) {
        this.prev_amount = prev_amount;
    }

    public void setDeb(String deb) {
        this.deb = deb;
    }

    public void setKred(String kred) {
        this.kred = kred;
    }

    public void setCurrent_amount(String current_amount) {
        this.current_amount = current_amount;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CbOtchDTO)) return false;
        final CbOtchDTO other = (CbOtchDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$account = this.getAccount();
        final Object other$account = other.getAccount();
        if (this$account == null ? other$account != null : !this$account.equals(other$account)) return false;
        final Object this$prev_amount = this.getPrev_amount();
        final Object other$prev_amount = other.getPrev_amount();
        if (this$prev_amount == null ? other$prev_amount != null : !this$prev_amount.equals(other$prev_amount))
            return false;
        final Object this$deb = this.getDeb();
        final Object other$deb = other.getDeb();
        if (this$deb == null ? other$deb != null : !this$deb.equals(other$deb)) return false;
        final Object this$kred = this.getKred();
        final Object other$kred = other.getKred();
        if (this$kred == null ? other$kred != null : !this$kred.equals(other$kred)) return false;
        final Object this$current_amount = this.getCurrent_amount();
        final Object other$current_amount = other.getCurrent_amount();
        if (this$current_amount == null ? other$current_amount != null : !this$current_amount.equals(other$current_amount))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CbOtchDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $account = this.getAccount();
        result = result * PRIME + ($account == null ? 43 : $account.hashCode());
        final Object $prev_amount = this.getPrev_amount();
        result = result * PRIME + ($prev_amount == null ? 43 : $prev_amount.hashCode());
        final Object $deb = this.getDeb();
        result = result * PRIME + ($deb == null ? 43 : $deb.hashCode());
        final Object $kred = this.getKred();
        result = result * PRIME + ($kred == null ? 43 : $kred.hashCode());
        final Object $current_amount = this.getCurrent_amount();
        result = result * PRIME + ($current_amount == null ? 43 : $current_amount.hashCode());
        return result;
    }

    public String toString() {
        return "CbOtchDTO(account=" + this.getAccount() + ", prev_amount=" + this.getPrev_amount() + ", deb=" + this.getDeb() + ", kred=" + this.getKred() + ", current_amount=" + this.getCurrent_amount() + ")";
    }
}
