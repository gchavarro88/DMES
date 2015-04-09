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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guschaor
 */
@Entity
@Table(name = "sc_process_machine", schema = "dmes")
@XmlRootElement
@NamedQueries({
                @NamedQuery(name = "ScProcessMachine.findAll", query = "SELECT s FROM ScProcessMachine s")
            })
public class ScProcessMachine implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscprocessmachine")
    @SequenceGenerator(name = "dmes.sqscprocessmachine", sequenceName = "dmes.sqscprocessmachine", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_process_machine")
    private Long idProcessMachine;
    
    @Basic(optional = false)
    @Column(name = "time_use")
    @NotNull
    private long timeUse;
    
    @Basic(optional = false)
    @Column(name = "other_expenses")
    private double otherExpenses;
    
    @Basic(optional = false)
    @Column(name = "total_value_employee")
    private double totalValueEmployee;
    
    @JoinColumn(name = "id_machine", referencedColumnName = "id_machine")
    @ManyToOne(optional = false)
    private ScMachine machine;
    
    @JoinColumn(name = "id_process", referencedColumnName = "id_process_product")
    @ManyToOne(optional = false)
    private ScProcessProduct processProduct;

    public ScProcessMachine()
    {
    }

    public Long getIdProcessMachine()
    {
        return idProcessMachine;
    }

    public void setIdProcessMachine(Long idProcessMachine)
    {
        this.idProcessMachine = idProcessMachine;
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

    public ScMachine getMachine()
    {
        return machine;
    }

    public void setMachine(ScMachine machine)
    {
        this.machine = machine;
    }

    public ScProcessProduct getProcessProduct()
    {
        return processProduct;
    }

    public void setProcessProduct(ScProcessProduct processProduct)
    {
        this.processProduct = processProduct;
    }

   
    
    
    
    
    

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.idProcessMachine);
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
        final ScProcessMachine other = (ScProcessMachine) obj;
        if (!Objects.equals(this.idProcessMachine, other.idProcessMachine))
        {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString()
    {
        return "ScProcessMachine{" + "idProcessMachine=" + idProcessMachine + ", timeUse=" + timeUse + ", otherExpenses=" + otherExpenses + ", totalValueEmployee=" + totalValueEmployee + ", machine=" + machine + ", processProduct=" + processProduct + '}';
    }

    

    
    
    
    
    
}
