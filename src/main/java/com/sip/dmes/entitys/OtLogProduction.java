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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "ot_log_production", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "OtLogProduction.findAll", query = "SELECT o FROM OtLogProduction o ORDER BY o.idLog DESC"),
    @NamedQuery(name = "OtLogProduction.findByIdLong", query = "SELECT o FROM OtLogProduction o WHERE o.idLog = :idLog"),
    @NamedQuery(name = "OtLogProduction.findByCreationDate", query = "SELECT o FROM OtLogProduction o WHERE o.creationDate >= :creationDate"),
    @NamedQuery(name = "OtLogProduction.findIdProcessProduct", query = "SELECT o FROM OtLogProduction o WHERE o.idProcessProduct = :idProcessProduct"),
    @NamedQuery(name = "OtLogProduction.findByIdOrder", query = "SELECT o FROM OtLogProduction o WHERE o.idOrder = :idOrder")
})
public class OtLogProduction implements Serializable, Comparable<OtLogProduction>
{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqotlogproduction")
    @SequenceGenerator(name = "dmes.sqotlogproduction", sequenceName = "dmes.sqotlogproduction", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_log")
    private Long idLog;
    @NotNull
    @Column(name = "id_process_product")
    private Long idProcessProduct;
    @NotNull
    @Column(name = "id_order")
    private Long idOrder;
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @NotNull
    @Column(name = "id_Machine")
    private Long idMachine;
    
    public OtLogProduction()
    {
    }

    public OtLogProduction(Long idLog)
    {
        this.idLog = idLog;
    }

    public Long getIdLog()
    {
        return idLog;
    }

    public void setIdLog(Long idLog)
    {
        this.idLog = idLog;
    }

    public Long getIdProcessProduct()
    {
        return idProcessProduct;
    }

    public void setIdProcessProduct(Long idProcessProduct)
    {
        this.idProcessProduct = idProcessProduct;
    }

    public Long getIdOrder()
    {
        return idOrder;
    }

    public void setIdOrder(Long idOrder)
    {
        this.idOrder = idOrder;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public Long getIdMachine()
    {
        return idMachine;
    }

    public void setIdMachine(Long idMachine)
    {
        this.idMachine = idMachine;
    }

    
    
    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.idLog);
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
        final OtLogProduction other = (OtLogProduction) obj;
        if (!Objects.equals(this.idLog, other.idLog))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "OtLogProduction{" + "idLog=" + idLog + ", idProcessProduct=" + idProcessProduct + ", idOrder=" + idOrder + ", creationDate=" + creationDate + '}';
    }

    @Override
    public int compareTo(OtLogProduction object)
    {
        int result = 2;
        if(this.idLog < object.getIdLog())
        {
            result = 1;
        }
        else if(this.idLog > object.getIdLog())
        {
            result = -1;
        }
        else
        {
            result = 0;
        }
        return result;
    }
    
    
}
