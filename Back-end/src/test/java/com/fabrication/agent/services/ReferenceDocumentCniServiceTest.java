package com.fabrication.agent.services;

import com.fabrication.agent.repositories.ReferenceDocumentRepository;
import com.fabrication.client.repositories.PersonRepository;
import com.fabrication.entities.Client;
import com.fabrication.entities.Cni;
import com.fabrication.entities.Passport;
import com.fabrication.entities.Referencedocument;
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
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.*;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
//@ExtendWith(MockitoExtension.class)
public class ReferenceDocumentCniServiceTest {




    private ReferenceDocumentRepository<Cni> cniRepository;
    private ReferenceDocumentRepository referenceDocumentRepository;
    private ReferentDocumentService referentDocumentService;
    private PersonRepository personService;

    private Cni getCni() {
        Cni cni = new Cni(
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
                null,
                new Client(
                        1L,
                        "jlkfsdf@gm.de",
                        PersonStatus.ACTIVE,
                        "123456",
                        Date.from(Instant.now())
                ),
                null,
                StatusTreatmentSystemeList.FORM,
                StatusInTreatment.Waiting,
                null
        );
        return cni;
    }

    private Cni getCni1() {
        Cni cni = new Cni(
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
                null,
                null,
                null,
                null,
                StatusInTreatment.Waiting,
                null
        );
        return cni;
    }


    @BeforeEach
    void setUp() {
        cniRepository = mock(ReferenceDocumentRepository.class);
        referenceDocumentRepository = mock(ReferenceDocumentRepository.class);
        personService = mock(PersonRepository.class);
        referentDocumentService = new ReferentDocumentServiceImpl(cniRepository,null,referenceDocumentRepository, personService) ;
    }


    @Test
    void itShouldSaveACni(){
        Optional<Object> optional = Optional.of(this.getCni());
        when(referenceDocumentRepository
                .getCurrentReferenceDocumentByClientId(
                        this.getCni().getIdDocumentReference(),
                        StatusTreatmentSystemeList.FINISH
                )).thenReturn(optional);
        when(referenceDocumentRepository.save(this.getCni())).thenReturn(this.getCni1());
        referentDocumentService.saveDocCni(this.getCni());
    }

    @Test
    public void itShouldThrowExceptionWhenCniIdDocumentIsNull(){
        Cni cni = getCni();
        cni.setIdDocumentReference(null);
        Throwable exception = assertThrows(
                ResourceNotFoundException.class,
                ()->referentDocumentService.saveDocCni(cni)
        );
        verify(referenceDocumentRepository, times(0))
                .getCurrentReferenceDocumentByClientId(
                        cni.getIdDocumentReference(),
                        StatusTreatmentSystemeList.FINISH
                );
        verify(referenceDocumentRepository, times(0))
                .save(cni);
        assertThat(exception.getMessage()).isEqualTo("Document Number is Null");
    }

    @Test
    public void itShouldThrowExceptionWhenCniClientIsNull(){
        Cni cni = getCni();
        cni.setClient(null);
        Throwable exception = assertThrows(
                ResourceNotFoundException.class,
                ()->referentDocumentService.saveDocCni(cni)
        );
        verify(referenceDocumentRepository, times(0))
                .getCurrentReferenceDocumentByClientId(
                        cni.getIdDocumentReference(),
                        StatusTreatmentSystemeList.FINISH
                );
        verify(referenceDocumentRepository, times(0))
                .save(cni);
        assertThat(exception.getMessage()).isEqualTo("Client is Null");
    }

    @Test
    public void itShouldThrowExceptionWhenFindCniByIdClientReturnOtherType(){

        Optional<Object> optional = Optional.of(new Passport());
        Cni cni = getCni();
        when(referenceDocumentRepository
                .getCurrentReferenceDocumentByClientId(
                        cni.getIdDocumentReference(),
                        StatusTreatmentSystemeList.FINISH
                )).thenReturn(optional);
        Throwable exception = assertThrows(
                ResourceNotFoundException.class,
                ()->referentDocumentService.saveDocCni(cni)
        );
        verify(referenceDocumentRepository, times(1))
                .getCurrentReferenceDocumentByClientId(
                        cni.getIdDocumentReference(),
                        StatusTreatmentSystemeList.FINISH
                );
        verify(referenceDocumentRepository, times(0))
                .save(cni);
        assertThat(exception.getMessage()).isEqualTo("Error data type is not valid");
    }

    @Test
    public void itShouldThrowExceptionWhenFindCniByIdClientReturnNull(){
        Cni cni = getCni();
        Optional<Object> objectOptional = Optional.empty();
        when(referenceDocumentRepository
                .getCurrentReferenceDocumentByClientId(
                        cni.getIdDocumentReference(),
                        StatusTreatmentSystemeList.FINISH
                )).thenReturn(objectOptional);
        Throwable exception = assertThrows(
                ResourceNotFoundException.class,
                ()->referentDocumentService.saveDocCni(cni)
        );
        verify(referenceDocumentRepository, times(1))
                .getCurrentReferenceDocumentByClientId(
                        cni.getIdDocumentReference(),
                        StatusTreatmentSystemeList.FINISH
                );
        verify(referenceDocumentRepository, times(0))
                .save(cni);
        assertThat(exception.getMessage()).isEqualTo("Client don't have any document");
    }

/*    @Test
    void itShouldUpdateACniById() {
        Cni cni = getCni();
        Optional<Cni> ofResult = Optional.of(cni);
        when(cniRepository.save(any(Cni.class))).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(cniRepository.findById(any(Long.class))).thenReturn(ofResult);
        Cni cni1 = getCni1();
        assertThrows(ResourceNotFoundException.class, () -> referentDocumentService.updateCni(cni1, 1L));
        verify(cniRepository).save(any());
        verify(cniRepository).findById(any(Long.class));
    }*/

    @Test
    void itShouldThrowAnExceptionForListOfReferenceDocumentEmpty() {

        //Given
        List<Object> objectList = new ArrayList<>();

        //When
        when(referenceDocumentRepository.getReferenceDocumentByClientId(any(Long.class))).thenReturn(objectList);
        Throwable exception = assertThrows(
                ResourceNotFoundException.class,
                () -> referentDocumentService.getReferenceDocumentByClientId(any(Long.class))
        );

        //Then
        assertThat(exception.getMessage()).isEqualTo("Element not exist");
       
        
    }

    @Test
    void itShouldReturnAListOfReferenceDocumentForClient() {

        //When
        List<Object> objectList = new ArrayList<>();
        objectList.add(this.getCni());
        objectList.add(this.getCni1());
        when(referenceDocumentRepository.getReferenceDocumentByClientId(any(Long.class)))
                .thenReturn(objectList);
        List<Object> objectListInService = referentDocumentService.getReferenceDocumentByClientId(any(Long.class));

        //Then
        assertThat(objectListInService.size()).isEqualTo(objectList.size());
    }

    @Test
    void itShouldThrowAnExceptionForCurrentReferenceDocument() {
        Optional<Object> objectOptional = Optional.empty();
        when(referenceDocumentRepository.getCurrentReferenceDocumentByClientId(1L, StatusTreatmentSystemeList.FINISH)).thenReturn(objectOptional);

       //When
        Throwable exception = assertThrows(
                ResourceNotFoundException.class,
                () -> referentDocumentService.getCurrentReferenceDocumentByClientId(1L)
        );

        //Then
        assertThat(exception.getMessage()).isEqualTo("Element not exist");
    }

    @Test
    void itShouldReturnCurrentReferenceDocument() {

        //Given
        Optional<Object> optional = Optional.of(this.getCni());
        //When
        when(referenceDocumentRepository.getCurrentReferenceDocumentByClientId(1L, StatusTreatmentSystemeList.FINISH))
                .thenReturn(optional);
        Cni cni = (Cni) referentDocumentService.getCurrentReferenceDocumentByClientId(1L);

        //Then
        assertThat(cni).isNotNull();
    }

    @Test
    void itShouldGenerateReferenceFormId(){
        String referenceFormId = referentDocumentService.generateReferenceFormId(1L);
        assertThat(referenceFormId.length()).isEqualTo(7);
        assertTrue(referenceFormId.endsWith("1"));
    }

    @Test
    void itShouldInitCni(){
        Client client = new Client(
                1L,
                "sf@gmail.co",
                PersonStatus.ACTIVE,
                "123456",
                Date.from(Instant.now())
        );
        when(personService.findClientById(1L)).thenReturn(client);
        when(referenceDocumentRepository.save(any())).thenReturn(any());
        referentDocumentService.initCni(1L);
        verify(personService, times(1)).findClientById(1L);
        verify(referenceDocumentRepository, times(1)).save(any());
    }

    @Test
    void itShouldThrowAnExceptionWhenInitCni(){
        when(personService.findClientById(1L)).thenThrow(new ResourceNotFoundException("Client don't exist"));
        Throwable exception = assertThrows(
                ResourceNotFoundException.class,
                ()->referentDocumentService.initCni(1L)
        );
        assertThat(exception.getMessage()).isEqualTo("Client don't exist");
        verify(referenceDocumentRepository, times(0)).save(any());
    }

    @Test
    void itShouldFindAllReferenceByStatusTreatmentSystemListAndPage() {
        //Given
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

        //When
        when(
                referenceDocumentRepository.findAll(StatusTreatmentSystemeList.BUILD,PageRequest.of(0,5))
        )
                .thenReturn(referenceDocumentPageMock);
        Page<Referencedocument>referencedocumentPage = referentDocumentService
                .findAllReferenceByStatusTreatmentSystemListAndPage(StatusTreatmentSystemeList.BUILD,0,5);

        //Then
        assertThat(referencedocumentPage.isEmpty()).isFalse();
        assertThat(referencedocumentPage.getSize()).isEqualTo(5);
        assertThat(referencedocumentPage.getNumberOfElements()).isEqualTo(10);
    }

    @Test
    void itShouldThrowAnExceptionFindAllReferenceByStatusTreatmentSystemListAndPage() {

        //When
        when(
                referenceDocumentRepository.findAll(StatusTreatmentSystemeList.BUILD,PageRequest.of(0,5))
        )
                .thenThrow(MockitoException.class);

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                ()->referentDocumentService
                        .findAllReferenceByStatusTreatmentSystemListAndPage(
                                StatusTreatmentSystemeList.BUILD,
                                0,
                                5
                        )
        ) ;

        //Then
        assertThat(exception.getMessage()).isEqualTo("Error While getting data");
    }

    @Test
    void itShouldFindAllReferenceInBuildingStepByStatusInTreatment() {
        //Given
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

        //When
        when(
                referenceDocumentRepository.findAll(
                        StatusTreatmentSystemeList.BUILD,
                        StatusInTreatment.Ready,
                        PageRequest.of(0,5))
        )
                .thenReturn(referenceDocumentPageMock);
        Page<Referencedocument>referencedocumentPage = referentDocumentService
                .findAllReferenceInBuildingStepByStatusInTreatmentAndPage(
                        StatusInTreatment.Ready,
                        0,
                        5);

        //Then
        assertThat(referencedocumentPage.isEmpty()).isFalse();
        assertThat(referencedocumentPage.getSize()).isEqualTo(5);
        assertThat(referencedocumentPage.getNumberOfElements()).isEqualTo(10);
    }

}
