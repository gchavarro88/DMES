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
@Table(name = "sc_machine", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScMachine.findAll", query = "SELECT s FROM ScMachine s"),
    @NamedQuery(name = "ScMachine.findByIdMachine", query = "SELECT s FROM ScMachine s WHERE s.idMachine = :idMachine"),
    @NamedQuery(name = "ScMachine.findByIdMachineFather", query = "SELECT s FROM ScMachine s WHERE s.idMachineFather = :idMachineFather"),
    @NamedQuery(name = "ScMachine.findByMachine", query = "SELECT s FROM ScMachine s WHERE s.machine = :machine"),
    @NamedQuery(name = "ScMachine.findByCreationDate", query = "SELECT s FROM ScMachine s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScMachine.findByModifyDate", query = "SELECT s FROM ScMachine s WHERE s.modifyDate = :modifyDate"),
    @NamedQuery(name = "ScMachine.deleteByIdMachine", query = "DELETE FROM ScMachine s WHERE s.idMachine = :idMachine"),
    @NamedQuery(name = "ScMachine.deleteByIdMachineFather", query = "DELETE FROM ScMachine s WHERE s.idMachineFather = :idMachineFather")
})
public class ScMachine implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqmachine")
    @SequenceGenerator(name = "dmes.sqmachine", sequenceName = "dmes.sqmachine", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_machine")
    private Long idMachine;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "machine")
    private String machine;
    @JoinColumn(name = "id_type_classification", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private ScType idTypeClassification;
    @JoinColumn(name = "id_type_cost", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private ScType idTypeCoast;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "cost")
    private BigDecimal cost;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "location")
    private String location;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "comments")
    private String comments;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "modify_date")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "brand")
    private String brand;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "series")
    private String series;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "lifespan")
    private Long lifespan;
    @JoinColumn(name = "id_type_lifespan", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private ScType idTypeLifespan;
    @JoinColumn(name = "id_type_priority", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private ScType idTypePriority;
    @JoinColumn(name = "id_cost_center", referencedColumnName = "id_cost_center")
    @ManyToOne(optional = false)
    private ScCostCenter idCostCenter;
    @JoinColumn(name = "id_partner", referencedColumnName = "id_partner")
    @ManyToOne(optional = false)
    private ScPartner idPartner;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "weight")
    private Long weight;
    @JoinColumn(name = "id_type_weight", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private ScType idTypeWeight;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "high")
    private Long high;
    @JoinColumn(name = "id_type_high", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private ScType idTypeHigh;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "potential")
    private Long potential;
    @JoinColumn(name = "id_type_potential", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private ScType idTypePotential;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "width")
    private Long width;
    @JoinColumn(name = "id_type_width", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private ScType idTypeWidth;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "long_machine")
    private Long longMachine;
    @JoinColumn(name = "id_type_long", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private ScType idTypeLong;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "voltage")
    private Long voltage;
    @JoinColumn(name = "id_type_voltage", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private ScType idTypeVoltage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "current_machine")
    private Long currentMachine;
    @JoinColumn(name = "id_type_current", referencedColumnName = "id_type")
    @ManyToOne(optional = false)
    private ScType idTypeCurrent;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "dimensions_observation")
    private String dimensionsObservation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "notes_custom_field")
    private String notesCustomField;
    @JoinColumn(name = "id_machine_father", referencedColumnName = "id_machine")
    @ManyToOne(optional = false)
    private ScMachine idMachineFather;
    
    public ScMachine()
    {
    }

    public ScMachine(Long idMachine)
    {
        this.idMachine = idMachine;
    }

    public ScMachine(Long idMachine, String machine, ScType idTypeClassification, ScType idTypeCoast, BigDecimal cost, String location, String comments, Date creationDate, Date modifyDate, String brand, String series, Long lifespan, ScType idTypeLifespan, ScType idTypePriority, ScCostCenter idCostCenter, ScPartner idPartner, Long weight, ScType idTypeWeight, Long high, ScType idTypeHigh, Long potential, ScType idTypePotential, Long width, ScType idTypeWidth, Long longMachine, ScType idTypeLong, Long voltage, ScType idTypeVoltage, Long currentMachine, ScType idTypeCurrent, String dimensionsObservation, String notesCustomField, ScMachine idMachineFather) {
        this.idMachine = idMachine;
        this.machine = machine;
        this.idTypeClassification = idTypeClassification;
        this.idTypeCoast = idTypeCoast;
        this.cost = cost;
        this.location = location;
        this.comments = comments;
        this.creationDate = creationDate;
        this.modifyDate = modifyDate;
        this.brand = brand;
        this.series = series;
        this.lifespan = lifespan;
        this.idTypeLifespan = idTypeLifespan;
        this.idTypePriority = idTypePriority;
        this.idCostCenter = idCostCenter;
        this.idPartner = idPartner;
        this.weight = weight;
        this.idTypeWeight = idTypeWeight;
        this.high = high;
        this.idTypeHigh = idTypeHigh;
        this.potential = potential;
        this.idTypePotential = idTypePotential;
        this.width = width;
        this.idTypeWidth = idTypeWidth;
        this.longMachine = longMachine;
        this.idTypeLong = idTypeLong;
        this.voltage = voltage;
        this.idTypeVoltage = idTypeVoltage;
        this.currentMachine = currentMachine;
        this.idTypeCurrent = idTypeCurrent;
        this.dimensionsObservation = dimensionsObservation;
        this.notesCustomField = notesCustomField;
        this.idMachineFather = idMachineFather;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idMachine != null ? idMachine.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScMachine))
        {
            return false;
        }
        ScMachine other = (ScMachine) object;
        if ((this.idMachine == null && other.idMachine != null) || (this.idMachine != null && !this.idMachine.equals(other.idMachine)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScMachine[ idMachine=" + idMachine + " ]";
    }

    public Long getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(Long idMachine) {
        this.idMachine = idMachine;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public ScType getIdTypeClassification() {
        return idTypeClassification;
    }

    public void setIdTypeClassification(ScType idTypeClassification) {
        this.idTypeClassification = idTypeClassification;
    }

    public ScType getIdTypeCoast() {
        return idTypeCoast;
    }

    public void setIdTypeCoast(ScType idTypeCoast) {
        this.idTypeCoast = idTypeCoast;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Long getLifespan() {
        return lifespan;
    }

    public void setLifespan(Long lifespan) {
        this.lifespan = lifespan;
    }

    public ScType getIdTypeLifespan() {
        return idTypeLifespan;
    }

    public void setIdTypeLifespan(ScType idTypeLifespan) {
        this.idTypeLifespan = idTypeLifespan;
    }

    public ScType getIdTypePriority() {
        return idTypePriority;
    }

    public void setIdTypePriority(ScType idTypePriority) {
        this.idTypePriority = idTypePriority;
    }

    public ScCostCenter getIdCostCenter() {
        return idCostCenter;
    }

    public void setIdCostCenter(ScCostCenter idCostCenter) {
        this.idCostCenter = idCostCenter;
    }

    public ScPartner getIdPartner() {
        return idPartner;
    }

    public void setIdPartner(ScPartner idPartner) {
        this.idPartner = idPartner;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public ScType getIdTypeWeight() {
        return idTypeWeight;
    }

    public void setIdTypeWeight(ScType idTypeWeight) {
        this.idTypeWeight = idTypeWeight;
    }

    public Long getHigh() {
        return high;
    }

    public void setHigh(Long high) {
        this.high = high;
    }

    public ScType getIdTypeHigh() {
        return idTypeHigh;
    }

    public void setIdTypeHigh(ScType idTypeHigh) {
        this.idTypeHigh = idTypeHigh;
    }

    public Long getPotential() {
        return potential;
    }

    public void setPotential(Long potential) {
        this.potential = potential;
    }

    public ScType getIdTypePotential() {
        return idTypePotential;
    }

    public void setIdTypePotential(ScType idTypePotential) {
        this.idTypePotential = idTypePotential;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public ScType getIdTypeWidth() {
        return idTypeWidth;
    }

    public void setIdTypeWidth(ScType idTypeWidth) {
        this.idTypeWidth = idTypeWidth;
    }

    public Long getLongMachine() {
        return longMachine;
    }

    public void setLongMachine(Long longMachine) {
        this.longMachine = longMachine;
    }

    public ScType getIdTypeLong() {
        return idTypeLong;
    }

    public void setIdTypeLong(ScType idTypeLong) {
        this.idTypeLong = idTypeLong;
    }

    public Long getVoltage() {
        return voltage;
    }

    public void setVoltage(Long voltage) {
        this.voltage = voltage;
    }

    public ScType getIdTypeVoltage() {
        return idTypeVoltage;
    }

    public void setIdTypeVoltage(ScType idTypeVoltage) {
        this.idTypeVoltage = idTypeVoltage;
    }

    public Long getCurrentMachine() {
        return currentMachine;
    }

    public void setCurrentMachine(Long currentMachine) {
        this.currentMachine = currentMachine;
    }

    public ScType getIdTypeCurrent() {
        return idTypeCurrent;
    }

    public void setIdTypeCurrent(ScType idTypeCurrent) {
        this.idTypeCurrent = idTypeCurrent;
    }

    public String getDimensionsObservation() {
        return dimensionsObservation;
    }

    public void setDimensionsObservation(String dimensionsObservation) {
        this.dimensionsObservation = dimensionsObservation;
    }

    public String getNotesCustomField() {
        return notesCustomField;
    }

    public void setNotesCustomField(String notesCustomField) {
        this.notesCustomField = notesCustomField;
    }

    public ScMachine getIdMachineFather() {
        return idMachineFather;
    }

    public void setIdMachineFather(ScMachine idMachineFather) {
        this.idMachineFather = idMachineFather;
    }

}
