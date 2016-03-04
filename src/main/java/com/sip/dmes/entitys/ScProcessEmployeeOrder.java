/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "sc_process_employee_order", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScProcessEmployeeOrder.findAll", query = "SELECT s FROM ScProcessEmployeeOrder s"),
    @NamedQuery(name = "ScProcessEmployeeOrder.findByIdProcessEmployeeOrder", query = "SELECT s FROM ScProcessEmployeeOrder s WHERE s.idProcessEmployeeOrder = :idProcessEmployeeOrder"),
    @NamedQuery(name = "ScProcessEmployeeOrder.findByLaborDescription", query = "SELECT s FROM ScProcessEmployeeOrder s WHERE s.laborDescription = :laborDescription"),
    @NamedQuery(name = "ScProcessEmployeeOrder.findByTimeUse", query = "SELECT s FROM ScProcessEmployeeOrder s WHERE s.timeUse = :timeUse"),
    @NamedQuery(name = "ScProcessEmployeeOrder.findByOtherExpenses", query = "SELECT s FROM ScProcessEmployeeOrder s WHERE s.otherExpenses = :otherExpenses"),
    @NamedQuery(name = "ScProcessEmployeeOrder.findByTotalValueEmployee", query = "SELECT s FROM ScProcessEmployeeOrder s WHERE s.totalValueEmployee = :totalValueEmployee"),
    @NamedQuery(name = "ScProcessEmployeeOrder.findByDescriptionOtherExpenses", query = "SELECT s FROM ScProcessEmployeeOrder s WHERE s.descriptionOtherExpenses = :descriptionOtherExpenses")
})
public class ScProcessEmployeeOrder implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(generator = "dmes.sqscprocessemployeeorder")
    @SequenceGenerator(name = "dmes.sqscprocessemployeeorder", sequenceName = "dmes.sqscprocessemployeeorder", allocationSize = 1)
    @NotNull
    @Column(name = "id_process_employee_order")
    private Long idProcessEmployeeOrder;
    @Size(max = 200)
    @Column(name = "labor_description")
    private String laborDescription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_use")
    private long timeUse;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "other_expenses")
    private Double otherExpenses;
    @Column(name = "total_value_employee")
    private Double totalValueEmployee;
    @Size(max = 2000)
    @Column(name = "description_other_expenses")
    private String descriptionOtherExpenses;
    @JoinColumn(name = "id_process_order", referencedColumnName = "id_process_product_order")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScProccesProductOrder idProcessOrder;
    @JoinColumn(name = "id_employee", referencedColumnName = "id_employee")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScEmployee idEmployee;

    public ScProcessEmployeeOrder()
    {
    }

    public ScProcessEmployeeOrder(Long idProcessEmployeeOrder)
    {
        this.idProcessEmployeeOrder = idProcessEmployeeOrder;
    }

    public ScProcessEmployeeOrder(Long idProcessEmployeeOrder, long timeUse)
    {
        this.idProcessEmployeeOrder = idProcessEmployeeOrder;
        this.timeUse = timeUse;
    }

    public Long getIdProcessEmployeeOrder()
    {
        return idProcessEmployeeOrder;
    }

    public void setIdProcessEmployeeOrder(Long idProcessEmployeeOrder)
    {
        this.idProcessEmployeeOrder = idProcessEmployeeOrder;
    }

    public String getLaborDescription()
    {
        return laborDescription;
    }

    public void setLaborDescription(String laborDescription)
    {
        this.laborDescription = laborDescription;
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

    public Double getTotalValueEmployee()
    {
        return totalValueEmployee;
    }

    public void setTotalValueEmployee(Double totalValueEmployee)
    {
        this.totalValueEmployee = totalValueEmployee;
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

    public ScEmployee getIdEmployee()
    {
        return idEmployee;
    }

    public void setIdEmployee(ScEmployee idEmployee)
    {
        this.idEmployee = idEmployee;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idProcessEmployeeOrder != null ? idProcessEmployeeOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScProcessEmployeeOrder))
        {
            return false;
        }
        ScProcessEmployeeOrder other = (ScProcessEmployeeOrder) object;
        if ((this.idProcessEmployeeOrder == null && other.idProcessEmployeeOrder != null) || (this.idProcessEmployeeOrder != null && !this.idProcessEmployeeOrder.equals(other.idProcessEmployeeOrder)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScProcessEmployeeOrder[ idProcessEmployeeOrder=" + idProcessEmployeeOrder + " ]";
    }
    
}
