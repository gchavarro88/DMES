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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "SC_SERVICES_OR_PRODUCTS")
@NamedQueries(
{
    @NamedQuery(name = "ScServicesOrProducts.findAll", query = "SELECT s FROM ScServicesOrProducts s"),
    @NamedQuery(name = "ScServicesOrProducts.findByIdServiceOrProducts", query = "SELECT s FROM ScServicesOrProducts s WHERE s.idServiceOrProducts = :idServiceOrProducts"),
    @NamedQuery(name = "ScServicesOrProducts.findByNameServiceOrProduct", query = "SELECT s FROM ScServicesOrProducts s WHERE s.nameServiceOrProduct = :nameServiceOrProduct"),
    @NamedQuery(name = "ScServicesOrProducts.findByCost", query = "SELECT s FROM ScServicesOrProducts s WHERE s.cost = :cost"),
    @NamedQuery(name = "ScServicesOrProducts.findByGuarantee", query = "SELECT s FROM ScServicesOrProducts s WHERE s.guarantee = :guarantee"),
    @NamedQuery(name = "ScServicesOrProducts.findByDescription", query = "SELECT s FROM ScServicesOrProducts s WHERE s.description = :description"),
    @NamedQuery(name = "ScServicesOrProducts.findByAmount", query = "SELECT s FROM ScServicesOrProducts s WHERE s.amount = :amount"),
    @NamedQuery(name = "ScServicesOrProducts.findByType", query = "SELECT s FROM ScServicesOrProducts s WHERE s.type = :type")
})
public class ScServicesOrProducts implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SERVICE_OR_PRODUCTS")
    private Long idServiceOrProducts;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NAME_SERVICE_OR_PRODUCT")
    private String nameServiceOrProduct;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "COST")
    private BigDecimal cost;
    @Size(max = 2000)
    @Column(name = "GUARANTEE")
    private String guarantee;
    @Size(max = 2000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT")
    private long amount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TYPE")
    private String type;
    @JoinColumn(name = "ID_PARTNER", referencedColumnName = "ID_PARTNER")
    @ManyToOne(optional = false)
    private ScPartner idPartner;

    public ScServicesOrProducts()
    {
    }

    public ScServicesOrProducts(Long idServiceOrProducts)
    {
        this.idServiceOrProducts = idServiceOrProducts;
    }

    public ScServicesOrProducts(Long idServiceOrProducts, String nameServiceOrProduct, BigDecimal cost, long amount, String type)
    {
        this.idServiceOrProducts = idServiceOrProducts;
        this.nameServiceOrProduct = nameServiceOrProduct;
        this.cost = cost;
        this.amount = amount;
        this.type = type;
    }

    public Long getIdServiceOrProducts()
    {
        return idServiceOrProducts;
    }

    public void setIdServiceOrProducts(Long idServiceOrProducts)
    {
        this.idServiceOrProducts = idServiceOrProducts;
    }

    public String getNameServiceOrProduct()
    {
        return nameServiceOrProduct;
    }

    public void setNameServiceOrProduct(String nameServiceOrProduct)
    {
        this.nameServiceOrProduct = nameServiceOrProduct;
    }

    public BigDecimal getCost()
    {
        return cost;
    }

    public void setCost(BigDecimal cost)
    {
        this.cost = cost;
    }

    public String getGuarantee()
    {
        return guarantee;
    }

    public void setGuarantee(String guarantee)
    {
        this.guarantee = guarantee;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public long getAmount()
    {
        return amount;
    }

    public void setAmount(long amount)
    {
        this.amount = amount;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public ScPartner getIdPartner()
    {
        return idPartner;
    }

    public void setIdPartner(ScPartner idPartner)
    {
        this.idPartner = idPartner;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idServiceOrProducts != null ? idServiceOrProducts.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScServicesOrProducts))
        {
            return false;
        }
        ScServicesOrProducts other = (ScServicesOrProducts) object;
        if ((this.idServiceOrProducts == null && other.idServiceOrProducts != null) || (this.idServiceOrProducts != null && !this.idServiceOrProducts.equals(other.idServiceOrProducts)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScServicesOrProducts[ idServiceOrProducts=" + idServiceOrProducts + " ]";
    }
    
}
