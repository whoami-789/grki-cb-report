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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class SaveProvisionService {

    private final RestTemplate restTemplate;
    private final KreditRepository kreditRepository;
    private final ZalogRepository zalogRepository;
    private final AzolikFizRepository azolikFizRepository;
    private final AzolikYurRepository azolikYurRepository;
    private static final Logger logger = Logger.getLogger(SaveProvisionService.class.getName());


    public SaveProvisionService(RestTemplate restTemplate, KreditRepository kreditRepository, ZalogRepository zalogRepository, AzolikFizRepository azolikFizRepository, AzolikYurRepository azolikYurRepository) {
        this.restTemplate = restTemplate;
        this.kreditRepository = kreditRepository;
        this.zalogRepository = zalogRepository;
        this.azolikFizRepository = azolikFizRepository;
        this.azolikYurRepository = azolikYurRepository;
    }

    public saveProvisionDTO createProvision(String contractNumber, String provisionNumber,
                                            LocalDate provisionDate, String nibbd, String engineNumber, String bodyNumber,
                                            String year, String stateNumber, String model, String chassisNumber, String color,
                                            String docSeriaNumber, String vinNumber) {

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
            creditorDTO.setType("03");
            creditorDTO.setCode("07074");
            creditorDTO.setOffice(null);
            dto.setCreditor(creditorDTO);

            ContractDTO contractDTO = new ContractDTO();
            contractDTO.setContract_guid(kredit.getGrkiContractId());
            String cleanedNumdog = kredit.getNumdog().replaceAll("^([0-9]+).*", "$1");
            contractDTO.setContract_id(cleanedNumdog.replaceAll("\\s", ""));
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
            provisionsDTO.setAmount(String.valueOf(zalog.getSums().intValue()));
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
                    ownerIndividual.setArea("06");
                    ownerIndividual.setRegion("030");
                    ownerIndividual.setDoc_type("1");
                    ownerIndividual.setDoc_seria(azolikFiz.getSer_pasp().replaceAll("\\s", ""));
                    ownerIndividual.setDoc_number(azolikFiz.getNum_pasp().replaceAll("\\s", ""));
                    ownerIndividual.setDoc_date(azolikFiz.getVidanPasp().format(formatter));
                    ownerIndividual.setDoc_issuer(azolikFiz.getKem_pasp().replaceAll("\\s", ""));
                    ownerIndividual.setSecond_name(azolikFiz.getImya().replaceAll("\\s", ""));
                    ownerIndividual.setFirst_name(azolikFiz.getFam().replaceAll("\\s", ""));
                    ownerIndividual.setPatronymic(azolikFiz.getOtch().replaceAll("\\s", ""));
                    ownerIndividual.setPost_address(azolikFiz.getAdres().replaceAll("\\s", ""));
                    ownerIndividual.setPhone("+998" + azolikFiz.getTelmobil().replaceAll("\\s", ""));

                    provisionsDTO.setOwner_individual(ownerIndividual);
                    if (zalog.getKodZalog() == 1) {
                        List<ProvisionsDTO.Collateral> collateralList = new ArrayList<>();
                        ProvisionsDTO.Collateral collateral = new ProvisionsDTO.Collateral();
                        collateral.setProvision_id(cleanedNumdog.replaceAll("\\s", "")); // Replace with actual data
                        collateral.setPledge_amount(String.valueOf(zalog.getSums().intValue())); // Replace with actual data
                        collateral.setObject_name("Ювелирные изделия"); // Replace with actual data
                        collateral.setObject_area("06"); // Replace with actual data
                        collateral.setObject_region("030"); // Replace with actual data

                        collateralList.add(collateral);
                        provisionsDTO.setCollateral(collateralList);
                    } else if (zalog.getKodZalog() == 2) {
                        List<ProvisionsDTO.Vehicle> vehicleArrayList = new ArrayList<>();
                        ProvisionsDTO.Vehicle vehicle = new ProvisionsDTO.Vehicle();
                        vehicle.setProvision_id(cleanedNumdog.replaceAll("\\s", "")); // Replace with actual data
                        vehicle.setPledge_amount(String.valueOf(zalog.getSums().intValue())); // Replace with actual data
                        vehicle.setEstimate_amount(String.valueOf(zalog.getSums().intValue())); // Replace with actual data
                        vehicle.setCountry("860");
                        vehicle.setEstimate_inn("300469626");
                        vehicle.setEstimate_name("NILUFAR ASILBEK BARAKA");
                        vehicle.setEstimate_date(kredit.getDatadog().format(formatter));
                        vehicle.setEngine_number(engineNumber);
                        vehicle.setBody_number(bodyNumber);
                        vehicle.setYear(year);
                        vehicle.setModel(model);
                        vehicle.setState_number(stateNumber);
                        vehicle.setChassis_number(chassisNumber);
                        vehicle.setColor(color);
                        vehicle.setDoc_seria_number(docSeriaNumber);
                        vehicle.setVin_number(vinNumber);

                        vehicleArrayList.add(vehicle);
                        provisionsDTO.setVehicles(vehicleArrayList);
                    }

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            List<ProvisionsDTO> provisionsList = new ArrayList<>();
            provisionsList.add(provisionsDTO);

// Set the list to the dto
            dto.setProvisions(provisionsList);

            Gson gson = new GsonBuilder()
                    .serializeNulls() // Include null values in the JSON output
                    .setPrettyPrinting() // Enable pretty printing for better readability
                    .create();
            String formattedJson = gson.toJson(dto);
            logger.info(formattedJson);
            return dto;

        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<String> sendSaveProvision(String contractNumber, String provisionNumber,
                                                    LocalDate provisionDate, String nibbd, String engineNumber, String bodyNumber,
                                                    String year, String stateNumber, String model, String chassisNumber, String color,
                                                    String docSeriaNumber, String vinNumber) {
        saveProvisionDTO dto = createProvision(contractNumber, provisionNumber, provisionDate, nibbd,
                engineNumber, bodyNumber, year, stateNumber, model, chassisNumber, color, docSeriaNumber, vinNumber);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Добавляем заголовки login и password
        headers.set("Login", "NK07074");
        headers.set("Password", "c8be958b5d7cec1fc78eb37600c15934");
        Gson gson = new GsonBuilder()
                .serializeNulls() // Include null values in the JSON output
                .setPrettyPrinting() // Enable pretty printing for better readability
                .create();
        String formattedJson = gson.toJson(dto);

        HttpEntity<String> request = new HttpEntity<>(formattedJson, headers);

        String url = "http://10.95.88.48/grci/resources/cb/saveProvision";
        return restTemplate.postForEntity(url, request, String.class);
    }
}
