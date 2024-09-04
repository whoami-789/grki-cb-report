package com.grkicbreport.dto.setStateToLitigation;

import com.grkicbreport.dto.CreditorDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class setStateToLitigationDTO {
    private String save_mode;
    private CreditorDTO creditor;
    private Contract contract;
    private LitigationBasis litigation_basis;



    @Data
    @NoArgsConstructor
    public static class Contract {
        private String contract_guid;
        private String contract_id;
    }

    @Data
    @NoArgsConstructor
    public static class LitigationBasis {
        private String decide;
        private String decide_number;
        private String decide_date;
        private String decide_chief;
        private String conclusion;
        private String send_date;
    }
}

