/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_store_requisition_item")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScStoreRequisitionItem.findAll", query = "SELECT s FROM ScStoreRequisitionItem s"),
    @NamedQuery(name = "ScStoreRequisitionItem.findByIdItem", query = "SELECT s FROM ScStoreRequisitionItem s WHERE s.idItem = :idItem"),
    @NamedQuery(name = "ScStoreRequisitionItem.findByClassItem", query = "SELECT s FROM ScStoreRequisitionItem s WHERE s.classItem = :classItem"),
    @NamedQuery(name = "ScStoreRequisitionItem.findByAmountRequired", query = "SELECT s FROM ScStoreRequisitionItem s WHERE s.amountRequired = :amountRequired"),
    @NamedQuery(name = "ScStoreRequisitionItem.findByAmountDelivery", query = "SELECT s FROM ScStoreRequisitionItem s WHERE s.amountDelivery = :amountDelivery"),
    @NamedQuery(name = "ScStoreRequisitionItem.findByAmountStore", query = "SELECT s FROM ScStoreRequisitionItem s WHERE s.amountStore = :amountStore"),
    @NamedQuery(name = "ScStoreRequisitionItem.findByAmountPending", query = "SELECT s FROM ScStoreRequisitionItem s WHERE s.amountPending = :amountPending"),
    @NamedQuery(name = "ScStoreRequisitionItem.findByItemDescription", query = "SELECT s FROM ScStoreRequisitionItem s WHERE s.itemDescription = :itemDescription"),
    @NamedQuery(name = "ScStoreRequisitionItem.findByIdItemClass", query = "SELECT s FROM ScStoreRequisitionItem s WHERE s.idItemClass = :idItemClass"),
    @NamedQuery(name = "ScStoreRequisitionItem.findByAmountPendingHidden", query = "SELECT s FROM ScStoreRequisitionItem s WHERE s.amountPendingHidden = :amountPendingHidden")
})
public class ScStoreRequisitionItem implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_item")
    private Long idItem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "class_item")
    private String classItem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount_required")
    private long amountRequired;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount_delivery")
    private long amountDelivery;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount_store")
    private long amountStore;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount_pending")
    private long amountPending;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "item_description")
    private String itemDescription;
    @Column(name = "id_item_class")
    private Long idItemClass;
    @Column(name = "amount_pending_hidden")
    private Long amountPendingHidden;
    @JoinColumn(name = "id_store_requisition", referencedColumnName = "id_store_requisition")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScStoreRequisition idStoreRequisition;

    public ScStoreRequisitionItem()
    {
    }

    public ScStoreRequisitionItem(Long idItem)
    {
        this.idItem = idItem;
    }

    public ScStoreRequisitionItem(Long idItem, String classItem, long amountRequired, long amountDelivery, long amountStore, long amountPending, String itemDescription)
    {
        this.idItem = idItem;
        this.classItem = classItem;
        this.amountRequired = amountRequired;
        this.amountDelivery = amountDelivery;
        this.amountStore = amountStore;
        this.amountPending = amountPending;
        this.itemDescription = itemDescription;
    }

    public Long getIdItem()
    {
        return idItem;
    }

    public void setIdItem(Long idItem)
    {
        this.idItem = idItem;
    }

    public String getClassItem()
    {
        return classItem;
    }

    public void setClassItem(String classItem)
    {
        this.classItem = classItem;
    }

    public long getAmountRequired()
    {
        return amountRequired;
    }

    public void setAmountRequired(long amountRequired)
    {
        this.amountRequired = amountRequired;
    }

    public long getAmountDelivery()
    {
        return amountDelivery;
    }

    public void setAmountDelivery(long amountDelivery)
    {
        this.amountDelivery = amountDelivery;
    }

    public long getAmountStore()
    {
        return amountStore;
    }

    public void setAmountStore(long amountStore)
    {
        this.amountStore = amountStore;
    }

    public long getAmountPending()
    {
        return amountPending;
    }

    public void setAmountPending(long amountPending)
    {
        this.amountPending = amountPending;
    }

    public String getItemDescription()
    {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription)
    {
        this.itemDescription = itemDescription;
    }

    public Long getIdItemClass()
    {
        return idItemClass;
    }

    public void setIdItemClass(Long idItemClass)
    {
        this.idItemClass = idItemClass;
    }

    public Long getAmountPendingHidden()
    {
        return amountPendingHidden;
    }

    public void setAmountPendingHidden(Long amountPendingHidden)
    {
        this.amountPendingHidden = amountPendingHidden;
    }

    public ScStoreRequisition getIdStoreRequisition()
    {
        return idStoreRequisition;
    }

    public void setIdStoreRequisition(ScStoreRequisition idStoreRequisition)
    {
        this.idStoreRequisition = idStoreRequisition;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idItem != null ? idItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScStoreRequisitionItem))
        {
            return false;
        }
        ScStoreRequisitionItem other = (ScStoreRequisitionItem) object;
        if ((this.idItem == null && other.idItem != null) || (this.idItem != null && !this.idItem.equals(other.idItem)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScStoreRequisitionItem[ idItem=" + idItem + " ]";
    }
    
}
