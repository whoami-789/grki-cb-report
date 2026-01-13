package com.grkicbreport.controller;

import com.grkicbreport.service.MahallaImportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/grki/mahalla")
public class MahallaAdminController {

    private final MahallaImportService importService;

    @PostMapping("/import")
    public ResponseEntity<?> importMahalla(@RequestParam("file") MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Файл не передан или пустой"));
        }

        String filename = file.getOriginalFilename();
        if (filename == null || !filename.toLowerCase().endsWith(".xlsx")) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Поддерживаются только файлы .xlsx"));
        }

        try {
            log.info("Mahalla import started, file={}", filename);

            int count = importService.importExcel(file);

            log.info("Mahalla import finished, processed={}", count);

            return ResponseEntity.ok(
                    Map.of(
                            "processed", count,
                            "status", "OK"
                    )
            );

        } catch (Exception e) {
            log.error("Mahalla import failed", e);

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            Map.of(
                                    "status", "ERROR",
                                    "message", e.getMessage()
                            )
                    );
        }
    }
}