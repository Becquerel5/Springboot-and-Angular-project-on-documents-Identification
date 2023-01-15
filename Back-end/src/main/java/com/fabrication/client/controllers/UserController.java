package com.fabrication.client.controllers;

import com.fabrication.client.services.BuildClientPDFService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {
    private final BuildClientPDFService buildClientPDFService;

    @Autowired
    public UserController(BuildClientPDFService buildClientPDFService) {
        this.buildClientPDFService = buildClientPDFService;
    }

    @GetMapping("/users/export/pdf/{id}")
    public void exportToPDF(HttpServletResponse response, @PathVariable("id") Long id) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        buildClientPDFService.exportForClient(response,id);

    }

    @GetMapping("/agent/export/pdf/{id}")
    public void exportPDFToAgent(HttpServletResponse response, @PathVariable("id") Long id) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        buildClientPDFService.exportForAgent(response,id);

    }
}