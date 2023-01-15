package com.fabrication.client.services;

import com.itextpdf.text.DocumentException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface BuildClientPDFService {
    public void exportForClient(HttpServletResponse response, Long idClient) throws DocumentException, IOException;
    public void exportForAgent(HttpServletResponse response, Long idClient) throws DocumentException, IOException, DocumentException;
}
