package com.fabrication.agent.controllers;

import com.fabrication.agent.services.ReferentDocumentService;
import com.fabrication.agent.services.ReferentDocumentServiceImpl;
import com.fabrication.entities.Cni;
import com.fabrication.entities.Passport;
import com.fabrication.entities.Referencedocument;
import com.fabrication.utils.StatusInTreatment;
import com.fabrication.utils.StatusTreatmentSystemeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/document")
public class ReferenceDocumentController {
    private ReferentDocumentService referentDocumentService;


    public ReferenceDocumentController(ReferentDocumentService referentDocumentService) {
        this.referentDocumentService = referentDocumentService;
    }

    @GetMapping("/find_by_id/{id}")
    public ResponseEntity<?> findReferenceDocumentById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(referentDocumentService.getReferenceDocumentByClientId(id));
    }

    @GetMapping("/find_current_document_by_id_client/{id}")
    public ResponseEntity<?> findCurrentReferenceDocumentByClientId(
            @PathVariable("id") Long id){
        return ResponseEntity.ok().body(referentDocumentService.getCurrentReferenceDocumentByClientId(id));
    }


    @GetMapping("/find_current_document_by_id/{id}")
    public ResponseEntity<?> findCurrentReferenceDocumentById(
            @PathVariable("id") Long id){
        return ResponseEntity.ok().body(referentDocumentService.getCurrentReferenceDocumentByIdDocument(id));
    }

    @PostMapping(path = "/save_cni")
    public ResponseEntity<?> saveCni(@RequestBody Cni cni){
        Cni cniResponse = referentDocumentService.saveDocCni(cni);
        return new ResponseEntity<>(cniResponse, HttpStatus.CREATED);
    }

    @GetMapping(path = "/init_cni/{id}")
    public ResponseEntity<?> initCni(@PathVariable("id") Long idClient){
        referentDocumentService.initCni(idClient);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/init_passPort/{id}")
    public ResponseEntity<?> initPassPort(@PathVariable("id") Long idClient){
        referentDocumentService.initPassPort(idClient);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/find_All_Documents2")
    public ResponseEntity<?> findAllDocument(){
        return ResponseEntity.ok().body(referentDocumentService.getAllDocuments());
    }

    @GetMapping("/find_All_Documents")
    public ResponseEntity<?> findValidate(){
        return ResponseEntity.ok().body(referentDocumentService.getAllDocuments());
    }

    @PutMapping("/cni/{id}")
    public ResponseEntity<?> updateCni(@RequestBody Cni cni,@PathVariable("id") Long id){
        Cni updatecni = referentDocumentService.updateCni(cni,id);
        return new ResponseEntity<>(updatecni, HttpStatus.CREATED);
    }

    @DeleteMapping("/cni/{id}")
    public String deleteCniById(@PathVariable Long id){
        referentDocumentService.deleteCni(id);
        return "Deleted Successfully";
    }

    @PostMapping(path = "/save_passport")
    public ResponseEntity<?> savePassport(@RequestBody Passport passport){
        Passport passportResponse = referentDocumentService.saveDocPassport(passport);
        return new ResponseEntity<>(passportResponse, HttpStatus.CREATED);
    }

    @PutMapping("/passport/{id}")
    public ResponseEntity<?> updatePassPort(@RequestBody Passport passport, @PathVariable("id") Long id){
        Passport updatepassport = referentDocumentService.updatePassport(passport,id);
        return new ResponseEntity<>(updatepassport, HttpStatus.CREATED);
    }

    @DeleteMapping("/find_passport_By_Id/{id}")
    public String deletePassPortById(@PathVariable Long id){
        referentDocumentService.deletePassport(id);
        return "Deleted Successfully";
    }

    @GetMapping("/referentdocument/{id}")
    public ResponseEntity<?> getReferenceDocById(@PathVariable Long id){
        Referencedocument getreferencedocument= referentDocumentService.findReferencedocumentById(id);
        return new ResponseEntity<>(getreferencedocument, HttpStatus.CREATED);
    }


    @GetMapping("/find_All_document_by_page")
    public ResponseEntity<?> findAllDocumentByPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name="status") StatusTreatmentSystemeList statusTreatmentSystemeList
    ){
        return ResponseEntity.ok().body(
                referentDocumentService
                        .findAllReferenceByStatusTreatmentSystemListAndPage(
                                statusTreatmentSystemeList,
                                page,
                                size
                        )
        );
    }

    @GetMapping("/find_All_document_in_building_step")
    public ResponseEntity<?> findAllDocumentBuildByPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name="status") StatusInTreatment statusInTreatment
    ){
        return ResponseEntity.ok().body(
                referentDocumentService
                        .findAllReferenceInBuildingStepByStatusInTreatmentAndPage(
                                statusInTreatment,
                                page,
                                size
                        )
        );
    }


}
