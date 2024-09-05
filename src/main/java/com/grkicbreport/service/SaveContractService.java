package com.grkicbreport.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grkicbreport.dto.CreditorDTO;
import com.grkicbreport.dto.saveContract.*;
import com.grkicbreport.model.Kredit;
import com.grkicbreport.repository.AzolikFizRepository;
import com.grkicbreport.repository.AzolikYurRepository;
import com.grkicbreport.repository.KreditRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class SaveContractService {

    private final AzolikYurRepository azolikYurRepository;
    private final AzolikFizRepository azolikFizRepository;
    private final KreditRepository kreditRepository;
    private final RestTemplate restTemplate;

    public SaveContractService(AzolikYurRepository azolikYurRepository, AzolikFizRepository azolikFizRepository, KreditRepository kreditRepository, RestTemplate restTemplate) {
        this.azolikYurRepository = azolikYurRepository;
        this.azolikFizRepository = azolikFizRepository;
        this.kreditRepository = kreditRepository;
        this.restTemplate = restTemplate;
    }

    public saveContractDTO createContract(String contractNumber, String Loan_line,
                                          String decisionNumber, String decisionDate) {
        Optional<Kredit> kreditList = kreditRepository.findByNumdog(contractNumber);


        if (kreditList.isEmpty()) {
            throw new IllegalArgumentException("Кредит с таким номером не найден.");
        }

        Kredit kredit = kreditList.get();

        try {
            // Создаем и заполняем DTO
            saveContractDTO dto = new saveContractDTO();

            dto.setSave_mode("1");

            // Заполнение CreditorDTO
            CreditorDTO creditorDTO = new CreditorDTO();
            creditorDTO.setType("02");
            creditorDTO.setCode("6005");
            creditorDTO.setOffice(null);
            dto.setCreditor(creditorDTO);

            // Заполнение ClaimDTO
            ClaimDTO claimDTO = new ClaimDTO();
            claimDTO.setClaim_guid(kredit.getGrkiClaimId().replaceAll("\\s", ""));
//            claimDTO.setClaim_guid("1");
            claimDTO.setClaim_id(kredit.getNumdog().replaceAll("\\s", ""));
            claimDTO.setContract_id(kredit.getNumdog().replaceAll("\\s", "")); // Вызов метода или сервиса для получения номера
            dto.setClaim(claimDTO);

            DecisionDTO decisionDTO = new DecisionDTO();
            decisionDTO.setDecide("03");
            decisionDTO.setNumber(decisionNumber); // вручную
            decisionDTO.setDate(decisionDate); // вручную
            decisionDTO.setDecide_chief("Тухтаева Манзура Мизробовна");
            decisionDTO.setBorrower_link("0");
            dto.setDecision(decisionDTO);

            ContractDTO contractDTO = new ContractDTO();
            contractDTO.setLoan_type("10");
            contractDTO.setIssue_mode("02");
            contractDTO.setLoan_line(Loan_line);
            contractDTO.setAsset_quality(String.valueOf(kredit.getKlass()));
            contractDTO.setNumber(kredit.getNumdog().replaceAll("\\s", ""));
            contractDTO.setDate_begin(String.valueOf(kredit.getDatadog()));
            contractDTO.setDate_end(String.valueOf(kredit.getDatsZakr()));
            contractDTO.setCurrency("0");
            contractDTO.setAmount(String.valueOf(kredit.getSumma()));
            PercentDTO percentDTO = new PercentDTO();
            percentDTO.setPercent_type("101");
            percentDTO.setPercent_total(String.valueOf(kredit.getProsent()));
            percentDTO.setBorrower_percent(String.valueOf(kredit.getProsent()));
            percentDTO.setOverdue_percent(String.valueOf(kredit.getProcpeni()));
            contractDTO.setPercent(percentDTO);
            contractDTO.setCurrency_first("0");
            contractDTO.setAmount_first("0");
            dto.setContract(contractDTO);

            TargetsDTO targetsDTO = new TargetsDTO();
            targetsDTO.setType("99");
            targetsDTO.setAmount(String.valueOf(kredit.getSumma()));
            targetsDTO.setInfo(null);
            dto.getTargets().add(targetsDTO);

            SourcesDTO sourcesDTO = new SourcesDTO();
            sourcesDTO.setType("100");
            sourcesDTO.setCurrency("0");
            sourcesDTO.setAmount(String.valueOf(kredit.getSumma()));
            dto.getSources().add(sourcesDTO);

// Возвращаем заполненный DTO
            Gson gson = new GsonBuilder()
                    .serializeNulls() // Include null values in the JSON output
                    .setPrettyPrinting() // Enable pretty printing for better readability
                    .create();
            String formattedJson = gson.toJson(dto);
            System.out.println(formattedJson);
            return dto;
        } catch (Exception e) {
            System.out.println(e);
            // Создаем и заполняем DTO
//            saveContractDTO dto = new saveContractDTO();
//
//            dto.setSave_mode("1");
//
//            // Заполнение CreditorDTO
//            CreditorDTO creditorDTO = new CreditorDTO();
//            creditorDTO.setType("02");
//            creditorDTO.setCode("6005");
//            dto.setCreditor(creditorDTO);
//
//            System.out.println("yur");
//            return dto;
            return null;
        }
    }

    public ResponseEntity<String> sendSaveContract(String contractNumber, String Loan_line,
                                                   String decisionNumber, String decisionDate) {
        saveContractDTO dto = createContract(contractNumber, Loan_line, decisionNumber, decisionDate);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Добавляем заголовки login и password
        headers.set("Login", "NK06005");
        headers.set("Password", "75c75fce1b53addf6c52f96c32555b12");

        Gson gson = new GsonBuilder()
                .serializeNulls() // Include null values in the JSON output
                .setPrettyPrinting() // Enable pretty printing for better readability
                .create();
        String formattedJson = gson.toJson(dto);

        HttpEntity<String> request = new HttpEntity<>(formattedJson, headers);

        String url = "http://grki-service/grci/resources/cb/saveContract";
        return restTemplate.postForEntity(url, request, String.class);
    }
}