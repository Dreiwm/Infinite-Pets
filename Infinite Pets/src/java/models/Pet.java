/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Chris
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
    , @NamedQuery(name = "Pet.findByPetName", query = "SELECT p FROM Pet p WHERE p.petName = :petName")})
public class Pet implements Serializable {

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
    @JoinColumn(name = "Owner", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Account owner;

    public Pet() {
    }

    public Pet(Integer petID) {
        this.petID = petID;
    }

    public Pet(Integer petID, Character sex, String species, String breed, String petName) {
        this.petID = petID;
        this.sex = sex;
        this.species = species;
        this.breed = breed;
        this.petName = petName;
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
    
}
