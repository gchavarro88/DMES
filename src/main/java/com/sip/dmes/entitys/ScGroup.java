/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author guschaor
 */
@Entity
@Table(name = "sc_group", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScGroup.findAll", query = "SELECT s FROM ScGroup s"),
    @NamedQuery(name = "ScGroup.findByIdGroup", query = "SELECT s FROM ScGroup s WHERE s.idGroup = :idGroup"),
    @NamedQuery(name = "ScGroup.findByName", query = "SELECT s FROM ScGroup s WHERE s.name = :name"),
    @NamedQuery(name = "ScGroup.findByDescription", query = "SELECT s FROM ScGroup s WHERE s.description = :description")
})
public class ScGroup implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscgroup")
    @SequenceGenerator(name = "dmes.sqscgroup", sequenceName = "dmes.sqscgroup", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_group")
    private Long idGroup;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @Column(name = "type")
    private Long type;

    public ScGroup()
    {
    }

    public ScGroup(Long idGroup)
    {
        this.idGroup = idGroup;
    }

    public ScGroup(Long idGroup, String name)
    {
        this.idGroup = idGroup;
        this.name = name;
    }

    public Long getIdGroup()
    {
        return idGroup;
    }

    public void setIdGroup(Long idGroup)
    {
        this.idGroup = idGroup;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Long getType()
    {
        return type;
    }

    public void setType(Long type)
    {
        this.type = type;
    }

    
    
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idGroup != null ? idGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScGroup))
        {
            return false;
        }
        ScGroup other = (ScGroup) object;
        if ((this.idGroup == null && other.idGroup != null) || (this.idGroup != null && !this.idGroup.equals(other.idGroup)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScGroup[ idGroup=" + idGroup + " ]";
    }
    
}
