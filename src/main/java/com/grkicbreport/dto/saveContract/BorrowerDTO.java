package com.grkicbreport.dto.saveContract;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BorrowerDTO {
   private DirectorDTO director;
   private CapitalDTO capital;
   private List<FoundersDTO> founders;
}
