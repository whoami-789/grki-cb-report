package com.grkicbreport.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grkicbreport.dto.CreditorDTO;
import com.grkicbreport.dto.getInformationDTO;
import com.grkicbreport.dto.saveContract.saveContractDTO;
import com.grkicbreport.dto.saveSchedule.saveScheduleDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Service
public class getIdentityService {
    private final RestTemplate restTemplate;
    private static final Logger logger = Logger.getLogger(SaveContractService.class.getName());

    public getIdentityService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public getInformationDTO createDTO(String id, String type) {
        try {
            // Создаем и заполняем DTO
            getInformationDTO dto = new getInformationDTO();


            // Заполнение CreditorDTO
            CreditorDTO creditorDTO = new CreditorDTO();
            creditorDTO.setType("02");
            creditorDTO.setCode("06005");
            creditorDTO.setOffice(null);
            dto.setCreditor(creditorDTO);

            getInformationDTO.IdentityDTO identityDTO = new getInformationDTO.IdentityDTO();
            identityDTO.setIdentity_type(type);
            identityDTO.setIdentity_id(id);
            dto.setIdentity(identityDTO);

            return dto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<String> sendSaveInfo(String id, String type) {
        getInformationDTO dto = createDTO(id, type);

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

        String url = "http://10.95.88.48/grci/resources/cb/getInformation";
        return restTemplate.postForEntity(url, request, String.class);
    }
}
