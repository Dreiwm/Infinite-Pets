/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author BTran
 */
@Entity
@Table(name = "pet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pet.findAll", query = "SELECT p FROM Pet p")
    , @NamedQuery(name = "Pet.findByPetID", query = "SELECT p FROM Pet p WHERE p.petID = :petID")
    , @NamedQuery(name = "Pet.findBySex", query = "SELECT p FROM Pet p WHERE p.sex = :sex")
    , @NamedQuery(name = "Pet.findBySpecies", query = "SELECT p FROM Pet p WHERE p.species = :species")
    , @NamedQuery(name = "Pet.findByBreed", query = "SELECT p FROM Pet p WHERE p.breed = :breed")
    , @NamedQuery(name = "Pet.findByPetName", query = "SELECT p FROM Pet p WHERE p.petName = :petName")
    , @NamedQuery(name = "Pet.findByBirthday", query = "SELECT p FROM Pet p WHERE p.birthday = :birthday")
    , @NamedQuery(name = "Pet.findByPreferredVet", query = "SELECT p FROM Pet p WHERE p.preferredVet = :preferredVet")
    , @NamedQuery(name = "Pet.findByMedicalInfo", query = "SELECT p FROM Pet p WHERE p.medicalInfo = :medicalInfo")
    , @NamedQuery(name = "Pet.findByImagePath", query = "SELECT p FROM Pet p WHERE p.imagePath = :imagePath")})
public class Pet implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "petID")
    private Collection<Appointmentservice> appointmentserviceCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PetID")
    private Integer petID;
    @Basic(optional = false)
    @Column(name = "Sex")
    private Character sex;
    @Basic(optional = false)
    @Column(name = "Species")
    private String species;
    @Basic(optional = false)
    @Column(name = "Breed")
    private String breed;
    @Basic(optional = false)
    @Column(name = "PetName")
    private String petName;
    @Basic(optional = false)
    @Column(name = "Birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Column(name = "PreferredVet")
    private String preferredVet;
    @Column(name = "MedicalInfo")
    private String medicalInfo;
    @Column(name = "ImagePath")
    private String imagePath;
    @OneToMany(mappedBy = "petID", fetch = FetchType.EAGER)
    private List<Appointment> appointmentList;
    @JoinColumn(name = "Owner", referencedColumnName = "UserId")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Account owner;

    public Pet() {
    }

    public Pet(Integer petID) {
        this.petID = petID;
    }

    public Pet(Integer petID, Character sex, String species, String breed, String petName, Date birthday) {
        this.petID = petID;
        this.sex = sex;
        this.species = species;
        this.breed = breed;
        this.petName = petName;
        this.birthday = birthday;
    }

    public Integer getPetID() {
        return petID;
    }

    public void setPetID(Integer petID) {
        this.petID = petID;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPreferredVet() {
        return preferredVet;
    }

    public void setPreferredVet(String preferredVet) {
        this.preferredVet = preferredVet;
    }

    public String getMedicalInfo() {
        return medicalInfo;
    }

    public void setMedicalInfo(String medicalInfo) {
        this.medicalInfo = medicalInfo;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @XmlTransient
    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (petID != null ? petID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pet)) {
            return false;
        }
        Pet other = (Pet) object;
        if ((this.petID == null && other.petID != null) || (this.petID != null && !this.petID.equals(other.petID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Pet[ petID=" + petID + " ]";
    }

    @XmlTransient
    public Collection<Appointmentservice> getAppointmentserviceCollection() {
        return appointmentserviceCollection;
    }

    public void setAppointmentserviceCollection(Collection<Appointmentservice> appointmentserviceCollection) {
        this.appointmentserviceCollection = appointmentserviceCollection;
    }
    
}
