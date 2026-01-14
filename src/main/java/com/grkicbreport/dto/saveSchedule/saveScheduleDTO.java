package com.grkicbreport.dto.saveSchedule;

import com.grkicbreport.dto.CreditorDTO;
import lombok.Data;

import java.util.List;

@Data
public class saveScheduleDTO {

    private String save_mode;
    private CreditorDTO creditor;
    private Contract contract;
    private List<Repayment> repayments;
    private ChangeBasis change_basis;
    private Prolongation prolongation;

    public saveScheduleDTO() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof saveScheduleDTO;
    }

    @Data
    public static class Contract {
        private String contract_guid;
        private String contract_id;

        public Contract() {
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Contract;
        }

    }

    @Data
    public static class Repayment {
        private String date_percent;
        private String amount_percent;
        private String date_main;
        private String amount_main;
        private String date_prolong_percent;
        private String amount_prolong_percent;
        private String amount_amortization;
        private String date_amortization;

        public Repayment() {
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Repayment;
        }

    }

    @Data
    public static class ChangeBasis {
        private String revisor;
        private String number;
        private String date;
        private String reason;
        private String revisor_chief;

        public ChangeBasis() {
        }

        protected boolean canEqual(final Object other) {
            return other instanceof ChangeBasis;
        }

    }

    @Data
    public static class Prolongation {
        private String date_prolongation;
        private String order_number;

        public Prolongation() {
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Prolongation;
        }

    }
}
