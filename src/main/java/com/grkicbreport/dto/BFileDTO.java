package com.grkicbreport.dto;

import java.util.List;

public class BFileDTO {
    private String bFileName;
    private List<RecordDTO> errorRecords;

    public BFileDTO() {}

    public BFileDTO(String bFileName, List<RecordDTO> errorRecords) {
        this.bFileName = bFileName;
        this.errorRecords = errorRecords;
    }

    // Геттеры и сеттеры

    public String getbFileName() {
        return bFileName;
    }

    public void setbFileName(String bFileName) {
        this.bFileName = bFileName;
    }

    public List<RecordDTO> getErrorRecords() {
        return errorRecords;
    }

    public void setErrorRecords(List<RecordDTO> errorRecords) {
        this.errorRecords = errorRecords;
    }
}
