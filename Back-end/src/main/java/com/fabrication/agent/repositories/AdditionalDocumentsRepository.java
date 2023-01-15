package com.fabrication.agent.repositories;

import com.fabrication.entities.Additionaldocument;
import com.fabrication.entities.Referencedocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdditionalDocumentsRepository <T extends Additionaldocument>  extends JpaRepository<T, Long> {
    @Query("from Additionaldocument ad where ad.client.id = ?1")
    List<Object> getAdditionaldocumentByClientId(Long id);

    @Query("from Additionaldocument ad where ad.agent.id = ?1")
    List<Object> getAdditionaldocumentByAgentId(Long id);

    @Query("from Additionaldocument ad where ad.referenceDocument.client.id = ?1")
    List<Object> getReferencedocumentClientById(Long id);

    @Query("from Additionaldocument ad where ad.referenceDocument.agent.id = ?1")
    List<Object> getReferencedocumentAgentById(Long id);

}
