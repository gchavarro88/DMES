/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author carlos guzman
 */
@Entity
@Table(name = "sc_machine", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScMachine.findAll", query = "SELECT s FROM ScMachine s")
    
})
public class ScMachine implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscmachine")
    @SequenceGenerator(name = "dmes.sqscmachine", sequenceName = "dmes.sqscmachine", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_machine")
    private Long idMachine;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hour_value")
    private Long hourValue;
    
    
    
    public ScMachine()
    {
    }

    public ScMachine(Long idMachine)
    {
        this.idMachine = idMachine;
    }

    public ScMachine(Long idMachine, String name, Long hourValue)
    {
        this.idMachine = idMachine;
        this.name = name;
        this.hourValue = hourValue;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.idMachine);
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
        final ScMachine other = (ScMachine) obj;
        if (!Objects.equals(this.idMachine, other.idMachine))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return idMachine.toString()+","+name;
    }

    
    
    public Long getIdMachine()
    {
        return idMachine;
    }

    public void setIdMachine(Long idMachine)
    {
        this.idMachine = idMachine;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Long getHourValue()
    {
        return hourValue;
    }

    public void setHourValue(Long hourValue)
    {
        this.hourValue = hourValue;
    }

    

}
