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
 * @author guschaor
 */
@Entity 
@Table(name = "sc_replacement_documents", schema = "dmes")
@XmlRootElement
@NamedQueries({
                @NamedQuery(name = "ScReplacementDocuments.findAll", query = "SELECT s FROM ScReplacementDocuments s ORDER BY s.creationDate DESC")
            })
public class ScReplacementDocuments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscreplacementdocuments")
    @SequenceGenerator(name = "dmes.sqscreplacementdocuments", sequenceName = "dmes.sqscreplacementdocuments", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_replacement_documents")
    private Long idReplacementDocuments;
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
    @Size(min = 1, max = 200)
    @Column(name = "document_name")
    private String documentName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "upload_by")
    private String uploadBy;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "type_document")
    private String typeDocument;
    @JoinColumn(name = "id_replacement", referencedColumnName = "id_replacement")
    @ManyToOne(optional = false)
    private ScReplacement replacement;
    
    public ScReplacementDocuments() 
    {
    }

    public ScReplacementDocuments(Long idReplacementDocuments)
    {
        this.idReplacementDocuments = idReplacementDocuments;
    }

    public ScReplacementDocuments(Long idReplacementDocuments, String documentPath, String documentTittle, Date creationDate, String uploadBy, String typeDocument, ScReplacement replacement)
    {
        this.idReplacementDocuments = idReplacementDocuments;
        this.documentPath = documentPath;
        this.documentTittle = documentTittle;
        this.creationDate = creationDate;
        this.uploadBy = uploadBy;
        this.typeDocument = typeDocument;
        this.replacement = replacement;
    }

    public Long getIdReplacementDocuments()
    {
        return idReplacementDocuments;
    }

    public void setIdReplacementDocuments(Long idReplacementDocuments)
    {
        this.idReplacementDocuments = idReplacementDocuments;
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

    public String getTypeDocument()
    {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument)
    {
        this.typeDocument = typeDocument;
    }

    public ScReplacement getReplacement()
    {
        return replacement;
    }

    public void setReplacement(ScReplacement replacement)
    {
        this.replacement = replacement;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.idReplacementDocuments);
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
        final ScReplacementDocuments other = (ScReplacementDocuments) obj;
        if (!Objects.equals(this.idReplacementDocuments, other.idReplacementDocuments))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ScReplacementDocuments{" + "idReplacementDocuments=" + idReplacementDocuments + ", documentPath=" + documentPath + ", documentTittle=" + documentTittle + ", creationDate=" + creationDate + ", documentName=" + documentName + ", uploadBy=" + uploadBy + ", typeDocument=" + typeDocument + ", replacement=" + replacement + '}';
    }

    

    
}
