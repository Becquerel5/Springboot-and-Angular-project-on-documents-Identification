package com.fabrication.entities;

import com.fabrication.utils.PersonStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends Person {

	@Column(name = "validation_code")
	private String validationCode;

	@Column(name = "date_obtaining_code")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateObtainingCode;

	public Client() {
	}

	public Client(Long id,
				  String email,
				  PersonStatus statusPerson,
				  String validationCode,
				  Date dateObtainingCode) {
		super(id, email, statusPerson);
		this.validationCode = validationCode;
		this.dateObtainingCode = dateObtainingCode;
	}
	public String getValidationCode() {
		return validationCode;
	}

	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}

	public Date getDateObtainingCode() {
		return dateObtainingCode;
	}

	public void setDateObtainingCode(Date dateObtainingCode) {
		this.dateObtainingCode = dateObtainingCode;
	}

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	@Override
	public String getEmail() {
		return super.getEmail();
	}

	@Override
	public void setEmail(String email) {
		super.setEmail(email);
	}

	@Override
	public PersonStatus getPersonStatus() {
		return super.getPersonStatus();
	}

	@Override
	public void setPersonStatus(PersonStatus personStatus) {
		super.setPersonStatus(personStatus);
	}

	@Override
	public String toString() {
		return "Client{" +
				"id='" + this.getId() + '\'' +
				", validationCode='" + validationCode + '\'' +
				", dateObtainingCode=" + dateObtainingCode +
				'}';
	}
}
