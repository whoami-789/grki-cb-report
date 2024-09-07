package com.grkicbreport.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grkicbreport.dto.CreditorDTO;
import com.grkicbreport.dto.saveProvision.ContractDTO;
import com.grkicbreport.dto.saveProvision.ProvisionsDTO;
import com.grkicbreport.dto.saveProvision.saveProvisionDTO;
import com.grkicbreport.model.AzolikFiz;
import com.grkicbreport.model.AzolikYur;
import com.grkicbreport.model.Kredit;
import com.grkicbreport.model.Zalog;
import com.grkicbreport.repository.AzolikFizRepository;
import com.grkicbreport.repository.AzolikYurRepository;
import com.grkicbreport.repository.KreditRepository;
import com.grkicbreport.repository.ZalogRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class SaveProvisionService {

    private final RestTemplate restTemplate;
    private final KreditRepository kreditRepository;
    private final ZalogRepository zalogRepository;
    private final AzolikFizRepository azolikFizRepository;
    private final AzolikYurRepository azolikYurRepository;

    public SaveProvisionService(RestTemplate restTemplate, KreditRepository kreditRepository, ZalogRepository zalogRepository, AzolikFizRepository azolikFizRepository, AzolikYurRepository azolikYurRepository) {
        this.restTemplate = restTemplate;
        this.kreditRepository = kreditRepository;
        this.zalogRepository = zalogRepository;
        this.azolikFizRepository = azolikFizRepository;
        this.azolikYurRepository = azolikYurRepository;
    }

    public saveProvisionDTO createProvision(String contractNumber, String provisionNumber,
                                            LocalDate provisionDate, String nibbd) {

        Optional<Kredit> kreditList = kreditRepository.findByNumdog(contractNumber);
        Optional<Zalog> zalogList = zalogRepository.findByNumdog(contractNumber);

        if (kreditList.isEmpty()) {
            throw new IllegalArgumentException("Кредит с таким номером не найден.");
        }

        if (zalogList.isEmpty()) {
            throw new IllegalArgumentException("Залог с таким номером не найден.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        Kredit kredit = kreditList.get();
        Zalog zalog = zalogList.get();

        try {


            saveProvisionDTO dto = new saveProvisionDTO();

            dto.setSave_mode("1");

            CreditorDTO creditorDTO = new CreditorDTO();
            creditorDTO.setType("02");
            creditorDTO.setCode("06005");
            creditorDTO.setOffice(null);
            dto.setCreditor(creditorDTO);

            ContractDTO contractDTO = new ContractDTO();
            contractDTO.setContract_guid(kredit.getGrkiContractId());
            contractDTO.setContract_id(kredit.getNumdog());
            dto.setContract(contractDTO);

            ProvisionsDTO provisionsDTO = new ProvisionsDTO();
            if (zalog.getKodZalog() == 1) {
                provisionsDTO.setProvision_type("712");
            } else if (zalog.getKodZalog() == 2) {
                provisionsDTO.setProvision_type("200");
            } else if (zalog.getKodZalog() == 3) {
                provisionsDTO.setProvision_type("101");
            } else if (zalog.getKodZalog() == 8) {
                provisionsDTO.setProvision_type("400");
            }
            provisionsDTO.setCurrency("000");
            provisionsDTO.setAmount(String.valueOf(zalog.getSums()));
            provisionsDTO.setProvision_source("02");
            provisionsDTO.setNumber(provisionNumber);
            provisionsDTO.setDate(provisionDate.format(formatter));
            if (zalog.getKodZalog() == 1) {
                provisionsDTO.setName("Ювелирные изделия");
            } else if (zalog.getKodZalog() == 2) {
                provisionsDTO.setName("Транспортные средства");
            } else if (zalog.getKodZalog() == 3) {
                provisionsDTO.setName("Недвижимость");
            } else if (zalog.getKodZalog() == 8) {
                provisionsDTO.setName("Гарантии и поручительства");
            }

            try {
                Optional<AzolikYur> azolikYur = azolikYurRepository.findByKodchlen(kredit.getKod());

                if (azolikYur.isPresent()) {
                    AzolikYur azolikYur1 = azolikYur.get();

                    ProvisionsDTO.OwnerLegal ownerLegal = new ProvisionsDTO.OwnerLegal();
                    ownerLegal.setResident_code("1");
                    ownerLegal.setResident_inn(azolikYur1.getInn());
                    ownerLegal.setNibbd_code(nibbd);
                    ownerLegal.setName(azolikYur1.getName());
                    ownerLegal.setCountry("860");
                    ownerLegal.setArea("06");
                    ownerLegal.setRegion("30");
                    ownerLegal.setPost_address(azolikYur1.getAdres());
                    provisionsDTO.setOwner_legal(ownerLegal);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            try {
                Optional<AzolikFiz> azolikFizList = azolikFizRepository.findByKodchlen(kredit.getKod());

                if (azolikFizList.isPresent()) {

                    AzolikFiz azolikFiz = azolikFizList.get();

                    ProvisionsDTO.OwnerIndividual ownerIndividual = new ProvisionsDTO.OwnerIndividual();
                    ownerIndividual.setResident_code("1");
                    ownerIndividual.setPinfl(azolikFiz.getKodPension());
                    ownerIndividual.setBirth_date(String.valueOf(azolikFiz.getDatsRojd().format(formatter)));
                    ownerIndividual.setGender(String.valueOf(azolikFiz.getFsobst()));
                    ownerIndividual.setCountry("860");
                    ownerIndividual.setArea("6");
                    ownerIndividual.setRegion("30");
                    ownerIndividual.setDoc_type("1");
                    ownerIndividual.setDoc_seria(azolikFiz.getSer_pasp());
                    ownerIndividual.setDoc_number(azolikFiz.getNum_pasp());
                    ownerIndividual.setDoc_date(String.valueOf(azolikFiz.getVidanPasp()));
                    ownerIndividual.setDoc_issuer(azolikFiz.getKem_pasp());
                    ownerIndividual.setSecond_name(azolikFiz.getImya());
                    ownerIndividual.setFirst_name(azolikFiz.getFam());
                    ownerIndividual.setPatronymic(azolikFiz.getOtch());
                    ownerIndividual.setPost_address(azolikFiz.getAdres());
                    ownerIndividual.setPhone(azolikFiz.getTelmobil());
                    provisionsDTO.setOwner_individual(ownerIndividual);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            dto.setProvisions(provisionsDTO);

            return dto;


        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<String> sendSaveProvision(String contractNumber, String provisionNumber,
                                                    LocalDate provisionDate, String nibbd) {
        saveProvisionDTO dto = createProvision(contractNumber, provisionNumber, provisionDate, nibbd);

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
