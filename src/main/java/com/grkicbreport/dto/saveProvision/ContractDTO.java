package com.grkicbreport.dto.saveProvision;

public class ContractDTO {
    private String contract_guid;
    private String contract_id;
    private String agreement_guid;
    private String agreement_id;

    public ContractDTO() {
    }

    public String getContract_guid() {
        return this.contract_guid;
    }

    public String getContract_id() {
        return this.contract_id;
    }

    public String getAgreement_guid() {
        return this.agreement_guid;
    }

    public String getAgreement_id() {
        return this.agreement_id;
    }

    public void setContract_guid(String contract_guid) {
        this.contract_guid = contract_guid;
    }

    public void setContract_id(String contract_id) {
        this.contract_id = contract_id;
    }

    public void setAgreement_guid(String agreement_guid) {
        this.agreement_guid = agreement_guid;
    }

    public void setAgreement_id(String agreement_id) {
        this.agreement_id = agreement_id;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ContractDTO)) return false;
        final ContractDTO other = (ContractDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$contract_guid = this.getContract_guid();
        final Object other$contract_guid = other.getContract_guid();
        if (this$contract_guid == null ? other$contract_guid != null : !this$contract_guid.equals(other$contract_guid))
            return false;
        final Object this$contract_id = this.getContract_id();
        final Object other$contract_id = other.getContract_id();
        if (this$contract_id == null ? other$contract_id != null : !this$contract_id.equals(other$contract_id))
            return false;
        final Object this$agreement_guid = this.getAgreement_guid();
        final Object other$agreement_guid = other.getAgreement_guid();
        if (this$agreement_guid == null ? other$agreement_guid != null : !this$agreement_guid.equals(other$agreement_guid))
            return false;
        final Object this$agreement_id = this.getAgreement_id();
        final Object other$agreement_id = other.getAgreement_id();
        if (this$agreement_id == null ? other$agreement_id != null : !this$agreement_id.equals(other$agreement_id))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $contract_guid = this.getContract_guid();
        result = result * PRIME + ($contract_guid == null ? 43 : $contract_guid.hashCode());
        final Object $contract_id = this.getContract_id();
        result = result * PRIME + ($contract_id == null ? 43 : $contract_id.hashCode());
        final Object $agreement_guid = this.getAgreement_guid();
        result = result * PRIME + ($agreement_guid == null ? 43 : $agreement_guid.hashCode());
        final Object $agreement_id = this.getAgreement_id();
        result = result * PRIME + ($agreement_id == null ? 43 : $agreement_id.hashCode());
        return result;
    }

    public String toString() {
        return "ContractDTO(contract_guid=" + this.getContract_guid() + ", contract_id=" + this.getContract_id() + ", agreement_guid=" + this.getAgreement_guid() + ", agreement_id=" + this.getAgreement_id() + ")";
    }
}
