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
@Table(name = "sc_parts_and_consumables", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScPartsAndConsumables.findAll", query = "SELECT s FROM ScPartsAndConsumables s"),
    @NamedQuery(name = "ScPartsAndConsumables.findByIdPartsAndConsumables", query = "SELECT s FROM ScPartsAndConsumables s WHERE s.idPartsAndConsumables = :idPartsAndConsumables"),
    @NamedQuery(name = "ScPartsAndConsumables.findByPartsAndConsumables", query = "SELECT s FROM ScPartsAndConsumables s WHERE s.partsAndConsumables = :partsAndConsumables"),
    @NamedQuery(name = "ScPartsAndConsumables.findByIdType", query = "SELECT s FROM ScPartsAndConsumables s WHERE s.idType = :idType"),
    @NamedQuery(name = "ScPartsAndConsumables.findByIdClassType", query = "SELECT s FROM ScPartsAndConsumables s WHERE s.idType.idClassType = :idClassType"),
    @NamedQuery(name = "ScPartsAndConsumables.findByCreationDate", query = "SELECT s FROM ScPartsAndConsumables s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScPartsAndConsumables.findByModifyDate", query = "SELECT s FROM ScPartsAndConsumables s WHERE s.modifyDate = :modifyDate"),
    @NamedQuery(name = "ScPartsAndConsumables.deleteByIdPartsAndConsumables", query = "DELETE FROM ScPartsAndConsumables s WHERE s.idPartsAndConsumables = :idPartsAndConsumables")
})
public class ScPartsAndConsumables implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscpartsandconsumables")
    @SequenceGenerator(name = "dmes.sqscpartsandconsumables", sequenceName = "dmes.sqscpartsandconsumables", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_parts_and_consumables")
    private Long idPartsAndConsumables;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "partsAndConsumables")
    private String partsAndConsumables;
    @JoinColumn(name = "id_type", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private ScType idType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "modify_date")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;

    public ScPartsAndConsumables()
    {
    }

    public ScPartsAndConsumables(Long idPartsAndConsumables)
    {
        this.idPartsAndConsumables = idPartsAndConsumables;
    }

    public ScPartsAndConsumables(Long idPartsAndConsumables, String partsAndConsumables, ScType idType, Date creationDate, Date modifyDate) {
        this.idPartsAndConsumables = idPartsAndConsumables;
        this.partsAndConsumables = partsAndConsumables;
        this.idType = idType;
        this.creationDate = creationDate;
        this.modifyDate = modifyDate;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idPartsAndConsumables != null ? idPartsAndConsumables.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScPartsAndConsumables))
        {
            return false;
        }
        ScPartsAndConsumables other = (ScPartsAndConsumables) object;
        if ((this.idPartsAndConsumables == null && other.idPartsAndConsumables != null) || (this.idPartsAndConsumables != null && !this.idPartsAndConsumables.equals(other.idPartsAndConsumables)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScPartsAndConsumables[ idPartsAndConsumables=" + idPartsAndConsumables + " ]";
    }

    public Long getIdPartsAndConsumables() {
        return idPartsAndConsumables;
    }

    public void setIdPartsAndConsumables(Long idPartsAndConsumables) {
        this.idPartsAndConsumables = idPartsAndConsumables;
    }

    public String getPartsAndConsumables() {
        return partsAndConsumables;
    }

    public void setPartsAndConsumables(String partsAndConsumables) {
        this.partsAndConsumables = partsAndConsumables;
    }

    public ScType getIdType() {
        return idType;
    }

    public void setIdType(ScType idType) {
        this.idType = idType;
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
