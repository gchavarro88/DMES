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
 * @author guschaor
 */
@Entity
@Table(schema = "dmes", name = "sc_store_order_state")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScStoreOrderState.findAll", query = "SELECT s FROM ScStoreOrderState s ORDER BY s.idState"),
    @NamedQuery(name = "ScStoreOrderState.findByIdState", query = "SELECT s FROM ScStoreOrderState s WHERE s.idState = :idState"),
    @NamedQuery(name = "ScStoreOrderState.findByDescription", query = "SELECT s FROM ScStoreOrderState s WHERE s.description = :description")
})
public class ScStoreOrderState implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_state")
    private Long idState;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "description")
    private String description;

    public ScStoreOrderState()
    {
    }

    public ScStoreOrderState(Long idState)
    {
        this.idState = idState;
    }

    public ScStoreOrderState(Long idState, String description)
    {
        this.idState = idState;
        this.description = description;
    }

    public Long getIdState()
    {
        return idState;
    }

    public void setIdState(Long idState)
    {
        this.idState = idState;
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
        hash += (idState != null ? idState.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScStoreOrderState))
        {
            return false;
        }
        ScStoreOrderState other = (ScStoreOrderState) object;
        if ((this.idState == null && other.idState != null) || (this.idState != null && !this.idState.equals(other.idState)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScStoreOrderState[ idState=" + idState + " ]";
    }
    
}
