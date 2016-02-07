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
@Table(name = "sc_class_type", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScClassType.findAll", query = "SELECT s FROM ScClassType s"),
    @NamedQuery(name = "ScClassType.findByIdClassType", query = "SELECT s FROM ScClassType s WHERE s.idClassType = :idClassType"),
    @NamedQuery(name = "ScClassType.findByClassType", query = "SELECT s FROM ScClassType s WHERE s.classType = :classType"),
    @NamedQuery(name = "ScClassType.findByCreationDate", query = "SELECT s FROM ScClassType s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScClassType.findByModifyDate", query = "SELECT s FROM ScClassType s WHERE s.modifyDate = :modifyDate"),
    @NamedQuery(name = "ScClassType.deleteByIdClassType", query = "DELETE FROM ScClassType s WHERE s.idClassType = :idClassType")
})
public class ScClassType implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqclasstype")
    @SequenceGenerator(name = "dmes.sqclasstype", sequenceName = "dmes.sqclasstype", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_class_type")
    private Long idClassType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "class_type")
    private String classType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "modify_date")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;

    public ScClassType()
    {
    }

    public ScClassType(Long idClassType)
    {
        this.idClassType = idClassType;
    }

    public ScClassType(Long idClassType, String classType, Date creationDate, Date modifyDate) {
        this.idClassType = idClassType;
        this.classType = classType;
        this.creationDate = creationDate;
        this.modifyDate = modifyDate;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idClassType != null ? idClassType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScClassType))
        {
            return false;
        }
        ScClassType other = (ScClassType) object;
        if ((this.idClassType == null && other.idClassType != null) || (this.idClassType != null && !this.idClassType.equals(other.idClassType)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScClassType[ idClassType=" + idClassType + " ]";
    }

    public Long getIdClassType() {
        return idClassType;
    }

    public void setIdClassType(Long idClassType) {
        this.idClassType = idClassType;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
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
    
}
