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
@Table(name = "sc_process_input", schema = "dmes")
@XmlRootElement
@NamedQueries({
                @NamedQuery(name = "ScProcessInput.findAll", query = "SELECT s FROM ScProcessInput s")
            })
public class ScProcessInput implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscprocessinput")
    @SequenceGenerator(name = "dmes.sqscprocessinput", sequenceName = "dmes.sqscprocessinput", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_process_input")
    private Long idProcessInput;
    
    @Basic(optional = false)
    @Column(name = "amount_distribution")
    @NotNull
    private long amountDistribution;
    
    @Basic(optional = false)
    @Column(name = "percentage_residue")
    @NotNull
    private double percentageResidue;
    
    @Basic(optional = false)
    @Column(name = "total_value_input")
    @NotNull
    private double totalValueInput;
    
    @JoinColumn(name = "id_input", referencedColumnName = "id_input")
    @ManyToOne(optional = false)
    private ScInput input;
    
    @JoinColumn(name = "id_process", referencedColumnName = "id_process_product")
    @ManyToOne(optional = false)
    private ScProcessProduct processProduct;

    public ScProcessInput()
    {
    }

    public ScProcessInput(Long idProcessInput)
    {
        this.idProcessInput = idProcessInput;
    }

    public Long getIdProcessInput()
    {
        return idProcessInput;
    }

    public void setIdProcessInput(Long idProcessInput)
    {
        this.idProcessInput = idProcessInput;
    }

    public long getAmountDistribution()
    {
        return amountDistribution;
    }

    public void setAmountDistribution(long amountDistribution)
    {
        this.amountDistribution = amountDistribution;
    }

    public double getPercentageResidue()
    {
        return percentageResidue;
    }

    public void setPercentageResidue(double percentageResidue)
    {
        this.percentageResidue = percentageResidue;
    }

    public double getTotalValueInput()
    {
        return totalValueInput;
    }

    public void setTotalValueInput(double totalValueInput)
    {
        this.totalValueInput = totalValueInput;
    }

    public ScInput getInput()
    {
        return input;
    }

    public void setInput(ScInput input)
    {
        this.input = input;
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
        hash = 37 * hash + Objects.hashCode(this.idProcessInput);
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
        final ScProcessInput other = (ScProcessInput) obj;
        if (!Objects.equals(this.idProcessInput, other.idProcessInput))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ScProcessInput{" + "idProcessInput=" + idProcessInput + ", amountDistribution=" 
                + amountDistribution + ", percentageResidue=" + percentageResidue + ", totalValueInput=" 
                + totalValueInput + ", input=" + input + ", processProduct=" + processProduct + '}';
    }

    
    
    
    
    
}
