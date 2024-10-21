package com.grkicbreport.dto.saveContract;

public class Change_basisDTO {
    private String revisor;
    private String number;
    private String date;
    private String reason;
    private String revisor_chief;

    public Change_basisDTO(String revisor, String number, String date, String reason, String revisor_chief) {
        this.revisor = revisor;
        this.number = number;
        this.date = date;
        this.reason = reason;
        this.revisor_chief = revisor_chief;
    }

    public String getRevisor() {
        return this.revisor;
    }

    public String getNumber() {
        return this.number;
    }

    public String getDate() {
        return this.date;
    }

    public String getReason() {
        return this.reason;
    }

    public String getRevisor_chief() {
        return this.revisor_chief;
    }

    public void setRevisor(String revisor) {
        this.revisor = revisor;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setRevisor_chief(String revisor_chief) {
        this.revisor_chief = revisor_chief;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Change_basisDTO)) return false;
        final Change_basisDTO other = (Change_basisDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$revisor = this.getRevisor();
        final Object other$revisor = other.getRevisor();
        if (this$revisor == null ? other$revisor != null : !this$revisor.equals(other$revisor)) return false;
        final Object this$number = this.getNumber();
        final Object other$number = other.getNumber();
        if (this$number == null ? other$number != null : !this$number.equals(other$number)) return false;
        final Object this$date = this.getDate();
        final Object other$date = other.getDate();
        if (this$date == null ? other$date != null : !this$date.equals(other$date)) return false;
        final Object this$reason = this.getReason();
        final Object other$reason = other.getReason();
        if (this$reason == null ? other$reason != null : !this$reason.equals(other$reason)) return false;
        final Object this$revisor_chief = this.getRevisor_chief();
        final Object other$revisor_chief = other.getRevisor_chief();
        if (this$revisor_chief == null ? other$revisor_chief != null : !this$revisor_chief.equals(other$revisor_chief))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Change_basisDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $revisor = this.getRevisor();
        result = result * PRIME + ($revisor == null ? 43 : $revisor.hashCode());
        final Object $number = this.getNumber();
        result = result * PRIME + ($number == null ? 43 : $number.hashCode());
        final Object $date = this.getDate();
        result = result * PRIME + ($date == null ? 43 : $date.hashCode());
        final Object $reason = this.getReason();
        result = result * PRIME + ($reason == null ? 43 : $reason.hashCode());
        final Object $revisor_chief = this.getRevisor_chief();
        result = result * PRIME + ($revisor_chief == null ? 43 : $revisor_chief.hashCode());
        return result;
    }

    public String toString() {
        return "Change_basisDTO(revisor=" + this.getRevisor() + ", number=" + this.getNumber() + ", date=" + this.getDate() + ", reason=" + this.getReason() + ", revisor_chief=" + this.getRevisor_chief() + ")";
    }
}
