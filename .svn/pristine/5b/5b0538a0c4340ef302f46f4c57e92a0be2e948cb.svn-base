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
@Table(name = "sc_product_documents", schema = "dmes")
@XmlRootElement
@NamedQueries({
                @NamedQuery(name = "ScProductDocuments.findAll", query = "SELECT s FROM ScProductDocuments s ORDER BY s.creationDate DESC")
            })
public class ScProductDocuments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscproductdocuments")
    @SequenceGenerator(name = "dmes.sqscproductdocuments", sequenceName = "dmes.sqscproductdocuments", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_product_documents")
    private Long idProductDocuments;
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
    @JoinColumn(name = "id_product_formulation", referencedColumnName = "id_product_formulation")
    @ManyToOne(optional = false)
    private ScProductFormulation productFormulation;
    
    public ScProductDocuments() 
    {
    }

    public ScProductDocuments(Long idProductDocuments)
    {
        this.idProductDocuments = idProductDocuments;
    }

    public ScProductDocuments(Long idProductDocuments, String documentPath, String documentTittle, Date creationDate, String uploadBy, String typeDocument, ScProductFormulation productFormulation)
    {
        this.idProductDocuments = idProductDocuments;
        this.documentPath = documentPath;
        this.documentTittle = documentTittle;
        this.creationDate = creationDate;
        this.uploadBy = uploadBy;
        this.typeDocument = typeDocument;
        this.productFormulation = productFormulation;
    }

    public Long getIdProductDocuments()
    {
        return idProductDocuments;
    }

    public void setIdProductDocuments(Long idProductDocuments)
    {
        this.idProductDocuments = idProductDocuments;
    }

    public ScProductFormulation getProductFormulation()
    {
        return productFormulation;
    }

    public void setProductFormulation(ScProductFormulation productFormulation)
    {
        this.productFormulation = productFormulation;
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

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.idProductDocuments);
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
        final ScProductDocuments other = (ScProductDocuments) obj;
        if (!Objects.equals(this.idProductDocuments, other.idProductDocuments))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ScProductDocuments{" + "idProductDocuments=" + idProductDocuments + ", documentPath=" + documentPath + ", documentTittle=" + documentTittle + ", creationDate=" + creationDate + ", documentName=" + documentName + ", uploadBy=" + uploadBy + ", typeDocument=" + typeDocument + ", productFormulation=" + productFormulation + '}';
    }

    

    
}
