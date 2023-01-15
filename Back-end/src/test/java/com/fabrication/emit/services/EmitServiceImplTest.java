package com.fabrication.emit.services;

import com.fabrication.agent.repositories.ReferenceDocumentRepository;
import com.fabrication.entities.*;
import com.fabrication.services.EmailService;
import com.fabrication.services.EmailServiceImpl;
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
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class EmitServiceImplTest {

    private ReferenceDocumentRepository referenceDocumentRepository;
    private EmailService emailService;
    private EmitService emitService;

    @BeforeEach
    void setUp(){
        referenceDocumentRepository = mock(ReferenceDocumentRepository.class);
        emailService = mock(EmailServiceImpl.class);
        emitService = new EmitServiceImpl(referenceDocumentRepository,emailService);
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
                StatusTreatmentSystemeList.EMIT,
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
                StatusTreatmentSystemeList.EMIT,
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
                StatusTreatmentSystemeList.EMIT,
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
                StatusTreatmentSystemeList.EMIT,
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
                StatusTreatmentSystemeList.EMIT,
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
                StatusTreatmentSystemeList.EMIT,
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
                StatusTreatmentSystemeList.EMIT,
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
                StatusTreatmentSystemeList.EMIT,
                StatusInTreatment.Ready,
                null
        );
    }

    @Test
    void itShouldReturnAPageListOfDocumentToEmit() {
        when(referenceDocumentRepository
                .findAll(
                        StatusTreatmentSystemeList.EMIT,
                        StatusInTreatment.Ready,
                        PageRequest.of(0,2)
                )
        ).thenReturn(this.listDocReadyToBuildData());
        Page<Object> data = emitService.listDocumentToEmit(0,2);
        verify(referenceDocumentRepository, times(1)).findAll(
                StatusTreatmentSystemeList.EMIT,
                StatusInTreatment.Ready,
                PageRequest.of(0,2)
        );
        assertThat(data.isEmpty()).isFalse();
        assertThat(data.getContent()).isNotEmpty();
    }

    @Test
    void itShouldReturnEmptyPageOfDocumentToEmit(){
        when(referenceDocumentRepository
                .findAll(
                        StatusTreatmentSystemeList.EMIT,
                        StatusInTreatment.Ready,
                        PageRequest.of(0,2)
                )
        ).thenReturn(new PageImpl(new ArrayList<Referencedocument>()));
        Page<Object> data = emitService.listDocumentToEmit(0,2);
        assertThat(data.isEmpty()).isTrue();
        assertThat(data.getContent()).isEmpty();
    }

    @Test
    void itShouldThrowAnExceptionWhenGetAListOfDocReadyToEmit() {
        when(referenceDocumentRepository
                .findAll(
                        StatusTreatmentSystemeList.BUILD,
                        StatusInTreatment.Ready,
                        PageRequest.of(1,1)
                )
        ).thenThrow(new MockitoException("Error while getting data"));
        Throwable throwable = assertThrows(
                Exception.class,
                ()->emitService.listDocumentToEmit(10,0)
        );
    }

    @Test
    void itShouldThrowAnExceptionWhenGetDocumentInformationById() {
        Optional<Object> objectOptional = Optional.empty();
        when(referenceDocumentRepository
                .getCurrentReferenceDocumentByClientId(1L, StatusTreatmentSystemeList.FINISH)
        ).thenReturn(objectOptional);
        Throwable throwable = assertThrows(
                Exception.class,
                ()->emitService.getReferenceDocument(1L)
        );
        assertThat(throwable.getMessage()).isEqualTo("Unexisting Element");
    }

    @Test
    void itShouldGetDocumentInformationById() {
        Optional<Object> objectOptional = Optional.of(this.getReferenceDocument());
        when(referenceDocumentRepository
                .getCurrentReferenceDocumentByClientId(1L, StatusTreatmentSystemeList.FINISH)
        )
                .thenReturn(objectOptional);
        Cni cni = (Cni) emitService.getReferenceDocument(1L);
        assertThat(cni.getIdDocumentReference()).isEqualTo(this.getReferenceDocument().getIdDocumentReference());
    }

    @Test
    void itShouldThrowAnExceptionWhenGetDocumentInformationByIdStatusIsNotEmit() {
        Cni cni = this.getReferenceDocument();
        cni.setStatusTreatmentSystemeList(StatusTreatmentSystemeList.VALIDATE);
        Optional<Object> objectOptional = Optional.of(cni);
        when(referenceDocumentRepository
                .getCurrentReferenceDocumentByClientId(1L, StatusTreatmentSystemeList.FINISH)
        ).thenReturn(objectOptional);
        Throwable throwable = assertThrows(
                Exception.class,
                ()->emitService.getReferenceDocument(1L)
        );
        assertThat(throwable.getMessage()).isEqualTo("Unexisting Element");
    }

    @Test
    void itShouldSendNotificationEmailToClient() {
        Optional<Object> objectOptional = Optional.of(this.getReferenceDocument());
        String code = "123654";
        when(referenceDocumentRepository
                .getCurrentReferenceDocumentByClientId(1L, StatusTreatmentSystemeList.FINISH)
        ).thenReturn(objectOptional);
        when(emailService.generateValidationCode()).thenReturn(code);
        emitService.sendEmailToNotifiedClient(1L);
        verify(emailService, times(1)).generateValidationCode();
        verify(referenceDocumentRepository, times(1)).save(any(Referencedocument.class));
    }

    @Test
    void itShouldThrowAnExceptionWhenSendNotificationEmailToClient() {
        Cni cni = this.getReferenceDocument();
        cni.setStatusTreatmentSystemeList(StatusTreatmentSystemeList.VALIDATE);
        Optional<Object> objectOptional = Optional.of(cni);
        when(referenceDocumentRepository
                .getCurrentReferenceDocumentByClientId(1L, StatusTreatmentSystemeList.FINISH)
        )
                .thenReturn(objectOptional);
        Throwable throwable = assertThrows(
                Exception.class,
                ()->emitService.sendEmailToNotifiedClient(1L)
        );
        assertThat(throwable.getMessage()).isEqualTo("Unexisting Element");
    }

    @Test
    void itShouldThrowAnExceptionNullPointerWhenSendNotificationEmailToClient() {
        Optional<Object> objectOptional = Optional.empty();
        when(referenceDocumentRepository
                .getCurrentReferenceDocumentByClientId(1L, StatusTreatmentSystemeList.FINISH)
        ).thenReturn(objectOptional);
        Throwable throwable = assertThrows(
                Exception.class,
                ()->emitService.sendEmailToNotifiedClient(1L)
        );
        assertThat(throwable.getMessage()).isEqualTo("Unexisting Element");
    }

    @Test
    void verifiedReferenceDocumentToReturnDocumentToClient() {
    }
}