package com.grkicbreport.service;

import com.grkicbreport.model.Kredit;
import com.grkicbreport.repository.KreditRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaveProvisionBatchService {

    private final KreditRepository kreditRepository;
    private final SaveProvisionService saveProvisionService;

    public SaveProvisionBatchService(KreditRepository kreditRepository,
                                     SaveProvisionService saveProvisionService) {
        this.kreditRepository = kreditRepository;
        this.saveProvisionService = saveProvisionService;
    }

    public List<String> processProvisionsForPeriod(LocalDate from, LocalDate to, boolean jewelryOnly) {
        List<Kredit> credits;

        if (jewelryOnly) {
            credits = kreditRepository.findWithJewelryCollateral(from, to);
        } else {
            credits = kreditRepository.findWithoutContractInPeriod(from, to);
        }

        List<String> results = new ArrayList<>();

        for (Kredit kredit : credits) {
            try {
                ResponseEntity<String> resp = saveProvisionService.sendSaveProvision(
                        kredit.getNumdog(),
                        "P-" + kredit.getNumdog(), // номер обеспечения
                        kredit.getDatadog(),       // дата обеспечения
                        "",                        // nibbd
                        "", "",                    // engine, body
                        "", "", "", "", "",        // year, state, model, chassis, color
                        "", "",                    // docSeriaNumber, vinNumber
                        "1"                        // save_mode
                );

                results.add("Кредит " + kredit.getNumdog() + " отправлен: " + resp.getStatusCode());
            } catch (Exception e) {
                results.add("Ошибка при обработке кредита " + kredit.getNumdog() + ": " + e.getMessage());
            }
        }
        return results;
    }
}
