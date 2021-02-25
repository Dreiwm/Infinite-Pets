/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Chris
 */
@Entity
@Table(name = "animal_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnimalType.findAll", query = "SELECT a FROM AnimalType a")
    , @NamedQuery(name = "AnimalType.findByAnimalTypeID", query = "SELECT a FROM AnimalType a WHERE a.animalTypeID = :animalTypeID")
    , @NamedQuery(name = "AnimalType.findByAnimalType", query = "SELECT a FROM AnimalType a WHERE a.animalType = :animalType")})
public class AnimalType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "animal_Type_ID")
    private Integer animalTypeID;
    @Basic(optional = false)
    @Column(name = "animal_Type")
    private String animalType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animalType", fetch = FetchType.EAGER)
    private List<Breed> breedList;

    public AnimalType() {
    }

    public AnimalType(Integer animalTypeID) {
        this.animalTypeID = animalTypeID;
    }

    public AnimalType(Integer animalTypeID, String animalType) {
        this.animalTypeID = animalTypeID;
        this.animalType = animalType;
    }

    public Integer getAnimalTypeID() {
        return animalTypeID;
    }

    public void setAnimalTypeID(Integer animalTypeID) {
        this.animalTypeID = animalTypeID;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    @XmlTransient
    public List<Breed> getBreedList() {
        return breedList;
    }

    public void setBreedList(List<Breed> breedList) {
        this.breedList = breedList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (animalTypeID != null ? animalTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnimalType)) {
            return false;
        }
        AnimalType other = (AnimalType) object;
        if ((this.animalTypeID == null && other.animalTypeID != null) || (this.animalTypeID != null && !this.animalTypeID.equals(other.animalTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.AnimalType[ animalTypeID=" + animalTypeID + " ]";
    }
    
}
