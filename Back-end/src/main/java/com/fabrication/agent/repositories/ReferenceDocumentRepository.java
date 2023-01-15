package com.fabrication.agent.repositories;

import com.fabrication.entities.Referencedocument;
import com.fabrication.utils.StatusInTreatment;
import com.fabrication.utils.StatusTreatmentSystemeList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReferenceDocumentRepository <T extends Referencedocument>  extends JpaRepository<T, Long> {

    @Query("from Referencedocument r where r.idDocumentReference = ?1")
    Referencedocument findReferenceDocumentById(Long id);

    @Query("from Referencedocument r where r.idDocumentReference = ?1")
    Optional<Object> findOptionalReferenceDocumentById(Long id);

    @Query("from Referencedocument rd where rd.client.id = ?1")
    List<Object> getReferenceDocumentByClientId(Long id);

    @Query("from Referencedocument r where r.statusTreatmentSystemeList <> ?1 and r.statusTreatmentSystemeList <> ?2")
    List<Object> findAll(StatusTreatmentSystemeList form, StatusTreatmentSystemeList finish);

    @Query("from Referencedocument r where r.statusTreatmentSystemeList = ?1")
    Page<Referencedocument> findAll(StatusTreatmentSystemeList form, Pageable pageable);

    @Query("from Referencedocument r where r.statusTreatmentSystemeList = ?1 and r.statusInTreatment = ?2")
    Page<Referencedocument> findAll(StatusTreatmentSystemeList form, StatusInTreatment statusInTreatment, Pageable pageable);


    @Query("from Referencedocument r where r.client.id = ?1 and r.statusTreatmentSystemeList <> ?2")
    Optional<Object> getCurrentReferenceDocumentByClientId(Long id, StatusTreatmentSystemeList statusTreatmentSystemeList);


    List<Referencedocument> findByStatusTreatmentSystemeList(StatusTreatmentSystemeList statusTreatmentSystemeList);
    
    @Query("from Referencedocument r where r.idDocumentReference = ?1 and r.statusTreatmentSystemeList <> ?2")
    Optional<Object> getCurrentReferenceDocumentByIdDocument(Long id, StatusTreatmentSystemeList statusTreatmentSystemeList);


}
