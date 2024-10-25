package com.grkicbreport.dto.saveSchedule;

import com.grkicbreport.dto.CreditorDTO;

import java.util.List;

public class saveScheduleDTO {

    private String save_mode;
    private CreditorDTO creditor;
    private Contract contract;
    private List<Repayment> repayments;
    private ChangeBasis change_basis;
    private Prolongation prolongation;

    public saveScheduleDTO() {
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

    public List<Repayment> getRepayments() {
        return this.repayments;
    }

    public ChangeBasis getChange_basis() {
        return this.change_basis;
    }

    public Prolongation getProlongation() {
        return this.prolongation;
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

    public void setRepayments(List<Repayment> repayments) {
        this.repayments = repayments;
    }

    public void setChange_basis(ChangeBasis change_basis) {
        this.change_basis = change_basis;
    }

    public void setProlongation(Prolongation prolongation) {
        this.prolongation = prolongation;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof saveScheduleDTO)) return false;
        final saveScheduleDTO other = (saveScheduleDTO) o;
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
        final Object this$repayments = this.getRepayments();
        final Object other$repayments = other.getRepayments();
        if (this$repayments == null ? other$repayments != null : !this$repayments.equals(other$repayments))
            return false;
        final Object this$change_basis = this.getChange_basis();
        final Object other$change_basis = other.getChange_basis();
        if (this$change_basis == null ? other$change_basis != null : !this$change_basis.equals(other$change_basis))
            return false;
        final Object this$prolongation = this.getProlongation();
        final Object other$prolongation = other.getProlongation();
        if (this$prolongation == null ? other$prolongation != null : !this$prolongation.equals(other$prolongation))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof saveScheduleDTO;
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
        final Object $repayments = this.getRepayments();
        result = result * PRIME + ($repayments == null ? 43 : $repayments.hashCode());
        final Object $change_basis = this.getChange_basis();
        result = result * PRIME + ($change_basis == null ? 43 : $change_basis.hashCode());
        final Object $prolongation = this.getProlongation();
        result = result * PRIME + ($prolongation == null ? 43 : $prolongation.hashCode());
        return result;
    }

    public String toString() {
        return "saveScheduleDTO(save_mode=" + this.getSave_mode() + ", creditor=" + this.getCreditor() + ", contract=" + this.getContract() + ", repayments=" + this.getRepayments() + ", change_basis=" + this.getChange_basis() + ", prolongation=" + this.getProlongation() + ")";
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
            return "saveScheduleDTO.Contract(contract_guid=" + this.getContract_guid() + ", contract_id=" + this.getContract_id() + ")";
        }
    }

    public static class Repayment {
        private String date_percent;
        private String amount_percent;
        private String date_main;
        private String amount_main;
        private String date_prolong_percent;
        private String amount_prolong_percent;

        public Repayment() {
        }

        public String getDate_percent() {
            return this.date_percent;
        }

        public String getAmount_percent() {
            return this.amount_percent;
        }

        public String getDate_main() {
            return this.date_main;
        }

        public String getAmount_main() {
            return this.amount_main;
        }

        public String getDate_prolong_percent() {
            return this.date_prolong_percent;
        }

        public String getAmount_prolong_percent() {
            return this.amount_prolong_percent;
        }

        public void setDate_percent(String date_percent) {
            this.date_percent = date_percent;
        }

        public void setAmount_percent(String amount_percent) {
            this.amount_percent = amount_percent;
        }

        public void setDate_main(String date_main) {
            this.date_main = date_main;
        }

        public void setAmount_main(String amount_main) {
            this.amount_main = amount_main;
        }

        public void setDate_prolong_percent(String date_prolong_percent) {
            this.date_prolong_percent = date_prolong_percent;
        }

        public void setAmount_prolong_percent(String amount_prolong_percent) {
            this.amount_prolong_percent = amount_prolong_percent;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Repayment)) return false;
            final Repayment other = (Repayment) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$date_percent = this.getDate_percent();
            final Object other$date_percent = other.getDate_percent();
            if (this$date_percent == null ? other$date_percent != null : !this$date_percent.equals(other$date_percent))
                return false;
            final Object this$amount_percent = this.getAmount_percent();
            final Object other$amount_percent = other.getAmount_percent();
            if (this$amount_percent == null ? other$amount_percent != null : !this$amount_percent.equals(other$amount_percent))
                return false;
            final Object this$date_main = this.getDate_main();
            final Object other$date_main = other.getDate_main();
            if (this$date_main == null ? other$date_main != null : !this$date_main.equals(other$date_main))
                return false;
            final Object this$amount_main = this.getAmount_main();
            final Object other$amount_main = other.getAmount_main();
            if (this$amount_main == null ? other$amount_main != null : !this$amount_main.equals(other$amount_main))
                return false;
            final Object this$date_prolong_percent = this.getDate_prolong_percent();
            final Object other$date_prolong_percent = other.getDate_prolong_percent();
            if (this$date_prolong_percent == null ? other$date_prolong_percent != null : !this$date_prolong_percent.equals(other$date_prolong_percent))
                return false;
            final Object this$amount_prolong_percent = this.getAmount_prolong_percent();
            final Object other$amount_prolong_percent = other.getAmount_prolong_percent();
            if (this$amount_prolong_percent == null ? other$amount_prolong_percent != null : !this$amount_prolong_percent.equals(other$amount_prolong_percent))
                return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Repayment;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $date_percent = this.getDate_percent();
            result = result * PRIME + ($date_percent == null ? 43 : $date_percent.hashCode());
            final Object $amount_percent = this.getAmount_percent();
            result = result * PRIME + ($amount_percent == null ? 43 : $amount_percent.hashCode());
            final Object $date_main = this.getDate_main();
            result = result * PRIME + ($date_main == null ? 43 : $date_main.hashCode());
            final Object $amount_main = this.getAmount_main();
            result = result * PRIME + ($amount_main == null ? 43 : $amount_main.hashCode());
            final Object $date_prolong_percent = this.getDate_prolong_percent();
            result = result * PRIME + ($date_prolong_percent == null ? 43 : $date_prolong_percent.hashCode());
            final Object $amount_prolong_percent = this.getAmount_prolong_percent();
            result = result * PRIME + ($amount_prolong_percent == null ? 43 : $amount_prolong_percent.hashCode());
            return result;
        }

        public String toString() {
            return "saveScheduleDTO.Repayment(date_percent=" + this.getDate_percent() + ", amount_percent=" + this.getAmount_percent() + ", date_main=" + this.getDate_main() + ", amount_main=" + this.getAmount_main() + ", date_prolong_percent=" + this.getDate_prolong_percent() + ", amount_prolong_percent=" + this.getAmount_prolong_percent() + ")";
        }
    }

    public static class ChangeBasis {
        private String revisor;
        private String number;
        private String date;
        private String reason;
        private String revisor_chief;

        public ChangeBasis() {
        }

        public String getRevisor() {
            return this.revisor;
        }

        public String getNumber() {
            return this.number;
        }

        public String getDate() {
            return this.date;
        }

        public String getReason() {
            return this.reason;
        }

        public String getRevisor_chief() {
            return this.revisor_chief;
        }

        public void setRevisor(String revisor) {
            this.revisor = revisor;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public void setRevisor_chief(String revisor_chief) {
            this.revisor_chief = revisor_chief;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof ChangeBasis)) return false;
            final ChangeBasis other = (ChangeBasis) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$revisor = this.getRevisor();
            final Object other$revisor = other.getRevisor();
            if (this$revisor == null ? other$revisor != null : !this$revisor.equals(other$revisor)) return false;
            final Object this$number = this.getNumber();
            final Object other$number = other.getNumber();
            if (this$number == null ? other$number != null : !this$number.equals(other$number)) return false;
            final Object this$date = this.getDate();
            final Object other$date = other.getDate();
            if (this$date == null ? other$date != null : !this$date.equals(other$date)) return false;
            final Object this$reason = this.getReason();
            final Object other$reason = other.getReason();
            if (this$reason == null ? other$reason != null : !this$reason.equals(other$reason)) return false;
            final Object this$revisor_chief = this.getRevisor_chief();
            final Object other$revisor_chief = other.getRevisor_chief();
            if (this$revisor_chief == null ? other$revisor_chief != null : !this$revisor_chief.equals(other$revisor_chief))
                return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof ChangeBasis;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $revisor = this.getRevisor();
            result = result * PRIME + ($revisor == null ? 43 : $revisor.hashCode());
            final Object $number = this.getNumber();
            result = result * PRIME + ($number == null ? 43 : $number.hashCode());
            final Object $date = this.getDate();
            result = result * PRIME + ($date == null ? 43 : $date.hashCode());
            final Object $reason = this.getReason();
            result = result * PRIME + ($reason == null ? 43 : $reason.hashCode());
            final Object $revisor_chief = this.getRevisor_chief();
            result = result * PRIME + ($revisor_chief == null ? 43 : $revisor_chief.hashCode());
            return result;
        }

        public String toString() {
            return "saveScheduleDTO.ChangeBasis(revisor=" + this.getRevisor() + ", number=" + this.getNumber() + ", date=" + this.getDate() + ", reason=" + this.getReason() + ", revisor_chief=" + this.getRevisor_chief() + ")";
        }
    }

    public static class Prolongation {
        private String date_prolongation;
        private String order_number;

        public Prolongation() {
        }

        public String getDate_prolongation() {
            return this.date_prolongation;
        }

        public String getOrder_number() {
            return this.order_number;
        }

        public void setDate_prolongation(String date_prolongation) {
            this.date_prolongation = date_prolongation;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Prolongation)) return false;
            final Prolongation other = (Prolongation) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$date_prolongation = this.getDate_prolongation();
            final Object other$date_prolongation = other.getDate_prolongation();
            if (this$date_prolongation == null ? other$date_prolongation != null : !this$date_prolongation.equals(other$date_prolongation))
                return false;
            final Object this$order_number = this.getOrder_number();
            final Object other$order_number = other.getOrder_number();
            if (this$order_number == null ? other$order_number != null : !this$order_number.equals(other$order_number))
                return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Prolongation;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $date_prolongation = this.getDate_prolongation();
            result = result * PRIME + ($date_prolongation == null ? 43 : $date_prolongation.hashCode());
            final Object $order_number = this.getOrder_number();
            result = result * PRIME + ($order_number == null ? 43 : $order_number.hashCode());
            return result;
        }

        public String toString() {
            return "saveScheduleDTO.Prolongation(date_prolongation=" + this.getDate_prolongation() + ", order_number=" + this.getOrder_number() + ")";
        }
    }
}
