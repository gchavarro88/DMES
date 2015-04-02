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
@Table(name = "sc_product_formulation", schema = "dmes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ScProductFormulation.findAll", query = "SELECT s FROM ScInput s ORDER BY s.creationDate DESC")
    
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
    @Column(name = "total_amount_distribution")
    private long totalAmountDistribution;
    @Column(name = "distribution_amount")
    private long distributionAmount;
    @Column(name = "distribution_value")
    private double distributionValue;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInput", fetch = FetchType.EAGER)
    private List<ScInputEquivalence> scInputEquivalenceList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInput", fetch = FetchType.EAGER)
    private List<ScInputSpecifications> scInputSpecifications;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInput", fetch = FetchType.EAGER)
    private List<ScInputObservations> scInputObservationsList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInput", fetch = FetchType.EAGER)
    private List<ScInputFeactures> scInputFeacturesList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInput", fetch = FetchType.EAGER)
    private List<ScInputDocuments> scInputDocuments;
    
    @JoinColumn(name = "cost_center", referencedColumnName = "id_cost_center")
    @ManyToOne(optional = false)
    private ScCostCenter costCenter;
    @JoinColumn(name = "id_input_stock", referencedColumnName = "id_input_stock")
    @ManyToOne(optional = false)
    private ScInputStock inputStock;
    @JoinColumn(name = "id_packing", referencedColumnName = "id_packing")
    @ManyToOne(optional = false)
    private ScPackingUnit packingUnit;
    
    @JoinColumn(name = "id_distribution_unit", referencedColumnName = "id_distribution_unit")
    @ManyToOne(optional = false)
    private ScDistributionUnit distributionUnit;
    
    @JoinColumn(name = "id_money", referencedColumnName = "id_money")
    @ManyToOne(optional = false)
    private ScMoney money;
    
    @JoinColumn(name = "id_priority", referencedColumnName = "id_priority")
    @ManyToOne(optional = false)
    private ScPriority priority;
    
    @JoinColumn(name = "id_input_dimension", referencedColumnName = "id_input_dimension")
    @ManyToOne(optional = false)
    private ScInputDimension dimension;
    
    @JoinColumn(name = "id_input_location", referencedColumnName = "id_input_location")
    @ManyToOne(optional = false)
    private ScInputLocation inputLocation;
    
    @JoinColumn(name = "supplier_guarantee", referencedColumnName = "id_partner")
    @ManyToOne(optional = false)
    private ScPartner supplierGuarantee;

    public ScProductFormulation()
    {
    }

    
    
    
   

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScInput[ idInput=" + idInput + " ]";
    }

}
