package com.fabrication.build.services;

import com.fabrication.agent.repositories.ReferenceDocumentRepository;
import com.fabrication.entities.*;
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

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class BuildServiceImplTest {
    private BuildService buildService;
    private ReferenceDocumentRepository referenceDocumentRepository;

    @BeforeEach
    void setUp(){
        referenceDocumentRepository = mock(ReferenceDocumentRepository.class);
        buildService = new BuildServiceImpl(referenceDocumentRepository);
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
    void itShouldReturnPageListOfDocReadyToBuild() {
        when(referenceDocumentRepository
                .findAll(
                        StatusTreatmentSystemeList.BUILD,
                        StatusInTreatment.Ready,
                        PageRequest.of(0,2)
                )
        ).thenReturn(this.listDocReadyToBuildData());
        Page<Referencedocument> data = buildService.listDocReadyToBuild(0,2);
        verify(referenceDocumentRepository, times(1)).findAll(
                StatusTreatmentSystemeList.BUILD,
                StatusInTreatment.Ready,
                PageRequest.of(0,2)
        );
        assertThat(data.isEmpty()).isFalse();
        assertThat(data.getContent()).isNotEmpty();
    }

    @Test
    void itShouldReturnAnEmptyPageListOfDocReadyToBuild() {
        when(referenceDocumentRepository
                .findAll(
                        StatusTreatmentSystemeList.BUILD,
                        StatusInTreatment.Ready,
                        PageRequest.of(0,2)
                )
        ).thenReturn(new PageImpl(new ArrayList<Referencedocument>()));
        Page<Referencedocument> data = buildService.listDocReadyToBuild(0,2);
        assertThat(data.isEmpty()).isTrue();
        assertThat(data.getContent()).isEmpty();
    }

    @Test
    void itShouldThrowAnExceptionWhenGetAListOfDocReadyToBuild() {
        when(referenceDocumentRepository
                .findAll(
                        StatusTreatmentSystemeList.BUILD,
                        StatusInTreatment.Ready,
                        PageRequest.of(1,1)
                )
        ).thenThrow(new MockitoException("Error while getting data"));
        Throwable throwable = assertThrows(
                Exception.class,
                ()->buildService.listDocReadyToBuild(10,0)
        );
    }

    @Test
    void itShouldThrowAnExceptionWhenGetDocumentInformationById() {
        Optional<Object> objectOptional = Optional.empty();
        when(referenceDocumentRepository.findOptionalReferenceDocumentById(1L)).thenReturn(objectOptional);
        Throwable throwable = assertThrows(
                Exception.class,
                ()->buildService.getDocumentInformationById(1L)
        );
        assertThat(throwable.getMessage()).isEqualTo("Unexisting Element");
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

    @Test
    void itShouldGetDocumentInformationById() {
        Optional<Object> objectOptional = Optional.of(this.getReferenceDocument());
        when(referenceDocumentRepository.findOptionalReferenceDocumentById(1L)).thenReturn(objectOptional);
        Cni cni = (Cni) buildService.getDocumentInformationById(1L);
        assertThat(cni.getIdDocumentReference()).isEqualTo(this.getReferenceDocument().getIdDocumentReference());
    }

    @Test
    void itShouldReturnPageListOfDocRealTimeBuilding() {
        when(referenceDocumentRepository
                .findAll(
                        StatusTreatmentSystemeList.BUILD,
                        StatusInTreatment.Waiting,
                        PageRequest.of(0,2)
                )
        ).thenReturn(this.listDocInRealTimeBuildData());
        Page<Referencedocument> data = buildService.listDocInRealTimeBuilding(0,2);
        verify(referenceDocumentRepository, times(1)).findAll(
                StatusTreatmentSystemeList.BUILD,
                StatusInTreatment.Waiting,
                PageRequest.of(0,2)
        );
        assertThat(data.isEmpty()).isFalse();
        assertThat(data.getContent()).isNotEmpty();
    }

    @Test
    void itShouldReturnAnEmptyPageListOfDocRealTimeBuilding() {
        when(referenceDocumentRepository
                .findAll(
                        StatusTreatmentSystemeList.BUILD,
                        StatusInTreatment.Waiting,
                        PageRequest.of(0,2)
                )
        ).thenReturn(new PageImpl(new ArrayList<Referencedocument>()));
        Page<Referencedocument> data = buildService.listDocInRealTimeBuilding(0,2);
        assertThat(data.isEmpty()).isTrue();
        assertThat(data.getContent()).isEmpty();
    }

    @Test
    void itShouldThrowAnExceptionWhenGetAListOfDocRealTimeBuilding() {
        when(referenceDocumentRepository
                .findAll(
                        StatusTreatmentSystemeList.BUILD,
                        StatusInTreatment.Waiting,
                        PageRequest.of(1,1)
                )
        ).thenThrow(new MockitoException("Error while getting data"));
        assertThrows(
                Exception.class,
                ()->buildService.listDocInRealTimeBuilding(10,0)
        );
    }

    @Test
    void itShouldThrowAnExceptionWhileChangeStatusOfDocument() {
        Optional<Object> objectOptional = Optional.empty();
        when(referenceDocumentRepository.findOptionalReferenceDocumentById(1L)).thenReturn(objectOptional);
        Throwable throwable = assertThrows(
                Exception.class,
                ()->buildService.changeStatusOfDocument(1L, StatusInTreatment.Ready)
        );
        assertThat(throwable.getMessage()).isEqualTo("Unexisting Element");
    }

    @Test
    void itShouldChangeStatusOfDocument() {
        Optional<Object> objectOptional = Optional.of(this.getReferenceDocument());
        when(referenceDocumentRepository.findOptionalReferenceDocumentById(1L)).thenReturn(objectOptional);
        buildService.changeStatusOfDocument(1L, StatusInTreatment.Ready);
        verify(referenceDocumentRepository, times(1)).save(any(Referencedocument.class));
    }

    @Test
    void itShouldThrowAnExceptionWhileChangeStatusOfDocumentToEmit() {
        Optional<Object> objectOptional = Optional.empty();
        when(referenceDocumentRepository.findOptionalReferenceDocumentById(1L)).thenReturn(objectOptional);
        Throwable throwable = assertThrows(
                Exception.class,
                ()->buildService.changeStatusOfDocumentToEmit(1L)
        );
        assertThat(throwable.getMessage()).isEqualTo("Unexisting Element");
    }

    @Test
    void itShouldThrowAnExceptionWhileChangeStatusOfDocumentToEmit1() {
        Optional<Object> objectOptional = Optional.of(this.getReferenceDocument());
        when(referenceDocumentRepository.findOptionalReferenceDocumentById(1L)).thenReturn(objectOptional);
        Throwable throwable = assertThrows(
                Exception.class,
                ()->buildService.changeStatusOfDocumentToEmit(1L)
        );
        assertThat(throwable.getMessage()).isEqualTo("Impossible d'effectuer cette operation");
    }

    @Test
    void itShouldChangeStatusOfDocumentToEmit() {
        Cni cni = this.getReferenceDocument();
        cni.setStatusInTreatment(StatusInTreatment.Done);
        cni.setStatusTreatmentSystemeList(StatusTreatmentSystemeList.BUILD);
        Optional<Object> objectOptional = Optional.of(cni);
        when(referenceDocumentRepository.findOptionalReferenceDocumentById(1L)).thenReturn(objectOptional);
        buildService.changeStatusOfDocumentToEmit(1L);
        verify(referenceDocumentRepository, times(1)).save(any(Referencedocument.class));
    }
}