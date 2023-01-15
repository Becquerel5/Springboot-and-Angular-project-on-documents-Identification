package com.fabrication.build.services;

import com.fabrication.agent.repositories.ReferenceDocumentRepository;
import com.fabrication.entities.Referencedocument;
import com.fabrication.exceptions.ResourceNotFoundException;
import com.fabrication.utils.StatusInTreatment;
import com.fabrication.utils.StatusTreatmentSystemeList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class BuildServiceImpl implements BuildService{

    private final ReferenceDocumentRepository referenceDocumentRepository;

    public BuildServiceImpl(ReferenceDocumentRepository referenceDocumentRepository) {
        this.referenceDocumentRepository = referenceDocumentRepository;
    }

    @Override
    public Page<Referencedocument> listDocReadyToBuild(int page, int size) {
        try {
            return referenceDocumentRepository.findAll(
                    StatusTreatmentSystemeList.BUILD,
                    StatusInTreatment.Ready,
                    PageRequest.of(page, size)
            );
        }catch (Exception ex){
            throw new ResourceNotFoundException("Error while getting data");
        }
    }

    @Override
    public Object getDocumentInformationById(Long idDocument){
        Optional<Object> objectOptional = referenceDocumentRepository.findOptionalReferenceDocumentById(idDocument);
        if(!objectOptional.isPresent())
            throw new ResourceNotFoundException("Unexisting Element");
        return objectOptional.get();
    }

    @Override
    public Page<Referencedocument> listDocInRealTimeBuilding(int page, int size) {
        try {
            return referenceDocumentRepository.findAll(
                    StatusTreatmentSystemeList.BUILD,
                    StatusInTreatment.Waiting,
                    PageRequest.of(page, size)
            );
        }catch (Exception ex){
            throw new ResourceNotFoundException("Error while getting data");
        }
    }

    @Override
    public void changeStatusOfDocument(Long idDocument, StatusInTreatment statusInTreatment) {
        Optional<Object> objectOptional = referenceDocumentRepository.findOptionalReferenceDocumentById(idDocument);
        if(!objectOptional.isPresent())
            throw new ResourceNotFoundException("Unexisting Element");
        Referencedocument referencedocument = (Referencedocument) objectOptional.get();
        referencedocument.setStatusInTreatment(statusInTreatment);
        referencedocument.setStatusTreatmentSystemeList(StatusTreatmentSystemeList.BUILD);
        referenceDocumentRepository.save(referencedocument);
    }
    @Override
    public void changeStatusOfDocumentToEmit(Long idDocument) {
        Optional<Object> objectOptional = referenceDocumentRepository.findOptionalReferenceDocumentById(idDocument);
        Referencedocument referencedocument = getReferenceDocument(objectOptional);
        referencedocument.setStatusTreatmentSystemeList(StatusTreatmentSystemeList.EMIT);
        referencedocument.setStatusInTreatment(StatusInTreatment.Ready);
        referenceDocumentRepository.save(referencedocument);
    }
    private Referencedocument getReferenceDocument(Optional<Object> objectOptional) {
        if(!objectOptional.isPresent())
            throw new ResourceNotFoundException("Unexisting Element");
        Referencedocument referencedocument = (Referencedocument) objectOptional.get();
        if(referencedocument.getStatusTreatmentSystemeList() != StatusTreatmentSystemeList.BUILD)
            throw new ResourceNotFoundException("Impossible d'effectuer cette operation");
        if(referencedocument.getStatusInTreatment() != StatusInTreatment.Done)
            throw new ResourceNotFoundException("Impossible d'effectuer cette operation");
        return referencedocument;
    }
}
