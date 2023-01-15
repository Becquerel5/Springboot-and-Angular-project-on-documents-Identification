package com.fabrication.build.services;

import com.fabrication.entities.Referencedocument;
import com.fabrication.utils.StatusInTreatment;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BuildService {
    Page<Referencedocument> listDocReadyToBuild(int page, int size);
    Object getDocumentInformationById(Long idDocument);
    Page<Referencedocument> listDocInRealTimeBuilding(int page, int size);
    void changeStatusOfDocument(Long idDocument, StatusInTreatment statusInTreatment);
    void changeStatusOfDocumentToEmit(Long idDocument);
}
