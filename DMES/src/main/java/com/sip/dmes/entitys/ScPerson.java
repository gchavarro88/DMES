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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author gchavarro88
 */
@Entity
@Table(name = "sc_person", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScPerson.findAll", query = "SELECT s FROM ScPerson s"),
    @NamedQuery(name = "ScPerson.findByIdPerson", query = "SELECT s FROM ScPerson s WHERE s.idPerson = :idPerson"),
    @NamedQuery(name = "ScPerson.findByFirstName", query = "SELECT s FROM ScPerson s WHERE s.firstName = :firstName"),
    @NamedQuery(name = "ScPerson.findByLastName", query = "SELECT s FROM ScPerson s WHERE s.lastName = :lastName"),
    @NamedQuery(name = "ScPerson.findByAge", query = "SELECT s FROM ScPerson s WHERE s.age = :age"),
    @NamedQuery(name = "ScPerson.findByCountry", query = "SELECT s FROM ScPerson s WHERE s.country = :country"),
    @NamedQuery(name = "ScPerson.findByCity", query = "SELECT s FROM ScPerson s WHERE s.city = :city"),
    @NamedQuery(name = "ScPerson.findByPersonalInformation", query = "SELECT s FROM ScPerson s WHERE s.personalInformation = :personalInformation"),
    @NamedQuery(name = "ScPerson.findByDomicilie", query = "SELECT s FROM ScPerson s WHERE s.domicilie = :domicilie"),
    @NamedQuery(name = "ScPerson.findByStudies", query = "SELECT s FROM ScPerson s WHERE s.studies = :studies"),
    @NamedQuery(name = "ScPerson.findByDescription", query = "SELECT s FROM ScPerson s WHERE s.description = :description"),
    @NamedQuery(name = "ScPerson.findByPathPhoto", query = "SELECT s FROM ScPerson s WHERE s.pathPhoto = :pathPhoto"),
    @NamedQuery(name = "ScPerson.findByCreationDate", query = "SELECT s FROM ScPerson s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScPerson.findByModifyDate", query = "SELECT s FROM ScPerson s WHERE s.modifyDate = :modifyDate")
})
public class ScPerson implements Serializable
{
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson")
    private List<ScPersonDocumentationAttached> scPersonDocumentationAttachedList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson")
    private List<ScEmployee> scEmployeeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson")
    private List<ScPersonObservations> scPersonObservationsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson")
    private List<ScMails> scMailsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson")
    private List<ScPhones> scPhonesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson")
    private List<ScPartner> scPartnerList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson")
    private List<ScPersonSpecifications> scPersonSpecificationsList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_person")
    private Long idPerson;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "age")
    private short age;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "city")
    private String city;
    @Size(max = 2000)
    @Column(name = "personal_information")
    private String personalInformation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "domicilie")
    private String domicilie;
    @Size(max = 2000)
    @Column(name = "studies")
    private String studies;
    @Size(max = 2000)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "path_photo")
    private String pathPhoto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "modify_date")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson")
    private List<ScUsers> scUsersList;

    public ScPerson()
    {
    }

    public ScPerson(Long idPerson)
    {
        this.idPerson = idPerson;
    }

    public ScPerson(Long idPerson, String firstName, String lastName, short age, String country, String city, String domicilie, String pathPhoto, Date creationDate)
    {
        this.idPerson = idPerson;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
        this.city = city;
        this.domicilie = domicilie;
        this.pathPhoto = pathPhoto;
        this.creationDate = creationDate;
    }

    public Long getIdPerson()
    {
        return idPerson;
    }

    public void setIdPerson(Long idPerson)
    {
        this.idPerson = idPerson;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public short getAge()
    {
        return age;
    }

    public void setAge(short age)
    {
        this.age = age;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getPersonalInformation()
    {
        return personalInformation;
    }

    public void setPersonalInformation(String personalInformation)
    {
        this.personalInformation = personalInformation;
    }

    public String getDomicilie()
    {
        return domicilie;
    }

    public void setDomicilie(String domicilie)
    {
        this.domicilie = domicilie;
    }

    public String getStudies()
    {
        return studies;
    }

    public void setStudies(String studies)
    {
        this.studies = studies;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getPathPhoto()
    {
        return pathPhoto;
    }

    public void setPathPhoto(String pathPhoto)
    {
        this.pathPhoto = pathPhoto;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public Date getModifyDate()
    {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate)
    {
        this.modifyDate = modifyDate;
    }

    public List<ScUsers> getScUsersList()
    {
        return scUsersList;
    }

    public void setScUsersList(List<ScUsers> scUsersList)
    {
        this.scUsersList = scUsersList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idPerson != null ? idPerson.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScPerson))
        {
            return false;
        }
        ScPerson other = (ScPerson) object;
        if ((this.idPerson == null && other.idPerson != null) || (this.idPerson != null && !this.idPerson.equals(other.idPerson)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScPerson[ idPerson=" + idPerson + " ]";
    }

    public List<ScPersonDocumentationAttached> getScPersonDocumentationAttachedList()
    {
        return scPersonDocumentationAttachedList;
    }

    public void setScPersonDocumentationAttachedList(List<ScPersonDocumentationAttached> scPersonDocumentationAttachedList)
    {
        this.scPersonDocumentationAttachedList = scPersonDocumentationAttachedList;
    }

    public List<ScEmployee> getScEmployeeList()
    {
        return scEmployeeList;
    }

    public void setScEmployeeList(List<ScEmployee> scEmployeeList)
    {
        this.scEmployeeList = scEmployeeList;
    }

    public List<ScPersonObservations> getScPersonObservationsList()
    {
        return scPersonObservationsList;
    }

    public void setScPersonObservationsList(List<ScPersonObservations> scPersonObservationsList)
    {
        this.scPersonObservationsList = scPersonObservationsList;
    }

    public List<ScMails> getScMailsList()
    {
        return scMailsList;
    }

    public void setScMailsList(List<ScMails> scMailsList)
    {
        this.scMailsList = scMailsList;
    }

    public List<ScPhones> getScPhonesList()
    {
        return scPhonesList;
    }

    public void setScPhonesList(List<ScPhones> scPhonesList)
    {
        this.scPhonesList = scPhonesList;
    }

    public List<ScPartner> getScPartnerList()
    {
        return scPartnerList;
    }

    public void setScPartnerList(List<ScPartner> scPartnerList)
    {
        this.scPartnerList = scPartnerList;
    }

    public List<ScPersonSpecifications> getScPersonSpecificationsList()
    {
        return scPersonSpecificationsList;
    }

    public void setScPersonSpecificationsList(List<ScPersonSpecifications> scPersonSpecificationsList)
    {
        this.scPersonSpecificationsList = scPersonSpecificationsList;
    }
    
}
