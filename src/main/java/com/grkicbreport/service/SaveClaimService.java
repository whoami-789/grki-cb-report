package com.grkicbreport.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grkicbreport.dto.CreditorDTO;
import com.grkicbreport.dto.saveClaim.*;
import com.grkicbreport.model.AzolikFiz;
import com.grkicbreport.model.AzolikYur;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SaveClaimService {

    private final AzolikFizRepository azolikFizRepository;
    private final AzolikYurRepository azolikYurRepository;
    private final KreditRepository kreditRepository;
    private final RestTemplate restTemplate;


    public SaveClaimService(AzolikFizRepository azolikFizRepository, AzolikYurRepository azolikYurRepository, KreditRepository kreditRepository, RestTemplate restTemplate) {
        this.azolikFizRepository = azolikFizRepository;
        this.azolikYurRepository = azolikYurRepository;
        this.kreditRepository = kreditRepository;
        this.restTemplate = restTemplate;
    }


    public saveClaimDTO createClaim(String contractNumber, String work) {

        Optional<Kredit> kreditList = kreditRepository.findByNumdog(contractNumber);


        if (kreditList.isEmpty()) {
            throw new IllegalArgumentException("Кредит с таким номером не найден.");
        }

        Kredit kredit = kreditList.get();

        try {
            Optional<AzolikFiz> azolikFizList = azolikFizRepository.findByKodchlen(kredit.getKod());

            AzolikFiz azolikFiz = azolikFizList.get();

            // Создаем и заполняем DTO
            saveClaimDTO dto = new saveClaimDTO();

            dto.setSave_mode("1");

            // Заполнение CreditorDTO
            CreditorDTO creditorDTO = new CreditorDTO();
            creditorDTO.setType("02");
            creditorDTO.setCode("6005");
            dto.setCreditor(creditorDTO);

            // Заполнение ClaimDTO
            ClaimDTO claimDTO = new ClaimDTO();
            claimDTO.setClaim_id(kredit.getNumdog().replaceAll("\\s", ""));
            claimDTO.setType("01");
            claimDTO.setNumber(kredit.getNumdog().replaceAll("\\s", "")); // Вызов метода или сервиса для получения номера
            claimDTO.setDate(LocalDate.now().toString());
            dto.setClaim(claimDTO);

            // Заполнение CreditDTO
            CreditDTO creditDTO = new CreditDTO();
            creditDTO.setSubject_type("2");
            creditDTO.setType("10");
            creditDTO.setCurrency("860");
            creditDTO.setAmount(String.valueOf(kredit.getSumma()));
            creditDTO.setPercent(String.valueOf(kredit.getProsent()));
            creditDTO.setPeriod(String.valueOf(kredit.getSrokkred()));
            creditDTO.setTargets(List.of()); // Если список пустой
            creditDTO.setNew_staff(null); // Если значение null
            dto.setCredit(creditDTO);

            // Заполнение BorrowerDTO
            BorrowerDTO borrowerDTO = new BorrowerDTO();
            borrowerDTO.setResident("1");
            borrowerDTO.setPinfl(azolikFiz.getKodPension().replaceAll("\\s", ""));
            borrowerDTO.setInn(azolikFiz.getInn().replaceAll("\\s", ""));
            borrowerDTO.setNibbd_code(azolikFiz.getKodchlen().replaceAll("\\s", ""));
            borrowerDTO.setSecond_name(azolikFiz.getFam().replaceAll("\\s", ""));
            borrowerDTO.setFirst_name(azolikFiz.getImya().replaceAll("\\s", ""));
            borrowerDTO.setBirth_date(String.valueOf(azolikFiz.getDatsRojd()));
            borrowerDTO.setGender(String.valueOf(azolikFiz.getFsobst()));
            borrowerDTO.setCitizenship("860");
            borrowerDTO.setArea(azolikFiz.getKodObl().replaceFirst("0", ""));
            borrowerDTO.setRegion(azolikFiz.getKodRayon().replaceFirst("0", ""));
            borrowerDTO.setDoc_type("1");
            borrowerDTO.setDoc_seria(azolikFiz.getSer_pasp().replaceAll("\\s", ""));
            borrowerDTO.setDoc_number(azolikFiz.getNum_pasp().replaceAll("\\s", ""));
            borrowerDTO.setDoc_date(String.valueOf(azolikFiz.getVidanPasp()));
            borrowerDTO.setDoc_issuer(azolikFiz.getKem_pasp().replaceAll("\\s", ""));
            dto.setBorrower(borrowerDTO);

            // Заполнение списка IncomeDTO
            IncomeDTO incomeDTO = new IncomeDTO();
            incomeDTO.setIncome_type(work);
            dto.setIncome(List.of(incomeDTO)); // Устанавливаем список доходов

            // Заполнение ContactsDTO
            ContactsDTO contactsDTO = new ContactsDTO();
            contactsDTO.setPost_address(azolikFiz.getAdres().replaceAll("\\s", ""));
            contactsDTO.setPhone(azolikFiz.getTelmobil().replaceAll("\\s", ""));
            dto.setContacts(contactsDTO);

            // Возвращаем заполненный DTO
            Gson gson = new GsonBuilder()
                    .serializeNulls() // Include null values in the JSON output
                    .setPrettyPrinting() // Enable pretty printing for better readability
                    .create();
            String formattedJson = gson.toJson(dto);
            System.out.println(formattedJson);
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
            creditorDTO.setCode("6005");
            dto.setCreditor(creditorDTO);

            // Заполнение ClaimDTO
            ClaimDTO claimDTO = new ClaimDTO();
            claimDTO.setClaim_guid(""); // Вызов метода или сервиса для получения GUID
            claimDTO.setClaim_id(kredit.getNumdog().replaceAll("\\s", ""));
            claimDTO.setType("01");
            claimDTO.setNumber(kredit.getNumdog().replaceAll("\\s", "")); // Вызов метода или сервиса для получения номера
            claimDTO.setDate(LocalDate.now().toString());
            dto.setClaim(claimDTO);

            // Заполнение CreditDTO
            CreditDTO creditDTO = new CreditDTO();
            creditDTO.setSubject_type("1");
            creditDTO.setType("10");
            creditDTO.setCurrency("860");
            creditDTO.setAmount(String.valueOf(kredit.getSumma()));
            creditDTO.setPercent(String.valueOf(kredit.getProsent()));
            creditDTO.setPeriod(String.valueOf(kredit.getSrokkred()));
            creditDTO.setTargets(List.of()); // Если список пустой
            creditDTO.setNew_staff(null); // Если значение null
            dto.setCredit(creditDTO);

            // Заполнение BorrowerDTO
            BorrowerDTO borrowerDTO = new BorrowerDTO();
            borrowerDTO.setResident("1");
            borrowerDTO.setPinfl("");
            borrowerDTO.setInn(azolikYur.getInn().replaceAll("\\s", ""));
            borrowerDTO.setNibbd_code(azolikYur.getKodchlen().replaceAll("\\s", ""));
            borrowerDTO.setCitizenship("860");
            borrowerDTO.setArea(azolikYur.getKodObl().replaceFirst("0", ""));
            borrowerDTO.setRegion(azolikYur.getKodRayon().replaceFirst("0", ""));
            borrowerDTO.setWork_area("6");
            borrowerDTO.setWork_region("30");
            borrowerDTO.setActivity_direction("1");
            borrowerDTO.setEgrsp_data("");
            borrowerDTO.setEgrsp_number("");
            borrowerDTO.setOked("");
            borrowerDTO.setOwnership("");
            borrowerDTO.setLegal_form("");
            borrowerDTO.setSoogu("");
            borrowerDTO.setSoato("");
            borrowerDTO.setType_business("");
            borrowerDTO.setFull_name(azolikYur.getName().replaceAll("\\s", ""));
            borrowerDTO.setShort_name("");
            borrowerDTO.setStaff_count("");
            borrowerDTO.setDoc_type("1");
            dto.setBorrower(borrowerDTO);

            // Заполнение списка IncomeDTO
            IncomeDTO incomeDTO = new IncomeDTO();
            incomeDTO.setIncome_type(work);
            dto.setIncome(List.of(incomeDTO)); // Устанавливаем список доходов

            // Заполнение ContactsDTO
            ContactsDTO contactsDTO = new ContactsDTO();
            contactsDTO.setPost_address(azolikYur.getAdres().replaceAll("\\s", ""));
            dto.setContacts(contactsDTO);

            // Возвращаем заполненный DTO
            System.out.println("yur");
            return dto;
        }
    }

    public ResponseEntity<String> sendSaveClaim(String contractNumber, String work) {
        saveClaimDTO dto = createClaim(contractNumber, work);

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

        String url = "http://grki-service/grci/resources/cb/saveClaim";
        return restTemplate.postForEntity(url, request, String.class);
    }

}
