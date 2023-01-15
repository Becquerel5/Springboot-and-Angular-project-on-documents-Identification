package com.fabrication.entities;

import com.fabrication.utils.Gender;
import com.fabrication.utils.StatusInTreatment;
import com.fabrication.utils.StatusTreatmentSystemeList;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Referencedocument {
    @Override
    public String toString() {
        return "Referencedocument{" +
                "idDocumentReference=" + idDocumentReference +
                ", documentNumber='" + documentNumber + '\'' +
                ", referenceNumber='" + referenceNumber + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", profession='" + profession + '\'' +
                ", nameOfFather='" + nameOfFather + '\'' +
                ", nameOfMother='" + nameOfMother + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", expirationDate=" + expirationDate +
                ", address='" + address + '\'' +
                ", agent=" + agent +
                ", client=" + client +
                ", imageadditionaldocument=" + imageadditionaldocument +
                ", statusTreatmentSystemeList=" + statusTreatmentSystemeList +
                ", statusInTreatment=" + statusInTreatment +
                ", withdrawalDate=" + withdrawalDate +
                '}';
    }

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_document_reference")
	private Long idDocumentReference;

	@Column(name = "document_number")
	private String documentNumber;

	@Column(name = "reference_number", nullable = false, updatable = false)
	private String referenceNumber;

	@Column(name = "last_name")
	//@Pattern(regexp = "^\\w\\S{5,20}$", message = "LastName error")
	private String lastName;

	@Column(name = "first_name")
	///@Pattern(regexp = "^\\w\\S{5,20}$", message = "FirstName error")
	private String firstName;

	@Column(name = "date_of_birth")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfBirth;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "profession")
	private String profession;

	@Column(name = "name_of_father")
	private String nameOfFather;

	@Column(name = "name_of_mother")
	private String nameOfMother;

	@Column(name = "delivery_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryDate;

	@Column(name = "expiration_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expirationDate;

	@Column(name = "address")
	private String address;

	@ManyToOne
	@JsonIncludeProperties(value = {"id","agentLastName","agentFirstName","login"})
	private Agent agent;

	@ManyToOne(optional = false)
	@JsonIncludeProperties(value = {"id","email","validationCode"})
	private Client client;

	@ManyToOne
	@JsonIncludeProperties(value = {"idAdditionalDocument","pathDocumentImage","client","dateAdded"})
	private Imageadditionaldocument imageadditionaldocument;

	@Enumerated(EnumType.STRING)
	private StatusTreatmentSystemeList statusTreatmentSystemeList;

	@Enumerated(EnumType.STRING)
	private StatusInTreatment statusInTreatment;

	@Column(name = "withdrawal_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date withdrawalDate;

	public Referencedocument() {
	}

	public Referencedocument(
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
			Date withdrawalDate) {
		this.idDocumentReference = idDocumentReference;
		this.documentNumber = documentNumber;
		this.referenceNumber = referenceNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.profession = profession;
		this.nameOfFather = nameOfFather;
		this.nameOfMother = nameOfMother;
		this.deliveryDate = deliveryDate;
		this.expirationDate = expirationDate;
		this.address = address;
		this.agent = agent;
		this.client = client;
		this.imageadditionaldocument = imageadditionaldocument;
		this.statusTreatmentSystemeList = statusTreatmentSystemeList;
		this.statusInTreatment = statusInTreatment;
		this.withdrawalDate = withdrawalDate;
	}

	public Long getIdDocumentReference() {
		return idDocumentReference;
	}

	public void setIdDocumentReference(Long idDocumentReference) {
		this.idDocumentReference = idDocumentReference;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getNameOfFather() {
		return nameOfFather;
	}

	public void setNameOfFather(String nameOfFather) {
		this.nameOfFather = nameOfFather;
	}

	public String getNameOfMother() {
		return nameOfMother;
	}

	public void setNameOfMother(String nameOfMother) {
		this.nameOfMother = nameOfMother;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Imageadditionaldocument getImageadditionaldocument() {
		return imageadditionaldocument;
	}

	public void setImageadditionaldocument(Imageadditionaldocument imageadditionaldocument) {
		this.imageadditionaldocument = imageadditionaldocument;
	}

	public StatusTreatmentSystemeList getStatusTreatmentSystemeList() {
		return statusTreatmentSystemeList;
	}

	public void setStatusTreatmentSystemeList(StatusTreatmentSystemeList statusTreatmentSystemeList) {
		this.statusTreatmentSystemeList = statusTreatmentSystemeList;
	}

	public StatusInTreatment getStatusInTreatment() {
		return statusInTreatment;
	}

	public void setStatusInTreatment(StatusInTreatment statusInTreatment) {
		this.statusInTreatment = statusInTreatment;
	}

	public Date getWithdrawalDate() {
		return withdrawalDate;
	}

	public void setWithdrawalDate(Date withdrawalDate) {
		this.withdrawalDate = withdrawalDate;
	}



}
