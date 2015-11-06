/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.entitys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author carlos guzman
 */
@Entity
@Table(name = "sc_maintenance_plan", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScMaintenancePlan.findAll", query = "SELECT s FROM ScMaintenancePlan s"),
    @NamedQuery(name = "ScMaintenancePlan.findByIdMaintenancePlan", query = "SELECT s FROM ScMaintenancePlan s WHERE s.idMaintenancePlan = :idMaintenancePlan"),
    @NamedQuery(name = "ScMaintenancePlan.findByIdMachine", query = "SELECT s FROM ScMaintenancePlan s WHERE s.idMachine = :idMachine"),
    @NamedQuery(name = "ScMaintenancePlan.findByCreationDate", query = "SELECT s FROM ScMaintenancePlan s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScMaintenancePlan.findByModifyDate", query = "SELECT s FROM ScMaintenancePlan s WHERE s.modifyDate = :modifyDate"),
    @NamedQuery(name = "ScMaintenancePlan.deleteByIdMachine", query = "DELETE FROM ScMaintenancePlan s WHERE s.idMachine = :idMachine"),
    @NamedQuery(name = "ScMaintenancePlan.deleteByIdMaintenancePlan", query = "DELETE FROM ScMaintenancePlan s WHERE s.idMaintenancePlan = :idMaintenancePlan")
})
public class ScMaintenancePlan implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscmaintenanceplan")
    @SequenceGenerator(name = "dmes.sqscmaintenanceplan", sequenceName = "dmes.sqscmaintenanceplan", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_maintenance_plan")
    private Long idMaintenancePlan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "maintenance_plan")
    private String maintenancePlan;
    @JoinColumn(name = "id_type_maintenance", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private ScType idTypeMaintenance;
    @JoinColumn(name = "idTypeHandWorkClassification", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private ScType idTypeHandWorkClassification;
    @JoinColumn(name = "id_type_priority", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private ScType idTypePriority;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000000000)
    @Column(name = "frequency")
    private BigDecimal frequency;
    @JoinColumn(name = "id_type_frequency", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private ScType idTypeFrequency;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duration")
    @Temporal(TemporalType.TIMESTAMP)
    private Date duration; 
    @JoinColumn(name = "id_machine", referencedColumnName = "id_machine")
    @ManyToOne(optional = false)
    private ScMachine idMachine;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "activity")
    private String activity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "programing_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date programingDate; 
    @JoinColumn(name = "id_type_downtime", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private ScType idTypeDowntime;
    @JoinColumn(name = "id_type_unit_unemployment", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private ScType idTypeUnitUnemployment;
    @JoinColumn(name = "id_tool", referencedColumnName = "id_tool")
    @ManyToOne(optional = false)
    private ScTools idTool;
    @JoinColumn(name = "id_parts_and_consumables", referencedColumnName = "id_parts_and_consumables")
    @ManyToOne(optional = false)
    private ScPartsAndConsumables idPartsAndConsumables;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;
    
    public ScMaintenancePlan()
    {
    }

    public ScMaintenancePlan(Long idMaintenancePlan)
    {
        this.idMaintenancePlan = idMaintenancePlan;
    }

    public ScMaintenancePlan(Long idMaintenancePlan, String maintenancePlan, ScType idTypeMaintenance, ScType idTypeHandWorkClassification, ScType idTypePriority, BigDecimal frequency, ScType idTypeFrequency, Date duration, ScMachine idMachine, String activity, Date programingDate, ScType idTypeDowntime, ScType idTypeUnitUnemployment, ScTools idTool, ScPartsAndConsumables idPartsAndConsumables, Date creationDate, Date modifyDate) {
        this.idMaintenancePlan = idMaintenancePlan;
        this.maintenancePlan = maintenancePlan;
        this.idTypeMaintenance = idTypeMaintenance;
        this.idTypeHandWorkClassification = idTypeHandWorkClassification;
        this.idTypePriority = idTypePriority;
        this.frequency = frequency;
        this.idTypeFrequency = idTypeFrequency;
        this.duration = duration;
        this.idMachine = idMachine;
        this.activity = activity;
        this.programingDate = programingDate;
        this.idTypeDowntime = idTypeDowntime;
        this.idTypeUnitUnemployment = idTypeUnitUnemployment;
        this.idTool = idTool;
        this.idPartsAndConsumables = idPartsAndConsumables;
        this.creationDate = creationDate;
        this.modifyDate = modifyDate;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idMaintenancePlan != null ? idMaintenancePlan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScMaintenancePlan))
        {
            return false;
        }
        ScMaintenancePlan other = (ScMaintenancePlan) object;
        if ((this.idMaintenancePlan == null && other.idMaintenancePlan != null) || (this.idMaintenancePlan != null && !this.idMaintenancePlan.equals(other.idMaintenancePlan)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScMaintenancePlan[ idMaintenancePlan=" + idMaintenancePlan + " ]";
    }

    public Long getIdMaintenancePlan() {
        return idMaintenancePlan;
    }

    public void setIdMaintenancePlan(Long idMaintenancePlan) {
        this.idMaintenancePlan = idMaintenancePlan;
    }

    public String getMaintenancePlan() {
        return maintenancePlan;
    }

    public void setMaintenancePlan(String maintenancePlan) {
        this.maintenancePlan = maintenancePlan;
    }

    public ScType getIdTypeMaintenance() {
        return idTypeMaintenance;
    }

    public void setIdTypeMaintenance(ScType idTypeMaintenance) {
        this.idTypeMaintenance = idTypeMaintenance;
    }

    public ScType getIdTypeHandWorkClassification() {
        return idTypeHandWorkClassification;
    }

    public void setIdTypeHandWorkClassification(ScType idTypeHandWorkClassification) {
        this.idTypeHandWorkClassification = idTypeHandWorkClassification;
    }

    public ScType getIdTypePriority() {
        return idTypePriority;
    }

    public void setIdTypePriority(ScType idTypePriority) {
        this.idTypePriority = idTypePriority;
    }

    public BigDecimal getFrequency() {
        return frequency;
    }

    public void setFrequency(BigDecimal frequency) {
        this.frequency = frequency;
    }

    public ScType getIdTypeFrequency() {
        return idTypeFrequency;
    }

    public void setIdTypeFrequency(ScType idTypeFrequency) {
        this.idTypeFrequency = idTypeFrequency;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public ScMachine getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(ScMachine idMachine) {
        this.idMachine = idMachine;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Date getProgramingDate() {
        return programingDate;
    }

    public void setProgramingDate(Date programingDate) {
        this.programingDate = programingDate;
    }

    public ScType getIdTypeDowntime() {
        return idTypeDowntime;
    }

    public void setIdTypeDowntime(ScType idTypeDowntime) {
        this.idTypeDowntime = idTypeDowntime;
    }

    public ScType getIdTypeUnitUnemployment() {
        return idTypeUnitUnemployment;
    }

    public void setIdTypeUnitUnemployment(ScType idTypeUnitUnemployment) {
        this.idTypeUnitUnemployment = idTypeUnitUnemployment;
    }

    public ScTools getIdTool() {
        return idTool;
    }

    public void setIdTool(ScTools idTool) {
        this.idTool = idTool;
    }

    public ScPartsAndConsumables getIdPartsAndConsumables() {
        return idPartsAndConsumables;
    }

    public void setIdPartsAndConsumables(ScPartsAndConsumables idPartsAndConsumables) {
        this.idPartsAndConsumables = idPartsAndConsumables;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

}
