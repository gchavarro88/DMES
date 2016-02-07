/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author guschaor
 */
@Entity
@Table(name = "sc_process_employee", schema = "dmes")
@XmlRootElement
@NamedQueries({
                @NamedQuery(name = "ScProcessEmployee.findAll", query = "SELECT s FROM ScProcessEmployee s")
            })
public class ScProcessEmployee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscprocessemployee")
    @SequenceGenerator(name = "dmes.sqscprocessemployee", sequenceName = "dmes.sqscprocessemployee", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_process_employee")
    private Long idProcessEmployee;
    
    @Basic(optional = false)
    @Size(min = 1, max = 200)
    @Column(name = "labor_description")
    private String laborDescription;
    
    @Basic(optional = false)
    @Column(name = "time_use")
    @NotNull
    private long timeUse;
    
    @Basic(optional = false)
    @Column(name = "other_expenses")
    private double otherExpenses;
    
    @Basic(optional = false)
    @Column(name = "description_other_expenses")
    private String descriptionOtherExpenses;
    
    @Basic(optional = false)
    @Column(name = "total_value_employee")
    private double totalValueEmployee;
    
    @JoinColumn(name = "id_employee", referencedColumnName = "id_employee")
    @ManyToOne(optional = false)
    private ScEmployee employee;
    
    @JoinColumn(name = "id_process", referencedColumnName = "id_process_product")
    @ManyToOne(optional = false)
    private ScProcessProduct processProduct;

    public ScProcessEmployee()
    {
    }

    public ScProcessEmployee(Long idProcessEmployee)
    {
        this.idProcessEmployee = idProcessEmployee;
    }
    

    public Long getIdProcessEmployee()
    {
        return idProcessEmployee;
    }

    public void setIdProcessEmployee(Long idProcessEmployee)
    {
        this.idProcessEmployee = idProcessEmployee;
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

    public double getOtherExpenses()
    {
        return otherExpenses;
    }

    public void setOtherExpenses(double otherExpenses)
    {
        this.otherExpenses = otherExpenses;
    }

    public double getTotalValueEmployee()
    {
        return totalValueEmployee;
    }

    public void setTotalValueEmployee(double totalValueEmployee)
    {
        this.totalValueEmployee = totalValueEmployee;
    }

    public ScEmployee getEmployee()
    {
        return employee;
    }

    public void setEmployee(ScEmployee employee)
    {
        this.employee = employee;
    }

    public ScProcessProduct getProcessProduct()
    {
        return processProduct;
    }

    public void setProcessProduct(ScProcessProduct processProduct)
    {
        this.processProduct = processProduct;
    }

    public String getDescriptionOtherExpenses()
    {
        return descriptionOtherExpenses;
    }

    public void setDescriptionOtherExpenses(String descriptionOtherExpenses)
    {
        this.descriptionOtherExpenses = descriptionOtherExpenses;
    }


    
    
    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.idProcessEmployee);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final ScProcessEmployee other = (ScProcessEmployee) obj;
        if (!Objects.equals(this.idProcessEmployee, other.idProcessEmployee))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ScProcessEmployee{" + "idProcessEmployee=" + idProcessEmployee + ", laborDescription=" + laborDescription + ", timeUse=" + timeUse + ", otherExpenses=" + otherExpenses + ", totalValueEmployee=" + totalValueEmployee + ", employee=" + employee + ", processProduct=" + processProduct + '}';
    }
    
    
    
    
}
