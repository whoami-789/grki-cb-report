package com.grkicbreport.dto.saveContract;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ContactsDTO {
    private String post_address;
    private String post_index;
    private String phone;
    private String email;
}
