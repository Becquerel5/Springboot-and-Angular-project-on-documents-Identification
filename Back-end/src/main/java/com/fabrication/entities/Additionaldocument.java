package com.fabrication.entities;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", length = 25)
public abstract class Additionaldocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_additional_document",nullable = true)
    private Long idAdditionalDocument;

    @Column(name = "path_document_image", nullable = true)
    @NotBlank()
    private String pathDocumentImage;

    @Column(name = "date_added", nullable = true, insertable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date dateAdded;

    @ManyToOne(optional = false)
    @JsonIncludeProperties(value = {"id","email","agentLastName","agentFirstName","login","password"})
    private Agent agent;

    @ManyToOne(optional = false)
    @JsonIncludeProperties(value = {"id","email","validationCode"})
    private Client client;

    @ManyToOne()
    @JsonIncludeProperties(value = {"idDocumentReference","documentNumber"})
    private Referencedocument referenceDocument;

    public Additionaldocument() {
    }

    public Additionaldocument(Long idAdditionalDocument,
                              @NotBlank() String pathDocumentImage,
                              Date dateAdded,
                              Agent agent,
                              Client client,
                              Referencedocument referenceDocument) {
        this.idAdditionalDocument = idAdditionalDocument;
        this.pathDocumentImage = pathDocumentImage;
        this.dateAdded = dateAdded;
        this.agent = agent;
        this.client = client;
        this.referenceDocument = referenceDocument;
    }

    public Long getIdAdditionalDocument() {
        return idAdditionalDocument;
    }

    public void setIdAdditionalDocument(Long idAdditionalDocument) {
        this.idAdditionalDocument = idAdditionalDocument;
    }

    public @NotBlank() String getPathDocumentImage() {
        return pathDocumentImage;
    }

    public void setPathDocumentImage(@NotBlank() String pathDocumentImage) {
        this.pathDocumentImage = pathDocumentImage;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
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

    public Referencedocument getReferenceDocument() {
        return referenceDocument;
    }

    public void setReferenceDocument(Referencedocument referenceDocument) {
        this.referenceDocument = referenceDocument;
    }

    @Override
    public String toString() {
        return "AdditionalDocument{" +
                "idAdditionalDocument=" + idAdditionalDocument +
                ", pathDocumentImage='" + pathDocumentImage + '\'' +
                ", dateAdded=" + dateAdded +
                ", agent=" + agent +
                ", client=" + client +
                ", ReferenceDocument=" + referenceDocument +
                '}';
    }

}
