package com.grkicbreport.dto.saveContract;

public class ClaimDTO {
    private String claim_guid;
    private String claim_id;
    private String contract_guid;
    private String contract_id;

    public ClaimDTO() {
    }

    public String getClaim_guid() {
        return this.claim_guid;
    }

    public String getClaim_id() {
        return this.claim_id;
    }

    public String getContract_guid() {
        return this.contract_guid;
    }

    public String getContract_id() {
        return this.contract_id;
    }

    public void setClaim_guid(String claim_guid) {
        this.claim_guid = claim_guid;
    }

    public void setClaim_id(String claim_id) {
        this.claim_id = claim_id;
    }

    public void setContract_guid(String contract_guid) {
        this.contract_guid = contract_guid;
    }

    public void setContract_id(String contract_id) {
        this.contract_id = contract_id;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ClaimDTO)) return false;
        final ClaimDTO other = (ClaimDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$claim_guid = this.getClaim_guid();
        final Object other$claim_guid = other.getClaim_guid();
        if (this$claim_guid == null ? other$claim_guid != null : !this$claim_guid.equals(other$claim_guid))
            return false;
        final Object this$claim_id = this.getClaim_id();
        final Object other$claim_id = other.getClaim_id();
        if (this$claim_id == null ? other$claim_id != null : !this$claim_id.equals(other$claim_id)) return false;
        final Object this$contract_guid = this.getContract_guid();
        final Object other$contract_guid = other.getContract_guid();
        if (this$contract_guid == null ? other$contract_guid != null : !this$contract_guid.equals(other$contract_guid))
            return false;
        final Object this$contract_id = this.getContract_id();
        final Object other$contract_id = other.getContract_id();
        if (this$contract_id == null ? other$contract_id != null : !this$contract_id.equals(other$contract_id))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ClaimDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $claim_guid = this.getClaim_guid();
        result = result * PRIME + ($claim_guid == null ? 43 : $claim_guid.hashCode());
        final Object $claim_id = this.getClaim_id();
        result = result * PRIME + ($claim_id == null ? 43 : $claim_id.hashCode());
        final Object $contract_guid = this.getContract_guid();
        result = result * PRIME + ($contract_guid == null ? 43 : $contract_guid.hashCode());
        final Object $contract_id = this.getContract_id();
        result = result * PRIME + ($contract_id == null ? 43 : $contract_id.hashCode());
        return result;
    }

    public String toString() {
        return "ClaimDTO(claim_guid=" + this.getClaim_guid() + ", claim_id=" + this.getClaim_id() + ", contract_guid=" + this.getContract_guid() + ", contract_id=" + this.getContract_id() + ")";
    }
}
