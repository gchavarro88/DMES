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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author guschaor
 */
@Entity
@Table(name = "sc_store_order")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScStoreOrder.findAll", query = "SELECT s FROM ScStoreOrder s"),
    @NamedQuery(name = "ScStoreOrder.findByIdStoreOrder", query = "SELECT s FROM ScStoreOrder s WHERE s.idStoreOrder = :idStoreOrder"),
    @NamedQuery(name = "ScStoreOrder.findByOrderType", query = "SELECT s FROM ScStoreOrder s WHERE s.orderType = :orderType"),
    @NamedQuery(name = "ScStoreOrder.findByIdRequisition", query = "SELECT s FROM ScStoreOrder s WHERE s.idRequisition = :idRequisition"),
    @NamedQuery(name = "ScStoreOrder.findByOrderClass", query = "SELECT s FROM ScStoreOrder s WHERE s.orderClass = :orderClass"),
    @NamedQuery(name = "ScStoreOrder.findByOrderState", query = "SELECT s FROM ScStoreOrder s WHERE s.orderState = :orderState")
})
public class ScStoreOrder implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_store_order")
    private Long idStoreOrder;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "order_type")
    private String orderType;
    @Column(name = "id_requisition")
    private Long idRequisition;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "order_class")
    private String orderClass;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    private Date  creationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_state")
    private long orderState;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reason_cancellation")
    private String  reasonCancellation;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storeOrder", fetch = FetchType.EAGER)
    private List<ScStoreOrderItem> storeOrderItemList;
    
    @JoinColumn(name = "id_state", referencedColumnName = "id_state")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScStoreOrderState idState;

    public ScStoreOrder()
    {
    }

    public ScStoreOrder(Long idStoreOrder)
    {
        this.idStoreOrder = idStoreOrder;
    }

    public ScStoreOrder(Long idStoreOrder, String orderType, String orderClass, long orderState)
    {
        this.idStoreOrder = idStoreOrder;
        this.orderType = orderType;
        this.orderClass = orderClass;
        this.orderState = orderState;
    }

    public Long getIdStoreOrder()
    {
        return idStoreOrder;
    }

    public void setIdStoreOrder(Long idStoreOrder)
    {
        this.idStoreOrder = idStoreOrder;
    }

    public String getOrderType()
    {
        return orderType;
    }

    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
    }

    public Long getIdRequisition()
    {
        return idRequisition;
    }

    public void setIdRequisition(Long idRequisition)
    {
        this.idRequisition = idRequisition;
    }

    public String getOrderClass()
    {
        return orderClass;
    }

    public void setOrderClass(String orderClass)
    {
        this.orderClass = orderClass;
    }

    public long getOrderState()
    {
        return orderState;
    }

    public void setOrderState(long orderState)
    {
        this.orderState = orderState;
    }

    @XmlTransient
    public List<ScStoreOrderItem> getStoreOrderItemList()
    {
        return storeOrderItemList;
    }

    public void setStoreOrderItemList(List<ScStoreOrderItem> scStoreOrderItemList)
    {
        this.storeOrderItemList = scStoreOrderItemList;
    }

    public ScStoreOrderState getIdState()
    {
        return idState;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    
    public void setIdState(ScStoreOrderState idState)
    {
        this.idState = idState;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idStoreOrder != null ? idStoreOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScStoreOrder))
        {
            return false;
        }
        ScStoreOrder other = (ScStoreOrder) object;
        if ((this.idStoreOrder == null && other.idStoreOrder != null) || (this.idStoreOrder != null && !this.idStoreOrder.equals(other.idStoreOrder)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScStoreOrder[ idStoreOrder=" + idStoreOrder + " ]";
    }
    
}
