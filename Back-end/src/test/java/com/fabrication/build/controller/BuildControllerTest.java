package com.fabrication.build.controller;

import com.fabrication.build.services.BuildService;
import com.fabrication.build.services.BuildServiceImpl;
import com.fabrication.entities.*;
import com.fabrication.exceptions.ResourceNotFoundException;
import com.fabrication.utils.Gender;
import com.fabrication.utils.PersonStatus;
import com.fabrication.utils.StatusInTreatment;
import com.fabrication.utils.StatusTreatmentSystemeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.exceptions.base.MockitoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuildControllerTest {
    private BuildController buildController;
    private BuildService buildService;

    @BeforeEach
    void setUp(){
        buildService = mock(BuildServiceImpl.class);
        buildController = new BuildController(buildService);
    }


    private Imageadditionaldocument getImageAdditionalDocument(){
        return new Imageadditionaldocument(
                1L,
                "jhvjhvjh",
                Date.from(Instant.now()),
                new Agent(),
                new Client(),
                new Cni()
        );
    }
    private Cni getReferenceDocument() {
        return new Cni(
                1L,
                null,
                "123456",
                "lastName",
                "sdvsdv",
                Date.from(Instant.now()),
                Gender.MALE,
                "profession",
                "nameOfFather",
                "nameOfMother",
                Date.from(Instant.now()),
                Date.from(Instant.now()),
                "address",
                new Agent(
                        2L,
                        "jkhkdfjhk@sd.sd",
                        PersonStatus.ACTIVE,
                        "jhbfbsdjfb",
                        "bjbnbvwsds",
                        "login",
                        "pwd"
                ),
                new Client(
                        1L,
                        "jlkfsdf@gm.de",
                        PersonStatus.ACTIVE,
                        "123456",
                        Date.from(Instant.now())
                ),
                this.getImageAdditionalDocument(),
                StatusTreatmentSystemeList.BUILD,
                StatusInTreatment.Ready,
                null
        );
    }

    private Page<Referencedocument> listDocReadyToBuildData(){
        Cni cni = getReferenceDocument();
        Cni cni1 =  new Cni(
                89L,
                null,
                "123456",
                "lastName",
                "sdvsdv",
                Date.from(Instant.now()),
                Gender.MALE,
                "profession",
                "nameOfFather",
                "nameOfMother",
                Date.from(Instant.now()),
                Date.from(Instant.now()),
                "address",
                new Agent(
                        2L,
                        "jkhkdfjhk@sd.sd",
                        PersonStatus.ACTIVE,
                        "jhbfbsdjfb",
                        "bjbnbvwsds",
                        "login",
                        "pwd"
                ),
                new Client(
                        1L,
                        "jlkfsdf@gm.de",
                        PersonStatus.ACTIVE,
                        "123456",
                        Date.from(Instant.now())
                ),
                this.getImageAdditionalDocument(),
                StatusTreatmentSystemeList.BUILD,
                StatusInTreatment.Ready,
                null
        );
        Cni cni2 =  new Cni(
                1L,
                null,
                "123456",
                "lastName",
                "sdvsdv",
                Date.from(Instant.now()),
                Gender.MALE,
                "profession",
                "nameOfFather",
                "nameOfMother",
                Date.from(Instant.now()),
                Date.from(Instant.now()),
                "address",
                new Agent(
                        2L,
                        "jkhkdfjhk@sd.sd",
                        PersonStatus.ACTIVE,
                        "jhbfbsdjfb",
                        "bjbnbvwsds",
                        "login",
                        "pwd"
                ),
                new Client(
                        198L,
                        "kjjlkfsdf@gm.de",
                        PersonStatus.ACTIVE,
                        "123456",
                        Date.from(Instant.now())
                ),
                this.getImageAdditionalDocument(),
                StatusTreatmentSystemeList.BUILD,
                StatusInTreatment.Ready,
                null
        );
        Cni cni3 =  new Cni(
                889L,
                null,
                "123456",
                "lastName",
                "sdvsdv",
                Date.from(Instant.now()),
                Gender.MALE,
                "profession",
                "nameOfFather",
                "nameOfMother",
                Date.from(Instant.now()),
                Date.from(Instant.now()),
                "address",
                new Agent(
                        2L,
                        "jkhkdfjhk@sd.sd",
                        PersonStatus.ACTIVE,
                        "jhbfbsdjfb",
                        "bjbnbvwsds",
                        "login",
                        "pwd"
                ),
                new Client(
                        7L,
                        "jlkfsf@gm.de",
                        PersonStatus.ACTIVE,
                        "123456",
                        Date.from(Instant.now())
                ),
                this.getImageAdditionalDocument(),
                StatusTreatmentSystemeList.BUILD,
                StatusInTreatment.Ready,
                null
        );
        List<Referencedocument> referencedocumentList = new ArrayList<>();
        referencedocumentList.add(cni);
        referencedocumentList.add(cni1);
        referencedocumentList.add(cni2);
        referencedocumentList.add(cni3);

        return new PageImpl<Referencedocument>(referencedocumentList, PageRequest.of(0, 2),4L);

    }

    private Page<Referencedocument> listDocInRealTimeBuildData(){
        Cni cni =  new Cni(
                1L,
                null,
                "123456",
                "lastName",
                "sdvsdv",
                Date.from(Instant.now()),
                Gender.MALE,
                "profession",
                "nameOfFather",
                "nameOfMother",
                Date.from(Instant.now()),
                Date.from(Instant.now()),
                "address",
                new Agent(
                        2L,
                        "jkhkdfjhk@sd.sd",
                        PersonStatus.ACTIVE,
                        "jhbfbsdjfb",
                        "bjbnbvwsds",
                        "login",
                        "pwd"
                ),
                new Client(
                        1L,
                        "jlkfsdf@gm.de",
                        PersonStatus.ACTIVE,
                        "123456",
                        Date.from(Instant.now())
                ),
                this.getImageAdditionalDocument(),
                StatusTreatmentSystemeList.BUILD,
                StatusInTreatment.Waiting,
                null
        );
        Cni cni1 =  new Cni(
                89L,
                null,
                "123456",
                "lastName",
                "sdvsdv",
                Date.from(Instant.now()),
                Gender.MALE,
                "profession",
                "nameOfFather",
                "nameOfMother",
                Date.from(Instant.now()),
                Date.from(Instant.now()),
                "address",
                new Agent(
                        2L,
                        "jkhkdfjhk@sd.sd",
                        PersonStatus.ACTIVE,
                        "jhbfbsdjfb",
                        "bjbnbvwsds",
                        "login",
                        "pwd"
                ),
                new Client(
                        1L,
                        "jlkfsdf@gm.de",
                        PersonStatus.ACTIVE,
                        "123456",
                        Date.from(Instant.now())
                ),
                this.getImageAdditionalDocument(),
                StatusTreatmentSystemeList.BUILD,
                StatusInTreatment.Waiting,
                null
        );
        Cni cni2 =  new Cni(
                1L,
                null,
                "123456",
                "lastName",
                "sdvsdv",
                Date.from(Instant.now()),
                Gender.MALE,
                "profession",
                "nameOfFather",
                "nameOfMother",
                Date.from(Instant.now()),
                Date.from(Instant.now()),
                "address",
                new Agent(
                        2L,
                        "jkhkdfjhk@sd.sd",
                        PersonStatus.ACTIVE,
                        "jhbfbsdjfb",
                        "bjbnbvwsds",
                        "login",
                        "pwd"
                ),
                new Client(
                        198L,
                        "kjjlkfsdf@gm.de",
                        PersonStatus.ACTIVE,
                        "123456",
                        Date.from(Instant.now())
                ),
                this.getImageAdditionalDocument(),
                StatusTreatmentSystemeList.BUILD,
                StatusInTreatment.Waiting,
                null
        );
        Cni cni3 =  new Cni(
                889L,
                null,
                "123456",
                "lastName",
                "sdvsdv",
                Date.from(Instant.now()),
                Gender.MALE,
                "profession",
                "nameOfFather",
                "nameOfMother",
                Date.from(Instant.now()),
                Date.from(Instant.now()),
                "address",
                new Agent(
                        2L,
                        "jkhkdfjhk@sd.sd",
                        PersonStatus.ACTIVE,
                        "jhbfbsdjfb",
                        "bjbnbvwsds",
                        "login",
                        "pwd"
                ),
                new Client(
                        7L,
                        "jlkfsf@gm.de",
                        PersonStatus.ACTIVE,
                        "123456",
                        Date.from(Instant.now())
                ),
                this.getImageAdditionalDocument(),
                StatusTreatmentSystemeList.BUILD,
                StatusInTreatment.Waiting,
                null
        );
        List<Referencedocument> referencedocumentList = new ArrayList<>();
        referencedocumentList.add(cni);
        referencedocumentList.add(cni1);
        referencedocumentList.add(cni2);
        referencedocumentList.add(cni3);

        return new PageImpl<Referencedocument>(referencedocumentList, PageRequest.of(0, 2),4L);

    }


    @Test
    void givenPageAndSize_itShouldReturnAllDocumentReadyToPrint() {
        //Given
        int page= 0;
        int size = 2;

        //When
        when(buildService.listDocReadyToBuild(page,size)).thenReturn(this.listDocReadyToBuildData());
        ResponseEntity<?> responseEntity = buildController.findAllDocumentReadyToPrint(page,size);

        //Then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().toString()).isEqualTo(this.listDocReadyToBuildData().toString());
    }

    @Test
    void givenPageAndSize_itShouldThrowAnExceptionDocumentReadyToPrint() {
        //Given
        int page= 0;
        int size = 2;

        //When
        when(buildService.listDocReadyToBuild(page,size)).thenThrow(new MockitoException("Error while getting data"));
        Throwable throwable = assertThrows(
                Exception.class,
                ()->buildController.findAllDocumentReadyToPrint(page,size)
        );
    }

    @Test
    void givenIdAndStatusInTreatment_itShouldUpdateDocumentStatus() {
        //Given
        Long id = 1L;
        StatusInTreatment statusInTreatment = StatusInTreatment.Ready;

        //When
        doNothing().when(mock(BuildServiceImpl.class)).changeStatusOfDocument(id, statusInTreatment);
        ResponseEntity<?> responseEntity = buildController.updateDocumentStatus(id, statusInTreatment);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void givenPageAndSize_itShouldReturnAllDocumentRealTimePrinting() {
        //Given
        int page= 0;
        int size = 2;

        //When
        when(buildService.listDocInRealTimeBuilding(page,size)).thenReturn(this.listDocReadyToBuildData());
        ResponseEntity<?> responseEntity = buildController.findAllDocumentRealTimePrinting(page,size);

        //Then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().toString()).isEqualTo(this.listDocReadyToBuildData().toString());
    }

    @Test
    void givenPageAndSize_itShouldThrowAnExceptionDocumentRealTimePrinting() {
        //Given
        int page= 0;
        int size = 2;

        //When
        when(buildService.listDocInRealTimeBuilding(page,size)).thenThrow(new MockitoException("Error while getting data"));
        Throwable throwable = assertThrows(
                Exception.class,
                ()->buildController.findAllDocumentRealTimePrinting(page,size)
        );
    }

    @Test
    void givenId_itShouldThrowAnExceptionWhenFindDocumentById() {
        //Given
        Long id= 1L;

        //When
        when(buildService.getDocumentInformationById(id)).thenThrow(new MockitoException("Error while getting data"));
        Throwable throwable = assertThrows(
                Exception.class,
                ()->buildController.findDocumentById(id)
        );
    }

    @Test
    void givenId_itShouldReturnDocument() {
        //Given
        Long id= 1L;

        //When
        when(buildService.getDocumentInformationById(id)).thenReturn(this.getReferenceDocument());
        ResponseEntity<?> responseEntity = buildController.findDocumentById(id);

        //Then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().toString()).isEqualTo(this.getReferenceDocument().toString());
    }
}