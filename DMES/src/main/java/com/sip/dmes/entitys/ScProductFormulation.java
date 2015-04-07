/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guschaor
 */
@Entity
@Table(name = "sc_product_formulation", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScProductFormulation.findAll", query = "SELECT s FROM ScProductFormulation s ORDER BY s.creationDate DESC"),
    @NamedQuery(name = "ScProductFormulation.findById", query = "SELECT s FROM ScProductFormulation s WHERE s.idProductFormulation = :idProduct ORDER BY s.creationDate DESC")
    
})
public class ScProductFormulation implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscproductformulation")
    @SequenceGenerator(name = "dmes.sqscproductformulation", sequenceName = "dmes.sqscproductformulation", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_product_formulation")
    private Long idProductFormulation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "type_material")
    private String typeMaterial;
    
    
    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;
    
    @Basic(optional = false)    
    @Size(min = 1, max = 200)
    @Column(name = "mark")
    private String mark;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "value")
    private long value;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "manufacturing_time")
    private long manufacturingTime;
    
    @Size(max = 2000)
    @Column(name = "path_picture")
    private String pathPicture;
    @Basic(optional = false)
    @NotNull
    
    @Size(min = 1, max = 2000)
    @Column(name = "serie")
    private String serie;
    
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    
    @Column(name = "description")
    private String description;
    
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productFormulation", fetch = FetchType.EAGER)
    private List<ScProductAttached> scProductAttached;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productFormulation", fetch = FetchType.EAGER)
    private List<ScProductDocuments> scProductDocuments;
    
    @JoinColumn(name = "id_cost_center", referencedColumnName = "id_cost_center")
    @ManyToOne(optional = false)
    private ScCostCenter costCenter;
    
    @JoinColumn(name = "id_packing", referencedColumnName = "id_packing")
    @ManyToOne(optional = false)
    private ScPackingUnit packingUnit;
    
    @JoinColumn(name = "id_money", referencedColumnName = "id_money")
    @ManyToOne(optional = false)
    private ScMoney money;
    
    @JoinColumn(name = "id_priority", referencedColumnName = "id_priority")
    @ManyToOne(optional = false)
    private ScPriority priority;
    
    @JoinColumn(name = "id_product_dimension", referencedColumnName = "id_input_dimension")
    @ManyToOne(optional = false)
    private ScInputDimension dimension;
    
    @JoinColumn(name = "id_location", referencedColumnName = "id_location")
    @ManyToOne(optional = false)
    private ScLocation location;
    
    @JoinColumn(name = "id_partner", referencedColumnName = "id_partner")
    @ManyToOne(optional = false)
    private ScPartner supplierGuarantee;

    public ScProductFormulation()
    {
    }

    public ScProductFormulation(Long idProductFormulation)
    {
        this.idProductFormulation = idProductFormulation;
    }

    public Long getIdProductFormulation()
    {
        return idProductFormulation;
    }

    public void setIdProductFormulation(Long idProductFormulation)
    {
        this.idProductFormulation = idProductFormulation;
    }

    public String getTypeMaterial()
    {
        return typeMaterial;
    }

    public void setTypeMaterial(String typeMaterial)
    {
        this.typeMaterial = typeMaterial;
    }

    public Date getExpiryDate()
    {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate)
    {
        this.expiryDate = expiryDate;
    }

    public String getMark()
    {
        return mark;
    }

    public void setMark(String mark)
    {
        this.mark = mark;
    }

    public long getValue()
    {
        return value;
    }

    public void setValue(long value)
    {
        this.value = value;
    }

    public long getManufacturingTime()
    {
        return manufacturingTime;
    }

    public void setManufacturingTime(long manufacturingTime)
    {
        this.manufacturingTime = manufacturingTime;
    }

    public String getPathPicture()
    {
        return pathPicture;
    }

    public void setPathPicture(String pathPicture)
    {
        this.pathPicture = pathPicture;
    }

    public String getSerie()
    {
        return serie;
    }

    public void setSerie(String serie)
    {
        this.serie = serie;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<ScProductAttached> getScProductAttached()
    {
        return scProductAttached;
    }

    public void setScProductAttached(List<ScProductAttached> scProductAttached)
    {
        this.scProductAttached = scProductAttached;
    }

    public List<ScProductDocuments> getScProductDocuments()
    {
        return scProductDocuments;
    }

    public void setScProductDocuments(List<ScProductDocuments> scProductDocuments)
    {
        this.scProductDocuments = scProductDocuments;
    }

    public ScCostCenter getCostCenter()
    {
        return costCenter;
    }

    public void setCostCenter(ScCostCenter costCenter)
    {
        this.costCenter = costCenter;
    }

    public ScPackingUnit getPackingUnit()
    {
        return packingUnit;
    }

    public void setPackingUnit(ScPackingUnit packingUnit)
    {
        this.packingUnit = packingUnit;
    }

    public ScMoney getMoney()
    {
        return money;
    }

    public void setMoney(ScMoney money)
    {
        this.money = money;
    }

    public ScPriority getPriority()
    {
        return priority;
    }

    public void setPriority(ScPriority priority)
    {
        this.priority = priority;
    }

    public ScInputDimension getDimension()
    {
        return dimension;
    }

    public void setDimension(ScInputDimension dimension)
    {
        this.dimension = dimension;
    }

    public ScLocation getLocation()
    {
        return location;
    }

    public void setLocation(ScLocation location)
    {
        this.location = location;
    }

    public ScPartner getSupplierGuarantee()
    {
        return supplierGuarantee;
    }

    public void setSupplierGuarantee(ScPartner supplierGuarantee)
    {
        this.supplierGuarantee = supplierGuarantee;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.idProductFormulation);
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
        final ScProductFormulation other = (ScProductFormulation) obj;
        if (!Objects.equals(this.idProductFormulation, other.idProductFormulation))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ScProductFormulation{" + "idProductFormulation=" + idProductFormulation + ", typeMaterial=" + typeMaterial + ", expiryDate=" + expiryDate + ", mark=" + mark + ", value=" + value + ", manufacturingTime=" + manufacturingTime + ", pathPicture=" + pathPicture + ", serie=" + serie + ", creationDate=" + creationDate + ", description=" + description + ", scProductAttached=" + scProductAttached + ", scProductDocuments=" + scProductDocuments + ", costCenter=" + costCenter + ", packingUnit=" + packingUnit + ", money=" + money + ", priority=" + priority + ", dimension=" + dimension + ", location=" + location + ", supplierGuarantee=" + supplierGuarantee + '}';
    }

    

}
