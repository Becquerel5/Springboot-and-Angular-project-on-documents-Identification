package com.fabrication.agent.repositories;

import com.fabrication.client.repositories.PersonRepository;
import com.fabrication.entities.*;
import com.fabrication.utils.Gender;
import com.fabrication.utils.PersonStatus;
import com.fabrication.utils.StatusInTreatment;
import com.fabrication.utils.StatusTreatmentSystemeList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ReferenceDocumentRepositoryTest {
    @Autowired
    ReferenceDocumentRepository<Referencedocument> referenceDocumentRepository;

    @Autowired
    PersonRepository<Person> personRepository;


    @Test
    void givenIdDocument_itShouldReturnNullReferenceDocument() {
        //Given
        Long id = 985L;

        //When
        Referencedocument referencedocument = referenceDocumentRepository.findReferenceDocumentById(id);

        //Then
        assertThat(referencedocument).isNull();

    }

    @Test
    void givenIdDocument_itShouldReturnReferenceDocument() {
        //Given
        Long id = this.saveReferenceDocumentAndReturnRefDocId();

        //When
        Referencedocument referencedocument = referenceDocumentRepository.findReferenceDocumentById(id);

        //Then
        assertThat(referencedocument).isNotNull();

    }

    @Test
    @DisplayName("given client id it should return null because client don't exist")
    void givenClientId_itShouldReturnNullBecauseClientDoNotExist() {
        //Given
        Long id =25685L;

        //When
        List<Object> object = referenceDocumentRepository.getReferenceDocumentByClientId(id);

        //Then
        assertTrue(object.isEmpty());
    }

    @Test
    @DisplayName("given client id it should return list of reference document")
    void givenClientId_itShouldReturnAListOfReferenceDocument() {
        //Given
        Long id = this.saveReferenceDocumentAndReturnIdClient();

        //When
        List<Object> object = referenceDocumentRepository.getReferenceDocumentByClientId(id);

        //Then
        assertThat(object.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    @DisplayName("given a null client id it should return empty list")
    void givenANullClientId_itShouldReturnEmptyList() {
        //Given
        Long id = null;

        //When
        List<Object> object = referenceDocumentRepository.getReferenceDocumentByClientId(id);

        //Then
        assertThat(object).isEmpty();
    }


    @Test
    void itShouldReturnAListOfReferenceDocument() {
        //Given
        StatusTreatmentSystemeList form = StatusTreatmentSystemeList.FORM;
        StatusTreatmentSystemeList finish = StatusTreatmentSystemeList.FINISH;
        this.saveReferenceDocumentForReseach();

        //When
        List<Object> object = referenceDocumentRepository.findAll(form,finish);

        //Then
        assertThat(object.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void itShouldReturnNullForReferenceDocument() {
        //Given
        StatusTreatmentSystemeList form = StatusTreatmentSystemeList.FORM;
        StatusTreatmentSystemeList finish = StatusTreatmentSystemeList.FINISH;

        //When
        List<Object> object = referenceDocumentRepository.findAll(form,finish);

        //Then
        assertTrue(object.isEmpty());
    }

    @Test
    void itShouldReturnAPageOfReferenceDocument() {
        //Given
        StatusTreatmentSystemeList build = StatusTreatmentSystemeList.BUILD;
        this.saveReferenceDocumentForReseach();

        //When
        Page<Referencedocument> object = referenceDocumentRepository.findAll(
                build,
                PageRequest.of(
                        0,
                        5
                )
        );

        //Then
        assertThat(object.getSize()).isGreaterThanOrEqualTo(1);
    }

    @Test
    @DisplayName("given client id it should return null because client don't exist for current reference document")
    void givenClientId_itShouldReturnNullBecauseClientDoNotExist1() {
        //Given
        Long id =256L;

        //When
        Optional<Object> cni =  referenceDocumentRepository.getCurrentReferenceDocumentByClientId(id,StatusTreatmentSystemeList.FINISH);

        //Then
        assertFalse(cni.isPresent());
    }

    @Test
    @DisplayName("given client id it should return current reference document")
    void givenClientId_itShouldGetCurrentReferenceDocument() {
        //Given
        Long id = this.saveReferenceDocumentAndReturnIdClient();

        //When
        Optional<Object> cni =  referenceDocumentRepository.getCurrentReferenceDocumentByClientId(id,StatusTreatmentSystemeList.FINISH);

        //Then

        assertThat(cni.isPresent()).isTrue();
        // assertThat(cni.getClient()).isEqualTo(personRepository.findClientById(id));
    }



    @Test
    @DisplayName("given client id it should return null because document don't exist")
    void givenClientId_itShouldReturnEmptyOptionalDocumentDoNotExist() {
        //Given
        Long id =256L;

        //When
        Optional<Object> cni =  referenceDocumentRepository.findOptionalReferenceDocumentById(id);

        //Then
        assertFalse(cni.isPresent());
    }

    @Test
    @DisplayName("given client id it should return optional reference document")
    void givenClientId_itShouldGetOptionalReferenceDocument() {
        //Given
        Long id = this.saveReferenceDocumentAndReturnRefDocId();

        //When
        Optional<Object> cni =  referenceDocumentRepository.findOptionalReferenceDocumentById(id);

        //Then
        assertThat(cni.isPresent()).isTrue();
    }

    private Passport getPassportFinish(Long id) {
        return new Passport(
                12L,
                null,
                "123655",
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
                personRepository.findClientById(id),
                null,
                StatusTreatmentSystemeList.FINISH,
                StatusInTreatment.Waiting,
                null,
                "Country",
                null
        );
    }

    private Cni getCniForm(Long id) {
        return new Cni(
                11L,
                null,
                "1236555",
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
                personRepository.findClientById(id),
                null,
                StatusTreatmentSystemeList.FORM,
                StatusInTreatment.Waiting,
                null
        );
    }

    private Passport getPassportBuild(Long id) {
        return new Passport(
                1278L,
                null,
                "123685",
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
                personRepository.findClientById(id),
                null,
                StatusTreatmentSystemeList.BUILD,
                StatusInTreatment.Waiting,
                null,
                "Country",
                null
        );
    }

    private Passport getPassportEmit(Long id) {
        return new Passport(
                5278L,
                null,
                "123685",
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
                personRepository.findClientById(id),
                null,
                StatusTreatmentSystemeList.BUILD,
                StatusInTreatment.Waiting,
                null,
                "Country",
                null
        );
    }

    private Cni getCni(Long id) {
        return new Cni(
                2511L,
                null,
                "124365",
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
                personRepository.findClientById(id),
                null,
                StatusTreatmentSystemeList.VALIDATE,
                StatusInTreatment.Waiting,
                null
        );
    }

    private Long saveReferenceDocumentAndReturnIdClient(){
        personRepository.save(
                new Client(
                        null,
                        "em22222ail@qd.col",
                        PersonStatus.ACTIVE,
                        "012345",
                        Date.from(Instant.now())
                )
        );
        Long id = personRepository.findClientByEmail("em22222ail@qd.col",PersonStatus.ACTIVE).getId();
        referenceDocumentRepository.save(this.getCniForm(id));
        referenceDocumentRepository.save(this.getPassportFinish(id));
        return id;
    }

    private Long saveReferenceDocumentAndReturnRefDocId(){
        personRepository.save(
                new Client(
                        null,
                        "defrgt@qd.col",
                        PersonStatus.ACTIVE,
                        "012345",
                        Date.from(Instant.now())
                )
        );
        Long idClient = personRepository.findClientByEmail("defrgt@qd.col",PersonStatus.ACTIVE).getId();
        Long id = referenceDocumentRepository.save(this.getCniForm(idClient)).getIdDocumentReference();
        return id;
    }

    private void saveReferenceDocumentForReseach(){
        personRepository.save(
                new Client(
                        null,
                        "em222ail@qd.col",
                        PersonStatus.CREATE,
                        "012345",
                        Date.from(Instant.now())
                )
        );
        Long id = personRepository.findClientByEmail("em222ail@qd.col",PersonStatus.CREATE).getId();
        referenceDocumentRepository.save(this.getCni(id));
        referenceDocumentRepository.save(this.getPassportBuild(id));
    }
}