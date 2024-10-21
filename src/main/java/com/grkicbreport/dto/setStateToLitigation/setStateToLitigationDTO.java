package com.grkicbreport.dto.setStateToLitigation;

import com.grkicbreport.dto.CreditorDTO;

public class setStateToLitigationDTO {
    private String save_mode;
    private CreditorDTO creditor;
    private Contract contract;
    private LitigationBasis litigation_basis;

    public setStateToLitigationDTO() {
    }

    public String getSave_mode() {
        return this.save_mode;
    }

    public CreditorDTO getCreditor() {
        return this.creditor;
    }

    public Contract getContract() {
        return this.contract;
    }

    public LitigationBasis getLitigation_basis() {
        return this.litigation_basis;
    }

    public void setSave_mode(String save_mode) {
        this.save_mode = save_mode;
    }

    public void setCreditor(CreditorDTO creditor) {
        this.creditor = creditor;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public void setLitigation_basis(LitigationBasis litigation_basis) {
        this.litigation_basis = litigation_basis;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof setStateToLitigationDTO)) return false;
        final setStateToLitigationDTO other = (setStateToLitigationDTO) o;
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
        final Object this$litigation_basis = this.getLitigation_basis();
        final Object other$litigation_basis = other.getLitigation_basis();
        if (this$litigation_basis == null ? other$litigation_basis != null : !this$litigation_basis.equals(other$litigation_basis))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof setStateToLitigationDTO;
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
        final Object $litigation_basis = this.getLitigation_basis();
        result = result * PRIME + ($litigation_basis == null ? 43 : $litigation_basis.hashCode());
        return result;
    }

    public String toString() {
        return "setStateToLitigationDTO(save_mode=" + this.getSave_mode() + ", creditor=" + this.getCreditor() + ", contract=" + this.getContract() + ", litigation_basis=" + this.getLitigation_basis() + ")";
    }


    public static class Contract {
        private String contract_guid;
        private String contract_id;

        public Contract() {
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
            if (!(o instanceof Contract)) return false;
            final Contract other = (Contract) o;
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
            return other instanceof Contract;
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
            return "setStateToLitigationDTO.Contract(contract_guid=" + this.getContract_guid() + ", contract_id=" + this.getContract_id() + ")";
        }
    }

    public static class LitigationBasis {
        private String decide;
        private String decide_number;
        private String decide_date;
        private String decide_chief;
        private String conclusion;
        private String send_date;

        public LitigationBasis() {
        }

        public String getDecide() {
            return this.decide;
        }

        public String getDecide_number() {
            return this.decide_number;
        }

        public String getDecide_date() {
            return this.decide_date;
        }

        public String getDecide_chief() {
            return this.decide_chief;
        }

        public String getConclusion() {
            return this.conclusion;
        }

        public String getSend_date() {
            return this.send_date;
        }

        public void setDecide(String decide) {
            this.decide = decide;
        }

        public void setDecide_number(String decide_number) {
            this.decide_number = decide_number;
        }

        public void setDecide_date(String decide_date) {
            this.decide_date = decide_date;
        }

        public void setDecide_chief(String decide_chief) {
            this.decide_chief = decide_chief;
        }

        public void setConclusion(String conclusion) {
            this.conclusion = conclusion;
        }

        public void setSend_date(String send_date) {
            this.send_date = send_date;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof LitigationBasis)) return false;
            final LitigationBasis other = (LitigationBasis) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$decide = this.getDecide();
            final Object other$decide = other.getDecide();
            if (this$decide == null ? other$decide != null : !this$decide.equals(other$decide)) return false;
            final Object this$decide_number = this.getDecide_number();
            final Object other$decide_number = other.getDecide_number();
            if (this$decide_number == null ? other$decide_number != null : !this$decide_number.equals(other$decide_number))
                return false;
            final Object this$decide_date = this.getDecide_date();
            final Object other$decide_date = other.getDecide_date();
            if (this$decide_date == null ? other$decide_date != null : !this$decide_date.equals(other$decide_date))
                return false;
            final Object this$decide_chief = this.getDecide_chief();
            final Object other$decide_chief = other.getDecide_chief();
            if (this$decide_chief == null ? other$decide_chief != null : !this$decide_chief.equals(other$decide_chief))
                return false;
            final Object this$conclusion = this.getConclusion();
            final Object other$conclusion = other.getConclusion();
            if (this$conclusion == null ? other$conclusion != null : !this$conclusion.equals(other$conclusion))
                return false;
            final Object this$send_date = this.getSend_date();
            final Object other$send_date = other.getSend_date();
            if (this$send_date == null ? other$send_date != null : !this$send_date.equals(other$send_date))
                return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof LitigationBasis;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $decide = this.getDecide();
            result = result * PRIME + ($decide == null ? 43 : $decide.hashCode());
            final Object $decide_number = this.getDecide_number();
            result = result * PRIME + ($decide_number == null ? 43 : $decide_number.hashCode());
            final Object $decide_date = this.getDecide_date();
            result = result * PRIME + ($decide_date == null ? 43 : $decide_date.hashCode());
            final Object $decide_chief = this.getDecide_chief();
            result = result * PRIME + ($decide_chief == null ? 43 : $decide_chief.hashCode());
            final Object $conclusion = this.getConclusion();
            result = result * PRIME + ($conclusion == null ? 43 : $conclusion.hashCode());
            final Object $send_date = this.getSend_date();
            result = result * PRIME + ($send_date == null ? 43 : $send_date.hashCode());
            return result;
        }

        public String toString() {
            return "setStateToLitigationDTO.LitigationBasis(decide=" + this.getDecide() + ", decide_number=" + this.getDecide_number() + ", decide_date=" + this.getDecide_date() + ", decide_chief=" + this.getDecide_chief() + ", conclusion=" + this.getConclusion() + ", send_date=" + this.getSend_date() + ")";
        }
    }
}

