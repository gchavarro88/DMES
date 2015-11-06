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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author guschaor
 */
@Entity
@Table(name = "sc_replacement", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScReplacement.findAll", query = "SELECT s FROM ScReplacement s ORDER BY s.creationDate DESC"),
    @NamedQuery(name = "ScReplacement.findByTypeMaterial", query = "SELECT s FROM ScReplacement s WHERE s.typeReplacement = :typeReplacement"),
    @NamedQuery(name = "ScReplacement.findByExpiryDate", query = "SELECT s FROM ScReplacement s WHERE s.usefulLife = :usefulLife"),
    @NamedQuery(name = "ScReplacement.findByMark", query = "SELECT s FROM ScReplacement s WHERE s.mark = :mark"),
    @NamedQuery(name = "ScReplacement.findByValue", query = "SELECT s FROM ScReplacement s WHERE s.value = :value"),
    @NamedQuery(name = "ScReplacement.findByPathPicture", query = "SELECT s FROM ScReplacement s WHERE s.pathPicture = :pathPicture"),
    @NamedQuery(name = "ScReplacement.findBySerie", query = "SELECT s FROM ScReplacement s WHERE s.serie = :serie")
})
public class ScReplacement implements Serializable, Cloneable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscreplacement")
    @SequenceGenerator(name = "dmes.sqscreplacement", sequenceName = "dmes.sqscreplacement", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_replacement")
    private Long idReplacement;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "type_replacement")
    private String typeReplacement;
    @Column(name = "name")
    private String name;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "useful_life")
    private Integer usefulLife;
        
    @Basic(optional = false)    
    @Size(min = 1, max = 200)
    @Column(name = "mark")
    private String mark;
    
    @Basic(optional = false)    
    @Column(name = "value_minutes")
    private Integer valueMinutes;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "value")
    private Double value;
    @Size(max = 2000)
    @Column(name = "path_picture") 
    private String pathPicture;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "serie")
    private String serie;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "description")
    private String description;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "replacement", fetch = FetchType.EAGER)
    private List<ScReplacementAttached> replacementAttacheds;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "replacement", fetch = FetchType.EAGER)
    private List<ScReplacementDocuments> replacementDocuments;
    
    @JoinColumn(name = "cost_center", referencedColumnName = "id_cost_center")
    @ManyToOne(optional = false)
    private ScCostCenter costCenter;
    
    @JoinColumn(name = "id_stock", referencedColumnName = "id_stock")
    @ManyToOne(optional = false)
    private ScInputStock stock;
    
    @JoinColumn(name = "time", referencedColumnName = "id_time")
    @ManyToOne(optional = false)
    private ScTime time;
    
    @JoinColumn(name = "id_money", referencedColumnName = "id_money")
    @ManyToOne(optional = false)
    private ScMoney money;
    
    @JoinColumn(name = "id_priority", referencedColumnName = "id_priority")
    @ManyToOne(optional = false)
    private ScPriority priority;
    
    @JoinColumn(name = "id_replacement_dimension", referencedColumnName = "id_input_dimension")
    @ManyToOne(optional = false)
    private ScInputDimension dimension;
    
    @JoinColumn(name = "id_location", referencedColumnName = "id_location")
    @ManyToOne(optional = false)
    private ScLocation inputLocation;
    
    @JoinColumn(name = "supplier_guarantee", referencedColumnName = "id_partner")
    @ManyToOne(optional = false)
    private ScPartner supplierGuarantee;

    public ScReplacement()
    {
    }

    public ScReplacement(Long idReplacement)
    {
        this.idReplacement = idReplacement;
    }

    public Long getIdReplacement()
    {
        return idReplacement;
    }

    public void setIdReplacement(Long idReplacement)
    {
        this.idReplacement = idReplacement;
    }

    public String getTypeReplacement()
    {
        return typeReplacement;
    }

    public void setTypeReplacement(String typeReplacement)
    {
        this.typeReplacement = typeReplacement;
    }

    public Integer getUsefulLife()
    {
        return usefulLife;
    }

    public void setUsefulLife(Integer usefulLife)
    {
        this.usefulLife = usefulLife;
    }

    public String getMark()
    {
        return mark;
    }

    public void setMark(String mark)
    {
        this.mark = mark;
    }

    public Double getValue()
    {
        return value;
    }

    public void setValue(Double value)
    {
        this.value = value;
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

    public ScCostCenter getCostCenter()
    {
        return costCenter;
    }

    public void setCostCenter(ScCostCenter costCenter)
    {
        this.costCenter = costCenter;
    }

    public ScInputStock getStock()
    {
        return stock;
    }

    public void setStock(ScInputStock stock)
    {
        this.stock = stock;
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

    public ScLocation getInputLocation()
    {
        return inputLocation;
    }

    public void setInputLocation(ScLocation inputLocation)
    {
        this.inputLocation = inputLocation;
    }

    public ScPartner getSupplierGuarantee()
    {
        return supplierGuarantee;
    }

    public void setSupplierGuarantee(ScPartner supplierGuarantee)
    {
        this.supplierGuarantee = supplierGuarantee;
    }

    public List<ScReplacementAttached> getReplacementAttacheds()
    {
        return replacementAttacheds;
    }

    public void setReplacementAttacheds(List<ScReplacementAttached> replacementAttacheds)
    {
        this.replacementAttacheds = replacementAttacheds;
    }

    public List<ScReplacementDocuments> getReplacementDocuments()
    {
        return replacementDocuments;
    }

    public void setReplacementDocuments(List<ScReplacementDocuments> replacementDocuments)
    {
        this.replacementDocuments = replacementDocuments;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ScTime getTime()
    {
        return time;
    }

    public void setTime(ScTime time)
    {
        this.time = time;
    }

    public Integer getValueMinutes()
    {
        return valueMinutes;
    }

    public void setValueMinutes(Integer valueMinutes)
    {
        this.valueMinutes = valueMinutes;
    }

    
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.idReplacement);
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
        final ScReplacement other = (ScReplacement) obj;
        if (!Objects.equals(this.idReplacement, other.idReplacement))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ScReplacement{" + "idReplacement=" + idReplacement + ", name="+ name +", typeReplacement=" + typeReplacement + ", usefulLife=" + usefulLife + ", mark=" + mark + ", value=" + value + ", pathPicture=" + pathPicture + ", serie=" + serie + ", creationDate=" + creationDate + ", description=" + description + ", costCenter=" + costCenter + ", stock=" + stock + ", money=" + money + ", priority=" + priority + ", dimension=" + dimension + ", inputLocation=" + inputLocation + ", supplierGuarantee=" + supplierGuarantee + '}';
    }

    
    
}
