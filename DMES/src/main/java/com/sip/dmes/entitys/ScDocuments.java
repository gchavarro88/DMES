/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author carlos guzman
 */
@Entity
@Table(name = "sc_documents", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScDocuments.findAll", query = "SELECT s FROM ScDocuments s"),
    @NamedQuery(name = "ScDocuments.findByIdDocument", query = "SELECT s FROM ScDocuments s WHERE s.idDocument = :idDocument"),
    @NamedQuery(name = "ScDocuments.findByDocumentName", query = "SELECT s FROM ScDocuments s WHERE s.documentName = :documentName"),
    @NamedQuery(name = "ScDocuments.findByIdMachine", query = "SELECT s FROM ScDocuments s WHERE s.idMachine = :idMachine"),
    @NamedQuery(name = "ScDocuments.findByCreationDate", query = "SELECT s FROM ScDocuments s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScDocuments.findByModifyDate", query = "SELECT s FROM ScDocuments s WHERE s.modifyDate = :modifyDate"),
    @NamedQuery(name = "ScDocuments.deleteByIdDocument", query = "DELETE FROM ScDocuments s WHERE s.idDocument = :idDocument"),
    @NamedQuery(name = "ScDocuments.deleteByIdMachine", query = "DELETE FROM ScDocuments s WHERE s.idMachine = :idMachine")
})
public class ScDocuments implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscdocuments")
    @SequenceGenerator(name = "dmes.sqscdocuments", sequenceName = "dmes.sqscdocuments", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_document")
    private Long idDocument;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "document_name")
    private String documentName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "document_path")
    private String documentPath;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "comments")
    private String comments;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "modify_date")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;
    @JoinColumn(name = "id_machine", referencedColumnName = "id_machine")
    @ManyToOne(optional = false)
    private ScMachine idMachine;

    public ScDocuments()
    {
    }

    public ScDocuments(Long idDocument) {
        this.idDocument = idDocument;
    }

    public ScDocuments(Long idDocument, String documentName, String documentPath, String comments, Date creationDate, Date modifyDate, ScMachine idMachine) {
        this.idDocument = idDocument;
        this.documentName = documentName;
        this.documentPath = documentPath;
        this.comments = comments;
        this.creationDate = creationDate;
        this.modifyDate = modifyDate;
        this.idMachine = idMachine;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idDocument != null ? idDocument.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScDocuments))
        {
            return false;
        }
        ScDocuments other = (ScDocuments) object;
        if ((this.idDocument == null && other.idDocument != null) || (this.idDocument != null && !this.idDocument.equals(other.idDocument)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScDocument[ idDocument=" + idDocument + " ]";
    }

    public Long getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(Long idDocument) {
        this.idDocument = idDocument;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public ScMachine getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(ScMachine idMachine) {
        this.idMachine = idMachine;
    }
      
}
