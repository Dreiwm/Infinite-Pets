/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
 * @author Riley
 */
@Entity
@Table(name = "pets")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pets.findAll", query = "SELECT p FROM Pets p"),
    @NamedQuery(name = "Pets.findByPetID", query = "SELECT p FROM Pets p WHERE p.petID = :petID"),
    @NamedQuery(name = "Pets.findBySex", query = "SELECT p FROM Pets p WHERE p.sex = :sex"),
    @NamedQuery(name = "Pets.findByPetName", query = "SELECT p FROM Pets p WHERE p.petName = :petName")})
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
    @Column(name = "PetName")
    private String petName;
    @JoinColumn(name = "Animal_type_breed_id", referencedColumnName = "breed_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Breed animaltypebreedid;
    @JoinColumn(name = "Owner", referencedColumnName = "UserId")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Accounts owner;

    public Pets() {
    }

    public Pets(Integer petID) {
        this.petID = petID;
    }

    public Pets(Integer petID, Character sex, String petName) {
        this.petID = petID;
        this.sex = sex;
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

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Breed getAnimaltypebreedid() {
        return animaltypebreedid;
    }

    public void setAnimaltypebreedid(Breed animaltypebreedid) {
        this.animaltypebreedid = animaltypebreedid;
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
        return "model.Pets[ petID=" + petID + " ]";
    }
    
}
