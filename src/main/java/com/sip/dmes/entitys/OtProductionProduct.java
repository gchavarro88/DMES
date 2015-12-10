/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "ot_production_product", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "OtProductionProduct.findAll", query = "SELECT o FROM OtProductionProduct o"),
    @NamedQuery(name = "OtProductionProduct.findByIdProductionOrder", query = "SELECT o FROM OtProductionProduct o WHERE o.idProductionProduct = :idProductionProduct"),
    @NamedQuery(name = "OtProductionProduct.findByCreationDate", query = "SELECT o FROM OtProductionProduct o WHERE o.idProductFormulation = :idProductFormulation"),
    @NamedQuery(name = "OtProductionProduct.findByStartDate", query = "SELECT o FROM OtProductionProduct o WHERE o.idProductionOrder = :idProductionOrder")
})
public class OtProductionProduct implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqotproductionproduct")
    @SequenceGenerator(name = "dmes.sqotproductionproduct", sequenceName = "dmes.sqotproductionproduct", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_production_product")
    private Long idProductionProduct;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private Long amount;
    @JoinColumn(name = "id_product_formulation", referencedColumnName = "id_product_formulation")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScProductFormulation idProductFormulation;
    @JoinColumn(name = "id_production_order", referencedColumnName = "id_production_order")
    @ManyToOne(optional = false)
    private OtProductionOrder idProductionOrder;
    
    public OtProductionProduct()
    {
    }

    public OtProductionProduct(Long idProductionProduct)
    {
        this.idProductionProduct = idProductionProduct;
    }

    public Long getIdProductionProduct()
    {
        return idProductionProduct;
    }

    public void setIdProductionProduct(Long idProductionProduct)
    {
        this.idProductionProduct = idProductionProduct;
    }

    public Long getAmount()
    {
        return amount;
    }

    public void setAmount(Long amount)
    {
        this.amount = amount;
    }

    public ScProductFormulation getIdProductFormulation()
    {
        return idProductFormulation;
    }

    public void setIdProductFormulation(ScProductFormulation idProductFormulation)
    {
        this.idProductFormulation = idProductFormulation;
    }

    public OtProductionOrder getIdProductionOrder()
    {
        return idProductionOrder;
    }

    public void setIdProductionOrder(OtProductionOrder idProductionOrder)
    {
        this.idProductionOrder = idProductionOrder;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.idProductionProduct);
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
        final OtProductionProduct other = (OtProductionProduct) obj;
        if (!Objects.equals(this.idProductionProduct, other.idProductionProduct))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "OtProductionProduct{" + "idProductionProduct=" + idProductionProduct + ", amount=" + amount + ", idProductFormulation=" + idProductFormulation + ", idProductionOrder=" + idProductionOrder + '}';
    }

   
    
}
