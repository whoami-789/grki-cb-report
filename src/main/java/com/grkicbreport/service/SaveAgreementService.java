package com.grkicbreport.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grkicbreport.dto.CreditorDTO;
import com.grkicbreport.dto.saveAgreement.AgreementDTO;
import com.grkicbreport.dto.saveAgreement.saveAgreementDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SaveAgreementService {

    private final RestTemplate restTemplate;


    public SaveAgreementService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public saveAgreementDTO createAgreement(String agreementId, String agreementNumber,
                                           String agreementDateBegin, String agreementDateEnd, String agreement_subject_type,
                                           String agreementInnPinfl, String agreementName,
                                           String agreementAmount) {

        try {
            // Создаем и заполняем DTO
            saveAgreementDTO dto = new saveAgreementDTO();

            dto.setSave_mode("1");

            // Заполнение CreditorDTO
            CreditorDTO creditorDTO = new CreditorDTO();
            creditorDTO.setType("02");
            creditorDTO.setCode("6005");
            creditorDTO.setOffice(null);
            dto.setCreditor(creditorDTO);

            AgreementDTO agreementDTO = new AgreementDTO();
            agreementDTO.setAgreement_id(agreementId);
            agreementDTO.setNumber(agreementNumber);
            agreementDTO.setDate_begin(agreementDateBegin);
            agreementDTO.setDate_end(agreementDateEnd);
            agreementDTO.setSubject_type(agreement_subject_type);
            if (agreement_subject_type.equals("1")) {
                agreementDTO.setInn(agreementInnPinfl);
            } else if (agreement_subject_type.equals("2")) {
                agreementDTO.setPinfl(agreementInnPinfl);
            }
            agreementDTO.setResident("1");
            agreementDTO.setName(agreementName);
            agreementDTO.setCurrency("0");
            agreementDTO.setAmount(agreementAmount);

            Gson gson = new GsonBuilder()
                    .serializeNulls() // Include null values in the JSON output
                    .setPrettyPrinting() // Enable pretty printing for better readability
                    .create();
            String formattedJson = gson.toJson(dto);
            System.out.println(formattedJson);
            return dto;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ResponseEntity<String> sendSaveAgreement(String agreementId, String agreementNumber,
                                                    String agreementDateBegin, String agreementDateEnd, String agreement_subject_type,
                                                    String agreementInnPinfl, String agreementName,
                                                    String agreementAmount) {
        saveAgreementDTO dto = createAgreement(agreementId, agreementNumber, agreementDateBegin, agreementDateEnd,
                agreement_subject_type, agreementInnPinfl, agreementName, agreementAmount);

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

        String url = "http://grki-service/grci/resources/cb/saveAgreement";
        return restTemplate.postForEntity(url, request, String.class);
    }
}
