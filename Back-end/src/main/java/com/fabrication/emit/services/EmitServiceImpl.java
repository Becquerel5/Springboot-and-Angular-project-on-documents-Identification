package com.fabrication.emit.services;

import com.fabrication.agent.repositories.ReferenceDocumentRepository;
import com.fabrication.entities.Referencedocument;
import com.fabrication.exceptions.ResourceNotFoundException;
import com.fabrication.services.EmailService;
import com.fabrication.utils.StatusInTreatment;
import com.fabrication.utils.StatusTreatmentSystemeList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class EmitServiceImpl implements EmitService{

    private final ReferenceDocumentRepository referenceDocumentRepository;
    private final EmailService emailService;

    public EmitServiceImpl(ReferenceDocumentRepository referenceDocumentRepository, EmailService emailService) {
        this.referenceDocumentRepository = referenceDocumentRepository;
        this.emailService = emailService;
    }

    @Override
    public Page<Object> listDocumentToEmit(int page, int size) {
        try {
            return referenceDocumentRepository.findAll(
                    StatusTreatmentSystemeList.EMIT,
                    StatusInTreatment.Ready,
                    PageRequest.of(page, size)
            );
        }catch (Exception ex){
            throw new ResourceNotFoundException("Error while getting data");
        }
    }

    @Override
    public Object getReferenceDocument(Long idDocument) {
        Optional<Object> objectOptional = referenceDocumentRepository
                .getCurrentReferenceDocumentByClientId(idDocument, StatusTreatmentSystemeList.FINISH);
        if(!objectOptional.isPresent())
            throw new ResourceNotFoundException("Unexisting Element");
        Referencedocument referencedocument = (Referencedocument) objectOptional.get();
        if(referencedocument.getStatusTreatmentSystemeList() != StatusTreatmentSystemeList.EMIT)
            throw new ResourceNotFoundException("Unexisting Element");
        return objectOptional.get();
    }

    @Override
    public void sendEmailToNotifiedClient(Long idDocument) throws ResourceNotFoundException {
        Optional<Object> objectOptional = referenceDocumentRepository.getCurrentReferenceDocumentByClientId(idDocument, StatusTreatmentSystemeList.FINISH);
        System.err.println(objectOptional.isPresent());
        if(!objectOptional.isPresent())
            throw new ResourceNotFoundException("Unexisting Element");
        Referencedocument referencedocument = (Referencedocument) objectOptional.get();
        if(referencedocument.getStatusTreatmentSystemeList()!= StatusTreatmentSystemeList.EMIT)
            throw new ResourceNotFoundException("Unexisting Element");
        try{
            referencedocument.setDocumentNumber(emailService.generateValidationCode());
            referenceDocumentRepository.save(referencedocument);
            emailService.sendSimpleMessage(
                    referencedocument.getClient().getEmail(),
                    "Emition",
                    referencedocument.getDocumentNumber());
        }catch (Exception e){
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public void verifiedReferenceDocumentToReturnDocumentToClient(String email, String reference) throws ResourceNotFoundException {

    }
}
