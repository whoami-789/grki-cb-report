package com.grkicbreport.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grkicbreport.components.InformHelper;
import com.grkicbreport.dto.CreditorDTO;
import com.grkicbreport.dto.saveCourtDecision.saveCourtDecisionDTO;
import com.grkicbreport.dto.setStateToLitigation.setStateToLitigationDTO;
import com.grkicbreport.model.Inform;
import com.grkicbreport.model.Kredit;
import com.grkicbreport.repository.KreditRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class saveCourtDecisionService {
    private final RestTemplate restTemplate;
    private final KreditRepository kreditRepository;
    private final InformHelper informHelper;


    public saveCourtDecisionService(RestTemplate restTemplate, KreditRepository kreditRepository, InformHelper informHelper) {
        this.restTemplate = restTemplate;
        this.kreditRepository = kreditRepository;
        this.informHelper = informHelper;
    }

    public saveCourtDecisionDTO createCourtDecide(String contractNumber, String type,
                                                  String number, LocalDate date) {
        Inform inform = informHelper.fetchSingleRow();

        Optional<Kredit> kreditList = kreditRepository.findByNumdog(contractNumber);

        if (kreditList.isEmpty()) {
            throw new IllegalArgumentException("Кредит с таким номером не найден.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


        Kredit kredit = kreditList.get();

        try {
            saveCourtDecisionDTO dto = new saveCourtDecisionDTO();

            dto.setSave_mode("1");

            CreditorDTO creditorDTO = new CreditorDTO();
            creditorDTO.setType("03");
            creditorDTO.setCode(inform.getNumks());
            creditorDTO.setOffice(null);
            dto.setCreditor(creditorDTO);

            saveCourtDecisionDTO.Contract contract = new saveCourtDecisionDTO.Contract();
            contract.setContract_guid(kredit.getGrkiContractId());
            String cleanedNumdog = kredit.getNumdog().replaceAll("[КK/]", "");

            contract.setContract_id(cleanedNumdog.replaceAll("\\s", ""));
            dto.setContract(contract);

            saveCourtDecisionDTO.Court_decision court_decision = new saveCourtDecisionDTO.Court_decision();
            if (type.equals("В пользу заемщика")) {
                court_decision.setType("01");
            } else if (type.equals("В пользу кредитной организации")) {
                court_decision.setType("02");
            }
            court_decision.setNumber(number);
            court_decision.setDate(date.format(formatter));
            dto.setCourt_decision(court_decision);

            return dto;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ResponseEntity<String> sendSaveCourtDecision(String contractNumber, String type,
                                                        String number, LocalDate date) {
        saveCourtDecisionDTO dto = createCourtDecide(contractNumber, type, number, date);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Inform inform = informHelper.fetchSingleRow();

        // Добавляем заголовки login и password
        headers.set("Login", "NK" + inform.getNumks());
        headers.set("Password", inform.getGrki_password());


        Gson gson = new GsonBuilder()
                .serializeNulls() // Include null values in the JSON output
                .setPrettyPrinting() // Enable pretty printing for better readability
                .create();
        String formattedJson = gson.toJson(dto);

        HttpEntity<String> request = new HttpEntity<>(formattedJson, headers);

        String url = "http://10.95.88.16:8080/grci/resources/cb/saveCourtDecision";
        return restTemplate.postForEntity(url, request, String.class);
    }
}
