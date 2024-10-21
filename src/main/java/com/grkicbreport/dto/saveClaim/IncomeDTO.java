package com.grkicbreport.dto.saveClaim;

public class IncomeDTO {
    private String income_type;
    private String average_income;

    public IncomeDTO() {
    }

    public String getIncome_type() {
        return this.income_type;
    }

    public String getAverage_income() {
        return this.average_income;
    }

    public void setIncome_type(String income_type) {
        this.income_type = income_type;
    }

    public void setAverage_income(String average_income) {
        this.average_income = average_income;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof IncomeDTO)) return false;
        final IncomeDTO other = (IncomeDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$income_type = this.getIncome_type();
        final Object other$income_type = other.getIncome_type();
        if (this$income_type == null ? other$income_type != null : !this$income_type.equals(other$income_type))
            return false;
        final Object this$average_income = this.getAverage_income();
        final Object other$average_income = other.getAverage_income();
        if (this$average_income == null ? other$average_income != null : !this$average_income.equals(other$average_income))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof IncomeDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $income_type = this.getIncome_type();
        result = result * PRIME + ($income_type == null ? 43 : $income_type.hashCode());
        final Object $average_income = this.getAverage_income();
        result = result * PRIME + ($average_income == null ? 43 : $average_income.hashCode());
        return result;
    }

    public String toString() {
        return "IncomeDTO(income_type=" + this.getIncome_type() + ", average_income=" + this.getAverage_income() + ")";
    }
}
