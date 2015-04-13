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
@Table(name = "sc_procces_product", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScProcessProduct.findAll", query = "SELECT s FROM ScProcessProduct s ORDER BY s.name"),
    @NamedQuery(name = "ScProcessProduct.findById", query = "SELECT s FROM ScProcessProduct s WHERE s.idProcessProduct = :idProcessProduct ORDER BY s.name")
    
})
public class ScProcessProduct implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscprocessproduct")
    @SequenceGenerator(name = "dmes.sqscprocessproduct", sequenceName = "dmes.sqscprocessproduct", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_process_product")
    private Long idProcessProduct;
    
    @JoinColumn(name = "id_process_type", referencedColumnName = "id_process_type")
    @ManyToOne(optional = false)
    private ScProcessType processType;
    
    @JoinColumn(name = "id_product_formulation", referencedColumnName = "id_product_formulation")
    @ManyToOne(optional = false)
    private ScProductFormulation productFormulation;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    private  String name;
    
    @Basic(optional = false)
    @Column(name = "description")
    private  String description;
    
    @Basic(optional = false)
    @Column(name = "total_time_machine")
    private long totalTimeMachine;
    
    @Basic(optional = false)
    @Column(name = "total_time_input")
    private long totalTimeInput;
    
    @Basic(optional = false)
    @Column(name = "total_time_employee")
    private long totalTimeEmployee;
    
    @Basic(optional = false)
    @Column(name = "total_time_process")
    private long totalTimeProcess;
            
    @Basic(optional = false)
    @Column(name = "total_value_machine")
    private double totalValueMachine; 
    
    @Basic(optional = false)
    @Column(name = "total_value_input")
    private double totalValueInput;
    
    @Basic(optional = false)
    @Column(name = "total_value_employee")
    private double totalValueEmployee;
    
    @Basic(optional = false)
    @Column(name = "total_value_process")
    private double totalValueProcess;
    
    

    public ScProcessProduct()
    {
    }

    public ScProcessProduct(Long idProcessProduct)
    {
        this.idProcessProduct = idProcessProduct;
    }

    public ScProcessProduct(Long idProcessProduct, ScProcessType processType, String name)
    {
        this.idProcessProduct = idProcessProduct;
        this.processType = processType;
        this.name = name;
    }

    public Long getIdProcessProduct()
    {
        return idProcessProduct;
    }

    public void setIdProcessProduct(Long idProcessProduct)
    {
        this.idProcessProduct = idProcessProduct;
    }

    public ScProcessType getProcessType()
    {
        return processType;
    }

    public void setProcessType(ScProcessType processType)
    {
        this.processType = processType;
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

    public long getTotalTimeMachine()
    {
        return totalTimeMachine;
    }

    public void setTotalTimeMachine(long totalTimeMachine)
    {
        this.totalTimeMachine = totalTimeMachine;
    }

    public long getTotalTimeInput()
    {
        return totalTimeInput;
    }

    public void setTotalTimeInput(long totalTimeInput)
    {
        this.totalTimeInput = totalTimeInput;
    }

    
    public long getTotalTimeEmployee()
    {
        return totalTimeEmployee;
    }

    public void setTotalTimeEmployee(long totalTimeEmployee)
    {
        this.totalTimeEmployee = totalTimeEmployee;
    }

    public long getTotalTimeProcess()
    {
        return totalTimeProcess;
    }

    public void setTotalTimeProcess(long totalTimeProcess)
    {
        this.totalTimeProcess = totalTimeProcess;
    }

    public double getTotalValueMachine()
    {
        return totalValueMachine;
    }

    public void setTotalValueMachine(double totalValueMachine)
    {
        this.totalValueMachine = totalValueMachine;
    }

    public double getTotalValueInput()
    {
        return totalValueInput;
    }

    public void setTotalValueInput(double totalValueInput)
    {
        this.totalValueInput = totalValueInput;
    }

    public double getTotalValueEmployee()
    {
        return totalValueEmployee;
    }

    public void setTotalValueEmployee(double totalValueEmployee)
    {
        this.totalValueEmployee = totalValueEmployee;
    }

    public double getTotalValueProcess()
    {
        return totalValueProcess;
    }

    public void setTotalValueProcess(double totalValueProcess)
    {
        this.totalValueProcess = totalValueProcess;
    }

    public ScProductFormulation getProductFormulation()
    {
        return productFormulation;
    }

    public void setProductFormulation(ScProductFormulation productFormulation)
    {
        this.productFormulation = productFormulation;
    }

    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.idProcessProduct);
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
        final ScProcessProduct other = (ScProcessProduct) obj;
        if (!Objects.equals(this.idProcessProduct, other.idProcessProduct))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ScProcessProduct{" + "idProcessProduct=" + idProcessProduct + ", processType=" + processType + ", name=" + name + ", description=" + description + ", totalTimeMachine=" + totalTimeMachine + ", totalTimeInput=" + totalTimeInput + ", totalTimeEmployee=" + totalTimeEmployee + ", totalTimeProcess=" + totalTimeProcess + ", totalValueMachine=" + totalValueMachine + ", totalValueInput=" + totalValueInput + ", totalValueEmployee=" + totalValueEmployee + ", totalValueProcess=" + totalValueProcess + '}';
    }

    

}
