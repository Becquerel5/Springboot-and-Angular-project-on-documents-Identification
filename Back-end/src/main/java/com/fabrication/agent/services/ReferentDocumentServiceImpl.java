package com.fabrication.agent.services;


import com.fabrication.agent.repositories.AdditionalDocumentsRepository;
import com.fabrication.agent.repositories.ReferenceDocumentRepository;
import com.fabrication.client.repositories.PersonRepository;
import com.fabrication.entities.*;
import com.fabrication.exceptions.ResourceNotFoundException;
import com.fabrication.utils.StatusInTreatment;
import com.fabrication.utils.StatusTreatmentSystemeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class ReferentDocumentServiceImpl implements ReferentDocumentService {


    private final ReferenceDocumentRepository<Cni> cniRepository;
    private final ReferenceDocumentRepository<Passport> passportRepository;
    private final ReferenceDocumentRepository referencedocumentRepository;

    private final PersonRepository personService;

     @Autowired
    private  AdditionalDocumentsRepository additionalDocumentsRepository;

    @Autowired
    public ReferentDocumentServiceImpl(ReferenceDocumentRepository<Cni> cniRepository, ReferenceDocumentRepository<Passport> passportRepository, ReferenceDocumentRepository referencedocumentRepository, PersonRepository personService) {
        this.cniRepository = cniRepository;
        this.passportRepository = passportRepository;
        this.referencedocumentRepository = referencedocumentRepository;
        this.personService = personService;
    }

    @Override
    public List getReferenceDocumentByClientId(Long id) throws ResourceNotFoundException {
        List object = referencedocumentRepository.getReferenceDocumentByClientId(id);
        this.throwExceptionObjet(object.isEmpty());
        return object;
    }

    private void throwExceptionObjet(boolean object) {
        if(object){
            throw new ResourceNotFoundException("Element not exist");
        }
    }

    @Override
    public Object getCurrentReferenceDocumentByClientId(Long id) throws ResourceNotFoundException {
        Optional<Object> object = referencedocumentRepository.getCurrentReferenceDocumentByClientId(id, StatusTreatmentSystemeList.FINISH);
        this.throwExceptionObjet(!object.isPresent());
        return object.get();
    }

    @Override
    public Object getCurrentReferenceDocumentByIdDocument(Long id) throws ResourceNotFoundException {
        Optional<Object> object = referencedocumentRepository.getCurrentReferenceDocumentByIdDocument(id, StatusTreatmentSystemeList.FINISH);
        this.throwExceptionObjet(!object.isPresent());
        return object.get();
    }


    @Override
    public Cni updateCni(Cni cni, Long idDocumentReference) {
        return null;
    }

    @Override
    public Cni saveDocCni(Cni cni) throws ResourceNotFoundException {
        if(cni.getIdDocumentReference()==null)
            throw new ResourceNotFoundException("Document Number is Null");
        if(cni.getClient()==null)
            throw new ResourceNotFoundException("Client is Null");
        Cni cniToSave;
        try{
            Optional<Object> object = referencedocumentRepository
                    .getCurrentReferenceDocumentByClientId(cni.getClient().getId(), StatusTreatmentSystemeList.FINISH);
            if(!object.isPresent())
                throw new ResourceNotFoundException("Client don't have any document");
            if(object.get().getClass()!=cni.getClass())
                throw new ResourceNotFoundException("Error data type is not valid");

            cniToSave = (Cni) referencedocumentRepository.save(this.cniToSave(cni, object));
        }catch (Exception ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }
        return cniToSave;
    }
    private Passport passPortToSave(Passport passPort, Optional object) {
        Passport passPortFromDataSource = (Passport) object.get();
        passPortFromDataSource.setStatusTreatmentSystemeList(passPort.getStatusTreatmentSystemeList());
        passPortFromDataSource.setDeliveryDate(passPort.getDeliveryDate());
        passPortFromDataSource.setFirstName(passPort.getFirstName());
        passPortFromDataSource.setProfession(passPort.getProfession());
        passPortFromDataSource.setDateOfBirth(passPort.getDateOfBirth());
        passPortFromDataSource.setLastName(passPort.getLastName());
        passPortFromDataSource.setExpirationDate(passPort.getExpirationDate());
        passPortFromDataSource.setGender(passPort.getGender());
        passPortFromDataSource.setNameOfFather(passPort.getNameOfFather());
        passPortFromDataSource.setNameOfMother(passPort.getNameOfMother());
        passPortFromDataSource.setAddress(passPort.getAddress());
        passPortFromDataSource.setDocumentNumber(passPort.getDocumentNumber());
        passPortFromDataSource.setImageadditionaldocument(passPort.getImageadditionaldocument());
        passPortFromDataSource.setStatusInTreatment(passPort.getStatusInTreatment());
        passPortFromDataSource.setCountry(passPort.getCountry());
        passPortFromDataSource.setWithdrawalDate(passPort.getWithdrawalDate());
        passPortFromDataSource.setAgent(passPort.getAgent());
        return passPortFromDataSource;
    }

    @Override
    public Object getAllDocuments() {
        Object object = referencedocumentRepository.findAll(StatusTreatmentSystemeList.FORM,StatusTreatmentSystemeList.FINISH);
        if (object==null){
            throw new ResourceNotFoundException("No Documents Found");
        }
        return object;
    }

    @Override
    public Page<Referencedocument> findAllReferenceByStatusTreatmentSystemListAndPage(
            StatusTreatmentSystemeList statusTreatmentSystemeList,
            int page,
            int size
    ) {
        try{
            return referencedocumentRepository.findAll(statusTreatmentSystemeList, PageRequest.of(page,size));
        }catch (Exception exception){
            throw new ResourceNotFoundException("Error While getting data");
        }
    }

    @Override
    public Page findAllReferenceInBuildingStepByStatusInTreatmentAndPage(
            StatusInTreatment statusInTreatment,
            int page,
            int size
    ) {
        try{
            return referencedocumentRepository.findAll(StatusTreatmentSystemeList.BUILD,statusInTreatment, PageRequest.of(page,size));
        }catch (Exception exception){
            throw new ResourceNotFoundException("Error While getting data");
        }
    }

    @Override
    public Object getAllDocumentsById(Long idDocumentReference) {
        Referencedocument referencedocument = referencedocumentRepository.findReferenceDocumentById(idDocumentReference);
        if (referencedocument==null){
            throw new ResourceNotFoundException("referencedocument not exist with id:" + idDocumentReference);
        }else{
            return referencedocument;
        }
    }


    public Optional<Cni> getCniById(Long id) {
        Optional<Cni> cni = cniRepository.findById(id);
        if (id==null){
            throw new ResourceNotFoundException("referencedocument not exist with id:" + cni);
        }else{
            return cni;
        }
    }

    @Override
    public Referencedocument findReferencedocumentById(Long idDocumentReference) {
            Referencedocument referencedocument = referencedocumentRepository.findReferenceDocumentById(idDocumentReference);
            if (referencedocument==null){
                throw new ResourceNotFoundException("referencedocument not exist with id:" + idDocumentReference);

            }else{
                return referencedocument;
            }
   }



    @Override
    public void deleteCni(Long idDocumentReference) {
        try {
            cniRepository.deleteById(idDocumentReference);
        }catch (Exception e){
            throw new ResourceNotFoundException("Error!!Cni Cannot be deleted");
        }
    }
    @Override
    public String generateReferenceFormId(Long clientId) {
        Random rand=new Random();
        StringBuilder res=new StringBuilder();
        for (int i = 0; i <= 1; i++) {
            int randIndex=rand.nextInt("0123456789".length());
            res.append("0123456789".charAt(randIndex));
        }
        int randIndexString=rand.nextInt("AZERTYUIOPMLKJHGFDSQWXCVBN".length());
        res.append("AZERTYUIOPMLKJHGFDSQWXCVBN".charAt(randIndexString));
        for (int i = 0; i <= 2; i++) {
            int randIndex=rand.nextInt("0123456789".length());
            res.append("0123456789".charAt(randIndex));
        }
        res.append(clientId);
        return res.toString().toUpperCase();
    }

    @Override
    public Passport saveDocPassport(Passport passport) throws ResourceNotFoundException {
        if(passport.getIdDocumentReference()==null)
            throw new ResourceNotFoundException("Document Number is Null");
        if(passport.getClient()==null)
            throw new ResourceNotFoundException("Client is Null");
        Passport passPortToSave;
        try{
            Optional<Object> object = referencedocumentRepository
                    .getCurrentReferenceDocumentByClientId(passport.getClient().getId(), StatusTreatmentSystemeList.FINISH);
            if(!object.isPresent())
                throw new ResourceNotFoundException("Client don't have any document");
            if(object.get().getClass()!=passport.getClass())
                throw new ResourceNotFoundException("Error data type is not valid");
            passPortToSave = (Passport) referencedocumentRepository.save(this.passPortToSave(passport, object));
        }catch (Exception ex){
            throw new ResourceNotFoundException(ex.getMessage());
        }
        return passPortToSave;
    }

    @Override
    public Passport updatePassport(Passport passport, Long idDocumentReference) {
        Passport passportDB = passportRepository.findById(passport.getIdDocumentReference()).get();
        if (Objects.nonNull(passport.getFirstName()) && !"".equalsIgnoreCase(
                passport.getFirstName()
        )){
            passportDB.setFirstName(passport.getFirstName());
        }

        //
        if (Objects.nonNull(passport.getLastName()) && !"".equalsIgnoreCase(
                passport.getLastName()
        )){
            passportDB.setLastName(passport.getLastName());
        }

        if (Objects.nonNull(passport.getDateOfBirth()) && !"".equalsIgnoreCase(
                String.valueOf(passport.getDateOfBirth())
        )){
            passportDB.setDateOfBirth(passport.getDateOfBirth());
        }

        if (Objects.nonNull(passport.getGender()) && !"".equalsIgnoreCase(
                String.valueOf(passport.getGender())
        )){
            passportDB.setGender(passport.getGender());
        }

        if (Objects.nonNull(passport.getProfession()) && !"".equalsIgnoreCase(
                passport.getProfession()
        )){
            passportDB.setProfession(passport.getProfession());
        }

        if (Objects.nonNull(passport.getNameOfMother()) && !"".equalsIgnoreCase(
                passport.getNameOfMother()
        )){
            passportDB.setNameOfMother(passport.getNameOfMother());
        }

        if (Objects.nonNull(passport.getNameOfFather()) && !"".equalsIgnoreCase(
                passport.getNameOfFather()
        )){
            passportDB.setNameOfFather(passport.getNameOfFather());
        }

        if (Objects.nonNull(passport.getDeliveryDate()) && !"".equalsIgnoreCase(
                String.valueOf(passport.getDeliveryDate())
        )){
            passportDB.setDeliveryDate(passport.getDeliveryDate());
        }

        if (Objects.nonNull(passport.getExpirationDate()) && !"".equalsIgnoreCase(
                String.valueOf(passport.getExpirationDate())
        )){
            passportDB.setExpirationDate(passport.getExpirationDate());
        }

        if (Objects.nonNull(passport.getAddress()) && !"".equalsIgnoreCase(
                passport.getAddress()
        )){
            passportDB.setAddress(passport.getAddress());
        }

        return passportRepository.save(passportDB);
    }
    private Cni cniToSave(Cni cni, Optional object) {
        Cni cniFromDataSource = (Cni) object.get();
        cniFromDataSource.setStatusTreatmentSystemeList(cni.getStatusTreatmentSystemeList());
        cniFromDataSource.setDeliveryDate(cni.getDeliveryDate());
        cniFromDataSource.setFirstName(cni.getFirstName());
        cniFromDataSource.setProfession(cni.getProfession());
        cniFromDataSource.setDateOfBirth(cni.getDateOfBirth());
        cniFromDataSource.setLastName(cni.getLastName());
        cniFromDataSource.setDocumentNumber(cni.getDocumentNumber());
        cniFromDataSource.setExpirationDate(cni.getExpirationDate());
        cniFromDataSource.setGender(cni.getGender());
        cniFromDataSource.setNameOfFather(cni.getNameOfFather());
        cniFromDataSource.setNameOfMother(cni.getNameOfMother());
        cniFromDataSource.setAddress(cni.getAddress());
        cniFromDataSource.setImageadditionaldocument(cni.getImageadditionaldocument());
        cniFromDataSource.setStatusInTreatment(cni.getStatusInTreatment());
        cniFromDataSource.setPosteIdentification(cni.getPosteIdentification());
        cniFromDataSource.setBiometric(cni.getBiometric());
        cniFromDataSource.setWithdrawalDate(cni.getWithdrawalDate());
        cniFromDataSource.setAgent(cni.getAgent());
        cniFromDataSource.setBirthcertificate(cni.getBirthcertificate());
        cniFromDataSource.setLostcertificate(cni.getLostcertificate());
        cniFromDataSource.setNationalitycertificate(cni.getNationalitycertificate());
        return cniFromDataSource;
    }
    @Override
    public void initCni(Long idClient) throws ResourceNotFoundException {
        Client client = getClientToInitReferenceDocument(idClient);
        Cni newCni = new Cni();
        newCni.setReferenceNumber(this.generateReferenceFormId(client.getId()));
        newCni.setClient(client);
        newCni.setStatusTreatmentSystemeList(StatusTreatmentSystemeList.FORM);
        referencedocumentRepository.save(newCni);
    }
    private Client getClientToInitReferenceDocument(Long idClient) {
        Client client = personService.findClientById(idClient);
        if(client==null)
            throw new ResourceNotFoundException("Client don't exist");
        return client;
    }

    @Override
    public void initPassPort(Long idClient) throws ResourceNotFoundException {
        Client client = getClientToInitReferenceDocument(idClient);
        Passport newPassPort = new Passport();
        newPassPort.setReferenceNumber(this.generateReferenceFormId(client.getId()));
        newPassPort.setClient(client);
        newPassPort.setStatusTreatmentSystemeList(StatusTreatmentSystemeList.FORM);
        referencedocumentRepository.save(newPassPort);
    }

    @Override
    public void deletePassport(Long idDocumentReference) {
        try {
            passportRepository.deleteById(idDocumentReference);
       }catch (Exception e){
            throw new ResourceNotFoundException("Error!!Passport Cannot be deleted");
        }
    }


    public Object additionaldocument(Long id) {
        Object additionaldocument = additionalDocumentsRepository.getReferenceById(id);
        return  additionaldocument;

    }

}
