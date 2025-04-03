package com.grkicbreport.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grkicbreport.component.InformHelper;
import com.grkicbreport.dto.CreditorDTO;
import com.grkicbreport.dto.getInformationDTO;
import com.grkicbreport.model.Inform;
import com.grkicbreport.model.Kredit;
import com.grkicbreport.repository.KreditRepository;
import com.grkicbreport.response.Response;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class getIdentityService {
    private final RestTemplate restTemplate;
    private static final Logger logger = Logger.getLogger(SaveContractService.class.getName());
    private final KreditRepository kreditRepository;
    private final InformHelper informHelper;

    public getIdentityService(RestTemplate restTemplate, KreditRepository kreditRepository, InformHelper informHelper) {
        this.restTemplate = restTemplate;
        this.kreditRepository = kreditRepository;
        this.informHelper = informHelper;
    }

    public getInformationDTO createDTO(String id, String type) {
        Inform inform = informHelper.fetchSingleRow();

        try {
            // Создаем и заполняем DTO
            getInformationDTO dto = new getInformationDTO();

            String cleanedNumdog = id.replaceAll("[-KК/\\\\]", "");

            // Заполнение CreditorDTO
            CreditorDTO creditorDTO = new CreditorDTO();
            creditorDTO.setType("02");
            creditorDTO.setCode(inform.getNumks());
            creditorDTO.setOffice(null);
            dto.setCreditor(creditorDTO);

            getInformationDTO.IdentityDTO identityDTO = new getInformationDTO.IdentityDTO();
            identityDTO.setIdentity_type(type);
            identityDTO.setIdentity_id(cleanedNumdog.replaceAll(" ", ""));
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

        String url = "http://10.95.88.16:8080/grci/resources/cb/getInformation";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            // Парсим ответ
            Response responseBody = gson.fromJson(response.getBody(), Response.class);
            logger.info("Ответ от внешнего сервиса: " + responseBody);

            String success = responseBody.getResult().getSuccess();

            if ("1".equals(success)) {
                // Успех, обработка данных
                Optional<Kredit> kreditOptional = kreditRepository.findByNumdog(id);

                if (kreditOptional.isPresent()) {
                    Kredit kredit = kreditOptional.get();
                    String claimGuid = responseBody.getAnswer().getIdentity().getClaim_guid();
                    String contractGuid = responseBody.getAnswer().getIdentity().getContract_guid();

                    if ("1".equals(type)) {
                        // Сохраняем claim_guid в grkiClaimId
                        if (Objects.equals(kredit.getGrkiClaimId(), "") || kredit.getGrkiClaimId() == null) {
                            kreditRepository.updateGrkiClaimId(claimGuid, kredit.getNumdog());
                            logger.info("Claim_guid сохранён в базе.");
                            return ResponseEntity.ok("Claim_guid успешно сохранён.");
                        } else {
                            logger.info("Поле ClaimId уже заполнено, обновление не требуется.");
                            return ResponseEntity.status(HttpStatus.OK)
                                    .body("Claim_guid уже существует, обновление не выполнено.");
                        }
                    } else if ("2".equals(type)) {
                        // Сохраняем contract_guid в grkiContractId
                        if (Objects.equals(kredit.getGrkiContractId(), "") || kredit.getGrkiContractId() == null) {
                            kreditRepository.updateGrkiContractId(contractGuid, kredit.getNumdog());
                            logger.info("Contract_guid сохранён в базе.");
                            return ResponseEntity.ok("Contract_guid успешно сохранён.");
                        } else {
                            logger.info("Поле ContractId уже заполнено, обновление не требуется.");
                            return ResponseEntity.status(HttpStatus.OK)
                                    .body("Contract_guid уже существует, обновление не выполнено.");
                        }
                    } else {
                        logger.warning("Неизвестный тип операции: " + type);
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("Неверный параметр type.");
                    }
                } else {
                    logger.warning("Кредит с таким номером договора не найден.");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Кредит с таким номером договора не найден.");
                }
            } else {
                logger.warning("Ответ сервиса не содержит успешного статуса.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Не удалось обработать запрос, статус success != 1.");
            }
        } else {
            logger.warning("Ошибка при выполнении запроса к внешнему сервису.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Ошибка при выполнении запроса к внешнему сервису.");
        }
    }


}
