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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_production_state", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScProductionState.findAll", query = "SELECT s FROM ScProductionState s"),
    @NamedQuery(name = "ScProductionState.findByIdProductionState", query = "SELECT s FROM ScProductionState s WHERE s.idProductionState = :idProductionState"),
    @NamedQuery(name = "ScProductionState.findByDescription", query = "SELECT s FROM ScProductionState s WHERE s.description = :description")
})
public class ScProductionState implements Serializable
{
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_production_state")
    private Long idProductionState;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "description")
    private String description;
    

    public ScProductionState()
    {
    }

    public ScProductionState(Long idProductionState)
    {
        this.idProductionState = idProductionState;
    }

    public ScProductionState(Long idProductionState, String description)
    {
        this.idProductionState = idProductionState;
        this.description = description;
    }

    public Long getIdProductionState()
    {
        return idProductionState;
    }

    public void setIdProductionState(Long idProductionState)
    {
        this.idProductionState = idProductionState;
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
        int hash = 0;
        hash += (idProductionState != null ? idProductionState.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScProductionState))
        {
            return false;
        }
        ScProductionState other = (ScProductionState) object;
        if ((this.idProductionState == null && other.idProductionState != null) || (this.idProductionState != null && !this.idProductionState.equals(other.idProductionState)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return idProductionState+","+description;
    }

}
