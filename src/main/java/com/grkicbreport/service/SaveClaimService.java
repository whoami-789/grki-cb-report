package com.grkicbreport.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grkicbreport.component.InformHelper;
import com.grkicbreport.dto.CreditorDTO;
import com.grkicbreport.dto.saveClaim.*;
import com.grkicbreport.model.AzolikFiz;
import com.grkicbreport.model.AzolikYur;
import com.grkicbreport.model.Inform;
import com.grkicbreport.model.Kredit;
import com.grkicbreport.repository.AzolikFizRepository;
import com.grkicbreport.repository.AzolikYurRepository;
import com.grkicbreport.repository.KreditRepository;
import com.grkicbreport.response.Response;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class SaveClaimService {

    private final AzolikFizRepository azolikFizRepository;
    private final AzolikYurRepository azolikYurRepository;
    private final KreditRepository kreditRepository;
    private final RestTemplate restTemplate;
    private static final Logger logger = Logger.getLogger(SaveClaimService.class.getName());
    private final InformHelper informHelper;



    public SaveClaimService(AzolikFizRepository azolikFizRepository, AzolikYurRepository azolikYurRepository, KreditRepository kreditRepository, RestTemplate restTemplate, InformHelper informHelper) {
        this.azolikFizRepository = azolikFizRepository;
        this.azolikYurRepository = azolikYurRepository;
        this.kreditRepository = kreditRepository;
        this.restTemplate = restTemplate;
        this.informHelper = informHelper;
    }


    public saveClaimDTO createClaim(String contractNumber, String save_mode) {

        Optional<Kredit> kreditList = kreditRepository.findByNumdog(contractNumber);

        Inform inform = informHelper.fetchSingleRow();

        if (kreditList.isEmpty()) {
            throw new IllegalArgumentException("Кредит с таким номером не найден.");
        }

        Kredit kredit = kreditList.get();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            Optional<AzolikFiz> azolikFizList = azolikFizRepository.findByKodchlen(kredit.getKod());

            AzolikFiz azolikFiz = azolikFizList.get();

            // Создаем и заполняем DTO
            saveClaimDTO dto = new saveClaimDTO();

            dto.setSave_mode(save_mode);

            // Заполнение CreditorDTO
            CreditorDTO creditorDTO = new CreditorDTO();
            creditorDTO.setType("02");
            creditorDTO.setCode(inform.getNumks());
            dto.setCreditor(creditorDTO);

            // Заполнение ClaimDTO
            ClaimDTO claimDTO = new ClaimDTO();
            String cleanedNumdog = kredit.getNumdog().replaceAll("[-KК/\\\\]", "");
            if (Objects.equals(save_mode, "5")) claimDTO.setClaim_guid(kredit.getGrkiClaimId());
            claimDTO.setClaim_id(cleanedNumdog.replaceAll("\\s", ""));
            claimDTO.setNumber(cleanedNumdog.replaceAll("\\s", ""));
            claimDTO.setType("01");
            claimDTO.setDate(kredit.getDatadog().format(formatter));
            dto.setClaim(claimDTO);

            // Заполнение CreditDTO
            CreditDTO creditDTO = new CreditDTO();
            creditDTO.setSubject_type("2");
            creditDTO.setType("10");
            creditDTO.setCurrency("000");
            creditDTO.setAmount(kredit.getSumma().intValue() + "00");
            creditDTO.setPercent(String.valueOf(kredit.getProsent()));
            creditDTO.setPeriod(String.valueOf(kredit.getSrokkred()));
            List<String> targets = new ArrayList<>();
            targets.add("0699");
            creditDTO.setTargets(targets);
            dto.setCredit(creditDTO);

            // Заполнение BorrowerDTO
            BorrowerDTO borrowerDTO = new BorrowerDTO();
            borrowerDTO.setResident("1");
            borrowerDTO.setPinfl(azolikFiz.getKodPension().replaceAll("\\s", ""));
            borrowerDTO.setInn(azolikFiz.getInn().replaceAll("\\s", ""));
            borrowerDTO.setNibbd_code(azolikFiz.getKodchlen().replaceAll("\\s", ""));
            borrowerDTO.setSecond_name(azolikFiz.getFam().replaceAll("\\s", ""));
            borrowerDTO.setFirst_name(azolikFiz.getImya().replaceAll("\\s", ""));
            borrowerDTO.setBirth_date(azolikFiz.getDatsRojd().format(formatter));
            borrowerDTO.setGender(String.valueOf(azolikFiz.getFsobst()));
            borrowerDTO.setCitizenship("860");
            borrowerDTO.setArea(azolikFiz.getKodObl());
            borrowerDTO.setRegion(azolikFiz.getKodRayon().replaceAll("\\s", ""));
            borrowerDTO.setDoc_type("1");
            borrowerDTO.setDoc_seria(azolikFiz.getSer_pasp().replaceAll("\\s", ""));
            borrowerDTO.setDoc_number(azolikFiz.getNum_pasp().replaceAll("\\s", ""));
            borrowerDTO.setDoc_date(azolikFiz.getVidanPasp().format(formatter));
            borrowerDTO.setDoc_issuer(azolikFiz.getKem_pasp().replaceAll("\\s", ""));
            dto.setBorrower(borrowerDTO);

            // Заполнение списка IncomeDTO
            IncomeDTO incomeDTO = new IncomeDTO();
            incomeDTO.setIncome_type("08");
            dto.setIncome(List.of(incomeDTO)); // Устанавливаем список доходов

            // Заполнение ContactsDTO
            ContactsDTO contactsDTO = new ContactsDTO();
            contactsDTO.setPost_address(azolikFiz.getAdres().replaceAll("\\s", ""));
            contactsDTO.setPhone("+998" + azolikFiz.getTelmobil().replaceAll("\\s", ""));
            dto.setContacts(contactsDTO);

            // Возвращаем заполненный DTO
            Gson gson = new GsonBuilder()
                    .serializeNulls() // Include null values in the JSON output
                    .setPrettyPrinting() // Enable pretty printing for better readability
                    .create();
            String formattedJson = gson.toJson(dto);
            logger.info(formattedJson);
            return dto;

        } catch (Exception e) {

            Optional<AzolikYur> azolikYurList = azolikYurRepository.findByKodchlen(kredit.getKod());

            AzolikYur azolikYur = azolikYurList.get();

            // Создаем и заполняем DTO
            saveClaimDTO dto = new saveClaimDTO();

            dto.setSave_mode("1");

            // Заполнение CreditorDTO
            CreditorDTO creditorDTO = new CreditorDTO();
            creditorDTO.setType("02");
            creditorDTO.setCode(inform.getNumks());
            dto.setCreditor(creditorDTO);

            // Заполнение ClaimDTO
            ClaimDTO claimDTO = new ClaimDTO();
            claimDTO.setClaim_guid("");
            String cleanedNumdog = kredit.getNumdog().replaceAll("[-K/\\\\]", "");
            claimDTO.setClaim_id(cleanedNumdog.replaceAll("\\s", ""));
            claimDTO.setNumber(cleanedNumdog.replaceAll("\\s", ""));
            claimDTO.setType("01");
            claimDTO.setDate(LocalDate.now().toString());
            dto.setClaim(claimDTO);

            // Заполнение CreditDTO
            CreditDTO creditDTO = new CreditDTO();
            creditDTO.setSubject_type("1");
            creditDTO.setType("10");
            creditDTO.setCurrency("000");
            creditDTO.setAmount(String.valueOf(kredit.getSumma()));
            creditDTO.setPercent(String.valueOf(kredit.getProsent()));
            creditDTO.setPeriod(String.valueOf(kredit.getSrokkred()));
            creditDTO.setTargets(List.of()); // Если список пустой
            creditDTO.setNew_staff(null); // Если значение null
            dto.setCredit(creditDTO);

            // Заполнение BorrowerDTO
            BorrowerDTO borrowerDTO = new BorrowerDTO();
            borrowerDTO.setResident("1");
            borrowerDTO.setInn(azolikYur.getInn().replaceAll("\\s", ""));
            borrowerDTO.setNibbd_code(azolikYur.getKodchlen().replaceAll("\\s", ""));
            borrowerDTO.setCitizenship("860");
            borrowerDTO.setArea(azolikYur.getKodObl().replaceFirst("0", ""));
            borrowerDTO.setRegion(azolikYur.getKodRayon().replaceFirst("0", ""));
            borrowerDTO.setWork_area("6");
            borrowerDTO.setWork_region("30");
            borrowerDTO.setActivity_direction("1");
            borrowerDTO.setFull_name(azolikYur.getName().replaceAll("\\s", ""));
            borrowerDTO.setDoc_type("1");
            dto.setBorrower(borrowerDTO);

            // Заполнение списка IncomeDTO
            IncomeDTO incomeDTO = new IncomeDTO();
            incomeDTO.setIncome_type("08");
            dto.setIncome(List.of(incomeDTO)); // Устанавливаем список доходов

            // Заполнение ContactsDTO
            ContactsDTO contactsDTO = new ContactsDTO();
            contactsDTO.setPost_address(azolikYur.getAdres().replaceAll("\\s", ""));
            dto.setContacts(contactsDTO);

            // Возвращаем заполненный DTO
            logger.info("yur");
            return dto;
        }
    }

    public ResponseEntity<String> sendSaveClaim(String contractNumber, String save_mode) {
        saveClaimDTO dto = createClaim(contractNumber, save_mode);
        Inform inform = informHelper.fetchSingleRow();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        headers.set("Login", "NK" + inform.getNumks());
        headers.set("Password", inform.getGrki_password());

        Gson gson = new GsonBuilder()
                .serializeNulls() // Включить null значения в JSON
                .setPrettyPrinting() // Для более удобного отображения JSON
                .create();
        String formattedJson = gson.toJson(dto);

        HttpEntity<String> request = new HttpEntity<>(formattedJson, headers);

        String url = "http://10.95.88.16:8080/grci/resources/cb/saveClaim";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        // Парсинг ответа
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            Response responseBody = gson.fromJson(response.getBody(), Response.class);
            String responseJson = gson.toJson(responseBody); // Форматируем ответ в красивый JSON
            logger.info("Ответ от сервера: " + responseJson);

            String success = responseBody.getResult().getSuccess();

            Optional<Kredit> kreditOptional = kreditRepository.findByNumdog(contractNumber);

            if (kreditOptional.isPresent()) {
                Kredit kredit = kreditOptional.get();

                if ("0".equals(success)) {
                    // Проверяем наличие ошибок с кодом 24023
                    boolean isDuplicateError = responseBody.getAnswer().getErrors().stream()
                            .anyMatch(error -> "24023".equals(error.getCode()));

                    if (isDuplicateError) {
                        String sendInfoUrl = "http://localhost:5051/api/grki/send-save-info?id=" + kredit.getNumdog() + "&type=1";
                        restTemplate.getForEntity(sendInfoUrl, String.class);
                        logger.info("Отправлен запрос на: " + sendInfoUrl);
                        return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body("Заявка с указанным номером кредитной организации уже существует. Дополнительный запрос выполнен. Ответ: " + responseJson);
                    }
                } else if ("1".equals(success)) {
                    // Успешный результат — сохраняем в базу
                    String claimGuid = responseBody.getAnswer().getIdentity().getClaim_guid();

                    if (Objects.equals(kredit.getGrkiClaimId(), "") || kredit.getGrkiClaimId() == null) {
                        kreditRepository.updateGrkiClaimId(claimGuid, kredit.getNumdog());
                        logger.info("Claim_guid сохранён в базе.");
                        return ResponseEntity.ok("Данные успешно сохранены в базу. Ответ: " + responseJson);
                    } else {
                        logger.info("Поле ClaimId уже заполнено, обновление не требуется.");
                        return ResponseEntity.status(HttpStatus.OK)
                                .body("Claim_guid уже существует, обновление не выполнено. Ответ: " + responseJson);
                    }
                }
            } else {
                logger.warning("Кредит с таким номером договора не найден.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Кредит с таким номером договора не найден. Ответ: " + gson.toJson(responseBody));
            }
        } else {
            logger.warning("Ошибка при выполнении запроса к внешнему сервису.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Ошибка при выполнении запроса к внешнему сервису. Ответ: " + response.getBody());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Неизвестная ошибка при обработке запроса.");
    }

}
