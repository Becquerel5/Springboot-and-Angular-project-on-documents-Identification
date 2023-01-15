package com.fabrication.agent.controllers;

import com.fabrication.agent.services.ReferentDocumentService;
import com.fabrication.entities.Additionaldocument;
import com.fabrication.entities.Cni;
import com.fabrication.entities.Passport;
import com.fabrication.entities.Referencedocument;
import com.fabrication.utils.Gender;
import com.fabrication.utils.StatusInTreatment;
import com.fabrication.utils.StatusTreatmentSystemeList;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ReferenceDocumentController.class})
@ExtendWith(SpringExtension.class)
class ReferenceDocumentControllerTest {
    @Autowired
    private ReferenceDocumentController referenceDoController;

    @MockBean
    private ReferentDocumentService referentDocumentService;

    private Cni getCni() {
        Cni cni = new Cni();
        cni.setAddress("add");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        cni.setDateOfBirth(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        cni.setDeliveryDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        cni.setDocumentNumber("42");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        cni.setExpirationDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        cni.setFirstName("fn");
        cni.setGender(Gender.FEMALE);
        cni.setIdDocumentReference(5L);
        cni.setLastName("ln");
        cni.setNameOfFather("nf");
        cni.setNameOfMother("nm");
        cni.setPosteIdentification("pi");
        cni.setProfession("p");
        cni.setReferenceNumber("42");
        cni.setStatusInTreatment(StatusInTreatment.Ready);
        cni.setStatusTreatmentSystemeList(StatusTreatmentSystemeList.FORM);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        cni.setWithdrawalDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        return cni;
    }
    private Cni getCni1() {
        Cni cni = new Cni();
        ArrayList<Additionaldocument> additionalDocumentList = new ArrayList<>();
        cni.setAddress("add1");
        ZoneId zone = ZoneId.of("UTC");
        cni.setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(zone).toInstant()));
        ZoneId zone1 = ZoneId.of("UTC");
        cni.setDeliveryDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(zone1).toInstant()));
        cni.setDocumentNumber("42");
        ZoneId zone2 = ZoneId.of("UTC");
        cni.setExpirationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(zone2).toInstant()));
        cni.setFirstName("FN");
        cni.setGender(Gender.FEMALE);
        cni.setIdDocumentReference(5L);
        cni.setLastName("LN");
        cni.setNameOfFather("FN");
        cni.setNameOfMother("MN");
        cni.setPosteIdentification("Pi");
        cni.setProfession("Profession");
        cni.setReferenceNumber("42");
        cni.setStatusInTreatment(StatusInTreatment.Ready);
        cni.setStatusTreatmentSystemeList(StatusTreatmentSystemeList.FORM);
        return cni;
    }

    private Passport getPassport() {
        Passport passport = new Passport();
        ArrayList<Additionaldocument> additionalDocumentList = new ArrayList<>();
        passport.setAddress("PA");
        passport.setCountry("GB");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        passport.setDateOfBirth(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        passport.setDeliveryDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        passport.setDocumentNumber("44");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        passport.setExpirationDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        passport.setFirstName("fn");
        passport.setGender(Gender.FEMALE);
        passport.setIdDocumentReference(6L);
        passport.setLastName("ln");
        passport.setNameOfFather("Nf");
        passport.setNameOfMother("Nm");
        passport.setProfession("p");
        passport.setReferenceNumber("44");
        passport.setStatusInTreatment(StatusInTreatment.Ready);
        passport.setStatusTreatmentSystemeList(StatusTreatmentSystemeList.FORM);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        passport.setWithdrawalDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        return passport;
    }
    private Passport getPassportupd() {
        Passport passport = new Passport();
        ArrayList<Additionaldocument> additionalDocumentList = new ArrayList<>();
        passport.setAddress("PA");
        passport.setCountry("GB");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        passport.setDateOfBirth(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        passport.setDeliveryDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        passport.setDocumentNumber("44");
        ZoneId zone2 = ZoneId.of("UTC");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        passport.setExpirationDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        passport.setGender(Gender.FEMALE);
        passport.setIdDocumentReference(6l);
        passport.setLastName("ln");
        passport.setNameOfFather("Nf");
        passport.setNameOfMother("Nm");
        passport.setProfession("p");
        passport.setReferenceNumber("44");
        passport.setStatusInTreatment(StatusInTreatment.Ready);
        passport.setStatusTreatmentSystemeList(StatusTreatmentSystemeList.FORM);
        return passport;
    }


    @Test
    void itShouldSaveCni() throws Exception {

        Cni cni1  = new Cni();
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .post("/api/v1/document/cni", cni1)
                .contentType(MediaType.APPLICATION_JSON);

        Cni cni = new Cni();
        cni.setAddress("42 Main St");
        ZoneId zone = ZoneId.of("UTC");
        cni.setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(zone).toInstant()));
        ZoneId zone1 = ZoneId.of("UTC");
        cni.setDeliveryDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(zone1).toInstant()));
        cni.setDocumentNumber("42");
        ZoneId zone2 = ZoneId.of("UTC");
        cni.setExpirationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(zone2).toInstant()));
        cni.setFirstName("Jane");
        cni.setGender(Gender.FEMALE);
        cni.setIdDocumentReference(5L);
        cni.setLastName("Doe");
        cni.setNameOfFather("Name Of Father");
        cni.setNameOfMother("Name Of Mother");
        cni.setPosteIdentification("Poste Identification");
        cni.setProfession("Profession");
        cni.setReferenceNumber("42");
        cni.setStatusInTreatment(StatusInTreatment.Ready);
        cni.setStatusTreatmentSystemeList(StatusTreatmentSystemeList.FORM);
        ZoneId zone3 = ZoneId.of("UTC");
        cni.setWithdrawalDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(zone3).toInstant()));
        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper.writeValueAsString(cni));
        Object[] controllers = new Object[]{referenceDoController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();

        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

    }

    @Test
    void itShouldFetchedCni() throws Exception {
        when(referentDocumentService.getAllDocuments()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/document/find_All_Documents");
        MockMvcBuilders.standaloneSetup(referenceDoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void itShouldUpdateCni() throws Exception {

        Cni cni = getCni();
        when(referentDocumentService.updateCni((Cni) any(), (Long) any())).thenReturn(cni);

        Cni cni1 = getCni1();
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        cni1.setWithdrawalDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        String content = (new ObjectMapper()).writeValueAsString(cni1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/document/cni/{id}", 5L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(referenceDoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"idDocumentReference\":5,\"documentNumber\":\"42\",\"referenceNumber\":\"42\",\"lastName\":\"ln\",\"firstName\":"
                                        + "\"fn\",\"dateOfBirth\":0,\"gender\":\"FEMALE\",\"profession\":\"p\",\"nameOfFather\":\"nf\","
                                        + "\"nameOfMother\":\"nm\",\"deliveryDate\":0,\"expirationDate\":0,\"address\":\"add\","
                                        + "\"agent\":null,\"client\":null,\"imageadditionaldocument\":null,\"statusTreatmentSystemeList\""
                                        + ":\"FORM\",\"statusInTreatment\":\"Ready\",\"withdrawalDate\":0,\"birthcertificate\":null,"
                                        + "\"nationalitycertificate\":null,\"lostcertificate\":null,\"biometric\":null,\"posteIdentification\":\"pi\"}"));


       /* ArrayList<Additionaldocument> additionalDocumentList = new ArrayList<>();
        cni.setAdditionalDocumentList(additionalDocumentList);
        cni.setAddress("42 Main St");
        ZoneId zone = ZoneId.of("UTC");
        cni.setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(zone).toInstant()));
        ZoneId zone1 = ZoneId.of("UTC");
        cni.setDeliveryDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(zone1).toInstant()));
        cni.setDocumentNumber("42");
        ZoneId zone2 = ZoneId.of("UTC");
        cni.setExpirationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(zone2).toInstant()));
        cni.setFirstName("Jane");
        cni.setGender(Gender.FEMALE);
        cni.setIdDocumentReference(1L);
        cni.setLastName("Doe");
        cni.setNameOfFather("Name Of Father");
        cni.setNameOfMother("Name Of Mother");
        cni.setPosteIdentification("Poste Identification");
        cni.setProfession("Profession");
        cni.setReferenceNumber("42");
        cni.setStatusInTreatment(StatusInTreatment.Ready);
        cni.setStatusTreatmentSystemeList(StatusTreatmentSystemeList.FORM);
        ZoneId zone3 = ZoneId.of("UTC");
        cni.setWithdrawalDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(zone3).toInstant()));

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper.writeValueAsString(cni));
        Object[] controllers = new Object[]{referenceDoController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();


        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert

        */

    }

    @Test
    void itShouldDeleteCniById() throws Exception {
        doNothing().when(referentDocumentService).deleteCni((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/document/cni/{id}", 5L);
        MockMvcBuilders.standaloneSetup(referenceDoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Deleted Successfully"));
    }


///Passports

    @Test
    void itShouldSavePassport() throws Exception {

        Passport passport1 = new Passport();
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .post("/api/v1/document/passport", passport1)
                .contentType(MediaType.APPLICATION_JSON);

        Passport passport = new Passport();
        ArrayList<Additionaldocument> additionalDocumentList = new ArrayList<>();
        passport.setAddress("42 Main St");
        passport.setCountry("GB");
        ZoneId zone = ZoneId.of("UTC");
        passport.setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(zone).toInstant()));
        ZoneId zone1 = ZoneId.of("UTC");
        passport.setDeliveryDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(zone1).toInstant()));
        passport.setDocumentNumber("42");
        ZoneId zone2 = ZoneId.of("UTC");
        passport.setExpirationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(zone2).toInstant()));
        passport.setFirstName("Jane");
        passport.setGender(Gender.FEMALE);
        passport.setIdDocumentReference(6L);
        passport.setLastName("Doe");
        passport.setNameOfFather("Name Of Father");
        passport.setNameOfMother("Name Of Mother");
        passport.setProfession("Profession");
        passport.setReferenceNumber("42");
        passport.setStatusInTreatment(StatusInTreatment.Ready);
        passport.setStatusTreatmentSystemeList(StatusTreatmentSystemeList.FORM);
        ZoneId zone3 = ZoneId.of("UTC");
        passport.setWithdrawalDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(zone3).toInstant()));

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(passport));
        Object[] controllers = new Object[]{referenceDoController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();


        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

    }




    @Test
    void itShouldUpdatePassPort() throws Exception {

        Passport passport1 = new Passport();
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/api/v1/document/passport/{id}", passport1)
                .contentType(MediaType.APPLICATION_JSON);

        Passport passport = new Passport();
        ArrayList<Additionaldocument> additionalDocumentList = new ArrayList<>();
        passport.setAddress("42 Main St");
        passport.setCountry("GB");
        ZoneId zone = ZoneId.of("UTC");
        passport.setDateOfBirth(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(zone).toInstant()));
        ZoneId zone1 = ZoneId.of("UTC");
        passport.setDeliveryDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(zone1).toInstant()));
        passport.setDocumentNumber("42");
        ZoneId zone2 = ZoneId.of("UTC");
        passport.setExpirationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(zone2).toInstant()));
        passport.setFirstName("Jane");
        passport.setGender(Gender.FEMALE);
        passport.setIdDocumentReference(6L);
        passport.setLastName("Doe");
        passport.setNameOfFather("Name Of Father");
        passport.setNameOfMother("Name Of Mother");
        passport.setProfession("Profession");
        passport.setReferenceNumber("42");
        passport.setStatusInTreatment(StatusInTreatment.Ready);
        passport.setStatusTreatmentSystemeList(StatusTreatmentSystemeList.FORM);
        ZoneId zone3 = ZoneId.of("UTC");
        passport.setWithdrawalDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(zone3).toInstant()));

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(passport));
        Object[] controllers = new Object[]{referenceDoController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();


        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

    }

    @Test
    void itShouldInitCni(){
        ResponseEntity responseEntity = referenceDoController.initCni(1L);
        verify(referentDocumentService,times(1)).initCni(1L);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void itShouldInitPassPort(){
        ResponseEntity responseEntity = referenceDoController.initPassPort(1L);
        verify(referentDocumentService,times(1)).initPassPort(1L);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void itShouldFindAlldDocument(){
        List<Referencedocument> referenceDocumentList = new ArrayList<>();
        referenceDocumentList.add(this.getCni());
        referenceDocumentList.add(this.getCni1());
        referenceDocumentList.add(this.getCni());
        referenceDocumentList.add(this.getCni1());
        referenceDocumentList.add(this.getCni());
        referenceDocumentList.add(this.getCni());
        referenceDocumentList.add(this.getCni1());
        referenceDocumentList.add(this.getCni());
        referenceDocumentList.add(this.getCni1());
        referenceDocumentList.add(this.getCni());
        Page<Referencedocument> referenceDocumentPageMock = new PageImpl<>(
                referenceDocumentList, PageRequest.of(0,5),5
        );
        StatusTreatmentSystemeList statusTreatmentSystemeList = StatusTreatmentSystemeList.BUILD;

        //When
        when(
                referentDocumentService.findAllReferenceByStatusTreatmentSystemListAndPage(
                        statusTreatmentSystemeList,
                0,
                5)
        )
                .thenReturn(referenceDocumentPageMock);
        ResponseEntity responseEntity = referenceDoController.findAllDocumentByPage(
                0,
                5,
                statusTreatmentSystemeList
        );
        verify(referentDocumentService,times(1))
                .findAllReferenceByStatusTreatmentSystemListAndPage(
                        StatusTreatmentSystemeList.BUILD,
                        0,
                        5);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    void itShouldFindAllBuildDocument(){
        List<Referencedocument> referenceDocumentList = new ArrayList<>();
        referenceDocumentList.add(this.getCni());
        referenceDocumentList.add(this.getCni1());
        referenceDocumentList.add(this.getCni());
        referenceDocumentList.add(this.getCni1());
        referenceDocumentList.add(this.getCni());
        referenceDocumentList.add(this.getCni());
        referenceDocumentList.add(this.getCni1());
        referenceDocumentList.add(this.getCni());
        referenceDocumentList.add(this.getCni1());
        referenceDocumentList.add(this.getCni());
        Page<Referencedocument> referenceDocumentPageMock = new PageImpl<>(
                referenceDocumentList, PageRequest.of(0,5),5
        );
        StatusInTreatment statusInTreatment = StatusInTreatment.Waiting;

        //When
        when(
                referentDocumentService.findAllReferenceInBuildingStepByStatusInTreatmentAndPage(
                        statusInTreatment,
                        0,
                        5)
        )
                .thenReturn(referenceDocumentPageMock);
        ResponseEntity responseEntity = referenceDoController.findAllDocumentBuildByPage(
                0,
                5,
                statusInTreatment
        );
        verify(referentDocumentService,times(1))
                .findAllReferenceInBuildingStepByStatusInTreatmentAndPage(
                        statusInTreatment,
                        0,
                        5);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
    }
}

