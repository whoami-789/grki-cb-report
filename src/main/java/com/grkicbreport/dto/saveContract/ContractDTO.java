package com.grkicbreport.dto.saveContract;

import java.util.List;

public class ContractDTO {
    private String loan_type;
    private String issue_mode;
    private String loan_line;
    private String factoring_type;
    private String guarantee_type;
    private String borrower_class;
    private String asset_quality;
    private String number;
    private String date_begin;
    private String date_end;
    private String currency;
    private String amount;
    private PercentDTO percent;
    private List<CompensationsDTO> compensations;
    private String currency_first;
    private String amount_first;
    private String amount_obligations;
    private String discont_comissions;
    private String currency_commission;
    private String amount_commission;
    private String guarantee_info;

    public ContractDTO() {
    }

    public String getLoan_type() {
        return this.loan_type;
    }

    public String getIssue_mode() {
        return this.issue_mode;
    }

    public String getLoan_line() {
        return this.loan_line;
    }

    public String getFactoring_type() {
        return this.factoring_type;
    }

    public String getGuarantee_type() {
        return this.guarantee_type;
    }

    public String getBorrower_class() {
        return this.borrower_class;
    }

    public String getAsset_quality() {
        return this.asset_quality;
    }

    public String getNumber() {
        return this.number;
    }

    public String getDate_begin() {
        return this.date_begin;
    }

    public String getDate_end() {
        return this.date_end;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getAmount() {
        return this.amount;
    }

    public PercentDTO getPercent() {
        return this.percent;
    }

    public List<CompensationsDTO> getCompensations() {
        return this.compensations;
    }

    public String getCurrency_first() {
        return this.currency_first;
    }

    public String getAmount_first() {
        return this.amount_first;
    }

    public String getAmount_obligations() {
        return this.amount_obligations;
    }

    public String getDiscont_comissions() {
        return this.discont_comissions;
    }

    public String getCurrency_commission() {
        return this.currency_commission;
    }

    public String getAmount_commission() {
        return this.amount_commission;
    }

    public String getGuarantee_info() {
        return this.guarantee_info;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public void setIssue_mode(String issue_mode) {
        this.issue_mode = issue_mode;
    }

    public void setLoan_line(String loan_line) {
        this.loan_line = loan_line;
    }

    public void setFactoring_type(String factoring_type) {
        this.factoring_type = factoring_type;
    }

    public void setGuarantee_type(String guarantee_type) {
        this.guarantee_type = guarantee_type;
    }

    public void setBorrower_class(String borrower_class) {
        this.borrower_class = borrower_class;
    }

    public void setAsset_quality(String asset_quality) {
        this.asset_quality = asset_quality;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDate_begin(String date_begin) {
        this.date_begin = date_begin;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setPercent(PercentDTO percent) {
        this.percent = percent;
    }

    public void setCompensations(List<CompensationsDTO> compensations) {
        this.compensations = compensations;
    }

    public void setCurrency_first(String currency_first) {
        this.currency_first = currency_first;
    }

    public void setAmount_first(String amount_first) {
        this.amount_first = amount_first;
    }

    public void setAmount_obligations(String amount_obligations) {
        this.amount_obligations = amount_obligations;
    }

    public void setDiscont_comissions(String discont_comissions) {
        this.discont_comissions = discont_comissions;
    }

    public void setCurrency_commission(String currency_commission) {
        this.currency_commission = currency_commission;
    }

    public void setAmount_commission(String amount_commission) {
        this.amount_commission = amount_commission;
    }

    public void setGuarantee_info(String guarantee_info) {
        this.guarantee_info = guarantee_info;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ContractDTO)) return false;
        final ContractDTO other = (ContractDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$loan_type = this.getLoan_type();
        final Object other$loan_type = other.getLoan_type();
        if (this$loan_type == null ? other$loan_type != null : !this$loan_type.equals(other$loan_type)) return false;
        final Object this$issue_mode = this.getIssue_mode();
        final Object other$issue_mode = other.getIssue_mode();
        if (this$issue_mode == null ? other$issue_mode != null : !this$issue_mode.equals(other$issue_mode))
            return false;
        final Object this$loan_line = this.getLoan_line();
        final Object other$loan_line = other.getLoan_line();
        if (this$loan_line == null ? other$loan_line != null : !this$loan_line.equals(other$loan_line)) return false;
        final Object this$factoring_type = this.getFactoring_type();
        final Object other$factoring_type = other.getFactoring_type();
        if (this$factoring_type == null ? other$factoring_type != null : !this$factoring_type.equals(other$factoring_type))
            return false;
        final Object this$guarantee_type = this.getGuarantee_type();
        final Object other$guarantee_type = other.getGuarantee_type();
        if (this$guarantee_type == null ? other$guarantee_type != null : !this$guarantee_type.equals(other$guarantee_type))
            return false;
        final Object this$borrower_class = this.getBorrower_class();
        final Object other$borrower_class = other.getBorrower_class();
        if (this$borrower_class == null ? other$borrower_class != null : !this$borrower_class.equals(other$borrower_class))
            return false;
        final Object this$asset_quality = this.getAsset_quality();
        final Object other$asset_quality = other.getAsset_quality();
        if (this$asset_quality == null ? other$asset_quality != null : !this$asset_quality.equals(other$asset_quality))
            return false;
        final Object this$number = this.getNumber();
        final Object other$number = other.getNumber();
        if (this$number == null ? other$number != null : !this$number.equals(other$number)) return false;
        final Object this$date_begin = this.getDate_begin();
        final Object other$date_begin = other.getDate_begin();
        if (this$date_begin == null ? other$date_begin != null : !this$date_begin.equals(other$date_begin))
            return false;
        final Object this$date_end = this.getDate_end();
        final Object other$date_end = other.getDate_end();
        if (this$date_end == null ? other$date_end != null : !this$date_end.equals(other$date_end)) return false;
        final Object this$currency = this.getCurrency();
        final Object other$currency = other.getCurrency();
        if (this$currency == null ? other$currency != null : !this$currency.equals(other$currency)) return false;
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !this$amount.equals(other$amount)) return false;
        final Object this$percent = this.getPercent();
        final Object other$percent = other.getPercent();
        if (this$percent == null ? other$percent != null : !this$percent.equals(other$percent)) return false;
        final Object this$compensations = this.getCompensations();
        final Object other$compensations = other.getCompensations();
        if (this$compensations == null ? other$compensations != null : !this$compensations.equals(other$compensations))
            return false;
        final Object this$currency_first = this.getCurrency_first();
        final Object other$currency_first = other.getCurrency_first();
        if (this$currency_first == null ? other$currency_first != null : !this$currency_first.equals(other$currency_first))
            return false;
        final Object this$amount_first = this.getAmount_first();
        final Object other$amount_first = other.getAmount_first();
        if (this$amount_first == null ? other$amount_first != null : !this$amount_first.equals(other$amount_first))
            return false;
        final Object this$amount_obligations = this.getAmount_obligations();
        final Object other$amount_obligations = other.getAmount_obligations();
        if (this$amount_obligations == null ? other$amount_obligations != null : !this$amount_obligations.equals(other$amount_obligations))
            return false;
        final Object this$discont_comissions = this.getDiscont_comissions();
        final Object other$discont_comissions = other.getDiscont_comissions();
        if (this$discont_comissions == null ? other$discont_comissions != null : !this$discont_comissions.equals(other$discont_comissions))
            return false;
        final Object this$currency_commission = this.getCurrency_commission();
        final Object other$currency_commission = other.getCurrency_commission();
        if (this$currency_commission == null ? other$currency_commission != null : !this$currency_commission.equals(other$currency_commission))
            return false;
        final Object this$amount_commission = this.getAmount_commission();
        final Object other$amount_commission = other.getAmount_commission();
        if (this$amount_commission == null ? other$amount_commission != null : !this$amount_commission.equals(other$amount_commission))
            return false;
        final Object this$guarantee_info = this.getGuarantee_info();
        final Object other$guarantee_info = other.getGuarantee_info();
        if (this$guarantee_info == null ? other$guarantee_info != null : !this$guarantee_info.equals(other$guarantee_info))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $loan_type = this.getLoan_type();
        result = result * PRIME + ($loan_type == null ? 43 : $loan_type.hashCode());
        final Object $issue_mode = this.getIssue_mode();
        result = result * PRIME + ($issue_mode == null ? 43 : $issue_mode.hashCode());
        final Object $loan_line = this.getLoan_line();
        result = result * PRIME + ($loan_line == null ? 43 : $loan_line.hashCode());
        final Object $factoring_type = this.getFactoring_type();
        result = result * PRIME + ($factoring_type == null ? 43 : $factoring_type.hashCode());
        final Object $guarantee_type = this.getGuarantee_type();
        result = result * PRIME + ($guarantee_type == null ? 43 : $guarantee_type.hashCode());
        final Object $borrower_class = this.getBorrower_class();
        result = result * PRIME + ($borrower_class == null ? 43 : $borrower_class.hashCode());
        final Object $asset_quality = this.getAsset_quality();
        result = result * PRIME + ($asset_quality == null ? 43 : $asset_quality.hashCode());
        final Object $number = this.getNumber();
        result = result * PRIME + ($number == null ? 43 : $number.hashCode());
        final Object $date_begin = this.getDate_begin();
        result = result * PRIME + ($date_begin == null ? 43 : $date_begin.hashCode());
        final Object $date_end = this.getDate_end();
        result = result * PRIME + ($date_end == null ? 43 : $date_end.hashCode());
        final Object $currency = this.getCurrency();
        result = result * PRIME + ($currency == null ? 43 : $currency.hashCode());
        final Object $amount = this.getAmount();
        result = result * PRIME + ($amount == null ? 43 : $amount.hashCode());
        final Object $percent = this.getPercent();
        result = result * PRIME + ($percent == null ? 43 : $percent.hashCode());
        final Object $compensations = this.getCompensations();
        result = result * PRIME + ($compensations == null ? 43 : $compensations.hashCode());
        final Object $currency_first = this.getCurrency_first();
        result = result * PRIME + ($currency_first == null ? 43 : $currency_first.hashCode());
        final Object $amount_first = this.getAmount_first();
        result = result * PRIME + ($amount_first == null ? 43 : $amount_first.hashCode());
        final Object $amount_obligations = this.getAmount_obligations();
        result = result * PRIME + ($amount_obligations == null ? 43 : $amount_obligations.hashCode());
        final Object $discont_comissions = this.getDiscont_comissions();
        result = result * PRIME + ($discont_comissions == null ? 43 : $discont_comissions.hashCode());
        final Object $currency_commission = this.getCurrency_commission();
        result = result * PRIME + ($currency_commission == null ? 43 : $currency_commission.hashCode());
        final Object $amount_commission = this.getAmount_commission();
        result = result * PRIME + ($amount_commission == null ? 43 : $amount_commission.hashCode());
        final Object $guarantee_info = this.getGuarantee_info();
        result = result * PRIME + ($guarantee_info == null ? 43 : $guarantee_info.hashCode());
        return result;
    }

    public String toString() {
        return "ContractDTO(loan_type=" + this.getLoan_type() + ", issue_mode=" + this.getIssue_mode() + ", loan_line=" + this.getLoan_line() + ", factoring_type=" + this.getFactoring_type() + ", guarantee_type=" + this.getGuarantee_type() + ", borrower_class=" + this.getBorrower_class() + ", asset_quality=" + this.getAsset_quality() + ", number=" + this.getNumber() + ", date_begin=" + this.getDate_begin() + ", date_end=" + this.getDate_end() + ", currency=" + this.getCurrency() + ", amount=" + this.getAmount() + ", percent=" + this.getPercent() + ", compensations=" + this.getCompensations() + ", currency_first=" + this.getCurrency_first() + ", amount_first=" + this.getAmount_first() + ", amount_obligations=" + this.getAmount_obligations() + ", discont_comissions=" + this.getDiscont_comissions() + ", currency_commission=" + this.getCurrency_commission() + ", amount_commission=" + this.getAmount_commission() + ", guarantee_info=" + this.getGuarantee_info() + ")";
    }
}
