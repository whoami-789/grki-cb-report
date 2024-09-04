package com.grkicbreport.dto.saveCourtDecision;

import com.grkicbreport.dto.CreditorDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class saveCourtDecisionDTO {

    private String save_mode;
    private CreditorDTO creditor;
    private Contract contract;
    private Court_decision court_decision;

    @Data
    @NoArgsConstructor
    public static class Creditor {
        private String type;
        private String code;
        private String office;
    }

    @Data
    @NoArgsConstructor
    public static class Contract {
        private String contract_guid;
        private String contract_id;
    }

    @Data
    @NoArgsConstructor
    public static class Court_decision {
        private String type;
        private String number;
        private String date;
    }
}
