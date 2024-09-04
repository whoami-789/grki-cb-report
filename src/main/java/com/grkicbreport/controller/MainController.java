package com.grkicbreport.controller;

import com.grkicbreport.dto.RequestDTO;
import com.grkicbreport.dto.saveClaim.saveClaimDTO;
import com.grkicbreport.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grki")
public class MainController {
    @Autowired
    private SaveClaimService saveClaimService;
    @Autowired
    private SaveContractService saveContractService;
    @Autowired
    private SaveAgreementService saveAgreementService;
    @Autowired
    private SaveProvisionService saveProvisionService;
    @Autowired
    private saveScheduleService saveScheduleService;
    @Autowired
    private setStateToLitigationService setStateToLitigationService;
    @Autowired
    private saveCourtDecisionService saveCourtDecisionService;

    @PostMapping("/get-save-claim")
    public ResponseEntity<String> sendSaveClaim(@RequestBody RequestDTO requestDTO) {
        return saveClaimService.sendSaveClaim(requestDTO.getContractNumber(), requestDTO.getWork());
    }

    @PostMapping("/get-save-contract")
    public ResponseEntity<String> sendSaveContract(@RequestBody RequestDTO requestDTO) {
        return saveContractService.sendSaveContract(requestDTO.getContractNumber(), requestDTO.getLoan_line(),
                requestDTO.getDecisionNumber(), requestDTO.getDecisionDate());
    }

    @PostMapping("/get-save-agreement")
    public ResponseEntity<String> sendSaveAgreement(@RequestBody RequestDTO requestDTO) {
        return saveAgreementService.sendSaveAgreement(requestDTO.getAgreement_id(), requestDTO.getAgreement_number(),
                requestDTO.getAgreement_date_begin(), requestDTO.getAgreement_date_end(), requestDTO.getAgreement_subject_type(),
                requestDTO.getAgreement_inn_pinfl(), requestDTO.getAgreement_name(), requestDTO.getAgreement_amount());
    }

    @PostMapping("/get-save-provision")
    public ResponseEntity<String> sendSaveProvision(@RequestBody RequestDTO requestDTO) {
        return saveProvisionService.sendSaveProvision(requestDTO.getContractNumber(), requestDTO.getProvisionNumber(), requestDTO.getProvisionDate(),
                requestDTO.getNibbd());
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
}
