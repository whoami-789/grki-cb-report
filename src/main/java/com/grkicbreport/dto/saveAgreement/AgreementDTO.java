package com.grkicbreport.dto.saveAgreement;

public class AgreementDTO {
    private String agreement_guid;
    private String agreement_id;
    private String number;
    private String date_begin;
    private String date_end;
    private String subject_type;
    private String pinfl;
    private String inn;
    private String resident;
    private String name;
    private String currency;
    private String amount;

    public AgreementDTO() {
    }

    public String getAgreement_guid() {
        return this.agreement_guid;
    }

    public String getAgreement_id() {
        return this.agreement_id;
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

    public String getSubject_type() {
        return this.subject_type;
    }

    public String getPinfl() {
        return this.pinfl;
    }

    public String getInn() {
        return this.inn;
    }

    public String getResident() {
        return this.resident;
    }

    public String getName() {
        return this.name;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAgreement_guid(String agreement_guid) {
        this.agreement_guid = agreement_guid;
    }

    public void setAgreement_id(String agreement_id) {
        this.agreement_id = agreement_id;
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

    public void setSubject_type(String subject_type) {
        this.subject_type = subject_type;
    }

    public void setPinfl(String pinfl) {
        this.pinfl = pinfl;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public void setResident(String resident) {
        this.resident = resident;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AgreementDTO)) return false;
        final AgreementDTO other = (AgreementDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$agreement_guid = this.getAgreement_guid();
        final Object other$agreement_guid = other.getAgreement_guid();
        if (this$agreement_guid == null ? other$agreement_guid != null : !this$agreement_guid.equals(other$agreement_guid))
            return false;
        final Object this$agreement_id = this.getAgreement_id();
        final Object other$agreement_id = other.getAgreement_id();
        if (this$agreement_id == null ? other$agreement_id != null : !this$agreement_id.equals(other$agreement_id))
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
        final Object this$subject_type = this.getSubject_type();
        final Object other$subject_type = other.getSubject_type();
        if (this$subject_type == null ? other$subject_type != null : !this$subject_type.equals(other$subject_type))
            return false;
        final Object this$pinfl = this.getPinfl();
        final Object other$pinfl = other.getPinfl();
        if (this$pinfl == null ? other$pinfl != null : !this$pinfl.equals(other$pinfl)) return false;
        final Object this$inn = this.getInn();
        final Object other$inn = other.getInn();
        if (this$inn == null ? other$inn != null : !this$inn.equals(other$inn)) return false;
        final Object this$resident = this.getResident();
        final Object other$resident = other.getResident();
        if (this$resident == null ? other$resident != null : !this$resident.equals(other$resident)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$currency = this.getCurrency();
        final Object other$currency = other.getCurrency();
        if (this$currency == null ? other$currency != null : !this$currency.equals(other$currency)) return false;
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !this$amount.equals(other$amount)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AgreementDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $agreement_guid = this.getAgreement_guid();
        result = result * PRIME + ($agreement_guid == null ? 43 : $agreement_guid.hashCode());
        final Object $agreement_id = this.getAgreement_id();
        result = result * PRIME + ($agreement_id == null ? 43 : $agreement_id.hashCode());
        final Object $number = this.getNumber();
        result = result * PRIME + ($number == null ? 43 : $number.hashCode());
        final Object $date_begin = this.getDate_begin();
        result = result * PRIME + ($date_begin == null ? 43 : $date_begin.hashCode());
        final Object $date_end = this.getDate_end();
        result = result * PRIME + ($date_end == null ? 43 : $date_end.hashCode());
        final Object $subject_type = this.getSubject_type();
        result = result * PRIME + ($subject_type == null ? 43 : $subject_type.hashCode());
        final Object $pinfl = this.getPinfl();
        result = result * PRIME + ($pinfl == null ? 43 : $pinfl.hashCode());
        final Object $inn = this.getInn();
        result = result * PRIME + ($inn == null ? 43 : $inn.hashCode());
        final Object $resident = this.getResident();
        result = result * PRIME + ($resident == null ? 43 : $resident.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $currency = this.getCurrency();
        result = result * PRIME + ($currency == null ? 43 : $currency.hashCode());
        final Object $amount = this.getAmount();
        result = result * PRIME + ($amount == null ? 43 : $amount.hashCode());
        return result;
    }

    public String toString() {
        return "AgreementDTO(agreement_guid=" + this.getAgreement_guid() + ", agreement_id=" + this.getAgreement_id() + ", number=" + this.getNumber() + ", date_begin=" + this.getDate_begin() + ", date_end=" + this.getDate_end() + ", subject_type=" + this.getSubject_type() + ", pinfl=" + this.getPinfl() + ", inn=" + this.getInn() + ", resident=" + this.getResident() + ", name=" + this.getName() + ", currency=" + this.getCurrency() + ", amount=" + this.getAmount() + ")";
    }
}
