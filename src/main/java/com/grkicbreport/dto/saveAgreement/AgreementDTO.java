package com.grkicbreport.dto.saveAgreement;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AgreementDTO {
    private String agreement_guid;
    private String agreement_id;
    private String number;
    private String date_begin;
    private String date_end;
    private String subject_type;
    private String pinfl;
    private String inn;
    private String resident;
    private String name;
    private String currency;
    private String amount;
}
