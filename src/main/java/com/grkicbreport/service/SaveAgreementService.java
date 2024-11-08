package com.grkicbreport.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grkicbreport.dto.CreditorDTO;
import com.grkicbreport.dto.saveAgreement.AgreementDTO;
import com.grkicbreport.dto.saveAgreement.saveAgreementDTO;
import com.grkicbreport.model.Kredit;
import com.grkicbreport.repository.KreditRepository;
import com.grkicbreport.response.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@Service
public class SaveAgreementService {

    private final RestTemplate restTemplate;
    private final KreditRepository kreditRepository;

    public SaveAgreementService(RestTemplate restTemplate, KreditRepository kreditRepository) {
        this.restTemplate = restTemplate;
        this.kreditRepository = kreditRepository;
    }

    public saveAgreementDTO createAgreement(String agreementId, String agreementNumber,
                                            LocalDate agreementDateBegin, LocalDate agreementDateEnd, String agreement_subject_type,
                                            String agreementInnPinfl, String agreementName,
                                            String agreementAmount) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        try {
            // Создаем и заполняем DTO
            saveAgreementDTO dto = new saveAgreementDTO();

            dto.setSave_mode("1");

            // Заполнение CreditorDTO
            CreditorDTO creditorDTO = new CreditorDTO();
            creditorDTO.setType("02");
            creditorDTO.setCode("06082");
            creditorDTO.setOffice(null);
            dto.setCreditor(creditorDTO);

            AgreementDTO agreementDTO = new AgreementDTO();
            agreementDTO.setAgreement_id(agreementId);
            agreementDTO.setNumber(agreementNumber);
            agreementDTO.setDate_begin(agreementDateBegin.format(formatter));
            agreementDTO.setDate_end(agreementDateEnd.format(formatter));
            agreementDTO.setSubject_type(agreement_subject_type);
            if (agreement_subject_type.equals("1")) {
                agreementDTO.setInn(agreementInnPinfl);
            } else if (agreement_subject_type.equals("2")) {
                agreementDTO.setPinfl(agreementInnPinfl);
            }
            agreementDTO.setResident("1");
            agreementDTO.setName(agreementName);
            agreementDTO.setCurrency("000");
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

    public ResponseEntity<String> sendSaveAgreement(String contractNumber,String agreementId, String agreementNumber,
                                                    LocalDate agreementDateBegin, LocalDate agreementDateEnd, String agreement_subject_type,
                                                    String agreementInnPinfl, String agreementName,
                                                    String agreementAmount) {
        saveAgreementDTO dto = createAgreement(agreementId, agreementNumber, agreementDateBegin, agreementDateEnd,
                agreement_subject_type, agreementInnPinfl, agreementName, agreementAmount);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Добавляем заголовки login и password
        headers.set("Login", "NK06082");
        headers.set("Password", "0F6852B8FF393C171B5E981432E13840");

        Gson gson = new GsonBuilder()
                .serializeNulls() // Include null values in the JSON output
                .setPrettyPrinting() // Enable pretty printing for better readability
                .create();
        String formattedJson = gson.toJson(dto);

        HttpEntity<String> request = new HttpEntity<>(formattedJson, headers);

        String url = "http://10.95.88.16:8080/grci/resources/cb/saveAgreement";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        // Парсинг ответа
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            Response responseBody = gson.fromJson(response.getBody(), Response.class);
            System.out.println(responseBody);

            // Извлечение claim_guid
            String claimGuid = responseBody.getAnswer().getIdentity().getAgreement_guid();

            Optional<Kredit> kreditOptional = kreditRepository.findByNumdog(contractNumber);

            if (kreditOptional.isPresent()) {
                Kredit kredit = kreditOptional.get();

                // Сохранить claim_guid в поле grkiClaimId
                kreditRepository.updateGrkiAgreementId(claimGuid, kredit.getNumdog());

            } else {
                System.out.println("Кредит с таким номером договора не найден");
            }
        } else {
            System.out.println("Ошибка при выполнении запроса");
        }

        return response;
    }
}
