package com.fabrication.emit.services;

import com.fabrication.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;

public interface EmitService {
    Page<Object> listDocumentToEmit(int page, int size);
    Object getReferenceDocument(Long idDocument);
    void sendEmailToNotifiedClient(Long IdDocument) throws ResourceNotFoundException;
    void verifiedReferenceDocumentToReturnDocumentToClient(String email, String reference)throws ResourceNotFoundException;
}
