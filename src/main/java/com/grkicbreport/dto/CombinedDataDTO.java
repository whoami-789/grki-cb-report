package com.grkicbreport.dto;

import com.grkicbreport.model.AzolikFiz;
import com.grkicbreport.model.Dokument;
import com.grkicbreport.model.Kredit;
import lombok.Data;

@Data
public class CombinedDataDTO {
    private Dokument dok;
    private Kredit kredit;
    private AzolikFiz azolikFiz;

    // Конструкторы, геттеры и сеттеры
}
