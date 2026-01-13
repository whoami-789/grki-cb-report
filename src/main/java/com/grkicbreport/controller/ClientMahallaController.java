package com.grkicbreport.controller;

import com.grkicbreport.service.ClientMahallaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientMahallaController {

    private final ClientMahallaService service;

    @PostMapping("/{clientCode}/mahalla")
    public ResponseEntity<?> saveMahalla(
            @PathVariable String clientCode,
            @RequestBody Map<String, String> body
    ) {
        String mahallaCode = body.get("mahallaCode");

        service.saveMahalla(clientCode, mahallaCode);

        return ResponseEntity.ok(Map.of("status", "OK"));
    }
}