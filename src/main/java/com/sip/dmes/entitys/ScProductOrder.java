/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_product_order", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScProductOrder.findAll", query = "SELECT s FROM ScProductOrder s"),
    @NamedQuery(name = "ScProductOrder.findByIdProductOrder", query = "SELECT s FROM ScProductOrder s WHERE s.idProductOrder = :idProductOrder"),
    @NamedQuery(name = "ScProductOrder.findByPathPicture", query = "SELECT s FROM ScProductOrder s WHERE s.pathPicture = :pathPicture"),
    @NamedQuery(name = "ScProductOrder.findByTypeMaterial", query = "SELECT s FROM ScProductOrder s WHERE s.typeMaterial = :typeMaterial"),
    @NamedQuery(name = "ScProductOrder.findByMark", query = "SELECT s FROM ScProductOrder s WHERE s.mark = :mark"),
    @NamedQuery(name = "ScProductOrder.findBySerie", query = "SELECT s FROM ScProductOrder s WHERE s.serie = :serie"),
    @NamedQuery(name = "ScProductOrder.findByExpiryDate", query = "SELECT s FROM ScProductOrder s WHERE s.expiryDate = :expiryDate"),
    @NamedQuery(name = "ScProductOrder.findByCreationDate", query = "SELECT s FROM ScProductOrder s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScProductOrder.findByValue", query = "SELECT s FROM ScProductOrder s WHERE s.value = :value"),
    @NamedQuery(name = "ScProductOrder.findByIdLocation", query = "SELECT s FROM ScProductOrder s WHERE s.idLocation = :idLocation"),
    @NamedQuery(name = "ScProductOrder.findByManufacturingTime", query = "SELECT s FROM ScProductOrder s WHERE s.manufacturingTime = :manufacturingTime"),
    @NamedQuery(name = "ScProductOrder.findByDescription", query = "SELECT s FROM ScProductOrder s WHERE s.description = :description"),
    @NamedQuery(name = "ScProductOrder.findByAmountRequired", query = "SELECT s FROM ScProductOrder s WHERE s.amountRequired = :amountRequired"),
    @NamedQuery(name = "ScProductOrder.findByAmountProduced", query = "SELECT s FROM ScProductOrder s WHERE s.amountProduced = :amountProduced")
})
public class ScProductOrder implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscproductorder")
    @SequenceGenerator(name = "dmes.sqscproductorder", sequenceName = "dmes.sqscproductorder", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_product_order")
    private Long idProductOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_product_formulation")
    private Long idProductFormulation;
    @Size(max = 200)
    @Column(name = "path_picture")
    private String pathPicture;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "type_material")
    private String typeMaterial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "mark")
    private String mark;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "serie")
    private String serie;
    @Column(name = "expiry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "value")
    private long value;
    @Basic(optional = false)
    @NotNull
    @Column(name = "manufacturing_time")
    private long manufacturingTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "description")
    private String description;
    @Column(name = "amount_required")
    private Long amountRequired;
    @Column(name = "amount_produced")
    private Long amountProduced;
    
    @JoinColumn(name = "id_location", referencedColumnName = "id_location")
    @ManyToOne(optional = false)
    private ScLocation idLocation;
    
    @JoinColumn(name = "id_priority", referencedColumnName = "id_priority")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScPriority idPriority;
    @JoinColumn(name = "id_packing", referencedColumnName = "id_packing")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScPackingUnit idPacking;
    @JoinColumn(name = "id_money", referencedColumnName = "id_money")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScMoney idMoney;
    @JoinColumn(name = "id_product_dimension", referencedColumnName = "id_input_dimension")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScInputDimension idProductDimension;
    @JoinColumn(name = "id_cost_center", referencedColumnName = "id_cost_center")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ScCostCenter idCostCenter;
    @JoinColumn(name = "id_order", referencedColumnName = "id_production_order")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private OtProductionOrder idOrder;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProductOrder", fetch = FetchType.EAGER)
    private List<ScProccesProductOrder> scProccesProductOrderList;

    public ScProductOrder()
    {
    }

    public ScProductOrder(Long idProductOrder)
    {
        this.idProductOrder = idProductOrder;
    }

    public ScProductOrder(Long idProductOrder, String typeMaterial, String mark, String serie, Date creationDate, long value, ScLocation idLocation, long manufacturingTime, String description)
    {
        this.idProductOrder = idProductOrder;
        this.typeMaterial = typeMaterial;
        this.mark = mark;
        this.serie = serie;
        this.creationDate = creationDate;
        this.value = value;
        this.idLocation = idLocation;
        this.manufacturingTime = manufacturingTime;
        this.description = description;
    }
    
    
    

    public Long getIdProductOrder()
    {
        return idProductOrder;
    }

    public void setIdProductOrder(Long idProductOrder)
    {
        this.idProductOrder = idProductOrder;
    }

    public String getPathPicture()
    {
        return pathPicture;
    }

    public void setPathPicture(String pathPicture)
    {
        this.pathPicture = pathPicture;
    }

    public String getTypeMaterial()
    {
        return typeMaterial;
    }

    public void setTypeMaterial(String typeMaterial)
    {
        this.typeMaterial = typeMaterial;
    }

    public String getMark()
    {
        return mark;
    }

    public void setMark(String mark)
    {
        this.mark = mark;
    }

    public String getSerie()
    {
        return serie;
    }

    public void setSerie(String serie)
    {
        this.serie = serie;
    }

    public Date getExpiryDate()
    {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate)
    {
        this.expiryDate = expiryDate;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public long getValue()
    {
        return value;
    }

    public void setValue(long value)
    {
        this.value = value;
    }

    public ScLocation getIdLocation()
    {
        return idLocation;
    }

    public void setIdLocation(ScLocation idLocation)
    {
        this.idLocation = idLocation;
    }

    public long getManufacturingTime()
    {
        return manufacturingTime;
    }

    public void setManufacturingTime(long manufacturingTime)
    {
        this.manufacturingTime = manufacturingTime;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Long getAmountRequired()
    {
        return amountRequired;
    }

    public void setAmountRequired(Long amountRequired)
    {
        this.amountRequired = amountRequired;
    }

    public Long getAmountProduced()
    {
        return amountProduced;
    }

    public void setAmountProduced(Long amountProduced)
    {
        this.amountProduced = amountProduced;
    }

    public ScPriority getIdPriority()
    {
        return idPriority;
    }

    public void setIdPriority(ScPriority idPriority)
    {
        this.idPriority = idPriority;
    }

    public ScPackingUnit getIdPacking()
    {
        return idPacking;
    }

    public void setIdPacking(ScPackingUnit idPacking)
    {
        this.idPacking = idPacking;
    }

    public ScMoney getIdMoney()
    {
        return idMoney;
    }

    public void setIdMoney(ScMoney idMoney)
    {
        this.idMoney = idMoney;
    }

    public ScInputDimension getIdProductDimension()
    {
        return idProductDimension;
    }

    public void setIdProductDimension(ScInputDimension idProductDimension)
    {
        this.idProductDimension = idProductDimension;
    }

    public ScCostCenter getIdCostCenter()
    {
        return idCostCenter;
    }

    public void setIdCostCenter(ScCostCenter idCostCenter)
    {
        this.idCostCenter = idCostCenter;
    }

    public OtProductionOrder getIdOrder()
    {
        return idOrder;
    }

    public void setIdOrder(OtProductionOrder idOrder)
    {
        this.idOrder = idOrder;
    }

    @XmlTransient
    public List<ScProccesProductOrder> getScProccesProductOrderList()
    {
        return scProccesProductOrderList;
    }

    public void setScProccesProductOrderList(List<ScProccesProductOrder> scProccesProductOrderList)
    {
        this.scProccesProductOrderList = scProccesProductOrderList;
    }

    public Long getIdProductFormulation()
    {
        return idProductFormulation;
    }

    public void setIdProductFormulation(Long idProductFormulation)
    {
        this.idProductFormulation = idProductFormulation;
    }
    
    

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idProductOrder != null ? idProductOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScProductOrder))
        {
            return false;
        }
        ScProductOrder other = (ScProductOrder) object;
        if ((this.idProductOrder == null && other.idProductOrder != null) || (this.idProductOrder != null && !this.idProductOrder.equals(other.idProductOrder)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScProductOrder[ idProductOrder=" + idProductOrder + " ]";
    }
    
}
