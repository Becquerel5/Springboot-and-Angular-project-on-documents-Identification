package com.fabrication.agent.services;


import com.fabrication.entities.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileResourceService {
    public String uploadBirthCertificate(MultipartFile file1,Long agentId,Long referenceDocumentId, String type) throws Exception;
    public String uploadNationalCertificate(MultipartFile file2,Long agentId,Long referenceDocumentId, String type) throws Exception;
    public String uploadLostCertificate(MultipartFile file3,Long agentId,Long referenceDocumentId, String type) throws Exception;
    public String createFolderUser(Referencedocument referencedocument, String path, String folderName) throws Exception;
    public String createUserDir(String path);
    public String uploadImage(String imageBase64,Long agentId,Long referenceDocumentId, String type) throws Exception;

    public String getImageBase64ToString(String path) throws IOException;

}
