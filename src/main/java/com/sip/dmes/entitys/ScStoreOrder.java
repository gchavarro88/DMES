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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author guschaor
 */
@Entity
@Table(schema = "dmes", name = "sc_store_order")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScStoreOrder.findAll", query = "SELECT s FROM ScStoreOrder s"),
    @NamedQuery(name = "ScStoreOrder.findByState", query = "SELECT s FROM ScStoreOrder s WHERE s.idState.idState IN (:storeOrderStatus)"),
    @NamedQuery(name = "ScStoreOrder.findByStateAndOrderType", query = "SELECT s FROM ScStoreOrder s WHERE s.idState.idState IN (:storeOrderStatus) AND s.orderType = :orderType"),
    @NamedQuery(name = "ScStoreOrder.findByIdStoreOrder", query = "SELECT s FROM ScStoreOrder s WHERE s.idStoreOrder = :idStoreOrder"),
    @NamedQuery(name = "ScStoreOrder.findByOrderType", query = "SELECT s FROM ScStoreOrder s WHERE s.orderType = :orderType"),
    @NamedQuery(name = "ScStoreOrder.findByOrderClass", query = "SELECT s FROM ScStoreOrder s WHERE s.orderClass = :orderClass")
})
public class ScStoreOrder implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscstoreorder")
    @SequenceGenerator(name = "dmes.sqscstoreorder", sequenceName = "dmes.sqscstoreorder", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_store_order")
    private Long idStoreOrder;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "order_type")
    private String orderType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "required_by")
    private String requiredBy;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "order_class")
    private String orderClass;
    @Column(name = "id_order_request")
    private String idOrderRequest;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    private Date  creationDate;
    
    @Column(name = "reason_cancellation")
    private String  reasonCancellation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount_items")
    private long amountItems;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storeOrder", fetch = FetchType.EAGER)
    private List<ScStoreOrderItem> storeOrderItemList;
    
    @JoinColumn(name = "id_state", referencedColumnName = "id_state")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScStoreOrderState idState;
    
    @JoinColumn(name = "id_employee_create", referencedColumnName = "id_employee")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScEmployee employeeCreate;
    
    @JoinColumn(name = "id_employee_store", referencedColumnName = "id_employee", nullable = true)
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private ScEmployee employeeStore;

    public ScStoreOrder()
    {
    }

    public ScStoreOrder(Long idStoreOrder)
    {
        this.idStoreOrder = idStoreOrder;
    }

    public ScStoreOrder(Long idStoreOrder, String orderType, String orderClass)
    {
        this.idStoreOrder = idStoreOrder;
        this.orderType = orderType;
        this.orderClass = orderClass;
        
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

    public ScEmployee getEmployeeCreate()
    {
        return employeeCreate;
    }

    public void setEmployeeCreate(ScEmployee employeeCreate)
    {
        this.employeeCreate = employeeCreate;
    }

    public ScEmployee getEmployeeStore()
    {
        return employeeStore;
    }

    public void setEmployeeStore(ScEmployee employeeStore)
    {
        this.employeeStore = employeeStore;
    }
    
    
    public String getOrderClass()
    {
        return orderClass;
    }

    public void setOrderClass(String orderClass)
    {
        this.orderClass = orderClass;
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

    public String getRequiredBy()
    {
        return requiredBy;
    }

    public void setRequiredBy(String requiredBy)
    {
        this.requiredBy = requiredBy;
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

    public String getIdOrderRequest()
    {
        return idOrderRequest; 
    }

    public void setIdOrderRequest(String idOrderRequest)
    {
        this.idOrderRequest = idOrderRequest;
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
