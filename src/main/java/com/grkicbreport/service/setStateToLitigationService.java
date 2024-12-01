package com.grkicbreport.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grkicbreport.dto.CreditorDTO;
import com.grkicbreport.dto.setStateToLitigation.setStateToLitigationDTO;
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
public class setStateToLitigationService {
    private final RestTemplate restTemplate;
    private final KreditRepository kreditRepository;

    public setStateToLitigationService(RestTemplate restTemplate, KreditRepository kreditRepository) {
        this.restTemplate = restTemplate;
        this.kreditRepository = kreditRepository;
    }

    public setStateToLitigationDTO createLitigation(String contractNumber, String decide_number,
                                                    LocalDate decide_date,
                                                    String conclusion, LocalDate send_date) {

        Optional<Kredit> kreditList = kreditRepository.findByNumdog(contractNumber);

        if (kreditList.isEmpty()) {
            throw new IllegalArgumentException("Кредит с таким номером не найден.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        Kredit kredit = kreditList.get();

        try {
            setStateToLitigationDTO dto = new setStateToLitigationDTO();

            dto.setSave_mode("1");

            CreditorDTO creditorDTO = new CreditorDTO();
            creditorDTO.setType("03");
            creditorDTO.setCode("07128");
            creditorDTO.setOffice(null);
            dto.setCreditor(creditorDTO);

            setStateToLitigationDTO.Contract contract = new setStateToLitigationDTO.Contract();
            contract.setContract_guid(kredit.getGrkiContractId());
            String cleanedNumdog = kredit.getNumdog().replaceAll("[-K\\\\]", "");
            contract.setContract_id(cleanedNumdog.replaceAll("\\s", ""));
            dto.setContract(contract);

            setStateToLitigationDTO.LitigationBasis litigationBasis = new setStateToLitigationDTO.LitigationBasis();
            litigationBasis.setDecide("03");
            litigationBasis.setDecide_number(decide_number);
            litigationBasis.setDecide_date(decide_date.format(formatter));
            litigationBasis.setDecide_chief("Щарипов Кудрат Исматович");
            litigationBasis.setConclusion(conclusion);
            litigationBasis.setSend_date(send_date.format(formatter));
            dto.setLitigation_basis(litigationBasis);

            return dto;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ResponseEntity<String> sendSetStateToLitigation(String contractNumber, String decide_number,
                                                           LocalDate decide_date,
                                                           String conclusion, LocalDate send_date) {
        setStateToLitigationDTO dto = createLitigation(contractNumber, decide_number, decide_date,
                conclusion, send_date);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Добавляем заголовки login и password
        headers.set("Login", "NK07128");
        headers.set("Password", "B184C7620C142B2EBD6932F4F29E55A9");


        Gson gson = new GsonBuilder()
                .serializeNulls() // Include null values in the JSON output
                .setPrettyPrinting() // Enable pretty printing for better readability
                .create();
        String formattedJson = gson.toJson(dto);

        HttpEntity<String> request = new HttpEntity<>(formattedJson, headers);

        String url = "http://10.95.88.16:8080/grci/resources/cb/setStateToLitigation";
        return restTemplate.postForEntity(url, request, String.class);
    }
}
