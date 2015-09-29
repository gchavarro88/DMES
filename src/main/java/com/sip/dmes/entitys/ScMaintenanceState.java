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
@Table(name = "sc_maintenance_state", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScMaintenanceState.findAll", query = "SELECT s FROM ScMaintenanceState s"),
    @NamedQuery(name = "ScMaintenanceState.findByIdMaintenanceState", query = "SELECT s FROM ScMaintenanceState s WHERE s.idMaintenanceState = :idMaintenanceState"),
    @NamedQuery(name = "ScMaintenanceState.findByState", query = "SELECT s FROM ScMaintenanceState s WHERE s.state = :state")
})
public class ScMaintenanceState implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_maintenance_state")
    private Long idMaintenanceState;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "state")
    private String state;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMaintenanceState", fetch = FetchType.EAGER)
    private List<OtMaintenance> otMaintenanceList;

    public ScMaintenanceState()
    {
    }

    public ScMaintenanceState(Long idMaintenanceState)
    {
        this.idMaintenanceState = idMaintenanceState;
    }

    public ScMaintenanceState(Long idMaintenanceState, String state)
    {
        this.idMaintenanceState = idMaintenanceState;
        this.state = state;
    }

    public Long getIdMaintenanceState()
    {
        return idMaintenanceState;
    }

    public void setIdMaintenanceState(Long idMaintenanceState)
    {
        this.idMaintenanceState = idMaintenanceState;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    @XmlTransient
    public List<OtMaintenance> getOtMaintenanceList()
    {
        return otMaintenanceList;
    }

    public void setOtMaintenanceList(List<OtMaintenance> otMaintenanceList)
    {
        this.otMaintenanceList = otMaintenanceList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idMaintenanceState != null ? idMaintenanceState.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScMaintenanceState))
        {
            return false;
        }
        ScMaintenanceState other = (ScMaintenanceState) object;
        if ((this.idMaintenanceState == null && other.idMaintenanceState != null) || (this.idMaintenanceState != null && !this.idMaintenanceState.equals(other.idMaintenanceState)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScMaintenanceState[ idMaintenanceState=" + idMaintenanceState + " ]";
    }
    
}
