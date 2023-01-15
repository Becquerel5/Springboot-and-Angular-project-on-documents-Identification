package com.fabrication.build.controller;

import com.fabrication.build.services.BuildService;
import com.fabrication.utils.StatusInTreatment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/build")
public class BuildController {
    private final BuildService buildService;

    public BuildController(BuildService buildService) {
        this.buildService = buildService;
    }

    @GetMapping("/")
    public ResponseEntity<?> findAllDocumentReadyToPrint(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size){
        return ResponseEntity.ok().body(buildService.listDocReadyToBuild(page,size));
    }

    @PutMapping("/update_document")
    public ResponseEntity<?> updateDocumentStatus(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "status") @Valid StatusInTreatment statusInTreatment){
        buildService.changeStatusOfDocument(id, statusInTreatment);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/real_time")
    public ResponseEntity<?> findAllDocumentRealTimePrinting(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size){
        return ResponseEntity.ok().body(buildService.listDocInRealTimeBuilding(page,size));
    }

    @GetMapping("/find_doc_by_id/{idClient}")
    public ResponseEntity<?> findDocumentById(
            @PathVariable("idClient") Long id){
        return ResponseEntity.ok().body(buildService.getDocumentInformationById(id));
    }
}
