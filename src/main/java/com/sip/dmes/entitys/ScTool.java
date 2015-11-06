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
@Table(name = "sc_tool", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScTool.findAll", query = "SELECT s FROM ScTool s ORDER BY s.creationDate DESC"),
    @NamedQuery(name = "ScTool.findByTypeMaterial", query = "SELECT s FROM ScTool s WHERE s.typeTool = :typeTool"),
    @NamedQuery(name = "ScTool.findByExpiryDate", query = "SELECT s FROM ScTool s WHERE s.usefulLife = :usefulLife"),
    @NamedQuery(name = "ScTool.findByMark", query = "SELECT s FROM ScTool s WHERE s.mark = :mark"),
    @NamedQuery(name = "ScTool.findByValue", query = "SELECT s FROM ScTool s WHERE s.value = :value"),
    @NamedQuery(name = "ScTool.findByPathPicture", query = "SELECT s FROM ScTool s WHERE s.pathPicture = :pathPicture"),
    @NamedQuery(name = "ScTool.findBySerie", query = "SELECT s FROM ScTool s WHERE s.serie = :serie")
})
public class ScTool implements Serializable, Cloneable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqsctool")
    @SequenceGenerator(name = "dmes.sqsctool", sequenceName = "dmes.sqsctool", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tool")
    private Long idTool;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "type_tool")
    private String typeTool;
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tool", fetch = FetchType.EAGER)
    private List<ScToolAttached> toolAttacheds;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tool", fetch = FetchType.EAGER)
    private List<ScToolDocuments> toolDocuments;
    
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
    
    @JoinColumn(name = "id_tool_dimension", referencedColumnName = "id_input_dimension")
    @ManyToOne(optional = false)
    private ScInputDimension dimension;
    
    @JoinColumn(name = "id_location", referencedColumnName = "id_location")
    @ManyToOne(optional = false)
    private ScLocation inputLocation;
    
    @JoinColumn(name = "supplier_guarantee", referencedColumnName = "id_partner")
    @ManyToOne(optional = false)
    private ScPartner supplierGuarantee;

    public ScTool()
    {
    }

    public ScTool(Long idTool)
    {
        this.idTool = idTool;
    }

    public Long getIdTool()
    {
        return idTool;
    }

    public void setIdTool(Long idTool)
    {
        this.idTool = idTool;
    }

    public String getTypeTool()
    {
        return typeTool;
    }

    public void setTypeTool(String typeTool)
    {
        this.typeTool = typeTool;
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

    public List<ScToolAttached> getToolAttacheds()
    {
        return toolAttacheds;
    }

    public void setToolAttacheds(List<ScToolAttached> toolAttacheds)
    {
        this.toolAttacheds = toolAttacheds;
    }

    public List<ScToolDocuments> getToolDocuments()
    {
        return toolDocuments;
    }

    public void setToolDocuments(List<ScToolDocuments> toolDocuments)
    {
        this.toolDocuments = toolDocuments;
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
        hash = 71 * hash + Objects.hashCode(this.idTool);
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
        final ScTool other = (ScTool) obj;
        if (!Objects.equals(this.idTool, other.idTool))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ScTool{" + "idTool=" + idTool + ", name="+ name +", typeTool=" + typeTool + ", usefulLife=" + usefulLife + ", mark=" + mark + ", value=" + value + ", pathPicture=" + pathPicture + ", serie=" + serie + ", creationDate=" + creationDate + ", description=" + description + ", costCenter=" + costCenter + ", stock=" + stock + ", money=" + money + ", priority=" + priority + ", dimension=" + dimension + ", inputLocation=" + inputLocation + ", supplierGuarantee=" + supplierGuarantee + '}';
    }

    
    
}
