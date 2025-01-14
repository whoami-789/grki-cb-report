package com.grkicbreport.dto;

public class RecordDTO {
    private Long number;
    private String date;
    private String line;

    // Конструкторы
    public RecordDTO() {}

    public RecordDTO(Long number, String date, String line) {
        this.number = number;
        this.date = date;
        this.line = line;
    }

    // Геттеры и сеттеры
    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
