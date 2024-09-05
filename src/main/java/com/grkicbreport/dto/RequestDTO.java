package com.grkicbreport.dto;

import lombok.*;

@Data
public class RequestDTO {
    private String contractNumber;
    private String work;

    private String Loan_line;
    private String decisionNumber;
    private String decisionDate;

    private String agreement_id;
    private String agreement_number;
    private String agreement_date_begin;
    private String agreement_date_end;
    private String agreement_subject_type;
    private String agreement_inn_pinfl;
    private String agreement_name;
    private String agreement_amount;

    private String provisionNumber;
    private String provisionDate;
    private String nibbd;

    private String decide_number;
    private String decide_date;
    private String conclusion;
    private String send_date;

    private String type;
    private String number;
    private String date;

}
