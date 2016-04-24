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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_process_input_order", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScProcessInputOrder.findAll", query = "SELECT s FROM ScProcessInputOrder s"),
    @NamedQuery(name = "ScProcessInputOrder.findByIdProcessInputOrder", query = "SELECT s FROM ScProcessInputOrder s WHERE s.idProcessInputOrder = :idProcessInputOrder"),
    @NamedQuery(name = "ScProcessInputOrder.findByAmountDistribution", query = "SELECT s FROM ScProcessInputOrder s WHERE s.amountDistribution = :amountDistribution"),
    @NamedQuery(name = "ScProcessInputOrder.findByPercentageResidue", query = "SELECT s FROM ScProcessInputOrder s WHERE s.percentageResidue = :percentageResidue"),
    @NamedQuery(name = "ScProcessInputOrder.findByTotalValueInput", query = "SELECT s FROM ScProcessInputOrder s WHERE s.totalValueInput = :totalValueInput")
})
public class ScProcessInputOrder implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscprocessinputorder")
    @SequenceGenerator(name = "dmes.sqscprocessinputorder", sequenceName = "dmes.sqscprocessinputorder", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_process_input_order")
    private Long idProcessInputOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount_distribution")
    private long amountDistribution;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "percentage_residue")
    private Double percentageResidue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_value_input")
    private Double totalValueInput;
    @JoinColumn(name = "id_process_order", referencedColumnName = "id_process_product_order")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScProccesProductOrder idProcessOrder;
    @JoinColumn(name = "id_input", referencedColumnName = "id_input")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScInput idInput;

    public ScProcessInputOrder()
    {
    }

    public ScProcessInputOrder(Long idProcessInputOrder)
    {
        this.idProcessInputOrder = idProcessInputOrder;
    }

    public ScProcessInputOrder(Long idProcessInputOrder, long amountDistribution, Double percentageResidue, Double totalValueInput)
    {
        this.idProcessInputOrder = idProcessInputOrder;
        this.amountDistribution = amountDistribution;
        this.percentageResidue = percentageResidue;
        this.totalValueInput = totalValueInput;
    }

    public Long getIdProcessInputOrder()
    {
        return idProcessInputOrder;
    }

    public void setIdProcessInputOrder(Long idProcessInputOrder)
    {
        this.idProcessInputOrder = idProcessInputOrder;
    }

    public long getAmountDistribution()
    {
        return amountDistribution;
    }

    public void setAmountDistribution(long amountDistribution)
    {
        this.amountDistribution = amountDistribution;
    }

    public Double getPercentageResidue()
    {
        return percentageResidue;
    }

    public void setPercentageResidue(Double percentageResidue)
    {
        this.percentageResidue = percentageResidue;
    }

    public Double getTotalValueInput()
    {
        return totalValueInput;
    }

    public void setTotalValueInput(Double totalValueInput)
    {
        this.totalValueInput = totalValueInput;
    }

    public ScProccesProductOrder getIdProcessOrder()
    {
        return idProcessOrder;
    }

    public void setIdProcessOrder(ScProccesProductOrder idProcessOrder)
    {
        this.idProcessOrder = idProcessOrder;
    }

    public ScInput getIdInput()
    {
        return idInput;
    }

    public void setIdInput(ScInput idInput)
    {
        this.idInput = idInput;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idProcessInputOrder != null ? idProcessInputOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScProcessInputOrder))
        {
            return false;
        }
        ScProcessInputOrder other = (ScProcessInputOrder) object;
        if ((this.idProcessInputOrder == null && other.idProcessInputOrder != null) || (this.idProcessInputOrder != null && !this.idProcessInputOrder.equals(other.idProcessInputOrder)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScProcessInputOrder[ idProcessInputOrder=" + idProcessInputOrder + " ]";
    }
    
}
