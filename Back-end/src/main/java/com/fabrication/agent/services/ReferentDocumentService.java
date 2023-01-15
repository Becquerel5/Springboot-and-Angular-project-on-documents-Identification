package com.fabrication.agent.services;

import com.fabrication.entities.Cni;
import com.fabrication.entities.Passport;
import com.fabrication.entities.Referencedocument;
import com.fabrication.exceptions.ResourceNotFoundException;
import com.fabrication.utils.StatusInTreatment;
import com.fabrication.utils.StatusTreatmentSystemeList;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReferentDocumentService {

    List<Object> getReferenceDocumentByClientId(Long id) throws ResourceNotFoundException;

    Object getCurrentReferenceDocumentByClientId(Long id) throws ResourceNotFoundException;

    Object getCurrentReferenceDocumentByIdDocument(Long id) throws ResourceNotFoundException;

    Cni saveDocCni(Cni cni) throws ResourceNotFoundException;

    Cni updateCni(Cni cni,Long idDocumentReference);

    void deleteCni(Long idDocumentReference);

    Object getAllDocuments();

    Page<Referencedocument> findAllReferenceByStatusTreatmentSystemListAndPage(
            StatusTreatmentSystemeList statusTreatmentSystemeList,
            int page,
            int size
    );

    Page<Referencedocument> findAllReferenceInBuildingStepByStatusInTreatmentAndPage(
            StatusInTreatment statusInTreatment,
            int page,
            int size
    );

    Object getAllDocumentsById(Long idDocumentReference);


    Referencedocument findReferencedocumentById(Long idDocumentReference);

    //Passport
    Passport saveDocPassport(Passport passport) throws ResourceNotFoundException;

    void deletePassport(Long idDocumentReference);

    Passport updatePassport(Passport passport,Long idDocumentReference);

    String generateReferenceFormId(Long clientId);

    void initCni(Long idClient) throws ResourceNotFoundException;

    void initPassPort(Long idClient) throws ResourceNotFoundException;

}
