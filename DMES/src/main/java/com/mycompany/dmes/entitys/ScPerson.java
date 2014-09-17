/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.dmes.entitys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author gustavo
 */
@Entity
@Table(name = "SC_PERSON")
@NamedQueries({
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
    @NamedQuery(name = "ScPerson.findByModifyDate", query = "SELECT s FROM ScPerson s WHERE s.modifyDate = :modifyDate")})
public class ScPerson implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERSON")
    private Long idPerson;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AGE")
    private short age;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "COUNTRY")
    private String country;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CITY")
    private String city;
    @Size(max = 2000)
    @Column(name = "PERSONAL_INFORMATION")
    private String personalInformation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DOMICILIE")
    private String domicilie;
    @Size(max = 2000)
    @Column(name = "STUDIES")
    private String studies;
    @Size(max = 2000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "PATH_PHOTO")
    private String pathPhoto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "MODIFY_DATE")
    @Temporal(TemporalType.DATE)
    private Date modifyDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson", fetch = FetchType.EAGER)
    private List<ScPhones> scPhonesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson", fetch = FetchType.EAGER)
    private List<ScPersonObservations> scPersonObservationsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson", fetch = FetchType.EAGER)
    private List<ScPersonSpecifications> scPersonSpecificationsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson", fetch = FetchType.EAGER)
    private List<ScMails> scMailsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerson", fetch = FetchType.EAGER)
    private List<ScPersonDocumentationAttached> scPersonDocumentationAttachedList;

    public ScPerson() {
    }

    public ScPerson(Long idPerson) {
        this.idPerson = idPerson;
    }

    public ScPerson(Long idPerson, String firstName, String lastName, short age, String country, String city, String domicilie, String pathPhoto, Date creationDate) {
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

    public Long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(String personalInformation) {
        this.personalInformation = personalInformation;
    }

    public String getDomicilie() {
        return domicilie;
    }

    public void setDomicilie(String domicilie) {
        this.domicilie = domicilie;
    }

    public String getStudies() {
        return studies;
    }

    public void setStudies(String studies) {
        this.studies = studies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathPhoto() {
        return pathPhoto;
    }

    public void setPathPhoto(String pathPhoto) {
        this.pathPhoto = pathPhoto;
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

    public List<ScPhones> getScPhonesList() {
        return scPhonesList;
    }

    public void setScPhonesList(List<ScPhones> scPhonesList) {
        this.scPhonesList = scPhonesList;
    }

    public List<ScPersonObservations> getScPersonObservationsList() {
        return scPersonObservationsList;
    }

    public void setScPersonObservationsList(List<ScPersonObservations> scPersonObservationsList) {
        this.scPersonObservationsList = scPersonObservationsList;
    }

    public List<ScPersonSpecifications> getScPersonSpecificationsList() {
        return scPersonSpecificationsList;
    }

    public void setScPersonSpecificationsList(List<ScPersonSpecifications> scPersonSpecificationsList) {
        this.scPersonSpecificationsList = scPersonSpecificationsList;
    }

    public List<ScMails> getScMailsList() {
        return scMailsList;
    }

    public void setScMailsList(List<ScMails> scMailsList) {
        this.scMailsList = scMailsList;
    }

    public List<ScPersonDocumentationAttached> getScPersonDocumentationAttachedList() {
        return scPersonDocumentationAttachedList;
    }

    public void setScPersonDocumentationAttachedList(List<ScPersonDocumentationAttached> scPersonDocumentationAttachedList) {
        this.scPersonDocumentationAttachedList = scPersonDocumentationAttachedList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerson != null ? idPerson.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScPerson)) {
            return false;
        }
        ScPerson other = (ScPerson) object;
        if ((this.idPerson == null && other.idPerson != null) || (this.idPerson != null && !this.idPerson.equals(other.idPerson))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.dmes.entitys.ScPerson[ idPerson=" + idPerson + " ]";
    }
    
}
