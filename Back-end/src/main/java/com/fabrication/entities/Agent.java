package com.fabrication.entities;
import com.fabrication.utils.PersonStatus;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AGENT")
public class Agent extends Person {

	@Column(name = "agent_last_name")
	private String agentLastName;

	@Column(name = "agent_first_name")
	private String agentFirstName;

	@Column(name = "login", unique = true)
	private String login;

	@Column(name = "password")
	private String password;

	public Agent() {
	}

	public Agent(
			Long id,
			String email,
			PersonStatus personStatus,
			String agentLastName,
			String agentFirstName,
			String login,
			String password) {
		super(id, email, personStatus);
		this.agentLastName = agentLastName;
		this.agentFirstName = agentFirstName;
		this.login = login;
		this.password = password;
	}

	public String getAgentLastName() {
		return agentLastName;
	}

	public void setAgentLastName(String agentLastName) {
		this.agentLastName = agentLastName;
	}

	public String getAgentFirstName() {
		return agentFirstName;
	}

	public void setAgentFirstName(String agentFirstName) {
		this.agentFirstName = agentFirstName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		return "Agent{" +
				"idAgent='" + this.getId() + '\'' +
				", emailAgent='" + this.getEmail() + '\'' +
				", agentLastName='" + agentLastName + '\'' +
				", agentFirstName='" + agentFirstName + '\'' +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", StatusAgent='" + this.personStatus + '\'' +
				'}';
	}
}
