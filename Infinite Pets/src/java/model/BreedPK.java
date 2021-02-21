/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Riley
 */
@Embeddable
public class BreedPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "breed_id")
    private int breedId;
    @Basic(optional = false)
    @Column(name = "animal_type_id")
    private int animalTypeId;

    public BreedPK() {
    }

    public BreedPK(int breedId, int animalTypeId) {
        this.breedId = breedId;
        this.animalTypeId = animalTypeId;
    }

    public int getBreedId() {
        return breedId;
    }

    public void setBreedId(int breedId) {
        this.breedId = breedId;
    }

    public int getAnimalTypeId() {
        return animalTypeId;
    }

    public void setAnimalTypeId(int animalTypeId) {
        this.animalTypeId = animalTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) breedId;
        hash += (int) animalTypeId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BreedPK)) {
            return false;
        }
        BreedPK other = (BreedPK) object;
        if (this.breedId != other.breedId) {
            return false;
        }
        if (this.animalTypeId != other.animalTypeId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.BreedPK[ breedId=" + breedId + ", animalTypeId=" + animalTypeId + " ]";
    }
    
}
