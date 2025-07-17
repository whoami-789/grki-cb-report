package com.grkicbreport.dto;

import com.grkicbreport.model.Dok;

public class DokWithSource {
    private Dok dok;
    private String source; // "ls" или "lscor"

    public DokWithSource(Dok dok, String source) {
        this.dok = dok;
        this.source = source;
    }

    public Dok getDok() {
        return dok;
    }

    public String getSource() {
        return source;
    }
}
