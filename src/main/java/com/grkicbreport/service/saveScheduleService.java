package com.grkicbreport.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grkicbreport.component.InformHelper;
import com.grkicbreport.dto.CreditorDTO;
import com.grkicbreport.dto.saveSchedule.saveScheduleDTO;
import com.grkicbreport.model.Grafik;
import com.grkicbreport.model.Inform;
import com.grkicbreport.model.Kredit;
import com.grkicbreport.repository.GrafikRepository;
import com.grkicbreport.repository.KreditRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    private final InformHelper informHelper;

    public saveScheduleService(RestTemplate restTemplate, KreditRepository kreditRepository, GrafikRepository grafikRepository, InformHelper informHelper) {
        this.restTemplate = restTemplate;
        this.kreditRepository = kreditRepository;
        this.grafikRepository = grafikRepository;
        this.informHelper = informHelper;
    }

    public saveScheduleDTO createSchedule(String contractNumber, String save_mode) {

        Optional<Kredit> kreditOptional;

        // Сначала пытаемся найти по номеру договора
        kreditOptional = kreditRepository.findByNumdog(contractNumber);

        // Если не нашли по номеру договора, пытаемся найти по GrkiContractId
        if (kreditOptional.isEmpty()) {
            kreditOptional = kreditRepository.findByGrkiContractId(contractNumber);
        }

        if (kreditOptional.isEmpty()) {
            throw new IllegalArgumentException("Кредит с номером/ID: " + contractNumber + " не найден.");
        }

        Kredit kredit = kreditOptional.get();

        // Используем actualNumdog для поиска графика платежей
        String actualNumdog = kredit.getNumdog();
        List<Grafik> grafikList = grafikRepository.findAllByNumdog(actualNumdog);

        if (grafikList.isEmpty()) {
            throw new IllegalArgumentException("График платежей для кредита с номером: " + actualNumdog + " не найден.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Inform inform = informHelper.fetchSingleRow();

        try {
            saveScheduleDTO dto = new saveScheduleDTO();

            dto.setSave_mode(save_mode);

            CreditorDTO creditorDTO = new CreditorDTO();
            creditorDTO.setType("02");
            creditorDTO.setCode(inform.getNumks());
            creditorDTO.setOffice(null);
            dto.setCreditor(creditorDTO);

            saveScheduleDTO.Contract contract = new saveScheduleDTO.Contract();
            contract.setContract_guid(kredit.getGrkiContractId());
            String cleanedNumdog = kredit.getNumdog().replaceAll("[-KК/\\\\]", "");
            contract.setContract_id(cleanedNumdog.replaceAll("\\s", ""));
            dto.setContract(contract);

            List<saveScheduleDTO.Repayment> repaymentList = new ArrayList<>();

            // Проходим по всем объектам Grafik и создаем объекты Repayment
            for (Grafik grafik : grafikList) {
                saveScheduleDTO.Repayment repayment = new saveScheduleDTO.Repayment();
                repayment.setDate_percent(grafik.getDats().format(formatter));
                repayment.setAmount_percent(grafik.getPogProc().intValue() + "00"); // Сумма процентов
                repayment.setDate_main(grafik.getDats().format(formatter)); // Устанавливаем дату основного платежа только если pogProc = 0.00
                repayment.setAmount_main(grafik.getPogKred().intValue() + "00"); // Сумма основного платежа

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

    public ResponseEntity<String> sendSaveSchedule(String contractNumber, String save_mode) {
        saveScheduleDTO dto = createSchedule(contractNumber, save_mode);

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

        String url = "http://10.95.88.16:8080/grci/resources/cb/saveSchedule";
        return restTemplate.postForEntity(url, request, String.class);
    }
}
