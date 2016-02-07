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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "sc_machine_part_document", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScMachinePartDocument.findAll", query = "SELECT s FROM ScMachinePartDocument s"),
    @NamedQuery(name = "ScMachinePartDocument.findByIdMachinePartDocument", query = "SELECT s FROM ScMachinePartDocument s WHERE s.idMachinePartDocument = :idMachinePartDocument"),
    @NamedQuery(name = "ScMachinePartDocument.findByDocumentPath", query = "SELECT s FROM ScMachinePartDocument s WHERE s.documentPath = :documentPath"),
    @NamedQuery(name = "ScMachinePartDocument.findByDocumentTittle", query = "SELECT s FROM ScMachinePartDocument s WHERE s.documentTittle = :documentTittle"),
    @NamedQuery(name = "ScMachinePartDocument.findByCreationDate", query = "SELECT s FROM ScMachinePartDocument s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScMachinePartDocument.findByDocumentName", query = "SELECT s FROM ScMachinePartDocument s WHERE s.documentName = :documentName"),
    @NamedQuery(name = "ScMachinePartDocument.findByUploadBy", query = "SELECT s FROM ScMachinePartDocument s WHERE s.uploadBy = :uploadBy"),
    @NamedQuery(name = "ScMachinePartDocument.findByIdMachinePart", query = "SELECT s FROM ScMachinePartDocument s WHERE s.idMachinePart = :idMachinePart")
})
public class ScMachinePartDocument implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscmachinepartdocument")
    @SequenceGenerator(name = "dmes.sqscmachinepartdocument", sequenceName = "dmes.sqscmachinepartdocument", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_machine_part_document")
    private long idMachinePartDocument;
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
    @Column(name = "document_type")
    private String documentType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "upload_by")
    private String uploadBy;
    
    @JoinColumn(name = "id_machine_part", referencedColumnName = "id_machine_part")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScMachinePart idMachinePart;
    
    public ScMachinePartDocument()
    {
    }

    public ScMachinePartDocument(long idMachinePartDocument)
    {
        this.idMachinePartDocument = idMachinePartDocument;
    }

    public ScMachinePartDocument(long idMachinePartDocument, String documentPath, String documentTittle, Date creationDate, String documentName, String uploadBy)
    {
        this.idMachinePartDocument = idMachinePartDocument;
        this.documentPath = documentPath;
        this.documentTittle = documentTittle;
        this.creationDate = creationDate;
        this.documentName = documentName;
        this.uploadBy = uploadBy;
    }

    public long getIdMachinePartDocument()
    {
        return idMachinePartDocument;
    }

    public void setIdMachinePartDocument(long idMachinePartDocument)
    {
        this.idMachinePartDocument = idMachinePartDocument;
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

    public ScMachinePart getIdMachinePart()
    {
        return idMachinePart;
    }

    public void setIdMachinePart(ScMachinePart idMachinePart)
    {
        this.idMachinePart = idMachinePart;
    }

   

    public String getDocumentType()
    {
        return documentType;
    }

    public void setDocumentType(String documentType)
    {
        this.documentType = documentType;
    } 

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 31 * hash + (int) (this.idMachinePartDocument ^ (this.idMachinePartDocument >>> 32));
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
        final ScMachinePartDocument other = (ScMachinePartDocument) obj;
        if (this.idMachinePartDocument != other.idMachinePartDocument)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ScMachinePartDocument{" + "idMachinePartDocument=" + idMachinePartDocument + '}';
    }

   
    
}
