package com.grkicbreport.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grkicbreport.component.InformHelper;
import com.grkicbreport.dto.saveSchedule.saveScheduleDTO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class saveScheduleBatchService {

    private final KreditRepository kreditRepository;
    private final saveScheduleService saveScheduleService;
    private final RestTemplate restTemplate;
    private final InformHelper informHelper;

    private static final Logger logger = Logger.getLogger(saveScheduleBatchService.class.getName());

    public saveScheduleBatchService(KreditRepository kreditRepository,
                                    saveScheduleService saveScheduleService,
                                    RestTemplate restTemplate,
                                    InformHelper informHelper) {
        this.kreditRepository = kreditRepository;
        this.saveScheduleService = saveScheduleService;
        this.restTemplate = restTemplate;
        this.informHelper = informHelper;
    }

    public List<String> processSchedulesForPeriod(LocalDate from, LocalDate to, String save_mode, boolean sendToCb) {
        List<Kredit> credits = kreditRepository.findAllByDatadogBetween(from, to);
        List<String> results = new ArrayList<>();

        Inform inform = informHelper.fetchSingleRow();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

        for (Kredit kredit : credits) {
            try {
                saveScheduleDTO dto = saveScheduleService.createSchedule(kredit.getNumdog(), save_mode);

                if (dto == null) {
                    results.add("Ошибка формирования графика: " + kredit.getNumdog());
                    continue;
                }

                if (sendToCb) {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.set("Login", "NK" + inform.getNumks());
                    headers.set("Password", inform.getGrki_password());

                    String json = gson.toJson(dto);
                    HttpEntity<String> request = new HttpEntity<>(json, headers);

                    String url = "http://10.95.88.16:8080/grci/resources/cb/saveSchedule";
                    ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

                    results.add("Кредит " + kredit.getNumdog() + " отправлен: " + response.getStatusCode() + "\n"+ response.getBody());
                } else {
                    results.add("Кредит " + kredit.getNumdog() + " сформирован (без отправки)");
                }

            } catch (Exception e) {
                results.add("Ошибка при обработке кредита " + kredit.getNumdog() + ": " + e.getMessage());
                logger.warning("Ошибка: " + e.getMessage());
            }
        }

        return results;
    }
}