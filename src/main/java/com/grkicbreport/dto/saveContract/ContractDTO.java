package com.grkicbreport.dto.saveContract;

import lombok.*;

import java.util.List;

@Data
public class ContractDTO {
    private String loan_type;
    private String issue_mode;
    private String financing_line;
    private String factoring_type;
    private String guarantee_type;
    private String borrower_class;
    private String asset_quality;
    private String number;
    private String date_begin;
    private String date_end;
    private String currency;
    private String amount;
    private PercentDTO percent;
    private List<CompensationsDTO> compensations;
    private String currency_first;
    private String amount_first;
    private String amount_obligations;
    private String discont_comissions;
    private String currency_commission;
    private String amount_commission;
    private String guarantee_info;

    public ContractDTO() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractDTO;
    }

}
