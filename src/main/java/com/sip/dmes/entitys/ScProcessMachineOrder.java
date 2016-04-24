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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_process_machine_order", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScProcessMachineOrder.findAll", query = "SELECT s FROM ScProcessMachineOrder s"),
    @NamedQuery(name = "ScProcessMachineOrder.findByIdProcessMachineOrder", query = "SELECT s FROM ScProcessMachineOrder s WHERE s.idProcessMachineOrder = :idProcessMachineOrder"),
    @NamedQuery(name = "ScProcessMachineOrder.findByTimeUse", query = "SELECT s FROM ScProcessMachineOrder s WHERE s.timeUse = :timeUse"),
    @NamedQuery(name = "ScProcessMachineOrder.findByOtherExpenses", query = "SELECT s FROM ScProcessMachineOrder s WHERE s.otherExpenses = :otherExpenses"),
    @NamedQuery(name = "ScProcessMachineOrder.findByTotalValueMachine", query = "SELECT s FROM ScProcessMachineOrder s WHERE s.totalValueMachine = :totalValueMachine"),
    @NamedQuery(name = "ScProcessMachineOrder.findByDescriptionOtherExpenses", query = "SELECT s FROM ScProcessMachineOrder s WHERE s.descriptionOtherExpenses = :descriptionOtherExpenses")
})
public class ScProcessMachineOrder implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscprocessmachineorder")
    @SequenceGenerator(name = "dmes.sqscprocessmachineorder", sequenceName = "dmes.sqscprocessmachineorder", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_process_machine_order")
    private Long idProcessMachineOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_use")
    private long timeUse;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "other_expenses")
    private Double otherExpenses;
    @Column(name = "total_value_machine")
    private Double totalValueMachine;
    @Size(max = 2000)
    @Column(name = "description_other_expenses")
    private String descriptionOtherExpenses;
    @JoinColumn(name = "id_process_order", referencedColumnName = "id_process_product_order")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScProccesProductOrder idProcessOrder;
    @JoinColumn(name = "id_machine", referencedColumnName = "id_machine")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScMachine idMachine;

    public ScProcessMachineOrder()
    {
    }

    public ScProcessMachineOrder(Long idProcessMachineOrder)
    {
        this.idProcessMachineOrder = idProcessMachineOrder;
    }

    public ScProcessMachineOrder(Long idProcessMachineOrder, long timeUse)
    {
        this.idProcessMachineOrder = idProcessMachineOrder;
        this.timeUse = timeUse;
    }

    public Long getIdProcessMachineOrder()
    {
        return idProcessMachineOrder;
    }

    public void setIdProcessMachineOrder(Long idProcessMachineOrder)
    {
        this.idProcessMachineOrder = idProcessMachineOrder;
    }

    public long getTimeUse()
    {
        return timeUse;
    }

    public void setTimeUse(long timeUse)
    {
        this.timeUse = timeUse;
    }

    public Double getOtherExpenses()
    {
        return otherExpenses;
    }

    public void setOtherExpenses(Double otherExpenses)
    {
        this.otherExpenses = otherExpenses;
    }

    public Double getTotalValueMachine()
    {
        return totalValueMachine;
    }

    public void setTotalValueMachine(Double totalValueMachine)
    {
        this.totalValueMachine = totalValueMachine;
    }

    public String getDescriptionOtherExpenses()
    {
        return descriptionOtherExpenses;
    }

    public void setDescriptionOtherExpenses(String descriptionOtherExpenses)
    {
        this.descriptionOtherExpenses = descriptionOtherExpenses;
    }

    public ScProccesProductOrder getIdProcessOrder()
    {
        return idProcessOrder;
    }

    public void setIdProcessOrder(ScProccesProductOrder idProcessOrder)
    {
        this.idProcessOrder = idProcessOrder;
    }

    public ScMachine getIdMachine()
    {
        return idMachine;
    }

    public void setIdMachine(ScMachine idMachine)
    {
        this.idMachine = idMachine;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idProcessMachineOrder != null ? idProcessMachineOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScProcessMachineOrder))
        {
            return false;
        }
        ScProcessMachineOrder other = (ScProcessMachineOrder) object;
        if ((this.idProcessMachineOrder == null && other.idProcessMachineOrder != null) || (this.idProcessMachineOrder != null && !this.idProcessMachineOrder.equals(other.idProcessMachineOrder)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScProcessMachineOrder[ idProcessMachineOrder=" + idProcessMachineOrder + " ]";
    }
    
}
