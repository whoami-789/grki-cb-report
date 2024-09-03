package com.grkicbreport.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grkicbreport.dto.CreditorDTO;
import com.grkicbreport.dto.saveProvision.ContractDTO;
import com.grkicbreport.dto.saveProvision.saveProvisionDTO;
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
public class SaveProvision {

    private final RestTemplate restTemplate;
    private final KreditRepository kreditRepository;
    private final AzolikFizRepository azolikFizRepository;
    private final AzolikYurRepository azolikYurRepository;

    public SaveProvision(RestTemplate restTemplate, KreditRepository kreditRepository, AzolikFizRepository azolikFizRepository, AzolikYurRepository azolikYurRepository) {
        this.restTemplate = restTemplate;
        this.kreditRepository = kreditRepository;
        this.azolikFizRepository = azolikFizRepository;
        this.azolikYurRepository = azolikYurRepository;
    }

    public saveProvisionDTO createProvision(String contractNumber) {

        Optional<Kredit> kreditList = kreditRepository.findByNumdog(contractNumber);
        if (kreditList.isEmpty()) {
            throw new IllegalArgumentException("Кредит с таким номером не найден.");
        }

        Kredit kredit = kreditList.get();

        try {

            saveProvisionDTO dto = new saveProvisionDTO();

            dto.setSave_mode("1");

            CreditorDTO creditorDTO = new CreditorDTO();
            creditorDTO.setType("02");
            creditorDTO.setCode("6005");
            creditorDTO.setOffice(null);
            dto.setCreditor(creditorDTO);

            ContractDTO contractDTO = new ContractDTO();
            contractDTO.setContract_guid(kredit.getGrkiClaimId());
            contractDTO.setContract_id(kredit.getNumdog());

        } catch (Exception e) {
            return  null;
        }
    }

    public ResponseEntity<String> sendSaveProvision(String contractNumber) {
        saveProvisionDTO dto = createProvision(contractNumber);

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

        String url = "http://grki-service/grci/resources/cb/saveProvision";
        return restTemplate.postForEntity(url, request, String.class);
    }
}
