/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_services_or_products", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScServicesOrProducts.findAll", query = "SELECT s FROM ScServicesOrProducts s"),
    @NamedQuery(name = "ScServicesOrProducts.findByIdServiceOrProducts", query = "SELECT s FROM ScServicesOrProducts s WHERE s.idServiceOrProducts = :idServiceOrProducts"),
    @NamedQuery(name = "ScServicesOrProducts.findByNameServiceOrProduct", query = "SELECT s FROM ScServicesOrProducts s WHERE s.nameServiceOrProduct = :nameServiceOrProduct"),
    @NamedQuery(name = "ScServicesOrProducts.findByCost", query = "SELECT s FROM ScServicesOrProducts s WHERE s.cost = :cost"),
    @NamedQuery(name = "ScServicesOrProducts.findByGuarantee", query = "SELECT s FROM ScServicesOrProducts s WHERE s.guarantee = :guarantee"),
    @NamedQuery(name = "ScServicesOrProducts.findByDescription", query = "SELECT s FROM ScServicesOrProducts s WHERE s.description = :description"),
    @NamedQuery(name = "ScServicesOrProducts.findByAmount", query = "SELECT s FROM ScServicesOrProducts s WHERE s.amount = :amount"),
    @NamedQuery(name = "ScServicesOrProducts.deleteByPartnerId", query = "DELETE FROM ScServicesOrProducts s WHERE s.idPartner = :idPartner"),
    @NamedQuery(name = "ScServicesOrProducts.findByType", query = "SELECT s FROM ScServicesOrProducts s WHERE s.type = :type")
})
public class ScServicesOrProducts implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(generator = "dmes.sqscservicesorproducts")
    @SequenceGenerator(name = "dmes.sqscservicesorproducts", sequenceName = "dmes.sqscservicesorproducts", allocationSize = 1)
    @NotNull
    @Column(name = "id_service_or_products")
    private Long idServiceOrProducts;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name_service_or_product")
    private String nameServiceOrProduct;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cost")
    private BigDecimal cost;
    @Size(max = 2000)
    @Column(name = "guarantee")
    private String guarantee;
    @Size(max = 2000)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private long amount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "type")
    private String type;
    @JoinColumn(name = "id_partner", referencedColumnName = "id_partner")
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
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idServiceOrProducts);
        hash = 97 * hash + Objects.hashCode(this.nameServiceOrProduct);
        hash = 97 * hash + Objects.hashCode(this.cost);
        hash = 97 * hash + Objects.hashCode(this.guarantee);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + (int) (this.amount ^ (this.amount >>> 32));
        hash = 97 * hash + Objects.hashCode(this.type);
        hash = 97 * hash + Objects.hashCode(this.idPartner);
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
        final ScServicesOrProducts other = (ScServicesOrProducts) obj;
        if (!Objects.equals(this.idServiceOrProducts, other.idServiceOrProducts))
        {
            return false;
        }
        if (!Objects.equals(this.nameServiceOrProduct, other.nameServiceOrProduct))
        {
            return false;
        }
        if (!Objects.equals(this.cost, other.cost))
        {
            return false;
        }
        if (!Objects.equals(this.guarantee, other.guarantee))
        {
            return false;
        }
        if (!Objects.equals(this.description, other.description))
        {
            return false;
        }
        if (this.amount != other.amount)
        {
            return false;
        }
        if (!Objects.equals(this.type, other.type))
        {
            return false;
        }
        if (!Objects.equals(this.idPartner, other.idPartner))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ScServicesOrProducts{" + "idServiceOrProducts=" + idServiceOrProducts + ", nameServiceOrProduct=" + nameServiceOrProduct + ", cost=" + cost + ", guarantee=" + guarantee + ", description=" + description + ", amount=" + amount + ", type=" + type + ", idPartner=" + idPartner + '}';
    }

    
    
}
