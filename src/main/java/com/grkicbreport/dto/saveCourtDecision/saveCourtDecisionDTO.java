package com.grkicbreport.dto.saveCourtDecision;

import com.grkicbreport.dto.CreditorDTO;

public class saveCourtDecisionDTO {

    private String save_mode;
    private CreditorDTO creditor;
    private Contract contract;
    private Court_decision court_decision;

    public saveCourtDecisionDTO() {
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

    public Court_decision getCourt_decision() {
        return this.court_decision;
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

    public void setCourt_decision(Court_decision court_decision) {
        this.court_decision = court_decision;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof saveCourtDecisionDTO)) return false;
        final saveCourtDecisionDTO other = (saveCourtDecisionDTO) o;
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
        final Object this$court_decision = this.getCourt_decision();
        final Object other$court_decision = other.getCourt_decision();
        if (this$court_decision == null ? other$court_decision != null : !this$court_decision.equals(other$court_decision))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof saveCourtDecisionDTO;
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
        final Object $court_decision = this.getCourt_decision();
        result = result * PRIME + ($court_decision == null ? 43 : $court_decision.hashCode());
        return result;
    }

    public String toString() {
        return "saveCourtDecisionDTO(save_mode=" + this.getSave_mode() + ", creditor=" + this.getCreditor() + ", contract=" + this.getContract() + ", court_decision=" + this.getCourt_decision() + ")";
    }

    public static class Creditor {
        private String type;
        private String code;
        private String office;

        public Creditor() {
        }

        public String getType() {
            return this.type;
        }

        public String getCode() {
            return this.code;
        }

        public String getOffice() {
            return this.office;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setOffice(String office) {
            this.office = office;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Creditor)) return false;
            final Creditor other = (Creditor) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$type = this.getType();
            final Object other$type = other.getType();
            if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
            final Object this$code = this.getCode();
            final Object other$code = other.getCode();
            if (this$code == null ? other$code != null : !this$code.equals(other$code)) return false;
            final Object this$office = this.getOffice();
            final Object other$office = other.getOffice();
            if (this$office == null ? other$office != null : !this$office.equals(other$office)) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Creditor;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $type = this.getType();
            result = result * PRIME + ($type == null ? 43 : $type.hashCode());
            final Object $code = this.getCode();
            result = result * PRIME + ($code == null ? 43 : $code.hashCode());
            final Object $office = this.getOffice();
            result = result * PRIME + ($office == null ? 43 : $office.hashCode());
            return result;
        }

        public String toString() {
            return "saveCourtDecisionDTO.Creditor(type=" + this.getType() + ", code=" + this.getCode() + ", office=" + this.getOffice() + ")";
        }
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
            return "saveCourtDecisionDTO.Contract(contract_guid=" + this.getContract_guid() + ", contract_id=" + this.getContract_id() + ")";
        }
    }

    public static class Court_decision {
        private String type;
        private String number;
        private String date;

        public Court_decision() {
        }

        public String getType() {
            return this.type;
        }

        public String getNumber() {
            return this.number;
        }

        public String getDate() {
            return this.date;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Court_decision)) return false;
            final Court_decision other = (Court_decision) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$type = this.getType();
            final Object other$type = other.getType();
            if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
            final Object this$number = this.getNumber();
            final Object other$number = other.getNumber();
            if (this$number == null ? other$number != null : !this$number.equals(other$number)) return false;
            final Object this$date = this.getDate();
            final Object other$date = other.getDate();
            if (this$date == null ? other$date != null : !this$date.equals(other$date)) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Court_decision;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $type = this.getType();
            result = result * PRIME + ($type == null ? 43 : $type.hashCode());
            final Object $number = this.getNumber();
            result = result * PRIME + ($number == null ? 43 : $number.hashCode());
            final Object $date = this.getDate();
            result = result * PRIME + ($date == null ? 43 : $date.hashCode());
            return result;
        }

        public String toString() {
            return "saveCourtDecisionDTO.Court_decision(type=" + this.getType() + ", number=" + this.getNumber() + ", date=" + this.getDate() + ")";
        }
    }
}
