package com.grkicbreport.dto;

import java.time.LocalDate;

public record SprMahallaRowDTO(
        String uzKad,          // "Код УзКад"
        String code1c,         // "Код 1C"
        String uzKad2,         // второй "Код УзКад" (если так в файле)
        String mahallaInn,     // "ИНН махалли"
        String soatoRegion,    // "СОАТО область"
        String soatoDistrict,  // "СОАТО район"
        String cbDistrict,     // "Код района ЦБ"
        String nameUz,
        String nameRu,
        String nameEn,
        LocalDate dateActive,
        LocalDate dateEnd,
        String activeFlag
) {}