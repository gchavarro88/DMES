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
@Table(name = "sc_type", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScType.findAll", query = "SELECT s FROM ScType s"),
    @NamedQuery(name = "ScType.findByIdType", query = "SELECT s FROM ScType s WHERE s.idType = :idType"),
    @NamedQuery(name = "ScType.findByType", query = "SELECT s FROM ScType s WHERE s.type = :type"),
    @NamedQuery(name = "ScType.findByIdClassType", query = "SELECT s FROM ScType s WHERE s.idClassType = :idClassType"),
    @NamedQuery(name = "ScType.findByCreationDate", query = "SELECT s FROM ScType s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScType.findByModifyDate", query = "SELECT s FROM ScType s WHERE s.modifyDate = :modifyDate"),
    @NamedQuery(name = "ScType.deleteByIdClassType", query = "DELETE FROM ScType s WHERE s.idClassType = :idClassType"),
    @NamedQuery(name = "ScType.deleteByIdType", query = "DELETE FROM ScType s WHERE s.idType = :idType")
})
public class ScType implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqtype")
    @SequenceGenerator(name = "dmes.sqtype", sequenceName = "dmes.sqtype", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_Type")
    private Long idType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;
    @JoinColumn(name = "id_class_type", referencedColumnName = "id_class_type")
    @ManyToOne(optional = false)
    private ScClassType idClassType;

    public ScType()
    {
    }

    public ScType(Long idType) {
        this.idType = idType;
    }

    public ScType(Long idType, String type, Date creationDate, Date modifyDate, ScClassType idClassType) {
        this.idType = idType;
        this.type = type;
        this.creationDate = creationDate;
        this.modifyDate = modifyDate;
        this.idClassType = idClassType;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idType != null ? idType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScType))
        {
            return false;
        }
        ScType other = (ScType) object;
        if ((this.idType == null && other.idType != null) || (this.idType != null && !this.idType.equals(other.idType)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScType[ idType=" + idType + " ]";
    }

    public Long getIdType() {
        return idType;
    }

    public void setIdType(Long idType) {
        this.idType = idType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public ScClassType getIdClassType() {
        return idClassType;
    }

    public void setIdClassType(ScClassType idClassType) {
        this.idClassType = idClassType;
    }
        
}
