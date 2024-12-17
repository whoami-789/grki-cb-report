package com.grkicbreport.controller;

import com.grkicbreport.dto.KreditDTO;
import com.grkicbreport.dto.RequestDTO;
import com.grkicbreport.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/grki")
public class MainController {
    private final SaveClaimService saveClaimService;
    private final SaveContractService saveContractService;
    private final SaveAgreementService saveAgreementService;
    private final SaveProvisionService saveProvisionService;
    private final saveScheduleService saveScheduleService;
    private final setStateToLitigationService setStateToLitigationService;
    private final saveCourtDecisionService saveCourtDecisionService;
    private final setStateToCloseService setStateToCloseService;
    private final FileGeneratorService fileGeneratorService;
    private final getIdentityService getIdentityService;
    private final KreditService kreditService;


    public MainController(SaveClaimService saveClaimService, SaveContractService saveContractService, SaveAgreementService saveAgreementService, SaveProvisionService saveProvisionService, saveScheduleService saveScheduleService, setStateToLitigationService setStateToLitigationService, saveCourtDecisionService saveCourtDecisionService, setStateToCloseService setStateToCloseService, FileGeneratorService fileGeneratorService, com.grkicbreport.service.getIdentityService getIdentityService, KreditService kreditService) {
        this.saveClaimService = saveClaimService;
        this.saveContractService = saveContractService;
        this.saveAgreementService = saveAgreementService;
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
        return saveClaimService.sendSaveClaim(requestDTO.getContractNumber());
    }

    @PostMapping("/get-save-contract")
    public ResponseEntity<String> sendSaveContract(@RequestBody RequestDTO requestDTO) {
        return saveContractService.sendSaveContract(requestDTO.getContractNumber(), requestDTO.getLoan_line(),
                requestDTO.getDecisionNumber(), requestDTO.getDecisionDate());
    }

    @PostMapping("/get-save-agreement")
    public ResponseEntity<String> sendSaveAgreement(@RequestBody RequestDTO requestDTO) {
        return saveAgreementService.sendSaveAgreement(requestDTO.getContractNumber(), requestDTO.getAgreement_id(), requestDTO.getAgreement_number(),
                requestDTO.getAgreement_date_begin(), requestDTO.getAgreement_date_end(), requestDTO.getAgreement_subject_type(),
                requestDTO.getAgreement_inn_pinfl(), requestDTO.getAgreement_name(), requestDTO.getAgreement_amount());
    }

    @PostMapping("/get-save-provision")
    public ResponseEntity<String> sendSaveProvision(@RequestBody RequestDTO requestDTO) {
        return saveProvisionService.sendSaveProvision(requestDTO.getContractNumber(), requestDTO.getProvisionNumber(), requestDTO.getProvisionDate(),
                requestDTO.getNibbd(), requestDTO.getEngine_number(), requestDTO.getBody_number(), requestDTO.getYear(), requestDTO.getState_number(),
                requestDTO.getModel(), requestDTO.getChassis_number(), requestDTO.getColor(), requestDTO.getDoc_seria_number(), requestDTO.getVin_number());
    }

    @PostMapping("/get-save-schedule")
    public ResponseEntity<String> sendSaveSchedule(@RequestBody RequestDTO requestDTO) {
        return saveScheduleService.sendSaveSchedule(requestDTO.getContractNumber());
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
