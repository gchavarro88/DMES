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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "sc_input", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScInput.findAll", query = "SELECT s FROM ScInput s ORDER BY s.creationDate DESC"),
    @NamedQuery(name = "ScInput.findByIdInput", query = "SELECT s FROM ScInput s WHERE s.idInput = :idInput"),
    @NamedQuery(name = "ScInput.findByTypeMaterial", query = "SELECT s FROM ScInput s WHERE s.typeMaterial = :typeMaterial"),
    @NamedQuery(name = "ScInput.findByExpiryDate", query = "SELECT s FROM ScInput s WHERE s.expiryDate = :expiryDate"),
    @NamedQuery(name = "ScInput.findByPackingUnit", query = "SELECT s FROM ScInput s WHERE s.packingUnit = :packingUnit"),
    @NamedQuery(name = "ScInput.findByMark", query = "SELECT s FROM ScInput s WHERE s.mark = :mark"),
    @NamedQuery(name = "ScInput.findByValue", query = "SELECT s FROM ScInput s WHERE s.value = :value"),
    @NamedQuery(name = "ScInput.findByPathPicture", query = "SELECT s FROM ScInput s WHERE s.pathPicture = :pathPicture"),
    @NamedQuery(name = "ScInput.findBySerie", query = "SELECT s FROM ScInput s WHERE s.serie = :serie")
})
public class ScInput implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscinput")
    @SequenceGenerator(name = "dmes.sqscinput", sequenceName = "dmes.sqscinput", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_input")
    private Long idInput;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "type_material")
    private String typeMaterial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "packing_unit")
    private String packingUnit;
    @Basic(optional = false)    
    @Size(min = 1, max = 200)
    @Column(name = "mark")
    private String mark;
    @Basic(optional = false)
    @NotNull
    @Column(name = "value")
    private long value;
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInput")
    private List<ScInputEquivalence> scInputEquivalenceList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInputReferenced")
//    private List<ScInputEquivalence> scInputEquivalenceList1;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInput")
    private List<ScInputDimension> scInputDimensionList; 
//    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInput")
    private List<ScInputSpecifications> scInputSpecifications;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInput")
    private List<ScInputObservations> scInputObservationsList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInput")
    private List<ScInputFeactures> scInputFeacturesList;
    
    @JoinColumn(name = "cost_center", referencedColumnName = "id_cost_center")
    @ManyToOne(optional = false)
    private ScCostCenter costCenter;
    @JoinColumn(name = "id_input_stock", referencedColumnName = "id_input_stock")
    @ManyToOne(optional = false)
    private ScInputStock inputStock;
    @JoinColumn(name = "id_input_location", referencedColumnName = "id_input_location")
    @ManyToOne(optional = false)
    private ScInputLocation inputLocation;
    @JoinColumn(name = "supplier_guarantee", referencedColumnName = "id_partner")
    @ManyToOne(optional = false)
    private ScPartner supplierGuarantee;

    public ScInput()
    {
    }

    public ScInput(Long idInput)
    {
        this.idInput = idInput;
    }

    public ScInput(Long idInput, String typeMaterial, Date expiryDate, String packingUnit, String mark, long value, String serie)
    {
        this.idInput = idInput;
        this.typeMaterial = typeMaterial;
        this.expiryDate = expiryDate;
        this.packingUnit = packingUnit;
        this.mark = mark;
        this.value = value;
        this.serie = serie;
    }

    public Long getIdInput()
    {
        return idInput;
    }

    public void setIdInput(Long idInput)
    {
        this.idInput = idInput;
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

    public String getPackingUnit()
    {
        return packingUnit;
    }

    public void setPackingUnit(String packingUnit)
    {
        this.packingUnit = packingUnit;
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

    @XmlTransient
    public List<ScInputEquivalence> getScInputEquivalenceList()
    {
        return scInputEquivalenceList;
    }

    public void setScInputEquivalenceList(List<ScInputEquivalence> scInputEquivalenceList)
    {
        this.scInputEquivalenceList = scInputEquivalenceList;
    }

//    @XmlTransient
//    public List<ScInputEquivalence> getScInputEquivalenceList1() {
//        return scInputEquivalenceList1;
//    }
//
//    public void setScInputEquivalenceList1(List<ScInputEquivalence> scInputEquivalenceList1) {
//        this.scInputEquivalenceList1 = scInputEquivalenceList1;
//    }
//    @XmlTransient
//    public List<ScInputDimension> getScInputDimensionList()
//    {
//        return scInputDimensionList;
//    }
//
//    public void setScInputDimensionList(List<ScInputDimension> scInputDimensionList)
//    {
//        this.scInputDimensionList = scInputDimensionList;
//    }
//
//    public ScInputSpecifications getScInputSpecifications()
//    {
//        return scInputSpecifications;
//    }
//
//    public void setScInputSpecifications(ScInputSpecifications scInputSpecifications)
//    {
//        this.scInputSpecifications = scInputSpecifications;
//    }
//
//    @XmlTransient
//    public List<ScInputObservations> getScInputObservationsList()
//    {
//        return scInputObservationsList;
//    }
//
//    public void setScInputObservationsList(List<ScInputObservations> scInputObservationsList)
//    {
//        this.scInputObservationsList = scInputObservationsList;
//    }
//
//    @XmlTransient
//    public List<ScInputFeactures> getScInputFeacturesList()
//    {
//        return scInputFeacturesList;
//    }
//
//    public void setScInputFeacturesList(List<ScInputFeactures> scInputFeacturesList)
//    {
//        this.scInputFeacturesList = scInputFeacturesList;
//    }

    public ScInputStock getInputStock()
    {
        return inputStock;
    }

    public void setInputStock(ScInputStock inputStock)
    {
        this.inputStock = inputStock;
    }

    public ScInputLocation getInputLocation()
    {
        return inputLocation;
    }

    public void setInputLocation(ScInputLocation inputLocation)
    {
        this.inputLocation = inputLocation;
    }

    

    public ScCostCenter getCostCenter()
    {
        return costCenter;
    }

    public void setCostCenter(ScCostCenter costCenter)
    {
        this.costCenter = costCenter;
    }

    public ScPartner getSupplierGuarantee()
    {
        return supplierGuarantee;
    }

    public void setSupplierGuarantee(ScPartner supplierGuarantee)
    {
        this.supplierGuarantee = supplierGuarantee;
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

    
    
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idInput != null ? idInput.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScInput))
        {
            return false;
        }
        ScInput other = (ScInput) object;
        if ((this.idInput == null && other.idInput != null) || (this.idInput != null && !this.idInput.equals(other.idInput)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScInput[ idInput=" + idInput + " ]";
    }

}
