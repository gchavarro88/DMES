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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_machine_document", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScMachineDocument.findAll", query = "SELECT s FROM ScMachineDocument s"),
    @NamedQuery(name = "ScMachineDocument.findByIdMachineDocument", query = "SELECT s FROM ScMachineDocument s WHERE s.idMachineDocument = :idMachineDocument"),
    @NamedQuery(name = "ScMachineDocument.findByDocumentPath", query = "SELECT s FROM ScMachineDocument s WHERE s.documentPath = :documentPath"),
    @NamedQuery(name = "ScMachineDocument.findByDocumentTittle", query = "SELECT s FROM ScMachineDocument s WHERE s.documentTittle = :documentTittle"),
    @NamedQuery(name = "ScMachineDocument.findByCreationDate", query = "SELECT s FROM ScMachineDocument s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScMachineDocument.findByDocumentName", query = "SELECT s FROM ScMachineDocument s WHERE s.documentName = :documentName"),
    @NamedQuery(name = "ScMachineDocument.findByUploadBy", query = "SELECT s FROM ScMachineDocument s WHERE s.uploadBy = :uploadBy"),
    @NamedQuery(name = "ScMachineDocument.findByDocumentType", query = "SELECT s FROM ScMachineDocument s WHERE s.documentType = :documentType")
})
public class ScMachineDocument implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_machine_document")
    private Long idMachineDocument;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "document_path")
    private String documentPath;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "document_tittle")
    private String documentTittle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "document_name")
    private String documentName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "upload_by")
    private String uploadBy;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "document_type")
    private String documentType;
    @JoinColumn(name = "id_machine", referencedColumnName = "id_machine")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScMachine idMachine;

    public ScMachineDocument()
    {
    }

    public ScMachineDocument(Long idMachineDocument)
    {
        this.idMachineDocument = idMachineDocument;
    }

    public ScMachineDocument(Long idMachineDocument, String documentPath, String documentTittle, Date creationDate, String documentName, String uploadBy, String documentType)
    {
        this.idMachineDocument = idMachineDocument;
        this.documentPath = documentPath;
        this.documentTittle = documentTittle;
        this.creationDate = creationDate;
        this.documentName = documentName;
        this.uploadBy = uploadBy;
        this.documentType = documentType;
    }

    public Long getIdMachineDocument()
    {
        return idMachineDocument;
    }

    public void setIdMachineDocument(Long idMachineDocument)
    {
        this.idMachineDocument = idMachineDocument;
    }

    public String getDocumentPath()
    {
        return documentPath;
    }

    public void setDocumentPath(String documentPath)
    {
        this.documentPath = documentPath;
    }

    public String getDocumentTittle()
    {
        return documentTittle;
    }

    public void setDocumentTittle(String documentTittle)
    {
        this.documentTittle = documentTittle;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public String getDocumentName()
    {
        return documentName;
    }

    public void setDocumentName(String documentName)
    {
        this.documentName = documentName;
    }

    public String getUploadBy()
    {
        return uploadBy;
    }

    public void setUploadBy(String uploadBy)
    {
        this.uploadBy = uploadBy;
    }

    public String getDocumentType()
    {
        return documentType;
    }

    public void setDocumentType(String documentType)
    {
        this.documentType = documentType;
    }

    public ScMachine getIdMachine()
    {
        return idMachine;
    }

    public void setIdMachine(ScMachine idMachine)
    {
        this.idMachine = idMachine;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idMachineDocument != null ? idMachineDocument.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScMachineDocument))
        {
            return false;
        }
        ScMachineDocument other = (ScMachineDocument) object;
        if ((this.idMachineDocument == null && other.idMachineDocument != null) || (this.idMachineDocument != null && !this.idMachineDocument.equals(other.idMachineDocument)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScMachineDocument[ idMachineDocument=" + idMachineDocument + " ]";
    }
    
}
