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
@Table(name = "sc_photo", schema = "dmes")
@NamedQueries(
{
    @NamedQuery(name = "ScPhoto.findAll", query = "SELECT s FROM ScPhoto s"),
    @NamedQuery(name = "ScPhoto.findByIdPhoto", query = "SELECT s FROM ScPhoto s WHERE s.idPhoto = :idPhoto"),
    @NamedQuery(name = "ScPhoto.findByPhotoName", query = "SELECT s FROM ScPhoto s WHERE s.photoName = :photoName"),
    @NamedQuery(name = "ScPhoto.findByIdMachine", query = "SELECT s FROM ScPhoto s WHERE s.idMachine = :idMachine"),
    @NamedQuery(name = "ScPhoto.findByCreationDate", query = "SELECT s FROM ScPhoto s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "ScPhoto.findByModifyDate", query = "SELECT s FROM ScPhoto s WHERE s.modifyDate = :modifyDate"),
    @NamedQuery(name = "ScPhoto.deleteByIdPhoto", query = "DELETE FROM ScPhoto s WHERE s.idPhoto = :idPhoto"),
    @NamedQuery(name = "ScPhoto.deleteByIdMachine", query = "DELETE FROM ScPhoto s WHERE s.idMachine = :idMachine")
})
public class ScPhoto implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "dmes.sqscphoto")
    @SequenceGenerator(name = "dmes.sqscphoto", sequenceName = "dmes.sqscphoto", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_photo")
    private Long idPhoto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "photo_name")
    private String photoName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "photo_path")
    private String photoPath;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "comments")
    private String comments;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;
    @JoinColumn(name = "id_machine", referencedColumnName = "id_machine")
    @ManyToOne(optional = false)
    private ScMachine idMachine;

    public ScPhoto()
    {
    }

    public ScPhoto(Long idPhoto) {
        this.idPhoto = idPhoto;
    }

    public ScPhoto(Long idPhoto, String photoName, String photoPath, String comments, Date creationDate, Date modifyDate, ScMachine idMachine) {
        this.idPhoto = idPhoto;
        this.photoName = photoName;
        this.photoPath = photoPath;
        this.comments = comments;
        this.creationDate = creationDate;
        this.modifyDate = modifyDate;
        this.idMachine = idMachine;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idPhoto != null ? idPhoto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScPhoto))
        {
            return false;
        }
        ScPhoto other = (ScPhoto) object;
        if ((this.idPhoto == null && other.idPhoto != null) || (this.idPhoto != null && !this.idPhoto.equals(other.idPhoto)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.sip.dmes.entitys.ScPhoto[ idPhoto=" + idPhoto + " ]";
    }

    public Long getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(Long idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
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

    public ScMachine getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(ScMachine idMachine) {
        this.idMachine = idMachine;
    }
        
}
