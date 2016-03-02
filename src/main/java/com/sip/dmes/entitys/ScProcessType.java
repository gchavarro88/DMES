/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
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
@Table(name = "sc_process_type", schema = "dmes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScProcessType.findAll", query = "SELECT s FROM ScProcessType s ORDER BY s.type"),
    @NamedQuery(name = "ScProcessType.findByIdPriority", query = "SELECT s FROM ScProcessType s WHERE s.idProcessType = :idProcessType"),
    @NamedQuery(name = "ScProcessType.findByName", query = "SELECT s FROM ScProcessType s WHERE s.type = :type"),
    @NamedQuery(name = "ScProcessType.findByDescription", query = "SELECT s FROM ScProcessType s WHERE s.description = :description")})

public class ScProcessType implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscprocesstype")
    @SequenceGenerator(name = "dmes.sqscprocesstype", sequenceName = "dmes.sqscprocesstype", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_process_type")
    private Long idProcessType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "type")
    private String type;
    @Size(max = 200)
    @Column(name = "description")
    private String description;

    public ScProcessType() 
    {
    }

    public ScProcessType(Long idProcessType) {
        this.idProcessType = idProcessType;
    }

    public ScProcessType(Long idProcessType, String name) {
        this.idProcessType = idProcessType;
        this.type = name;
    }

    
    public Long getIdProcessType()
    {
        return idProcessType;
    }

    public void setIdProcessType(Long idProcessType)
    {
        this.idProcessType = idProcessType;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.idProcessType);
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
        final ScProcessType other = (ScProcessType) obj;
        if (!Objects.equals(this.idProcessType, other.idProcessType))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return idProcessType.toString();
    }

    
    
}
