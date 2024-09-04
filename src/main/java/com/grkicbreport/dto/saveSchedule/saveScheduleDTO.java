package com.grkicbreport.dto.saveSchedule;

import com.grkicbreport.dto.CreditorDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class saveScheduleDTO {
    
    private String save_mode;
    private CreditorDTO creditor;
    private Contract contract;
    private List<Repayment> repayments;
    private ChangeBasis change_basis;
    private Prolongation prolongation;

    @Data
    @NoArgsConstructor
    public static class Contract {
        private String contract_guid;
        private String contract_id;
    }

    @Data
    @NoArgsConstructor
    public static class Repayment {
        private String date_percent;
        private String amount_percent;
        private String date_main;
        private String amount_main;
        private String date_prolong_percent;
        private String amount_prolong_percent;
    }

    @Data
    @NoArgsConstructor
    public static class ChangeBasis {
        private String revisor;
        private String number;
        private String date;
        private String reason;
        private String revisor_chief;
    }

    @Data
    @NoArgsConstructor
    public static class Prolongation {
        private String date_prolongation;
        private String order_number;
    }
}
