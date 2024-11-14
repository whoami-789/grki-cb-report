package com.grkicbreport.dto.saveContract;

public class AgreementDTO {
    private String agreement_id;
    private String agreement_guid;

    public AgreementDTO(String agreement_id, String agreement_guid) {
        this.agreement_id = agreement_id;
        this.agreement_guid = agreement_guid;
    }

    public String getAgreement_id() {
        return this.agreement_id;
    }

    public String getAgreement_guid() {
        return this.agreement_guid;
    }

    public void setAgreement_id(String agreement_id) {
        this.agreement_id = agreement_id;
    }

    public void setAgreement_guid(String agreement_guid) {
        this.agreement_guid = agreement_guid;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AgreementDTO)) return false;
        final AgreementDTO other = (AgreementDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$agreement_id = this.getAgreement_id();
        final Object other$agreement_id = other.getAgreement_id();
        if (this$agreement_id == null ? other$agreement_id != null : !this$agreement_id.equals(other$agreement_id))
            return false;
        final Object this$agreement_guid = this.getAgreement_guid();
        final Object other$agreement_guid = other.getAgreement_guid();
        if (this$agreement_guid == null ? other$agreement_guid != null : !this$agreement_guid.equals(other$agreement_guid))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AgreementDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $agreement_id = this.getAgreement_id();
        result = result * PRIME + ($agreement_id == null ? 43 : $agreement_id.hashCode());
        final Object $agreement_guid = this.getAgreement_guid();
        result = result * PRIME + ($agreement_guid == null ? 43 : $agreement_guid.hashCode());
        return result;
    }

    public String toString() {
        return "AgreementDTO(agreement_id=" + this.getAgreement_id() + ", agreement_guid=" + this.getAgreement_guid() + ")";
    }
}
