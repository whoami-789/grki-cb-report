package com.grkicbreport.dto;

import java.util.List;

public class ZipFileDTO {
    private String zipFileName;
    private String zipDate; // Добавлено поле для даты
    private List<RecordDTO> file008Records;
    private List<RecordDTO> file009Records;
    private List<PFileDTO> pFiles;
    private List<BFileDTO> bFiles;

    public ZipFileDTO() {}

    public ZipFileDTO(String zipFileName, String zipDate, List<RecordDTO> file008Records, List<RecordDTO> file009Records,
                      List<PFileDTO> pFiles, List<BFileDTO> bFiles) {
        this.zipFileName = zipFileName;
        this.zipDate = zipDate;
        this.file008Records = file008Records;
        this.file009Records = file009Records;
        this.pFiles = pFiles;
        this.bFiles = bFiles;
    }

    // Getters and Setters

    public String getZipFileName() {
        return zipFileName;
    }

    public void setZipFileName(String zipFileName) {
        this.zipFileName = zipFileName;
    }

    public String getZipDate() {
        return zipDate;
    }

    public void setZipDate(String zipDate) {
        this.zipDate = zipDate;
    }

    public List<RecordDTO> getFile008Records() {
        return file008Records;
    }

    public void setFile008Records(List<RecordDTO> file008Records) {
        this.file008Records = file008Records;
    }

    public List<RecordDTO> getFile009Records() {
        return file009Records;
    }

    public void setFile009Records(List<RecordDTO> file009Records) {
        this.file009Records = file009Records;
    }

    public List<PFileDTO> getpFiles() {
        return pFiles;
    }

    public void setpFiles(List<PFileDTO> pFiles) {
        this.pFiles = pFiles;
    }

    public List<BFileDTO> getbFiles() {
        return bFiles;
    }

    public void setbFiles(List<BFileDTO> bFiles) {
        this.bFiles = bFiles;
    }
}
