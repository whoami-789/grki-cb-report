package com.grkicbreport.dto.saveContract;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DirectorDTO {
    private String inn;
    private String pinfl;
    private String full_name;
    private String birth_date;
    private String gender;
    private String citizenship;
    private String country;
    private String area;
    private String region;
    private String doc_type;
    private String doc_seria;
    private String doc_number;
    private String doc_date;
    private String doc_issuer;
    private ContactsDTO contacts;
}
