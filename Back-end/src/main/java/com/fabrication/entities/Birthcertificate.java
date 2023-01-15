package com.fabrication.entities;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@DiscriminatorValue("BIRTHECERTIFICATE")
@Builder
public class Birthcertificate extends Additionaldocument {

	@Column(name = "path_verso_image")
	private String pathVersoImage;

	public Birthcertificate() {

	}

	public Birthcertificate(
			Long idAdditionalDocument,
			@NotBlank() String pathDocumentImage,
			Date dateAdded,
			Agent agent,
			Client client,
			Referencedocument referenceDocument,
			String pathVersoImage) {
		super(idAdditionalDocument, pathDocumentImage, dateAdded, agent, client, referenceDocument);
		this.pathVersoImage = pathVersoImage;
	}

	public String getPathVersoImage() {
		return pathVersoImage;
	}

	public void setPathVersoImage( String pathVersoImage) {
		this.pathVersoImage = pathVersoImage;
	}

	@Override
	public String toString() {
		return "BirthCertificate{" +
				"pathVersoImage='" + pathVersoImage + '\'' +
				'}';
	}

	public Birthcertificate(String pathVersoImage) {
		this.pathVersoImage = pathVersoImage;
	}

	public Birthcertificate(Long idAdditionalDocument, @NotBlank() String pathDocumentImage, Date dateAdded, Agent agent, Client client, Referencedocument referenceDocument) {
		super(idAdditionalDocument, pathDocumentImage, dateAdded, agent, client, referenceDocument);
	}

	@Override
	public Long getIdAdditionalDocument() {
		return super.getIdAdditionalDocument();
	}

	@Override
	public void setIdAdditionalDocument(Long idAdditionalDocument) {
		super.setIdAdditionalDocument(idAdditionalDocument);
	}

	@Override
	public @NotBlank() String getPathDocumentImage() {
		return super.getPathDocumentImage();
	}

	@Override
	public void setPathDocumentImage(@NotBlank() String pathDocumentImage) {
		super.setPathDocumentImage(pathDocumentImage);
	}

	@Override
	public Date getDateAdded() {
		return super.getDateAdded();
	}

	@Override
	public void setDateAdded(Date dateAdded) {
		super.setDateAdded(dateAdded);
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
	public Referencedocument getReferenceDocument() {
		return super.getReferenceDocument();
	}

	@Override
	public void setReferenceDocument(Referencedocument referenceDocument) {
		super.setReferenceDocument(referenceDocument);
	}
}
