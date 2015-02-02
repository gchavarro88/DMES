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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guschavor
 */
@Entity
@Table(name = "sc_documents", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScDocuments.findAll", query = "SELECT s FROM ScDocuments s"),
    @NamedQuery(name = "ScDocuments.findByIdDocument", query = "SELECT s FROM ScDocuments s WHERE s.idDocument = :idDocument"),
    @NamedQuery(name = "ScDocuments.findByDocumentPath", query = "SELECT s FROM ScDocuments s WHERE s.documentPath = :documentPath"),
    @NamedQuery(name = "ScDocuments.findByDocumentTittle", query = "SELECT s FROM ScDocuments s WHERE s.documentTittle = :documentTittle"),
    @NamedQuery(name = "ScDocuments.findByCreationDate", query = "SELECT s FROM ScDocuments s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScDocuments.findByDocumentName", query = "SELECT s FROM ScDocuments s WHERE s.documentName = :documentName"),
    @NamedQuery(name = "ScDocuments.findByPerson", query = "SELECT s FROM ScDocuments s WHERE s.idPerson = :idPerson")
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
    @Size(min = 1, max = 200)
    @Column(name = "document_path")
    private String documentPath;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "document_tittle")
    private String documentTittle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "document_name")
    private String documentName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "upload_by")
    private String uploadBy;
    @JoinColumn(name = "id_person", referencedColumnName = "id_person")
    @ManyToOne(optional = false)
    private ScPerson idPerson;
    
    public ScDocuments()
    {
    }

    public ScDocuments(Long idDocument)
    {
        this.idDocument = idDocument;
    }

    public ScDocuments(Long idDocument, String documentPath, String documentTittle, Date creationDate, String documentName)
    {
        this.idDocument = idDocument;
        this.documentPath = documentPath;
        this.documentTittle = documentTittle;
        this.creationDate = creationDate;
        this.documentName = documentName;
    }

    public Long getIdDocument()
    {
        return idDocument;
    }

    public void setIdDocument(Long idDocument)
    {
        this.idDocument = idDocument;
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

    public ScPerson getIdPerson()
    {
        return idPerson;
    }

    public void setIdPerson(ScPerson idPerson)
    {
        this.idPerson = idPerson;
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
        return "com.sip.dmes.entitys.ScDocuments[ idDocument=" + idDocument + " ]";
    }

    public String getUploadBy()
    {
        return uploadBy;
    }

    public void setUploadBy(String uploadBy)
    {
        this.uploadBy = uploadBy;
    }
    
    
}
