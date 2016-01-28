/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guschaor
 */
@Entity
@Table(name = "sc_person_by_group")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScPersonByGroup.findAll", query = "SELECT s FROM ScPersonByGroup s"),
    @NamedQuery(name = "ScPersonByGroup.findByIdPersonByGroup", query = "SELECT s FROM ScPersonByGroup s WHERE s.idPersonByGroup = :idPersonByGroup")
})
public class ScPersonByGroup implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_person_by_group")
    private Long idPersonByGroup;
    @JoinColumn(name = "id_group", referencedColumnName = "id_group")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScGroup idGroup;

    public ScPersonByGroup()
    {
    }

    public ScPersonByGroup(Long idPersonByGroup)
    {
        this.idPersonByGroup = idPersonByGroup;
    }

    public Long getIdPersonByGroup()
    {
        return idPersonByGroup;
    }

    public void setIdPersonByGroup(Long idPersonByGroup)
    {
        this.idPersonByGroup = idPersonByGroup;
    }

    public ScGroup getIdGroup()
    {
        return idGroup;
    }

    public void setIdGroup(ScGroup idGroup)
    {
        this.idGroup = idGroup;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idPersonByGroup != null ? idPersonByGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScPersonByGroup))
        {
            return false;
        }
        ScPersonByGroup other = (ScPersonByGroup) object;
        if ((this.idPersonByGroup == null && other.idPersonByGroup != null) || (this.idPersonByGroup != null && !this.idPersonByGroup.equals(other.idPersonByGroup)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScPersonByGroup[ idPersonByGroup=" + idPersonByGroup + " ]";
    }
    
}
