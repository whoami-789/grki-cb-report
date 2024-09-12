package com.grkicbreport.dto;

import lombok.*;

import java.time.LocalDate;

@Data
public class RequestDTO {
    private String contractNumber;

    private String loan_line;
    private String decisionNumber;
    private LocalDate decisionDate;

    private String agreement_id;
    private String agreement_number;
    private LocalDate agreement_date_begin;
    private LocalDate agreement_date_end;
    private String agreement_subject_type;
    private String agreement_inn_pinfl;
    private String agreement_name;
    private String agreement_amount;

    private String provisionNumber;
    private LocalDate provisionDate;
    private String nibbd;
    private String engine_number;
    private String body_number;
    private String year;
    private String state_number;
    private String model;
    private String chassis_number;
    private String color;
    private String doc_seria_number;
    private String vin_number;

    private String decide_number;
    private LocalDate decide_date;
    private String conclusion;
    private LocalDate send_date;

    private String type;
    private String number;
    private LocalDate date;

}

