/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_time", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScTime.findAll", query = "SELECT s FROM ScTime s ORDER BY s.idTime")
    
})
public class ScTime implements Serializable, Cloneable
{
    @Column(name = "minutes")
    private Integer minutes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTime", fetch = FetchType.EAGER)
    private List<ScMachinePart> scMachinePartList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_time")
    private Long idTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "acronym")
    private String acronym;
    

    public ScTime()
    {
    }

    public ScTime(Long idTime)
    {
        this.idTime = idTime;
    }

    public ScTime(Long idTime, String acronym, Integer minutes)
    {
        this.idTime = idTime;
        this.acronym = acronym;
        this.minutes = minutes;
    }

    public Long getIdTime()
    {
        return idTime;
    }

    public void setIdTime(Long idTime)
    {
        this.idTime = idTime;
    }

    public String getAcronym()
    {
        return acronym;
    }

    public void setAcronym(String acronym)
    {
        this.acronym = acronym;
    }


    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idTime);
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
        final ScTime other = (ScTime) obj;
        if (!Objects.equals(this.idTime, other.idTime))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return idTime.toString();
    }

    
    
    @Override
    public Object clone() throws CloneNotSupportedException 
    {
        return super.clone();
    }

    public Integer getMinutes()
    {
        return minutes;
    }

    public void setMinutes(Integer minutes)
    {
        this.minutes = minutes;
    }

    @XmlTransient
    public List<ScMachinePart> getScMachinePartList()
    {
        return scMachinePartList;
    }

    public void setScMachinePartList(List<ScMachinePart> scMachinePartList)
    {
        this.scMachinePartList = scMachinePartList;
    }
    
}
