package com.grkicbreport.service;

import com.grkicbreport.dto.saveContract.saveContractDTO;
import com.grkicbreport.model.Kredit;
import com.grkicbreport.repository.KreditRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaveContractBatchService {
    private final KreditRepository kreditRepository;
    private final SaveContractService saveContractService;

    public SaveContractBatchService(KreditRepository kreditRepository, SaveContractService saveContractService) {
        this.kreditRepository = kreditRepository;
        this.saveContractService = saveContractService;
    }

    public List<String> processContracts(LocalDate from, LocalDate to, String loanLine, String decisionNumber, String save_mode, boolean sendToCb) {
        List<Kredit> credits = kreditRepository.findWithoutContractInPeriod(from, to);
        List<String> results = new ArrayList<>();

        for (Kredit kredit : credits) {
            try {
                if (sendToCb) {
                    ResponseEntity<String> resp = saveContractService.sendSaveContract(kredit.getNumdog(), loanLine, decisionNumber, kredit.getDatadog(), save_mode);
                    results.add("Контракт по кредиту " + kredit.getNumdog() + " отправлен: " + resp.getStatusCode());
                } else {
                    saveContractDTO dto = saveContractService.createContract(kredit.getNumdog(), loanLine, decisionNumber, save_mode);
                    results.add("Контракт по кредиту " + kredit.getNumdog() + " сформирован локально");
                }
            } catch (Exception e) {
                results.add("Ошибка по контракту " + kredit.getNumdog() + ": " + e.getMessage());
            }
        }
        return results;
    }
}

