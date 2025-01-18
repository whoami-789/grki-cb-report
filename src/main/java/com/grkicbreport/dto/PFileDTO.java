package com.grkicbreport.dto;

import java.util.List;

public class PFileDTO {
    private String pFileName;
    private List<String> lines; // Хранит извлечённые строки

    public PFileDTO() {}

    public PFileDTO(String pFileName, List<String> lines) {
        this.pFileName = pFileName;
        this.lines = lines;
    }

    // Getters and Setters

    public String getpFileName() {
        return pFileName;
    }

    public void setpFileName(String pFileName) {
        this.pFileName = pFileName;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
}
