package com.grkicbreport.controller;

import com.grkicbreport.dto.RequestDTO;
import com.grkicbreport.dto.saveClaim.saveClaimDTO;
import com.grkicbreport.service.SaveClaimService;
import com.grkicbreport.service.SaveContractService;
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

    @PostMapping("/get-save-claim")
    public ResponseEntity<String> sendSaveClaim(@RequestBody RequestDTO requestDTO) {
        return saveClaimService.sendSaveClaim(requestDTO.getContractNumber(), requestDTO.getWork());
    }

    @PostMapping("/get-save-contract")
    public ResponseEntity<String> sendSaveContract(@RequestBody RequestDTO requestDTO) {
        return saveContractService.sendSaveContract(requestDTO.getContractNumber(), requestDTO.getLoan_line(),
                requestDTO.getDecisionNumber(), requestDTO.getDecisionDate());
    }
}
