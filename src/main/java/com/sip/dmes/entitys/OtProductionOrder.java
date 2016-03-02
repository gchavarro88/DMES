/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "ot_production_order", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "OtProductionOrder.findAll", query = "SELECT o FROM OtProductionOrder o"),
    @NamedQuery(name = "OtProductionOrder.findByIdProductionOrder", query = "SELECT o FROM OtProductionOrder o WHERE o.idProductionOrder = :idProductionOrder"),
    @NamedQuery(name = "OtProductionOrder.findByCreationDate", query = "SELECT o FROM OtProductionOrder o WHERE o.creationDate >= :creationDate"),
    @NamedQuery(name = "OtProductionOrder.findByStartDate", query = "SELECT o FROM OtProductionOrder o WHERE o.startDate = :startDate"),
    @NamedQuery(name = "OtProductionOrder.findByEndDate", query = "SELECT o FROM OtProductionOrder o WHERE o.endDate = :endDate")
})
public class OtProductionOrder implements Serializable
{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqotproductionorder")
    @SequenceGenerator(name = "dmes.sqotproductionorder", sequenceName = "dmes.sqotproductionorder", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_production_order")
    private Long idProductionOrder;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @JoinColumn(name = "id_production_state", referencedColumnName = "id_production_state")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScProductionState idProductionState;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrder", fetch = FetchType.EAGER)
    private List<ScProductOrder> scProductOrderList;
    
    public OtProductionOrder()
    {
    }

    public OtProductionOrder(Long idProductionOrder)
    {
        this.idProductionOrder = idProductionOrder;
    }

    public OtProductionOrder(Long idProductionOrder, Date creationDate)
    {
        this.idProductionOrder = idProductionOrder;
        this.creationDate = creationDate;
    }

    public Long getIdProductionOrder()
    {
        return idProductionOrder;
    }

    public void setIdProductionOrder(Long idProductionOrder)
    {
        this.idProductionOrder = idProductionOrder;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public ScProductionState getIdProductionState()
    {
        return idProductionState;
    }

    public void setIdProductionState(ScProductionState idProductionState)
    {
        this.idProductionState = idProductionState;
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

    
    
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idProductionOrder != null ? idProductionOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OtProductionOrder))
        {
            return false;
        }
        OtProductionOrder other = (OtProductionOrder) object;
        if ((this.idProductionOrder == null && other.idProductionOrder != null) || (this.idProductionOrder != null && !this.idProductionOrder.equals(other.idProductionOrder)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.OtProductionOrder[ idProductionOrder=" + idProductionOrder + " ]";
    }

    @XmlTransient
    public List<ScProductOrder> getScProductOrderList()
    {
        return scProductOrderList;
    }

    public void setScProductOrderList(List<ScProductOrder> scProductOrderList)
    {
        this.scProductOrderList = scProductOrderList;
    }
    
}
