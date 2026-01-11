package com.grkicbreport.controller;

import com.google.gson.JsonObject;

import com.grkicbreport.service.ClientCorrectService;
import com.grkicbreport.service.NumdogCorrectService;
import com.grkicbreport.service.ReportDataDogService;
import com.grkicbreport.service.ReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;


@RestController
@RequestMapping("/api/crif")
public class CRIFReportController {

    private final ReportService kreditService;
    private final ClientCorrectService clientCorrectService;
    private final NumdogCorrectService numdogCorrectService;
    private final ReportDataDogService reportDataDogService;

    public CRIFReportController(ReportService kreditService, ClientCorrectService clientCorrectService, NumdogCorrectService numdogCorrectService, ReportDataDogService reportDataDogService) {
        this.kreditService = kreditService;
        this.clientCorrectService = clientCorrectService;
        this.numdogCorrectService = numdogCorrectService;
        this.reportDataDogService = reportDataDogService;
    }

//    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<String> generateReport(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        File reportFile = kreditService.getReport(startDate, endDate);

        // Проверка наличия файла
        if (reportFile != null && reportFile.exists()) {
            try {
                byte[] fileContent = Files.readAllBytes(reportFile.toPath());
                String fileName = reportFile.getName();

                // Преобразование содержимого файла в строку base64
                String base64Content = Base64.getEncoder().encodeToString(fileContent);

                // Создание объекта JSON с именем файла и его содержимым в формате base64
                JsonObject responseData = new JsonObject();
                responseData.addProperty("fileName", fileName);
                responseData.addProperty("fileContent", base64Content);

                // Отправка ответа
                return ResponseEntity.ok(responseData.toString());
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Arrays.toString("Error reading report file".getBytes()));
            }
        }

        // Если файл не найден, возвращаем ошибку
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Arrays.toString("Report file not found".getBytes()));
    }

//    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/datadog")
    public ResponseEntity<String> generateReportByDataDog(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        // Генерация файла отчета
        File reportFile = reportDataDogService.getReport(startDate, endDate);

        // Проверка наличия файла
        if (reportFile != null && reportFile.exists()) {
            try {
                byte[] fileContent = Files.readAllBytes(reportFile.toPath());
                String fileName = reportFile.getName();

                // Преобразование содержимого файла в строку base64
                String base64Content = Base64.getEncoder().encodeToString(fileContent);

                // Создание объекта JSON с именем файла и его содержимым в формате base64
                JsonObject responseData = new JsonObject();
                responseData.addProperty("fileName", fileName);
                responseData.addProperty("fileContent", base64Content);

                // Отправка ответа
                return ResponseEntity.ok(responseData.toString());
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Arrays.toString("Error reading report file".getBytes()));
            }
        }

        // Если файл не найден, возвращаем ошибку
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Arrays.toString("Report file not found".getBytes()));
    }


//    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/numdog")
    public ResponseEntity<String> generateReportByNumdog(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam("numdog") String numdog) {
        File reportFile = numdogCorrectService.getNumdog(startDate, endDate, numdog);

        // Проверка наличия файла
        if (reportFile != null && reportFile.exists()) {
            try {
                byte[] fileContent = Files.readAllBytes(reportFile.toPath());
                String fileName = reportFile.getName();

                // Преобразование содержимого файла в строку base64
                String base64Content = Base64.getEncoder().encodeToString(fileContent);

                // Создание объекта JSON с именем файла и его содержимым в формате base64
                JsonObject responseData = new JsonObject();
                responseData.addProperty("fileName", fileName);
                responseData.addProperty("fileContent", base64Content);

                // Отправка ответа
                return ResponseEntity.ok(responseData.toString());
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Arrays.toString("Error reading report file".getBytes()));
            }
        }

        // Если файл не найден, возвращаем ошибку
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Arrays.toString("Report file not found".getBytes()));
    }

//    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/client")
    public ResponseEntity<String> generateReportByKodchlen(
            @RequestParam("kodchlen") String kodchlen) {
        File reportFile = clientCorrectService.getClientsWithDetails(kodchlen);

        // Проверка наличия файла
        if (reportFile != null && reportFile.exists()) {
            try {
                byte[] fileContent = Files.readAllBytes(reportFile.toPath());
                String fileName = reportFile.getName();

                // Преобразование содержимого файла в строку base64
                String base64Content = Base64.getEncoder().encodeToString(fileContent);

                // Создание объекта JSON с именем файла и его содержимым в формате base64
                JsonObject responseData = new JsonObject();
                responseData.addProperty("fileName", fileName);
                responseData.addProperty("fileContent", base64Content);

                // Отправка ответа
                return ResponseEntity.ok(responseData.toString());
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Arrays.toString("Error reading report file".getBytes()));
            }
        }

        // Если файл не найден, возвращаем ошибку
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Arrays.toString("Report file not found".getBytes()));
    }
}
