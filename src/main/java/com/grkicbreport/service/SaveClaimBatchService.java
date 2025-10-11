package com.grkicbreport.service;

import com.grkicbreport.dto.saveClaim.saveClaimDTO;
import com.grkicbreport.model.Kredit;
import com.grkicbreport.repository.KreditRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaveClaimBatchService {
    private final KreditRepository kreditRepository;
    private final SaveClaimService saveClaimService;

    public SaveClaimBatchService(KreditRepository kreditRepository, SaveClaimService saveClaimService) {
        this.kreditRepository = kreditRepository;
        this.saveClaimService = saveClaimService;
    }

    public List<String> processClaims(LocalDate from, LocalDate to, String save_mode, String avgIncome, boolean sendToCb) {
        List<Kredit> credits = kreditRepository.findWithoutContractInPeriod(from, to);
        List<String> results = new ArrayList<>();

        for (Kredit kredit : credits) {
            try {
                if (sendToCb) {
                    ResponseEntity<String> resp = saveClaimService.sendSaveClaim(kredit.getNumdog(), save_mode, avgIncome);
                    results.add("Заявка по кредиту " + kredit.getNumdog() + " отправлена: " + resp.getStatusCode() + "\n" + resp.getBody());
                } else {
                    saveClaimDTO dto = saveClaimService.createClaim(kredit.getNumdog(), save_mode, avgIncome);
                    results.add("Заявка по кредиту " + kredit.getNumdog() + " сформирована локально");
                }
            } catch (Exception e) {
                results.add("Ошибка по кредиту " + kredit.getNumdog() + ": " + e.getMessage());
            }
        }
        return results;
    }
}

