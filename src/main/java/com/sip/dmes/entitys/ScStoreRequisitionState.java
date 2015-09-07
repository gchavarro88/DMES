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
@Table(schema = "dmes", name = "sc_store_requisition_state")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScStoreRequisitionState.findAll", query = "SELECT s FROM ScStoreRequisitionState s"),
    @NamedQuery(name = "ScStoreRequisitionState.findByIdState", query = "SELECT s FROM ScStoreRequisitionState s WHERE s.idState = :idState"),
    @NamedQuery(name = "ScStoreRequisitionState.findByDescription", query = "SELECT s FROM ScStoreRequisitionState s WHERE s.description = :description")
})
public class ScStoreRequisitionState implements Serializable
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idState", fetch = FetchType.EAGER)
    private List<ScStoreRequisition> scStoreRequisitionList;

    public ScStoreRequisitionState()
    {
    }

    public ScStoreRequisitionState(Long idState)
    {
        this.idState = idState;
    }

    public ScStoreRequisitionState(Long idState, String description)
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

    @XmlTransient
    public List<ScStoreRequisition> getScStoreRequisitionList()
    {
        return scStoreRequisitionList;
    }

    public void setScStoreRequisitionList(List<ScStoreRequisition> scStoreRequisitionList)
    {
        this.scStoreRequisitionList = scStoreRequisitionList;
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
        if (!(object instanceof ScStoreRequisitionState))
        {
            return false;
        }
        ScStoreRequisitionState other = (ScStoreRequisitionState) object;
        if ((this.idState == null && other.idState != null) || (this.idState != null && !this.idState.equals(other.idState)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScStoreRequisitionState[ idState=" + idState + " ]";
    }
    
}
