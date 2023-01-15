package com.fabrication.client.services;

import com.fabrication.agent.repositories.ReferenceDocumentRepository;
import com.fabrication.entities.Client;
import com.fabrication.entities.Cni;
import com.fabrication.entities.Passport;
import com.fabrication.exceptions.ResourceNotFoundException;
import com.fabrication.utils.Gender;
import com.fabrication.utils.PersonStatus;
import com.fabrication.utils.StatusInTreatment;
import com.fabrication.utils.StatusTreatmentSystemeList;
import com.itextpdf.text.DocumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuildClientPDFTest {
    private BuildClientPDFService buildClientPDFService;
    private PersonService personService;

    private ReferenceDocumentRepository referencedocumentRepository;

    private Cni getCni() {
        Cni cni = new Cni();
        cni.setIdDocumentReference(12L);
        cni.setFirstName("updaterepoDTFB");
        cni.setAddress("updaterepoAdd");
        cni.setLastName("updaterepoLN");
        cni.setDateOfBirth(Date.from(Instant.now()));
        cni.setDeliveryDate(Date.from(Instant.now()));
        cni.setGender(Gender.MALE);
        cni.setExpirationDate(Date.from(Instant.now()));
        cni.setDocumentNumber(null);
        cni.setNameOfFather("updaterepoFa");
        cni.setNameOfMother("updaterepoMo");
        return cni;
    }

    private Passport getPassport(){
        Passport passport= new Passport(
                2L,
                null,
                null,
                "passport1Firstname9",
                "passportLastName9",
                Date.from(Instant.now()),
                Gender.MALE,
                "INGENIEUR",
                "passportFATHER6",
                "passportMother6",
                null,
                null,
                "Mendong",
                null,
                null,
                null,
                null,
                null,
                null
        );
        return passport;
    }

    @BeforeEach
    void setUp(){
        personService = mock(PersonServiceImpl.class);
        referencedocumentRepository = mock(ReferenceDocumentRepository.class);
        buildClientPDFService = new BuildClientPDFServiceImpl(personService, referencedocumentRepository);
    }


}
