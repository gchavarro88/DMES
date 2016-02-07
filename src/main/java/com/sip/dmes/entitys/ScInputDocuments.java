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
@Table(name = "sc_input_documents", schema = "dmes")
@XmlRootElement
@NamedQueries({
                @NamedQuery(name = "ScInputDocuments.findAll", query = "SELECT s FROM ScInputDocuments s")
            })
public class ScInputDocuments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscinputdocuments")
    @SequenceGenerator(name = "dmes.sqscinputdocuments", sequenceName = "dmes.sqscinputdocuments", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_input_documents")
    private Long idInputDocuments;
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
    @JoinColumn(name = "id_input", referencedColumnName = "id_input")
    @ManyToOne(optional = false)
    private ScInput idInput;
    
    public ScInputDocuments() 
    {
    }

    public ScInputDocuments(Long idInputDocuments)
    {
        this.idInputDocuments = idInputDocuments;
    }

    public ScInputDocuments(Long idInputDocuments, String documentPath, String documentTittle, Date creationDate, String uploadBy, String typeDocument, ScInput idInput)
    {
        this.idInputDocuments = idInputDocuments;
        this.documentPath = documentPath;
        this.documentTittle = documentTittle;
        this.creationDate = creationDate;
        this.uploadBy = uploadBy;
        this.typeDocument = typeDocument;
        this.idInput = idInput;
    }

    public Long getIdInputDocuments()
    {
        return idInputDocuments;
    }

    public void setIdInputDocuments(Long idInputDocuments)
    {
        this.idInputDocuments = idInputDocuments;
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

    public ScInput getIdInput()
    {
        return idInput;
    }

    public void setIdInput(ScInput idInput)
    {
        this.idInput = idInput;
    }

    @Override
    public String toString()
    {
        return "ScInputDocuments{" + "idInputDocuments=" + idInputDocuments + ", documentPath=" + documentPath + ", documentTittle=" + documentTittle + ", creationDate=" + creationDate + ", documentName=" + documentName + ", uploadBy=" + uploadBy + ", typeDocument=" + typeDocument + ", idInput=" + idInput + '}';
    }

    
}
