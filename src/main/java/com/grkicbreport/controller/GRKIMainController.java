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
    private final SaveAgreementService saveAgreementService;
    private final SaveProvisionService saveProvisionService;
    private final saveScheduleService saveScheduleService;
    private final setStateToLitigationService setStateToLitigationService;
    private final saveCourtDecisionService saveCourtDecisionService;
    private final setStateToCloseService setStateToCloseService;
    private final FileGeneratorService fileGeneratorService;
    private final getIdentityService getIdentityService;
    private final KreditService kreditService;
    private final saveScheduleBatchService batchService;
    private final SaveProvisionBatchService provbatchService;
    private final SaveClaimBatchService claimbatchService;
    private final SaveContractBatchService contractbatchService;


    public GRKIMainController(SaveClaimService saveClaimService, SaveContractService saveContractService, SaveAgreementService saveAgreementService, SaveProvisionService saveProvisionService, saveScheduleService saveScheduleService, setStateToLitigationService setStateToLitigationService, saveCourtDecisionService saveCourtDecisionService, setStateToCloseService setStateToCloseService, FileGeneratorService fileGeneratorService, com.grkicbreport.service.getIdentityService getIdentityService, KreditService kreditService, saveScheduleBatchService batchService, SaveProvisionBatchService provbatchService, SaveClaimBatchService claimbatchService, SaveContractBatchService contractbatchService) {
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
        this.batchService = batchService;
        this.provbatchService = provbatchService;
        this.claimbatchService = claimbatchService;
        this.contractbatchService = contractbatchService;
    }

    @PostMapping("/get-save-claim")
    public ResponseEntity<String> sendSaveClaim(@RequestBody RequestDTO requestDTO) {
        return saveClaimService.sendSaveClaim(requestDTO.getContractNumber(), requestDTO.getSave_mode(), requestDTO.getAverage_income());
    }

    @PostMapping("/get-save-contract")
    public ResponseEntity<String> sendSaveContract(@RequestBody RequestDTO requestDTO) {
        return saveContractService.sendSaveContract(requestDTO.getContractNumber(), requestDTO.getLoan_line(),
                requestDTO.getDecisionNumber(), requestDTO.getDecisionDate(), requestDTO.getSave_mode());
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
                requestDTO.getModel(), requestDTO.getChassis_number(), requestDTO.getColor(), requestDTO.getDoc_seria_number(), requestDTO.getVin_number(), requestDTO.getSave_mode());
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

    @GetMapping("/generate-files")
    public String generateFiles(@RequestParam String date) {
        return fileGeneratorService.createFiles(date);
    }

    @GetMapping("/run")
    public ResponseEntity<List<String>> runBatch(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(defaultValue = "save") String save_mode,
            @RequestParam(defaultValue = "false") boolean sendToCb) {

        List<String> results = batchService.processSchedulesForPeriod(from, to, save_mode, sendToCb);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/prov/run")
    public ResponseEntity<List<String>> runBatch(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(defaultValue = "false") boolean jewelryOnly,
            @RequestParam(defaultValue = "false") boolean sendToCb
    ) {
        List<String> results = provbatchService.processProvisionsForPeriod(from, to, jewelryOnly);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/claim/run")
    public ResponseEntity<List<String>> runClaims(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(defaultValue = "1") String save_mode,
            @RequestParam(defaultValue = "4000000") String avgIncome,
            @RequestParam(defaultValue = "false") boolean sendToCb) {
        return ResponseEntity.ok(claimbatchService.processClaims(from, to, save_mode, avgIncome, sendToCb));
    }

    @GetMapping("/contract/run")
    public ResponseEntity<List<String>> runContracts(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(defaultValue = "02") String loanLine,
            @RequestParam(required = false) String decisionNumber,
            @RequestParam(defaultValue = "1") String save_mode,
            @RequestParam(defaultValue = "false") boolean sendToCb) {
        return ResponseEntity.ok(contractbatchService.processContracts(from, to, loanLine, decisionNumber, save_mode, sendToCb));
    }

}
