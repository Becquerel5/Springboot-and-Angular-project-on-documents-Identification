package com.fabrication.entities;
import com.fabrication.utils.PersonStatus;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", length = 9)
public abstract class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_person")
	private Long id;

	@Column(name = "email",nullable = false)
	@Email(message = "Email invalid")
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(name = "person_status")
	protected PersonStatus personStatus;

	public Person() {
	}

	public Person(Long id, String email, PersonStatus personStatus) {
		this.id = id;
		this.email = email;
		this.personStatus = personStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PersonStatus getPersonStatus() {
		return personStatus;
	}

	public void setPersonStatus(PersonStatus personStatus) {
		this.personStatus = personStatus;
	}
}
