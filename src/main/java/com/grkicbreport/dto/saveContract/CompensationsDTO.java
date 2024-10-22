package com.grkicbreport.dto.saveContract;

public class CompensationsDTO {
    private String compensation;

    public CompensationsDTO() {
    }

    public String getCompensation() {
        return this.compensation;
    }

    public void setCompensation(String compensation) {
        this.compensation = compensation;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CompensationsDTO)) return false;
        final CompensationsDTO other = (CompensationsDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$compensation = this.getCompensation();
        final Object other$compensation = other.getCompensation();
        if (this$compensation == null ? other$compensation != null : !this$compensation.equals(other$compensation))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CompensationsDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $compensation = this.getCompensation();
        result = result * PRIME + ($compensation == null ? 43 : $compensation.hashCode());
        return result;
    }

    public String toString() {
        return "CompensationsDTO(compensation=" + this.getCompensation() + ")";
    }
}
