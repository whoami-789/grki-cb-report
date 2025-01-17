package com.grkicbreport.dto;

import java.util.List;

public class PFileDTO {
    private String pFileName;
    private List<RecordDTO> errorRecords;

    public PFileDTO() {}

    public PFileDTO(String pFileName, List<RecordDTO> errorRecords) {
        this.pFileName = pFileName;
        this.errorRecords = errorRecords;
    }

    // Геттеры и сеттеры

    public String getpFileName() {
        return pFileName;
    }

    public void setpFileName(String pFileName) {
        this.pFileName = pFileName;
    }

    public List<RecordDTO> getErrorRecords() {
        return errorRecords;
    }

    public void setErrorRecords(List<RecordDTO> errorRecords) {
        this.errorRecords = errorRecords;
    }
}
