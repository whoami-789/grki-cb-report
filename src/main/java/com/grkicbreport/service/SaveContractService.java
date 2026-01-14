package com.grkicbreport.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grkicbreport.component.InformHelper;
import com.grkicbreport.dto.CreditorDTO;
import com.grkicbreport.dto.saveContract.*;
import com.grkicbreport.model.Grafik;
import com.grkicbreport.model.Inform;
import com.grkicbreport.model.Kredit;
import com.grkicbreport.repository.AzolikFizRepository;
import com.grkicbreport.repository.AzolikYurRepository;
import com.grkicbreport.repository.GrafikRepository;
import com.grkicbreport.repository.KreditRepository;
import com.grkicbreport.response.Response;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
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
    private final InformHelper informHelper;

    public SaveContractService(AzolikYurRepository azolikYurRepository, AzolikFizRepository azolikFizRepository, KreditRepository kreditRepository, GrafikRepository grafikRepository, RestTemplate restTemplate, InformHelper informHelper) {
        this.azolikYurRepository = azolikYurRepository;
        this.azolikFizRepository = azolikFizRepository;
        this.kreditRepository = kreditRepository;
        this.grafikRepository = grafikRepository;
        this.restTemplate = restTemplate;
        this.informHelper = informHelper;
    }

    public saveContractDTO createContract(String contractNumber, String save_mode) {
        Optional<Kredit> kreditList = kreditRepository.findByNumdog(contractNumber);
        LocalDate maxDats = grafikRepository.findMaxDatsByNumdog(contractNumber);
        System.out.println("Максимальная дата: " + maxDats);
        Inform inform = informHelper.fetchSingleRow();


        if (kreditList.isEmpty()) {
            throw new IllegalArgumentException("Кредит с таким номером не найден.");
        }


        Kredit kredit = kreditList.get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        try {
            // Создаем и заполняем DTO
            saveContractDTO dto = new saveContractDTO();

            dto.setSave_mode(save_mode);

            // Заполнение CreditorDTO
            CreditorDTO creditorDTO = new CreditorDTO();
            creditorDTO.setType("03");
            creditorDTO.setCode(inform.getNumks());
            creditorDTO.setOffice(null);
            dto.setCreditor(creditorDTO);

            // Заполнение ClaimDTO
            ClaimDTO claimDTO = new ClaimDTO();
            claimDTO.setClaim_guid(kredit.getGrkiClaimId().replaceAll("\\s", ""));
            String cleanedNumdog = kredit.getNumdog().replaceAll("[.\\-KК/\\\\]", "");
            if (Objects.equals(save_mode, "5")) claimDTO.setContract_guid(kredit.getGrkiContractId());
            claimDTO.setClaim_id(cleanedNumdog.replaceAll("\\s", ""));
            claimDTO.setContract_id(cleanedNumdog.replaceAll("\\s", ""));
            dto.setClaim(claimDTO);

            DecisionDTO decisionDTO = new DecisionDTO();
            decisionDTO.setDecide("03");
            decisionDTO.setNumber(cleanedNumdog); // вручную
            decisionDTO.setDate(kredit.getDatadog().format(formatter)); // вручную
            decisionDTO.setDecide_chief(inform.getFioDirektor());
            decisionDTO.setBorrower_link("0");
            dto.setDecision(decisionDTO);

            ContractDTO contractDTO = new ContractDTO();
            contractDTO.setLoan_type("10");
            contractDTO.setIssue_mode("02");
            contractDTO.setLoan_line("02");
            contractDTO.setAsset_quality("1");
            contractDTO.setNumber(kredit.getNumdog().replaceAll("\\s", ""));
            contractDTO.setDate_begin(kredit.getDatadog().format(formatter));
            contractDTO.setDate_end(maxDats.format(formatter));
            contractDTO.setCurrency("000");
            contractDTO.setAmount(kredit.getSumma().intValue() + "00");
            contractDTO.setDiscont_comissions(null);
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
            targetsDTO.setAmount(kredit.getSumma().intValue() + "00");
            targetsDTO.setInfo("На бумажном носителе");
            dto.getTargets().add(targetsDTO);

            SourcesDTO sourcesDTO = new SourcesDTO();
            sourcesDTO.setType("100");
            sourcesDTO.setCurrency("000");
            sourcesDTO.setAmount(kredit.getSumma().intValue() + "00");
            dto.getSources().add(sourcesDTO);

            if (Objects.equals(save_mode, "5")) {
                Change_basisDTO changeBasisDTO = new Change_basisDTO();
                changeBasisDTO.setRevisor("01");
                changeBasisDTO.setNumber(cleanedNumdog);
                changeBasisDTO.setDate(kredit.getDatadog().format(formatter));
                changeBasisDTO.setReason("Корректировка суммы");
                changeBasisDTO.setRevisor_chief(inform.getFioDirektor());
                dto.setChange_basis(changeBasisDTO);
            }

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
            return null;
        }
    }

    public ResponseEntity<String> sendSaveContract(String contractNumber, String save_mode) {
        saveContractDTO dto = createContract(contractNumber, save_mode);

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

        String url = "http://10.95.88.16:8080/grci/resources/cb/saveContract";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        // Обработка ответа
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            Response responseBody = gson.fromJson(response.getBody(), Response.class);
            logger.info("Ответ от сервера: " + gson.toJson(responseBody));

            String success = responseBody.getResult().getSuccess();
            Optional<Kredit> kreditOptional = kreditRepository.findByNumdog(contractNumber);

            if (kreditOptional.isPresent()) {
                Kredit kredit = kreditOptional.get();

                if ("1".equals(success)) {
                    // Успешный результат — сохраняем в базу
                    String contractGuid = responseBody.getAnswer().getIdentity().getContract_guid();

                    if (Objects.equals(kredit.getGrkiContractId(), "") || kredit.getGrkiContractId() == null) {
                        kreditRepository.updateGrkiContractId(contractGuid, kredit.getNumdog());
                        logger.info("Contract_guid сохранён в базе.");
                        return ResponseEntity.ok("Данные успешно сохранены в базу. Ответ: " + gson.toJson(responseBody));
                    } else {
                        logger.info("Поле ContractId уже заполнено, обновление не требуется.");
                        return ResponseEntity.status(HttpStatus.OK)
                                .body("Contract_guid уже существует, обновление не выполнено. Ответ: " + gson.toJson(responseBody));
                    }
                } else if ("0".equals(success)) {
                    // Если код успеха равен 0, проверяем наличие ошибки 24023
                    boolean isDuplicateError = responseBody.getAnswer().getErrors().stream()
                            .anyMatch(error -> "24029".equals(error.getCode()) || "24033".equals(error.getCode()) || "24039".equals(error.getCode()));

                    if (isDuplicateError) {
                        String sendInfoUrl = "http://localhost:5051/api/grki/send-save-info?id=" + kredit.getNumdog() + "&type=2";
                        restTemplate.getForEntity(sendInfoUrl, String.class);
                        logger.info("Отправлен запрос на: " + sendInfoUrl);
                        return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body("Контракт с указанным номером кредитной организации уже существует. Дополнительный запрос выполнен. Ответ: " + gson.toJson(responseBody));
                    }
                }
            } else {
                logger.warning("Кредит с таким номером договора не найден.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Кредит с таким номером договора не найден. Ответ: " + gson.toJson(responseBody));
            }
        } else {
            // Обработка ошибок с передачей тела ответа
            String errorResponse = response.getBody();
            logger.warning("Ошибка при выполнении запроса к внешнему сервису. Ответ: " + errorResponse);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Ошибка при выполнении запроса к внешнему сервису. Детали: " + errorResponse);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Неизвестная ошибка при обработке запроса.");
    }

}