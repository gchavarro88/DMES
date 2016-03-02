/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author guschaor
 */
@Entity
@Table(name = "sc_stock", schema = "dmes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScInputStock.findAll", query = "SELECT s FROM ScInputStock s"),
    @NamedQuery(name = "ScInputStock.findByIdInputStock", query = "SELECT s FROM ScInputStock s WHERE s.idInputStock = :idInputStock"),
    @NamedQuery(name = "ScInputStock.findByMaximeStock", query = "SELECT s FROM ScInputStock s WHERE s.maximeStock = :maximeStock"),
    @NamedQuery(name = "ScInputStock.findByMinimeStock", query = "SELECT s FROM ScInputStock s WHERE s.minimeStock = :minimeStock"),
    @NamedQuery(name = "ScInputStock.findByCurrentStock", query = "SELECT s FROM ScInputStock s WHERE s.currentStock = :currentStock"),
    @NamedQuery(name = "ScInputStock.findByPriceUnit", query = "SELECT s FROM ScInputStock s WHERE s.priceUnit = :priceUnit"),
    @NamedQuery(name = "ScInputStock.findByTotalValue", query = "SELECT s FROM ScInputStock s WHERE s.totalValue = :totalValue"),
    @NamedQuery(name = "ScInputStock.findByOptimeStock", query = "SELECT s FROM ScInputStock s WHERE s.optimeStock = :optimeStock")})
public class ScInputStock implements Serializable {
    
    
   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscstock")
    @SequenceGenerator(name = "dmes.sqscstock", sequenceName = "dmes.sqscstock", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_stock")
    private Long idInputStock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price_unit")
    private Double priceUnit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_value")
    private Double totalValue;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "maxime_stock")
    private long maximeStock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "minime_stock")
    private long minimeStock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "current_stock")
    private long currentStock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "optime_stock")
    private long optimeStock;
    @JoinColumn(name = "id_store", referencedColumnName = "id_store")
    @ManyToOne(optional = false)
    private ScStore idStore;
    
    public ScInputStock() {
    }

    public ScInputStock(Long idInputStock) {
        this.idInputStock = idInputStock;
    }

    public ScInputStock(Long idInputStock, long maximeStock, long minimeStock, long currentStock, Double priceUnit, Double totalValue, long optimeStock) {
        this.idInputStock = idInputStock;
        this.maximeStock = maximeStock;
        this.minimeStock = minimeStock;
        this.currentStock = currentStock;
        this.priceUnit = priceUnit;
        this.totalValue = totalValue;
        this.optimeStock = optimeStock;
    }

    public Long getIdInputStock()
    {
        return idInputStock;
    }

    public void setIdInputStock(Long idInputStock)
    {
        this.idInputStock = idInputStock;
    }

    public Double getPriceUnit()
    {
        return priceUnit;
    }

    public void setPriceUnit(Double priceUnit)
    {
        this.priceUnit = priceUnit;
    }

    public Double getTotalValue()
    {
        return totalValue;
    }

    public void setTotalValue(Double totalValue)
    {
        this.totalValue = totalValue;
    }

    public long getMaximeStock()
    {
        return maximeStock;
    }

    public void setMaximeStock(long maximeStock)
    {
        this.maximeStock = maximeStock;
    }

    public long getMinimeStock()
    {
        return minimeStock;
    }

    public void setMinimeStock(long minimeStock)
    {
        this.minimeStock = minimeStock;
    }

    public long getCurrentStock()
    {
        return currentStock;
    }

    public void setCurrentStock(long currentStock)
    {
        this.currentStock = currentStock;
    }

    public long getOptimeStock()
    {
        return optimeStock;
    }

    public void setOptimeStock(long optimeStock)
    {
        this.optimeStock = optimeStock;
    }

    public ScStore getIdStore()
    {
        return idStore;
    }

    public void setIdStore(ScStore idStore)
    {
        this.idStore = idStore;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInputStock != null ? idInputStock.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScInputStock)) {
            return false;
        }
        ScInputStock other = (ScInputStock) object;
        if ((this.idInputStock == null && other.idInputStock != null) || (this.idInputStock != null && !this.idInputStock.equals(other.idInputStock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sip.dmes.entitys.ScInputStock[ idInputStock=" + idInputStock + " ]";
    }
    private static final Logger LOG = Logger.getLogger(ScInputStock.class.getName());

}
