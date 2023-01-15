package com.fabrication.agent.services;


import com.fabrication.agent.repositories.ReferenceDocumentRepository;
import com.fabrication.client.repositories.PersonRepository;
import com.fabrication.entities.Client;
import com.fabrication.entities.Cni;
import com.fabrication.entities.Passport;
import com.fabrication.exceptions.ResourceNotFoundException;
import com.fabrication.utils.Gender;
import com.fabrication.utils.PersonStatus;
import com.fabrication.utils.StatusInTreatment;
import com.fabrication.utils.StatusTreatmentSystemeList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
//@ExtendWith(MockitoExtension.class)
public class ReferenceDocumentPassportServiceTest {

    private ReferenceDocumentRepository<Passport> passportRepository;
    private ReferentDocumentService referentDocumentService;

    private ReferenceDocumentRepository referenceDocumentRepository;
    private PersonRepository personService;


    private Passport getPassport() {
        return new Passport(
                2L,
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
                null,
                new Client(
                        2L,
                        "kljsdklf@qsd.qsd",
                        PersonStatus.ACTIVE,
                        "123456",
                        Date.from(Instant.now())
                ),
                null,
                StatusTreatmentSystemeList.FORM,
                StatusInTreatment.Waiting,
                null,
                "Country",
                null
        );
    }

    private Passport getPassport1() {
        return new Passport(
                2L,
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
                null,
                new Client(
                        2L,
                        "kljsdklf@qsd.qsd",
                        PersonStatus.ACTIVE,
                        "123456",
                        Date.from(Instant.now())
                ),
                null,
                StatusTreatmentSystemeList.FORM,
                StatusInTreatment.Waiting,
                null,
                "Country",
                null
        );
    }



    @BeforeEach
    void setUp() {
        passportRepository = mock(ReferenceDocumentRepository.class);
        personService = mock(PersonRepository.class);
        referenceDocumentRepository = mock(ReferenceDocumentRepository.class);
        referentDocumentService = new ReferentDocumentServiceImpl(null,passportRepository,referenceDocumentRepository, personService) ;
    }

    @Test
    void itShouldSaveAPassPort(){
        when(referenceDocumentRepository
                .getCurrentReferenceDocumentByClientId(
                        this.getPassport().getIdDocumentReference(),
                        StatusTreatmentSystemeList.FINISH
                )).thenReturn(Optional.of(this.getPassport()));
        when(referenceDocumentRepository.save(this.getPassport())).thenReturn(this.getPassport1());
        referentDocumentService.saveDocPassport(this.getPassport());
       // verify(referenceDocumentRepository, times(1)).save(this.getPassport());
    }

    @Test
    public void itShouldThrowExceptionWhenPassPortIdDocumentIsNull(){
        Passport passport = getPassport();
        passport.setIdDocumentReference(null);
        Throwable exception = assertThrows(
                ResourceNotFoundException.class,
                ()->referentDocumentService.saveDocPassport(passport)
        );
        verify(referenceDocumentRepository, times(0))
                .getCurrentReferenceDocumentByClientId(
                        passport.getIdDocumentReference(),
                        StatusTreatmentSystemeList.FINISH
                );
        verify(referenceDocumentRepository, times(0))
                .save(passport);
        Assertions.assertThat(exception.getMessage()).isEqualTo("Document Number is Null");
    }

    @Test
    public void itShouldThrowExceptionWhenCniClientIsNull(){
        Passport passport = getPassport();
        passport.setClient(null);
        Throwable exception = assertThrows(
                ResourceNotFoundException.class,
                ()->referentDocumentService.saveDocPassport(passport)
        );
        verify(referenceDocumentRepository, times(0))
                .getCurrentReferenceDocumentByClientId(
                        passport.getIdDocumentReference(),
                        StatusTreatmentSystemeList.FINISH
                );
        verify(referenceDocumentRepository, times(0))
                .save(passport);
        Assertions.assertThat(exception.getMessage()).isEqualTo("Client is Null");
    }

    @Test
    public void itShouldThrowExceptionWhenFindPassPortByIdClientReturnOtherType(){
        Passport passport = getPassport();
        when(referenceDocumentRepository
                .getCurrentReferenceDocumentByClientId(
                        passport.getIdDocumentReference(),
                        StatusTreatmentSystemeList.FINISH
                )).thenReturn(Optional.of(new Cni()));
        Throwable exception = assertThrows(
                ResourceNotFoundException.class,
                ()->referentDocumentService.saveDocPassport(passport)
        );
        verify(referenceDocumentRepository, times(1))
                .getCurrentReferenceDocumentByClientId(
                        passport.getIdDocumentReference(),
                        StatusTreatmentSystemeList.FINISH
                );
        verify(referenceDocumentRepository, times(0))
                .save(passport);
        Assertions.assertThat(exception.getMessage()).isEqualTo("Error data type is not valid");
    }

    @Test
    public void itShouldThrowExceptionWhenFindPassPortByIdClientReturnNull(){
        Passport passport = this.getPassport();
        Optional<Object> objectOptional = null;
        when(referenceDocumentRepository
                .getCurrentReferenceDocumentByClientId(
                        1L,
                        StatusTreatmentSystemeList.FINISH
                )).thenReturn(objectOptional);
        Throwable exception = assertThrows(
                ResourceNotFoundException.class,
                ()->referentDocumentService.saveDocPassport(passport)
        );
        verify(referenceDocumentRepository, times(1))
                .getCurrentReferenceDocumentByClientId(
                        passport.getIdDocumentReference(),
                        StatusTreatmentSystemeList.FINISH
                );
        verify(referenceDocumentRepository, times(0))
                .save(passport);
        Assertions.assertThat(exception.getMessage()).isEqualTo("Client don't have any document");
    }

    @Test
    void itShouldUpdateAPassportById() {
        Passport passport = getPassport();

        Optional<Passport> ofResult = Optional.of(passport);
        when(passportRepository.save(any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(passportRepository.findById(any())).thenReturn(ofResult);

        Passport passport1 = getPassport1();
        assertThrows(ResourceNotFoundException.class, () -> referentDocumentService.updatePassport(passport1, 1L));
        verify(passportRepository).save(any());
        verify(passportRepository).findById(any());
    }

    @Test
    void itShouldInitPassPort(){
        Client client = new Client(
                1L,
                "sf@gmail.co",
                PersonStatus.ACTIVE,
                "123456",
                Date.from(Instant.now())
        );
        when(personService.findClientById(1L)).thenReturn(client);
        when(referenceDocumentRepository.save(any())).thenReturn(any());
        referentDocumentService.initPassPort(1L);
        verify(personService, times(1)).findClientById(1L);
        verify(referenceDocumentRepository, times(1)).save(any());
    }

    @Test
    void itShouldThrowAnExceptionWhenInitPassPort(){
        when(personService.findClientById(1L)).thenThrow(new ResourceNotFoundException("Client don't exist"));
        Throwable exception = assertThrows(
                ResourceNotFoundException.class,
                ()->referentDocumentService.initPassPort(1L)
        );
        Assertions.assertThat(exception.getMessage()).isEqualTo("Client don't exist");
        verify(referenceDocumentRepository, times(0)).save(any());
    }









}
