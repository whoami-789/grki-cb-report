package com.grkicbreport.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grkicbreport.dto.CreditorDTO;
import com.grkicbreport.dto.saveContract.*;
import com.grkicbreport.model.Grafik;
import com.grkicbreport.model.Kredit;
import com.grkicbreport.repository.AzolikFizRepository;
import com.grkicbreport.repository.AzolikYurRepository;
import com.grkicbreport.repository.GrafikRepository;
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
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class SaveContractService {

    private final AzolikYurRepository azolikYurRepository;
    private final AzolikFizRepository azolikFizRepository;
    private final KreditRepository kreditRepository;
    private final GrafikRepository grafikRepository;
    private final RestTemplate restTemplate;
    private static final Logger logger = Logger.getLogger(SaveContractService.class.getName());

    public SaveContractService(AzolikYurRepository azolikYurRepository, AzolikFizRepository azolikFizRepository, KreditRepository kreditRepository, GrafikRepository grafikRepository, RestTemplate restTemplate) {
        this.azolikYurRepository = azolikYurRepository;
        this.azolikFizRepository = azolikFizRepository;
        this.kreditRepository = kreditRepository;
        this.grafikRepository = grafikRepository;
        this.restTemplate = restTemplate;
    }

    public saveContractDTO createContract(String contractNumber, String Loan_line,
                                          String decisionNumber, LocalDate decisionDate) {
        Optional<Kredit> kreditList = kreditRepository.findByNumdog(contractNumber);
        LocalDate maxDats = grafikRepository.findMaxDatsByNumdog(contractNumber);
        System.out.println("Максимальная дата: " + maxDats);


        if (kreditList.isEmpty()) {
            throw new IllegalArgumentException("Кредит с таким номером не найден.");
        }


        Kredit kredit = kreditList.get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        try {
            // Создаем и заполняем DTO
            saveContractDTO dto = new saveContractDTO();

            dto.setSave_mode("1");

            // Заполнение CreditorDTO
            CreditorDTO creditorDTO = new CreditorDTO();
            creditorDTO.setType("03");
            creditorDTO.setCode("07087");
            creditorDTO.setOffice(null);
            dto.setCreditor(creditorDTO);

            // Заполнение ClaimDTO
            ClaimDTO claimDTO = new ClaimDTO();
            claimDTO.setClaim_guid(kredit.getGrkiClaimId().replaceAll("\\s", ""));
            String cleanedNumdog = kredit.getNumdog().replaceAll("[-K\\\\]", "");
            claimDTO.setClaim_id(cleanedNumdog.replaceAll("\\s", ""));
            claimDTO.setContract_id(cleanedNumdog.replaceAll("\\s", ""));
            dto.setClaim(claimDTO);

            DecisionDTO decisionDTO = new DecisionDTO();
            decisionDTO.setDecide("03");
            decisionDTO.setNumber(decisionNumber); // вручную
            decisionDTO.setDate(decisionDate.format(formatter)); // вручную
            decisionDTO.setDecide_chief("Ашрапова Робия Азамовна");
            decisionDTO.setBorrower_link("0");
            dto.setDecision(decisionDTO);

            ContractDTO contractDTO = new ContractDTO();
            contractDTO.setLoan_type("10");
            contractDTO.setIssue_mode("02");
            contractDTO.setLoan_line(Loan_line);
            contractDTO.setAsset_quality(String.valueOf(kredit.getKlass()));
            contractDTO.setNumber(kredit.getNumdog().replaceAll("\\s", ""));
            contractDTO.setDate_begin(kredit.getDatadog().format(formatter));
            contractDTO.setDate_end(maxDats.format(formatter));
            contractDTO.setCurrency("000");
            contractDTO.setAmount(String.valueOf(kredit.getSumma().intValue()));
            contractDTO.setDiscont_comissions("0");
            PercentDTO percentDTO = new PercentDTO();
            percentDTO.setPercent_type("101");
            percentDTO.setPercent_total(String.valueOf(kredit.getProsent()));
            percentDTO.setBorrower_percent(String.valueOf(kredit.getProsent()));
            percentDTO.setOverdue_percent("0");
            contractDTO.setPercent(percentDTO);
            contractDTO.setCurrency_first("000");
            contractDTO.setAmount_first("0");
            dto.setContract(contractDTO);

            TargetsDTO targetsDTO = new TargetsDTO();
            targetsDTO.setType("0699");
            targetsDTO.setAmount(String.valueOf(kredit.getSumma().intValue()));
            dto.getTargets().add(targetsDTO);

            SourcesDTO sourcesDTO = new SourcesDTO();
            sourcesDTO.setType("100");
            sourcesDTO.setCurrency("000");
            sourcesDTO.setAmount(String.valueOf(kredit.getSumma().intValue()));
            dto.getSources().add(sourcesDTO);

// Возвращаем заполненный DTO
            // Возвращаем заполненный DTO
            Gson gson = new GsonBuilder()
                    .serializeNulls() // Include null values in the JSON output
                    .setPrettyPrinting() // Enable pretty printing for better readability
                    .create();
            String formattedJson = gson.toJson(dto);
            logger.info(formattedJson);
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
                                                   String decisionNumber, LocalDate decisionDate) {
        saveContractDTO dto = createContract(contractNumber, Loan_line, decisionNumber, decisionDate);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Добавляем заголовки login и password
        headers.set("Login", "NK07087");
        headers.set("Password", "9c6985a189528a06226b22da7cf60666");


        Gson gson = new GsonBuilder()
                .serializeNulls() // Include null values in the JSON output
                .setPrettyPrinting() // Enable pretty printing for better readability
                .create();
        String formattedJson = gson.toJson(dto);

        HttpEntity<String> request = new HttpEntity<>(formattedJson, headers);

        String url = "http://10.95.88.48/grci/resources/cb/saveContract";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        // Парсинг ответа
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            Response responseBody = gson.fromJson(response.getBody(), Response.class);
            System.out.println(responseBody);

            // Извлечение claim_guid
            String claimGuid = responseBody.getAnswer().getIdentity().getContract_guid();

            Optional<Kredit> kreditOptional = kreditRepository.findByNumdog(contractNumber);


            if (kreditOptional.isPresent()) {
                Kredit kredit = kreditOptional.get();

                // Сохранить claim_guid в поле grkiClaimId
                kreditRepository.updateGrkiContractId(claimGuid, kredit.getNumdog());

            } else {
                System.out.println("Кредит с таким номером договора не найден");
            }
        } else {
            System.out.println("Ошибка при выполнении запроса");
        }

        return response;
    }
}
