/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "breed")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Breed.findAll", query = "SELECT b FROM Breed b")
    , @NamedQuery(name = "Breed.findByBreedId", query = "SELECT b FROM Breed b WHERE b.breedPK.breedId = :breedId")
    , @NamedQuery(name = "Breed.findByAnimalTypeId", query = "SELECT b FROM Breed b WHERE b.breedPK.animalTypeId = :animalTypeId")
    , @NamedQuery(name = "Breed.findByBreedName", query = "SELECT b FROM Breed b WHERE b.breedName = :breedName")})
public class Breed implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BreedPK breedPK;
    @Column(name = "breed_name")
    private String breedName;
    @JoinColumn(name = "animal_type_id", referencedColumnName = "animal_Type_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AnimalType animalType;

    public Breed() {
    }

    public Breed(BreedPK breedPK) {
        this.breedPK = breedPK;
    }

    public Breed(int breedId, int animalTypeId) {
        this.breedPK = new BreedPK(breedId, animalTypeId);
    }

    public BreedPK getBreedPK() {
        return breedPK;
    }

    public void setBreedPK(BreedPK breedPK) {
        this.breedPK = breedPK;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (breedPK != null ? breedPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Breed)) {
            return false;
        }
        Breed other = (Breed) object;
        if ((this.breedPK == null && other.breedPK != null) || (this.breedPK != null && !this.breedPK.equals(other.breedPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Breed[ breedPK=" + breedPK + " ]";
    }
    
}
