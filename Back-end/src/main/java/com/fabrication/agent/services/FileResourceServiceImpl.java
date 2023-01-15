package com.fabrication.agent.services;

import com.fabrication.agent.repositories.AdditionalDocumentsRepository;
import com.fabrication.client.services.PersonService;
import com.fabrication.entities.*;
import com.fabrication.exceptions.ResourceNotFoundException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Date;
import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;

@Service
@Transactional
public class FileResourceServiceImpl implements FileResourceService {
    public static final String uploadDirectory =System.getProperty("user.dir")+"/fileUploads/";
    private final PersonService personService;

    private final ReferentDocumentService referentDocumentService;


    private final AdditionalDocumentsRepository additionalDocumentsRepository;




    @Autowired
    public FileResourceServiceImpl(PersonService personService, ReferentDocumentService referentDocumentService, AdditionalDocumentsRepository additionalDocumentsRepository) {
        this.personService = personService;
        this.referentDocumentService = referentDocumentService;
        this.additionalDocumentsRepository = additionalDocumentsRepository;
    }

    @Override
    public String uploadBirthCertificate(MultipartFile file1, Long agentId, Long referenceDocumentId, String type) throws Exception {
        if (file1.isEmpty()){
            throw new ResourceNotFoundException("File invalid");
        }
        Referencedocument referencedocument = referentDocumentService.findReferencedocumentById(referenceDocumentId);
        Agent agent =  personService.getAgentById(agentId);
        String fileName = StringUtils.cleanPath(file1.getOriginalFilename());
        String pathString = this.createFolderUser(referencedocument,"birthCertificate",type).trim();
        Path fileStorage = get(pathString, fileName).toAbsolutePath().normalize();
        copy(file1.getInputStream(), fileStorage, REPLACE_EXISTING);
        String separator = "/fileUploads/";
        String[] mots = pathString.split(separator);
        Birthcertificate birthcertificate = new Birthcertificate();
        birthcertificate.setDateAdded(Date.from(Instant.now()));
        birthcertificate.setClient(referencedocument.getClient());
        birthcertificate.setAgent(agent);
        birthcertificate.setReferenceDocument(referencedocument);
        birthcertificate.setPathDocumentImage("fileUploads/"+mots[1]+'/'+fileName);
        birthcertificate = (Birthcertificate)additionalDocumentsRepository.save(birthcertificate);
        Cni cni = (Cni) referentDocumentService.getCurrentReferenceDocumentByIdDocument(referenceDocumentId);
        cni.setBirthcertificate(birthcertificate);
        referentDocumentService.saveDocCni(cni);
        return "Upload birthcertificate Successful";
    }

    @Override
    public String uploadNationalCertificate(MultipartFile file, Long agentId,Long referenceDocumentId, String type) throws Exception {
        if (file.isEmpty()){
            throw new ResourceNotFoundException("File invalid");
        }
        Referencedocument referencedocument = referentDocumentService.findReferencedocumentById(referenceDocumentId);
        Agent agent =  personService.getAgentById(agentId);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String pathString = this.createFolderUser(referencedocument,"nationalCertificate",type).trim();
        Path fileStorage = get(pathString, fileName).toAbsolutePath().normalize();
        copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
        String separator = "/fileUploads/";
        String[] mots = pathString.split(separator);
        Nationalitycertificate nationalitycertificate = new Nationalitycertificate();
        nationalitycertificate.setDateAdded(Date.from(Instant.now()));
        nationalitycertificate.setClient(referencedocument.getClient());
        nationalitycertificate.setAgent(agent);
        nationalitycertificate.setReferenceDocument(referencedocument);
        nationalitycertificate.setPathDocumentImage("fileUploads/"+mots[1]+'/'+fileName);
        nationalitycertificate = (Nationalitycertificate)additionalDocumentsRepository.save(nationalitycertificate);
        Cni cni = (Cni) referentDocumentService.getCurrentReferenceDocumentByIdDocument(referenceDocumentId);
        cni.setNationalitycertificate(nationalitycertificate);
        referentDocumentService.saveDocCni(cni);
        return "Upload national certificate Successful";
    }

    @Override
    public String uploadLostCertificate(MultipartFile file, Long agentId,Long referenceDocumentId, String type) throws Exception {
        if (file.isEmpty()){
            throw new ResourceNotFoundException("File invalid");
        }
        Referencedocument referencedocument = referentDocumentService.findReferencedocumentById(referenceDocumentId);
        Agent agent =  personService.getAgentById(agentId);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String pathString = this.createFolderUser(referencedocument,"lostCertificate",type).trim();
        Path fileStorage = get(pathString, fileName).toAbsolutePath().normalize();
        copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
        String separator = "/fileUploads/";
        String[] mots = pathString.split(separator);
        Lostcertificate lostcertificate = new Lostcertificate();
        lostcertificate.setDateAdded(Date.from(Instant.now()));
        lostcertificate.setClient(referencedocument.getClient());
        lostcertificate.setAgent(agent);
        lostcertificate.setReferenceDocument(referencedocument);
        lostcertificate.setPathDocumentImage("fileUploads/"+mots[1]+'/'+fileName);
        lostcertificate = (Lostcertificate)additionalDocumentsRepository.save(lostcertificate);
        Cni cni = (Cni) referentDocumentService.getCurrentReferenceDocumentByIdDocument(referenceDocumentId);
        cni.setLostcertificate(lostcertificate);
        referentDocumentService.saveDocCni(cni);
        return "Upload lostcertificate Successful";
    }

    @Override
    public String createFolderUser(Referencedocument referencedocument, String path, String folderName) throws Exception {
        String pathToCreate=folderName+'/'+referencedocument.getIdDocumentReference()+'_'+referencedocument.getClient().getEmail()+
                "_"+referencedocument.getFirstName()+'_'+referencedocument.getLastName()+'/'+path.trim();
        return this.createUserDir(pathToCreate);
    }

    @Override
    public String createUserDir(String path) {
        File theDir = new File(this.uploadDirectory+path.trim());
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        return this.uploadDirectory+path.trim();
    }

    @Override
    public String uploadImage(String imageBase64, Long agentId,Long referenceDocumentId, String type) throws Exception {
        Referencedocument referencedocument = referentDocumentService.findReferencedocumentById(referenceDocumentId);
        Client client = referencedocument.getClient();
        if(client == null){
            throw new ResourceNotFoundException("Client not Found");
        }
        Agent agent =  personService.getAgentById(agentId);
        if(agent == null){
            throw new ResourceNotFoundException("Agent with Id"+" "+agentId+" "+ "is not Found");
        }
        String[] strings = imageBase64.split(",");
        String extension;
        switch (strings[0]) {//check image's extension
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default:
                extension = "jpg";
                break;
        }
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        String fileName = referencedocument.getFirstName()+referencedocument.getClient().getEmail()+".";
        String pathString = this.createFolderUser(referencedocument,"image",type).trim();
        String separator = "/fileUploads/";
        String[] mots = pathString.split(separator);
        String path = pathString+"/"+fileName+extension;
        File file = new File(path);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            Imageadditionaldocument imageadditionaldocument = new Imageadditionaldocument();
            imageadditionaldocument.setPathDocumentImage("fileUploads/"+mots[1]+'/'+fileName+extension);
            imageadditionaldocument.setDateAdded(Date.from(Instant.now()));
            imageadditionaldocument.setClient(client);
            imageadditionaldocument.setAgent(agent);
            imageadditionaldocument.setReferenceDocument(referencedocument);
            imageadditionaldocument = (Imageadditionaldocument) additionalDocumentsRepository.save(imageadditionaldocument);
            Object object = referentDocumentService.getCurrentReferenceDocumentByIdDocument(referenceDocumentId);
            if(object.getClass().toString().contains("Cni")){
                Cni cni = (Cni) object;
                cni.setImageadditionaldocument(imageadditionaldocument);
                referentDocumentService.saveDocCni(cni);
            }else if(object.getClass().toString().contains("Passport")){
                Passport passport = (Passport) object;
                passport.setImageadditionaldocument(imageadditionaldocument);
                referentDocumentService.saveDocPassport(passport);
            }
            outputStream.write(data);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
        return "Upload Image Successful";
    }

    @Override
    public String getImageBase64ToString(String path){
        if(path.equals(null) || path.trim().length()<1)
            throw new ResourceNotFoundException("Error");
        try {
            File file = new File(path);
            FileInputStream fin = new FileInputStream(file);
            byte[] imageByteArray = new byte[(int) file.length()];
            fin.read(imageByteArray);
            String extension = path.substring(path.lastIndexOf(".")+1);
            String image = "data:image/"+extension+";base64,"+Base64.encodeBase64String(imageByteArray);
            fin.close();
            return image;
        }catch (Exception exception){
            throw new ResourceNotFoundException("Error");
        }
    }

}
