package com.grkicbreport.dto;

import java.util.List;

public class RecordsResponseDTO {
    private List<ZipFileDTO> zipFiles;

    public RecordsResponseDTO() {}

    public RecordsResponseDTO(List<ZipFileDTO> zipFiles) {
        this.zipFiles = zipFiles;
    }

    // Геттеры и сеттеры

    public List<ZipFileDTO> getZipFiles() {
        return zipFiles;
    }

    public void setZipFiles(List<ZipFileDTO> zipFiles) {
        this.zipFiles = zipFiles;
    }
}
