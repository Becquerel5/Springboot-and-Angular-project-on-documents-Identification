package com.fabrication.agent.controllers;

import com.fabrication.agent.repositories.AdditionalDocumentsRepository;
import com.fabrication.agent.repositories.ReferenceDocumentRepository;
import com.fabrication.client.repositories.PersonRepository;
import com.fabrication.entities.*;
import com.fabrication.agent.services.FileResourceService;
import com.fabrication.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/file")
public class FileController {

    private final FileResourceService fileResourceService;


    @Autowired
    public FileController(FileResourceService fileResourceService) {
        this.fileResourceService = fileResourceService;
    }

    @PostMapping(value = "/upload_BirthCertificate")
    public ResponseEntity<?> uploadBirthCertificate(
            @RequestParam("files")MultipartFile birthCertificate,
            @RequestParam("agentId")Long agentId,
            @RequestParam("referenceDocumentId")Long referenceDocumentId,
            @RequestParam("type")String type
    ) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        getStringMap(
                                fileResourceService
                                        .uploadBirthCertificate(
                                                birthCertificate,
                                                agentId,
                                                referenceDocumentId,
                                                type
                                        )
                        )
        );
    }

    private Map<String, String> getStringMap(String fileResourceService) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("response", fileResourceService);
        return map;
    }


    @PostMapping("/upload_NationalCertificate")
    public ResponseEntity<?> uploadNationalCertificate(
            @RequestParam("files")MultipartFile nationalCertificate,
            @RequestParam("agentId")Long agentId,
            @RequestParam("referenceDocumentId")Long referenceDocumentId,
            @RequestParam("type")String type
    ) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        getStringMap(
                                fileResourceService
                                        .uploadNationalCertificate(
                                                nationalCertificate,
                                                agentId,
                                                referenceDocumentId,
                                                type
                                        )
                        )
                );
    }

    @PostMapping("/upload_LostCertificate")
    public ResponseEntity<?> uploadLostCertificate(
            @RequestParam("files")MultipartFile lostCertificate,
            @RequestParam("agentId")Long agentId,
            @RequestParam("referenceDocumentId")Long referenceDocumentId,
            @RequestParam("type")String type
    ) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        getStringMap(
                                fileResourceService
                                        .uploadLostCertificate(
                                                lostCertificate,
                                                agentId,
                                                referenceDocumentId,
                                                type
                                        )
                        )
                );
    }

    @PostMapping("/upload_Image")
    public ResponseEntity<?> uploadImage(
            @RequestBody String imageBase64,
            @RequestParam("agentId")Long agentId,
            @RequestParam("referenceDocumentId")Long referenceDocumentId,
            @RequestParam("type")String type
    ) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getStringMap(fileResourceService.uploadImage(imageBase64, agentId, referenceDocumentId, type)));
    }

    @GetMapping("/get_image")
    ResponseEntity<?> getImage(@RequestParam("path") String path) throws Exception {
        return ResponseEntity
                .ok()
                .body(getStringMap(fileResourceService.getImageBase64ToString(path)));
    }





}
