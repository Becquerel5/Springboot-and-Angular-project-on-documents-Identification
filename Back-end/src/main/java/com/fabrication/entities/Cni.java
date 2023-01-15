package com.fabrication.entities;

import com.fabrication.utils.*;
import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@Entity
@DiscriminatorValue("CNI")
public class Cni extends Referencedocument {
    @Override
    public String toString() {
        return "Cni{" +
                "PosteIdentification='" + PosteIdentification + '\'' +
                ", birthcertificate=" + birthcertificate +
                ", nationalitycertificate=" + nationalitycertificate +
                ", lostcertificate=" + lostcertificate +
                ", biometric=" + biometric +
                "} " + super.toString();
    }

    @Column(name = "poste_identification")
	private String PosteIdentification;

	@ManyToOne
	private Birthcertificate birthcertificate;

	@ManyToOne
	private Nationalitycertificate nationalitycertificate;

	@ManyToOne
	private Lostcertificate lostcertificate;

	@ManyToOne
	private Biometric biometric;




	public Cni() {
	}

	public Cni(
			Long idDocumentReference,
			String documentNumber,
			String referenceNumber,
			String lastName,
			String firstName,
			Date dateOfBirth,
			Gender gender,
			String profession,
			String nameOfFather,
			String nameOfMother,
			Date deliveryDate,
			Date expirationDate,
			String address,
			Agent agent,
			Client client,
			Imageadditionaldocument imageadditionaldocument,
			StatusTreatmentSystemeList statusTreatmentSystemeList,
			StatusInTreatment statusInTreatment,
			Date withdrawalDate,
			String posteIdentification,
			Birthcertificate birthcertificate,
			Nationalitycertificate nationalitycertificate,
			Lostcertificate lostcertificate,
			Biometric biometric) {
		super(
				idDocumentReference,
				documentNumber,
				referenceNumber,
				lastName,
				firstName,
				dateOfBirth,
				gender,
				profession,
				nameOfFather,
				nameOfMother,
				deliveryDate,
				expirationDate,
				address,
				agent,
				client,
				imageadditionaldocument,
				statusTreatmentSystemeList,
				statusInTreatment,
				withdrawalDate);
		PosteIdentification = posteIdentification;
		this.birthcertificate = birthcertificate;
		this.nationalitycertificate = nationalitycertificate;
		this.lostcertificate = lostcertificate;
		this.biometric = biometric;
	}

	public String getPosteIdentification() {
		return PosteIdentification;
	}

	public void setPosteIdentification(String posteIdentification) {
		PosteIdentification = posteIdentification;
	}

	public Birthcertificate getBirthcertificate() {
		return birthcertificate;
	}

	public void setBirthcertificate(Birthcertificate birthcertificate) {
		this.birthcertificate = birthcertificate;
	}

	public Nationalitycertificate getNationalitycertificate() {
		return nationalitycertificate;
	}

	public void setNationalitycertificate(Nationalitycertificate nationalitycertificate) {
		this.nationalitycertificate = nationalitycertificate;
	}

	public Lostcertificate getLostcertificate() {
		return lostcertificate;
	}

	public void setLostcertificate(Lostcertificate lostcertificate) {
		this.lostcertificate = lostcertificate;
	}

	public Biometric getBiometric() {
		return biometric;
	}

	public void setBiometric(Biometric biometric) {
		this.biometric = biometric;
	}

	public Cni(
			Long idDocumentReference, String documentNumber, String referenceNumber, String lastName, String firstName, Date dateOfBirth, Gender gender, String profession, String nameOfFather, String nameOfMother, Date deliveryDate, Date expirationDate, String address, Agent agent, Client client, Imageadditionaldocument imageadditionaldocument, StatusTreatmentSystemeList statusTreatmentSystemeList, StatusInTreatment statusInTreatment, Date withdrawalDate) {
		super(
				idDocumentReference,
				documentNumber,
				referenceNumber,
				lastName,
				firstName,
				dateOfBirth,
				gender,
				profession,
				nameOfFather,
				nameOfMother,
				deliveryDate,
				expirationDate,
				address,
				agent,
				client,
				imageadditionaldocument,
				statusTreatmentSystemeList,
				statusInTreatment,
				withdrawalDate);
	}

	@Override
	public Long getIdDocumentReference() {
		return super.getIdDocumentReference();
	}

	@Override
	public void setIdDocumentReference(Long idDocumentReference) {
		super.setIdDocumentReference(idDocumentReference);
	}

	@Override
	public String getDocumentNumber() {
		return super.getDocumentNumber();
	}

	@Override
	public void setDocumentNumber(String documentNumber) {
		super.setDocumentNumber(documentNumber);
	}

	@Override
	public String getReferenceNumber() {
		return super.getReferenceNumber();
	}

	@Override
	public void setReferenceNumber(String referenceNumber) {
		super.setReferenceNumber(referenceNumber);
	}

	@Override
	public String getLastName() {
		return super.getLastName();
	}

	@Override
	public void setLastName(String lastName) {
		super.setLastName(lastName);
	}

	@Override
	public String getFirstName() {
		return super.getFirstName();
	}

	@Override
	public void setFirstName(String firstName) {
		super.setFirstName(firstName);
	}

	@Override
	public Date getDateOfBirth() {
		return super.getDateOfBirth();
	}

	@Override
	public void setDateOfBirth(Date dateOfBirth) {
		super.setDateOfBirth(dateOfBirth);
	}

	@Override
	public Gender getGender() {
		return super.getGender();
	}

	@Override
	public void setGender(Gender gender) {
		super.setGender(gender);
	}

	@Override
	public String getProfession() {
		return super.getProfession();
	}

	@Override
	public void setProfession(String profession) {
		super.setProfession(profession);
	}

	@Override
	public String getNameOfFather() {
		return super.getNameOfFather();
	}

	@Override
	public void setNameOfFather(String nameOfFather) {
		super.setNameOfFather(nameOfFather);
	}

	@Override
	public String getNameOfMother() {
		return super.getNameOfMother();
	}

	@Override
	public void setNameOfMother(String nameOfMother) {
		super.setNameOfMother(nameOfMother);
	}

	@Override
	public Date getDeliveryDate() {
		return super.getDeliveryDate();
	}

	@Override
	public void setDeliveryDate(Date deliveryDate) {
		super.setDeliveryDate(deliveryDate);
	}

	@Override
	public Date getExpirationDate() {
		return super.getExpirationDate();
	}

	@Override
	public void setExpirationDate(Date expirationDate) {
		super.setExpirationDate(expirationDate);
	}

	@Override
	public String getAddress() {
		return super.getAddress();
	}

	@Override
	public void setAddress(String address) {
		super.setAddress(address);
	}

	@Override
	public Agent getAgent() {
		return super.getAgent();
	}

	@Override
	public void setAgent(Agent agent) {
		super.setAgent(agent);
	}

	@Override
	public Client getClient() {
		return super.getClient();
	}

	@Override
	public void setClient(Client client) {
		super.setClient(client);
	}

	@Override
	public Imageadditionaldocument getImageadditionaldocument() {
		return super.getImageadditionaldocument();
	}

	@Override
	public void setImageadditionaldocument(Imageadditionaldocument imageadditionaldocument) {
		super.setImageadditionaldocument(imageadditionaldocument);
	}

	@Override
	public StatusTreatmentSystemeList getStatusTreatmentSystemeList() {
		return super.getStatusTreatmentSystemeList();
	}

	@Override
	public void setStatusTreatmentSystemeList(StatusTreatmentSystemeList statusTreatmentSystemeList) {
		super.setStatusTreatmentSystemeList(statusTreatmentSystemeList);
	}

	@Override
	public StatusInTreatment getStatusInTreatment() {
		return super.getStatusInTreatment();
	}

	@Override
	public void setStatusInTreatment(StatusInTreatment statusInTreatment) {
		super.setStatusInTreatment(statusInTreatment);
	}

	@Override
	public Date getWithdrawalDate() {
		return super.getWithdrawalDate();
	}

	@Override
	public void setWithdrawalDate(Date withdrawalDate) {
		super.setWithdrawalDate(withdrawalDate);
	}
}
