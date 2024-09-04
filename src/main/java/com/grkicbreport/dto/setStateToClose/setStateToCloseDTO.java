package com.grkicbreport.dto.setStateToClose;

import com.grkicbreport.dto.CreditorDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class setStateToCloseDTO {

    private String save_mode;
    private CreditorDTO creditor;
    private ContractDTO contract;

    @Data
    @NoArgsConstructor
    public static class ContractDTO {
        private String contract_guid;
        private String contract_id;
    }
}
