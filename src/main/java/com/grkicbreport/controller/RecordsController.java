package com.grkicbreport.controller;

import com.grkicbreport.dto.RecordDTO;
import com.grkicbreport.dto.RecordsResponseDTO;
import com.grkicbreport.dto.ZipFileDTO;
import com.grkicbreport.service.ZipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class RecordsController {

    private final ZipService zipService;

    @Autowired
    public RecordsController(ZipService zipService) {
        this.zipService = zipService;
    }

    /**
     * Получение записей по дате.
     *
     * @return Список записей
     */
    @GetMapping
    public RecordsResponseDTO getAllRecords() {
        return zipService.getAllRecords();
    }
}

