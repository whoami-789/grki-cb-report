package com.grkicbreport.dto.saveContract;

public class DecisionDTO {
    private String decide;
    private String number;
    private String date;
    private String decide_chief;
    private String borrower_link;

    public DecisionDTO() {
    }

    public String getDecide() {
        return this.decide;
    }

    public String getNumber() {
        return this.number;
    }

    public String getDate() {
        return this.date;
    }

    public String getDecide_chief() {
        return this.decide_chief;
    }

    public String getBorrower_link() {
        return this.borrower_link;
    }

    public void setDecide(String decide) {
        this.decide = decide;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDecide_chief(String decide_chief) {
        this.decide_chief = decide_chief;
    }

    public void setBorrower_link(String borrower_link) {
        this.borrower_link = borrower_link;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof DecisionDTO)) return false;
        final DecisionDTO other = (DecisionDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$decide = this.getDecide();
        final Object other$decide = other.getDecide();
        if (this$decide == null ? other$decide != null : !this$decide.equals(other$decide)) return false;
        final Object this$number = this.getNumber();
        final Object other$number = other.getNumber();
        if (this$number == null ? other$number != null : !this$number.equals(other$number)) return false;
        final Object this$date = this.getDate();
        final Object other$date = other.getDate();
        if (this$date == null ? other$date != null : !this$date.equals(other$date)) return false;
        final Object this$decide_chief = this.getDecide_chief();
        final Object other$decide_chief = other.getDecide_chief();
        if (this$decide_chief == null ? other$decide_chief != null : !this$decide_chief.equals(other$decide_chief))
            return false;
        final Object this$borrower_link = this.getBorrower_link();
        final Object other$borrower_link = other.getBorrower_link();
        if (this$borrower_link == null ? other$borrower_link != null : !this$borrower_link.equals(other$borrower_link))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DecisionDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $decide = this.getDecide();
        result = result * PRIME + ($decide == null ? 43 : $decide.hashCode());
        final Object $number = this.getNumber();
        result = result * PRIME + ($number == null ? 43 : $number.hashCode());
        final Object $date = this.getDate();
        result = result * PRIME + ($date == null ? 43 : $date.hashCode());
        final Object $decide_chief = this.getDecide_chief();
        result = result * PRIME + ($decide_chief == null ? 43 : $decide_chief.hashCode());
        final Object $borrower_link = this.getBorrower_link();
        result = result * PRIME + ($borrower_link == null ? 43 : $borrower_link.hashCode());
        return result;
    }

    public String toString() {
        return "DecisionDTO(decide=" + this.getDecide() + ", number=" + this.getNumber() + ", date=" + this.getDate() + ", decide_chief=" + this.getDecide_chief() + ", borrower_link=" + this.getBorrower_link() + ")";
    }
}
