package com.fabrication.agent.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fabrication.agent.repositories.AdditionalDocumentsRepository;
import com.fabrication.agent.repositories.FileRepository;
import com.fabrication.agent.repositories.ReferenceDocumentRepository;
import com.fabrication.agent.services.FileResourceService;
import com.fabrication.agent.services.FileResourceServiceImpl;
import com.fabrication.client.repositories.PersonRepository;
import com.fabrication.client.services.PersonServiceImpl;
import com.fabrication.services.EmailServiceImpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

class FileControllerTest {

    private FileController fileController;
    private FileResourceService fileResourceService;

    @BeforeEach
    void setUp(){
        fileResourceService = mock(FileResourceServiceImpl.class);
        fileController = new FileController(fileResourceService);
    }


    @Test
    void itShouldUploadABirthCertificate() throws Exception {
        when(fileResourceService.uploadBirthCertificate((MultipartFile) any(), (Long) any(), (Long) any(), (String) any()))
                .thenReturn("Birth Certificate");
        ResponseEntity<?> actualBirthCertificateResult = fileController.uploadBirthCertificate(
                new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8"))), 123L , 123L,"String");
        Map<String, String> data = new HashMap<>();
        data.put("response", "Birth Certificate");
        assertEquals(data, actualBirthCertificateResult.getBody());
        assertEquals(HttpStatus.OK, actualBirthCertificateResult.getStatusCode());
        assertTrue(actualBirthCertificateResult.getHeaders().isEmpty());
        verify(fileResourceService).uploadBirthCertificate((MultipartFile) any(), (Long) any(), (Long) any(), (String) any());
    }



    @Test
    void itShouldUploadANationalCertificate() throws Exception {
        when(fileResourceService.uploadNationalCertificate((MultipartFile) any(), (Long) any(), (Long) any(), (String) any()))
                .thenReturn("Upload national certificate Successful");
        ResponseEntity<?> actualNationalCertificateResult = fileController.uploadNationalCertificate(
                new MockMultipartFile(
                        "Name",
                        new ByteArrayInputStream(
                                "AAAAAAAA".getBytes("UTF-8")
                        )
                ),
                123L,
                123L,
                "(String) any()"
        );
        Map<String, String> data = new HashMap<>();
        data.put("response", "Upload national certificate Successful");
        assertEquals(data, actualNationalCertificateResult.getBody());
        assertEquals(HttpStatus.OK, actualNationalCertificateResult.getStatusCode());
        assertTrue(actualNationalCertificateResult.getHeaders().isEmpty());
        verify(fileResourceService).uploadNationalCertificate((MultipartFile) any(), (Long) any(),
                (Long) any(), (String) any());
    }



    @Test
    void itShouldUploadALostCertificate() throws Exception {
        when(fileResourceService.uploadLostCertificate((MultipartFile) any(), (Long) any(), (Long) any(), (String) any()))
                .thenReturn("Upload lostcertificate Successful");
        ResponseEntity<?> actualLostCertificateResult = fileController.uploadLostCertificate(
                new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8"))), 123L, 123L, "(String) any()");
        Map<String, String> data = new HashMap<>();
        data.put("response", "Upload lostcertificate Successful");
        assertEquals(data, actualLostCertificateResult.getBody());
        assertEquals(HttpStatus.OK, actualLostCertificateResult.getStatusCode());
        assertTrue(actualLostCertificateResult.getHeaders().isEmpty());
        verify(fileResourceService).uploadLostCertificate((MultipartFile) any(), (Long) any(), (Long) any(), (String) any());
    }



    @Test
    void itShouldUploadAnImage() throws Exception {
        when(fileResourceService.uploadImage((String) any(), (Long) any(), (Long) any(), (String) any()))
                .thenReturn("Upload Image Successful");
        ResponseEntity<?> actualImageResult = fileController.uploadImage(
                "AAAAAAAA",
                    123L,
                12L,
                "(String) any()");
        Map<String, String> data = new HashMap<>();
        data.put("response", "Upload Image Successful");
        assertEquals(data, actualImageResult.getBody());
        assertEquals(HttpStatus.OK, actualImageResult.getStatusCode());
        assertTrue(actualImageResult.getHeaders().isEmpty());
        verify(fileResourceService).uploadImage((String) any(), any(), (Long) any(), (String) any());
    }
}

