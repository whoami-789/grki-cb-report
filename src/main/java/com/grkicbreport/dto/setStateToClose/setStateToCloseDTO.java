package com.grkicbreport.dto.setStateToClose;

import com.grkicbreport.dto.CreditorDTO;

public class setStateToCloseDTO {

    private String save_mode;
    private CreditorDTO creditor;
    private ContractDTO contract;

    public setStateToCloseDTO() {
    }

    public String getSave_mode() {
        return this.save_mode;
    }

    public CreditorDTO getCreditor() {
        return this.creditor;
    }

    public ContractDTO getContract() {
        return this.contract;
    }

    public void setSave_mode(String save_mode) {
        this.save_mode = save_mode;
    }

    public void setCreditor(CreditorDTO creditor) {
        this.creditor = creditor;
    }

    public void setContract(ContractDTO contract) {
        this.contract = contract;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof setStateToCloseDTO)) return false;
        final setStateToCloseDTO other = (setStateToCloseDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$save_mode = this.getSave_mode();
        final Object other$save_mode = other.getSave_mode();
        if (this$save_mode == null ? other$save_mode != null : !this$save_mode.equals(other$save_mode)) return false;
        final Object this$creditor = this.getCreditor();
        final Object other$creditor = other.getCreditor();
        if (this$creditor == null ? other$creditor != null : !this$creditor.equals(other$creditor)) return false;
        final Object this$contract = this.getContract();
        final Object other$contract = other.getContract();
        if (this$contract == null ? other$contract != null : !this$contract.equals(other$contract)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof setStateToCloseDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $save_mode = this.getSave_mode();
        result = result * PRIME + ($save_mode == null ? 43 : $save_mode.hashCode());
        final Object $creditor = this.getCreditor();
        result = result * PRIME + ($creditor == null ? 43 : $creditor.hashCode());
        final Object $contract = this.getContract();
        result = result * PRIME + ($contract == null ? 43 : $contract.hashCode());
        return result;
    }

    public String toString() {
        return "setStateToCloseDTO(save_mode=" + this.getSave_mode() + ", creditor=" + this.getCreditor() + ", contract=" + this.getContract() + ")";
    }

    public static class ContractDTO {
        private String contract_guid;
        private String contract_id;

        public ContractDTO() {
        }

        public String getContract_guid() {
            return this.contract_guid;
        }

        public String getContract_id() {
            return this.contract_id;
        }

        public void setContract_guid(String contract_guid) {
            this.contract_guid = contract_guid;
        }

        public void setContract_id(String contract_id) {
            this.contract_id = contract_id;
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
            return result;
        }

        public String toString() {
            return "setStateToCloseDTO.ContractDTO(contract_guid=" + this.getContract_guid() + ", contract_id=" + this.getContract_id() + ")";
        }
    }
}
