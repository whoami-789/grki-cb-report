package com.grkicbreport.dto;

import java.time.LocalDate;

public record MahallaRowDTO(
        Integer dictCode,
        String inn,
        String name,
        String code,
        String regionName,
        String regionCbCode,
        String districtName,
        Integer sectorNo,
        String districtSoato,
        String districtCb,
        LocalDate dateActive,
        LocalDate dateEnd,
        String activeFlag
) {
    public boolean isActive() {
        return "A".equalsIgnoreCase(activeFlag);
    }
}