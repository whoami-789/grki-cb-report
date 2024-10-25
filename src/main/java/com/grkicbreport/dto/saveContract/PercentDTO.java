package com.grkicbreport.dto.saveContract;

public class PercentDTO {
    private String percent_type;
    private String percent_total;
    private String percent_nonresident;
    private String percent_resident;
    private String borrower_percent;
    private String overdue_percent;

    public PercentDTO() {
    }

    public String getPercent_type() {
        return this.percent_type;
    }

    public String getPercent_total() {
        return this.percent_total;
    }

    public String getPercent_nonresident() {
        return this.percent_nonresident;
    }

    public String getPercent_resident() {
        return this.percent_resident;
    }

    public String getBorrower_percent() {
        return this.borrower_percent;
    }

    public String getOverdue_percent() {
        return this.overdue_percent;
    }

    public void setPercent_type(String percent_type) {
        this.percent_type = percent_type;
    }

    public void setPercent_total(String percent_total) {
        this.percent_total = percent_total;
    }

    public void setPercent_nonresident(String percent_nonresident) {
        this.percent_nonresident = percent_nonresident;
    }

    public void setPercent_resident(String percent_resident) {
        this.percent_resident = percent_resident;
    }

    public void setBorrower_percent(String borrower_percent) {
        this.borrower_percent = borrower_percent;
    }

    public void setOverdue_percent(String overdue_percent) {
        this.overdue_percent = overdue_percent;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PercentDTO)) return false;
        final PercentDTO other = (PercentDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$percent_type = this.getPercent_type();
        final Object other$percent_type = other.getPercent_type();
        if (this$percent_type == null ? other$percent_type != null : !this$percent_type.equals(other$percent_type))
            return false;
        final Object this$percent_total = this.getPercent_total();
        final Object other$percent_total = other.getPercent_total();
        if (this$percent_total == null ? other$percent_total != null : !this$percent_total.equals(other$percent_total))
            return false;
        final Object this$percent_nonresident = this.getPercent_nonresident();
        final Object other$percent_nonresident = other.getPercent_nonresident();
        if (this$percent_nonresident == null ? other$percent_nonresident != null : !this$percent_nonresident.equals(other$percent_nonresident))
            return false;
        final Object this$percent_resident = this.getPercent_resident();
        final Object other$percent_resident = other.getPercent_resident();
        if (this$percent_resident == null ? other$percent_resident != null : !this$percent_resident.equals(other$percent_resident))
            return false;
        final Object this$borrower_percent = this.getBorrower_percent();
        final Object other$borrower_percent = other.getBorrower_percent();
        if (this$borrower_percent == null ? other$borrower_percent != null : !this$borrower_percent.equals(other$borrower_percent))
            return false;
        final Object this$overdue_percent = this.getOverdue_percent();
        final Object other$overdue_percent = other.getOverdue_percent();
        if (this$overdue_percent == null ? other$overdue_percent != null : !this$overdue_percent.equals(other$overdue_percent))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PercentDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $percent_type = this.getPercent_type();
        result = result * PRIME + ($percent_type == null ? 43 : $percent_type.hashCode());
        final Object $percent_total = this.getPercent_total();
        result = result * PRIME + ($percent_total == null ? 43 : $percent_total.hashCode());
        final Object $percent_nonresident = this.getPercent_nonresident();
        result = result * PRIME + ($percent_nonresident == null ? 43 : $percent_nonresident.hashCode());
        final Object $percent_resident = this.getPercent_resident();
        result = result * PRIME + ($percent_resident == null ? 43 : $percent_resident.hashCode());
        final Object $borrower_percent = this.getBorrower_percent();
        result = result * PRIME + ($borrower_percent == null ? 43 : $borrower_percent.hashCode());
        final Object $overdue_percent = this.getOverdue_percent();
        result = result * PRIME + ($overdue_percent == null ? 43 : $overdue_percent.hashCode());
        return result;
    }

    public String toString() {
        return "PercentDTO(percent_type=" + this.getPercent_type() + ", percent_total=" + this.getPercent_total() + ", percent_nonresident=" + this.getPercent_nonresident() + ", percent_resident=" + this.getPercent_resident() + ", borrower_percent=" + this.getBorrower_percent() + ", overdue_percent=" + this.getOverdue_percent() + ")";
    }
}
