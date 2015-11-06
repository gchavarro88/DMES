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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(schema = "dmes", name = "sc_store_requisition")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScStoreRequisition.findAll", query = "SELECT s FROM ScStoreRequisition s"),
    @NamedQuery(name = "ScStoreRequisition.findByIdStoreRequisition", query = "SELECT s FROM ScStoreRequisition s WHERE s.idStoreRequisition = :idStoreRequisition"),
    @NamedQuery(name = "ScStoreRequisition.findByRequisitionType", query = "SELECT s FROM ScStoreRequisition s WHERE s.requisitionType = :requisitionType"),
    @NamedQuery(name = "ScStoreRequisition.findByOrderClass", query = "SELECT s FROM ScStoreRequisition s WHERE s.orderClass = :orderClass"),
    @NamedQuery(name = "ScStoreRequisition.findByCreationDate", query = "SELECT s FROM ScStoreRequisition s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScStoreRequisition.findByReasonCancellation", query = "SELECT s FROM ScStoreRequisition s WHERE s.reasonCancellation = :reasonCancellation"),
    @NamedQuery(name = "ScStoreRequisition.findByAmountItems", query = "SELECT s FROM ScStoreRequisition s WHERE s.amountItems = :amountItems")
})
public class ScStoreRequisition implements Serializable
{
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStoreRequisition", fetch = FetchType.EAGER)
    private List<ScStoreRequisitionItem> scStoreRequisitionItemList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_store_requisition")
    private Long idStoreRequisition;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "requisition_type")
    private String requisitionType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "order_class")
    private String orderClass;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Size(max = 200)
    @Column(name = "reason_cancellation")
    private String reasonCancellation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount_items")
    private long amountItems;
    @JoinColumn(name = "id_state", referencedColumnName = "id_state")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScStoreRequisitionState idState;

    public ScStoreRequisition()
    {
    }

    public ScStoreRequisition(Long idStoreRequisition)
    {
        this.idStoreRequisition = idStoreRequisition;
    }

    public ScStoreRequisition(Long idStoreRequisition, String requisitionType, String orderClass, Date creationDate, long amountItems)
    {
        this.idStoreRequisition = idStoreRequisition;
        this.requisitionType = requisitionType;
        this.orderClass = orderClass;
        this.creationDate = creationDate;
        this.amountItems = amountItems;
    }

    public Long getIdStoreRequisition()
    {
        return idStoreRequisition;
    }

    public void setIdStoreRequisition(Long idStoreRequisition)
    {
        this.idStoreRequisition = idStoreRequisition;
    }

    public String getRequisitionType()
    {
        return requisitionType;
    }

    public void setRequisitionType(String requisitionType)
    {
        this.requisitionType = requisitionType;
    }

    public String getOrderClass()
    {
        return orderClass;
    }

    public void setOrderClass(String orderClass)
    {
        this.orderClass = orderClass;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public String getReasonCancellation()
    {
        return reasonCancellation;
    }

    public void setReasonCancellation(String reasonCancellation)
    {
        this.reasonCancellation = reasonCancellation;
    }

    public long getAmountItems()
    {
        return amountItems;
    }

    public void setAmountItems(long amountItems)
    {
        this.amountItems = amountItems;
    }

    public ScStoreRequisitionState getIdState()
    {
        return idState;
    }

    public void setIdState(ScStoreRequisitionState idState)
    {
        this.idState = idState;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idStoreRequisition != null ? idStoreRequisition.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScStoreRequisition))
        {
            return false;
        }
        ScStoreRequisition other = (ScStoreRequisition) object;
        if ((this.idStoreRequisition == null && other.idStoreRequisition != null) || (this.idStoreRequisition != null && !this.idStoreRequisition.equals(other.idStoreRequisition)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScStoreRequisition[ idStoreRequisition=" + idStoreRequisition + " ]";
    }

    @XmlTransient
    public List<ScStoreRequisitionItem> getScStoreRequisitionItemList()
    {
        return scStoreRequisitionItemList;
    }

    public void setScStoreRequisitionItemList(List<ScStoreRequisitionItem> scStoreRequisitionItemList)
    {
        this.scStoreRequisitionItemList = scStoreRequisitionItemList;
    }
    
}
