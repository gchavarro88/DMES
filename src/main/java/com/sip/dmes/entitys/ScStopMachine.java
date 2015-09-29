/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.util.Date;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_stop_machine", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScStopMachine.findAll", query = "SELECT s FROM ScStopMachine s"),
    @NamedQuery(name = "ScStopMachine.findByIdStopMachine", query = "SELECT s FROM ScStopMachine s WHERE s.idStopMachine = :idStopMachine"),
    @NamedQuery(name = "ScStopMachine.findByCreationDate", query = "SELECT s FROM ScStopMachine s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScStopMachine.findByReason", query = "SELECT s FROM ScStopMachine s WHERE s.reason = :reason"),
    @NamedQuery(name = "ScStopMachine.findByDurationEstimate", query = "SELECT s FROM ScStopMachine s WHERE s.durationEstimate = :durationEstimate"),
    @NamedQuery(name = "ScStopMachine.findByEndDate", query = "SELECT s FROM ScStopMachine s WHERE s.endDate = :endDate"),
    @NamedQuery(name = "ScStopMachine.findByDurationReal", query = "SELECT s FROM ScStopMachine s WHERE s.durationReal = :durationReal")
})
public class ScStopMachine implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscstopmachine")
    @SequenceGenerator(name = "dmes.sqscstopmachine", sequenceName = "dmes.sqscstopmachine", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_stop_machine")
    private Long idStopMachine;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Size(max = 400)
    @Column(name = "reason")
    private String reason;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duration_estimate")
    private long durationEstimate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "duration_real")
    private Long durationReal;
    @JoinColumn(name = "id_maintenance", referencedColumnName = "id_maintenance")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private OtMaintenance idMaintenance;

    public ScStopMachine()
    {
    }

    public ScStopMachine(Long idStopMachine)
    {
        this.idStopMachine = idStopMachine;
    }

    public ScStopMachine(Long idStopMachine, Date creationDate, long durationEstimate)
    {
        this.idStopMachine = idStopMachine;
        this.creationDate = creationDate;
        this.durationEstimate = durationEstimate;
    }

    public Long getIdStopMachine()
    {
        return idStopMachine;
    }

    public void setIdStopMachine(Long idStopMachine)
    {
        this.idStopMachine = idStopMachine;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public long getDurationEstimate()
    {
        return durationEstimate;
    }

    public void setDurationEstimate(long durationEstimate)
    {
        this.durationEstimate = durationEstimate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public Long getDurationReal()
    {
        return durationReal;
    }

    public void setDurationReal(Long durationReal)
    {
        this.durationReal = durationReal;
    }

    public OtMaintenance getIdMaintenance()
    {
        return idMaintenance;
    }

    public void setIdMaintenance(OtMaintenance idMaintenance)
    {
        this.idMaintenance = idMaintenance;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idStopMachine != null ? idStopMachine.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScStopMachine))
        {
            return false;
        }
        ScStopMachine other = (ScStopMachine) object;
        if ((this.idStopMachine == null && other.idStopMachine != null) || (this.idStopMachine != null && !this.idStopMachine.equals(other.idStopMachine)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScStopMachine[ idStopMachine=" + idStopMachine + " ]";
    }
    
}
