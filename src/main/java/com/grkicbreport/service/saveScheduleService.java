package com.grkicbreport.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grkicbreport.dto.CreditorDTO;
import com.grkicbreport.dto.saveSchedule.saveScheduleDTO;
import com.grkicbreport.model.Grafik;
import com.grkicbreport.model.Kredit;
import com.grkicbreport.repository.GrafikRepository;
import com.grkicbreport.repository.KreditRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class saveScheduleService {

    private final RestTemplate restTemplate;
    private final KreditRepository kreditRepository;
    private final GrafikRepository grafikRepository;
    private static final Logger logger = Logger.getLogger(saveScheduleService.class.getName());

    public saveScheduleService(RestTemplate restTemplate, KreditRepository kreditRepository, GrafikRepository grafikRepository) {
        this.restTemplate = restTemplate;
        this.kreditRepository = kreditRepository;
        this.grafikRepository = grafikRepository;
    }

    public saveScheduleDTO createSchedule(String contractNumber) {

        Optional<Kredit> kreditList = kreditRepository.findByNumdog(contractNumber);
        List<Grafik> grafikList = grafikRepository.findAllByNumdog(contractNumber);

        if (kreditList.isEmpty()) {
            throw new IllegalArgumentException("Кредит с таким номером не найден.");
        }
        if (grafikList.isEmpty()) {
            throw new IllegalArgumentException("График с таким номером не найден.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        Kredit kredit = kreditList.get();

        try {
            saveScheduleDTO dto = new saveScheduleDTO();

            dto.setSave_mode("1");

            CreditorDTO creditorDTO = new CreditorDTO();
            creditorDTO.setType("03");
            creditorDTO.setCode("07104");
            creditorDTO.setOffice(null);
            dto.setCreditor(creditorDTO);

            saveScheduleDTO.Contract contract = new saveScheduleDTO.Contract();
            contract.setContract_guid(kredit.getGrkiContractId());
            String cleanedNumdog = kredit.getNumdog().replaceAll("[-KК/\\\\.]", "");
            contract.setContract_id(cleanedNumdog.replaceAll("\\s", ""));
            dto.setContract(contract);

            List<saveScheduleDTO.Repayment> repaymentList = new ArrayList<>();

            // Проходим по всем объектам Grafik и создаем объекты Repayment
            for (Grafik grafik : grafikList) {
                saveScheduleDTO.Repayment repayment = new saveScheduleDTO.Repayment();
                repayment.setDate_percent(grafik.getDats().format(formatter));
                repayment.setAmount_percent(String.valueOf(grafik.getPogProc().intValue())); // Сумма процентов
                repayment.setDate_main(grafik.getDats().format(formatter)); // Устанавливаем дату основного платежа только если pogProc = 0.00
                repayment.setAmount_main(String.valueOf(grafik.getPogKred().intValue())); // Сумма основного платежа

                repaymentList.add(repayment);
            }

            // Устанавливаем список Repayment в DTO
            dto.setRepayments(repaymentList);

            Gson gson = new GsonBuilder()
                    .serializeNulls() // Include null values in the JSON output
                    .setPrettyPrinting() // Enable pretty printing for better readability
                    .create();
            String formattedJson = gson.toJson(dto);
            logger.info(formattedJson);
            return dto;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ResponseEntity<String> sendSaveSchedule(String contractNumber) {
        saveScheduleDTO dto = createSchedule(contractNumber);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Добавляем заголовки login и password
        headers.set("Login", "NK07104");
        headers.set("Password", "A782F7ACD7BFDDA728F2903C1C63423A");


        Gson gson = new GsonBuilder()
                .serializeNulls() // Include null values in the JSON output
                .setPrettyPrinting() // Enable pretty printing for better readability
                .create();
        String formattedJson = gson.toJson(dto);

        HttpEntity<String> request = new HttpEntity<>(formattedJson, headers);

        String url = "http://10.95.88.16:8080/grci/resources/cb/saveSchedule";
        return restTemplate.postForEntity(url, request, String.class);
    }
}
