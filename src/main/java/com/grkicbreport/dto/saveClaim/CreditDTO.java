package com.grkicbreport.dto.saveClaim;

import java.util.List;

public class CreditDTO {
    private String subject_type;
    private String type;
    private String currency;
    private String amount;
    private String percent;
    private String period;
    private List<String> targets;
    private String new_staff;

    public CreditDTO() {
    }

    public String getSubject_type() {
        return this.subject_type;
    }

    public String getType() {
        return this.type;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getAmount() {
        return this.amount;
    }

    public String getPercent() {
        return this.percent;
    }

    public String getPeriod() {
        return this.period;
    }

    public List<String> getTargets() {
        return this.targets;
    }

    public String getNew_staff() {
        return this.new_staff;
    }

    public void setSubject_type(String subject_type) {
        this.subject_type = subject_type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setTargets(List<String> targets) {
        this.targets = targets;
    }

    public void setNew_staff(String new_staff) {
        this.new_staff = new_staff;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreditDTO)) return false;
        final CreditDTO other = (CreditDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$subject_type = this.getSubject_type();
        final Object other$subject_type = other.getSubject_type();
        if (this$subject_type == null ? other$subject_type != null : !this$subject_type.equals(other$subject_type))
            return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$currency = this.getCurrency();
        final Object other$currency = other.getCurrency();
        if (this$currency == null ? other$currency != null : !this$currency.equals(other$currency)) return false;
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !this$amount.equals(other$amount)) return false;
        final Object this$percent = this.getPercent();
        final Object other$percent = other.getPercent();
        if (this$percent == null ? other$percent != null : !this$percent.equals(other$percent)) return false;
        final Object this$period = this.getPeriod();
        final Object other$period = other.getPeriod();
        if (this$period == null ? other$period != null : !this$period.equals(other$period)) return false;
        final Object this$targets = this.getTargets();
        final Object other$targets = other.getTargets();
        if (this$targets == null ? other$targets != null : !this$targets.equals(other$targets)) return false;
        final Object this$new_staff = this.getNew_staff();
        final Object other$new_staff = other.getNew_staff();
        if (this$new_staff == null ? other$new_staff != null : !this$new_staff.equals(other$new_staff)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CreditDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $subject_type = this.getSubject_type();
        result = result * PRIME + ($subject_type == null ? 43 : $subject_type.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $currency = this.getCurrency();
        result = result * PRIME + ($currency == null ? 43 : $currency.hashCode());
        final Object $amount = this.getAmount();
        result = result * PRIME + ($amount == null ? 43 : $amount.hashCode());
        final Object $percent = this.getPercent();
        result = result * PRIME + ($percent == null ? 43 : $percent.hashCode());
        final Object $period = this.getPeriod();
        result = result * PRIME + ($period == null ? 43 : $period.hashCode());
        final Object $targets = this.getTargets();
        result = result * PRIME + ($targets == null ? 43 : $targets.hashCode());
        final Object $new_staff = this.getNew_staff();
        result = result * PRIME + ($new_staff == null ? 43 : $new_staff.hashCode());
        return result;
    }

    public String toString() {
        return "CreditDTO(subject_type=" + this.getSubject_type() + ", type=" + this.getType() + ", currency=" + this.getCurrency() + ", amount=" + this.getAmount() + ", percent=" + this.getPercent() + ", period=" + this.getPeriod() + ", targets=" + this.getTargets() + ", new_staff=" + this.getNew_staff() + ")";
    }
}
