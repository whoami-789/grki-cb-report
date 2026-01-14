package com.grkicbreport.dto.saveContract;

import lombok.*;

import java.util.List;

@Data
public class ContractDTO {
    private String loan_type;
    private String issue_mode;
    private String financing_line;
    private String factoring_type;
    private String factoring_sort;
    private String legal_form_factoring;
    private String factoring_structure;
    private String factoring_main_type;
    private String guarantee_type;
    private String letter_of_credit;
    private String settlement_method;
    private String order_of_changes;
    private String type_of_operation;
    private String target_letter_of_credit;
    private String swift_code;
    private String asset_quality;
    private String number;
    private String date_begin;
    private String date_end;
    private String currency;
    private String amount;
    private String account;
    private String effective_percent;
    private String mortgage;
    private String business_project_info ;
    private PercentDTO percent;
    private List<CompensationsDTO> compensations;



    public ContractDTO() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractDTO;
    }

}
