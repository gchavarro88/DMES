/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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
    @NamedQuery(name = "ScMachineDocument.findAll", query = "SELECT s FROM ScMachineDocument s")
    
})
public class ScMachineDocument implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscmachinedocument")
    @SequenceGenerator(name = "dmes.sqscmachinedocument", sequenceName = "dmes.sqscmachinedocument", allocationSize = 1)
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
    @Temporal(TemporalType.TIMESTAMP)
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
    @ManyToOne(optional = false)
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
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final ScMachineDocument other = (ScMachineDocument) obj;
        if (!Objects.equals(this.idMachineDocument, other.idMachineDocument))
        {
            return false;
        }
        if (!Objects.equals(this.documentPath, other.documentPath))
        {
            return false;
        }
        if (!Objects.equals(this.documentTittle, other.documentTittle))
        {
            return false;
        }
        if (!Objects.equals(this.creationDate, other.creationDate))
        {
            return false;
        }
        if (!Objects.equals(this.documentName, other.documentName))
        {
            return false;
        }
        if (!Objects.equals(this.uploadBy, other.uploadBy))
        {
            return false;
        }
        if (!Objects.equals(this.documentType, other.documentType))
        {
            return false;
        }
        if (!Objects.equals(this.idMachine, other.idMachine))
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
