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
import javax.persistence.FetchType;
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
 * @author BTran
 */
@Entity
@Table(name = "pets")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pets.findAll", query = "SELECT p FROM Pets p")
    , @NamedQuery(name = "Pets.findByPetID", query = "SELECT p FROM Pets p WHERE p.petID = :petID")
    , @NamedQuery(name = "Pets.findBySex", query = "SELECT p FROM Pets p WHERE p.sex = :sex")
    , @NamedQuery(name = "Pets.findBySpecies", query = "SELECT p FROM Pets p WHERE p.species = :species")
    , @NamedQuery(name = "Pets.findByBreed", query = "SELECT p FROM Pets p WHERE p.breed = :breed")
    , @NamedQuery(name = "Pets.findByPetName", query = "SELECT p FROM Pets p WHERE p.petName = :petName")})
public class Pets implements Serializable {

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
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Accounts owner;

    public Pets() {
    }

    public Pets(Integer petID) {
        this.petID = petID;
    }

    public Pets(Integer petID, Character sex, String species, String breed, String petName) {
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

    public Accounts getOwner() {
        return owner;
    }

    public void setOwner(Accounts owner) {
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
        if (!(object instanceof Pets)) {
            return false;
        }
        Pets other = (Pets) object;
        if ((this.petID == null && other.petID != null) || (this.petID != null && !this.petID.equals(other.petID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Pets[ petID=" + petID + " ]";
    }
    
}
