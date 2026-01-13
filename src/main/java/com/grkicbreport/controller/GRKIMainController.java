package com.grkicbreport.controller;

import com.grkicbreport.dto.KreditDTO;
import com.grkicbreport.dto.RequestDTO;
import com.grkicbreport.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/grki")
public class GRKIMainController {
    private final SaveClaimService saveClaimService;
    private final SaveContractService saveContractService;
    private final SaveProvisionService saveProvisionService;
    private final saveScheduleService saveScheduleService;
    private final setStateToLitigationService setStateToLitigationService;
    private final saveCourtDecisionService saveCourtDecisionService;
    private final setStateToCloseService setStateToCloseService;
    private final FileGeneratorService fileGeneratorService;
    private final getIdentityService getIdentityService;
    private final KreditService kreditService;


    public GRKIMainController(SaveClaimService saveClaimService, SaveContractService saveContractService, SaveProvisionService saveProvisionService, saveScheduleService saveScheduleService, setStateToLitigationService setStateToLitigationService, saveCourtDecisionService saveCourtDecisionService, setStateToCloseService setStateToCloseService, FileGeneratorService fileGeneratorService, com.grkicbreport.service.getIdentityService getIdentityService, KreditService kreditService) {
        this.saveClaimService = saveClaimService;
        this.saveContractService = saveContractService;
        this.saveProvisionService = saveProvisionService;
        this.saveScheduleService = saveScheduleService;
        this.setStateToLitigationService = setStateToLitigationService;
        this.saveCourtDecisionService = saveCourtDecisionService;
        this.setStateToCloseService = setStateToCloseService;
        this.fileGeneratorService = fileGeneratorService;
        this.getIdentityService = getIdentityService;
        this.kreditService = kreditService;
    }

    @PostMapping("/get-save-claim")
    public ResponseEntity<String> sendSaveClaim(@RequestBody RequestDTO requestDTO) {
        return saveClaimService.sendSaveClaim(requestDTO.getContractNumber(), requestDTO.getSave_mode());
    }

    @PostMapping("/get-save-contract")
    public ResponseEntity<String> sendSaveContract(@RequestBody RequestDTO requestDTO) {
        return saveContractService.sendSaveContract(requestDTO.getContractNumber(), requestDTO.getSave_mode());
    }

    @PostMapping("/get-save-provision")
    public ResponseEntity<String> sendSaveProvision(@RequestBody RequestDTO requestDTO) {
        return saveProvisionService.sendSaveProvision(requestDTO.getContractNumber(), requestDTO.getSave_mode());
    }

    @PostMapping("/get-save-schedule")
    public ResponseEntity<String> sendSaveSchedule(@RequestBody RequestDTO requestDTO) {
        return saveScheduleService.sendSaveSchedule(requestDTO.getContractNumber(), requestDTO.getSave_mode());
    }

    @PostMapping("/get-setStateToLitigation")
    public ResponseEntity<String> setStateToLitigation(@RequestBody RequestDTO requestDTO) {
        return setStateToLitigationService.sendSetStateToLitigation(requestDTO.getContractNumber(), requestDTO.getDecide_number(),
                requestDTO.getDecide_date(), requestDTO.getConclusion(), requestDTO.getSend_date());
    }

    @PostMapping("/get-saveCourtDecision")
    public ResponseEntity<String> saveCourtDecision(@RequestBody RequestDTO requestDTO) {
        return saveCourtDecisionService.sendSaveCourtDecision(requestDTO.getContractNumber(), requestDTO.getType(),
                requestDTO.getNumber(), requestDTO.getDate());
    }

    @PostMapping("/get-setStateToClose")
    public ResponseEntity<String> setStateToClose(@RequestBody RequestDTO requestDTO) {
        return setStateToCloseService.sendSetStateToClose(requestDTO.getContractNumber());
    }

    @GetMapping("/generate-files")
    public String generateFiles(@RequestParam String date) {
        return fileGeneratorService.createFiles(date);
    }

    @GetMapping("/send-save-info")
    public ResponseEntity<String> sendSaveInfo(
            @RequestParam String id,
            @RequestParam String type) {
        return getIdentityService.sendSaveInfo(id, type);
    }

    @GetMapping("/status")
    public ResponseEntity<List<KreditDTO>> getCreditsByStatus() {
        List<KreditDTO> credits = kreditService.findCreditsByStatus();
        if (credits.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.emptyList());
        }
        return ResponseEntity.ok(credits);
    }
}
