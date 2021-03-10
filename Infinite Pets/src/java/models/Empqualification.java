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
 * @author BTran
 */
@Entity
@Table(name = "empqualification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empqualification.findAll", query = "SELECT e FROM Empqualification e")
    , @NamedQuery(name = "Empqualification.findByQualificationID", query = "SELECT e FROM Empqualification e WHERE e.qualificationID = :qualificationID")
    , @NamedQuery(name = "Empqualification.findByQualificationName", query = "SELECT e FROM Empqualification e WHERE e.qualificationName = :qualificationName")
    , @NamedQuery(name = "Empqualification.findByQualificationDescription", query = "SELECT e FROM Empqualification e WHERE e.qualificationDescription = :qualificationDescription")})
public class Empqualification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "QualificationID")
    private Integer qualificationID;
    @Basic(optional = false)
    @Column(name = "QualificationName")
    private String qualificationName;
    @Basic(optional = false)
    @Column(name = "QualificationDescription")
    private String qualificationDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qualifications", fetch = FetchType.EAGER)
    private List<Employee> employeeList;

    public Empqualification() {
    }

    public Empqualification(Integer qualificationID) {
        this.qualificationID = qualificationID;
    }

    public Empqualification(Integer qualificationID, String qualificationName, String qualificationDescription) {
        this.qualificationID = qualificationID;
        this.qualificationName = qualificationName;
        this.qualificationDescription = qualificationDescription;
    }

    public Integer getQualificationID() {
        return qualificationID;
    }

    public void setQualificationID(Integer qualificationID) {
        this.qualificationID = qualificationID;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public String getQualificationDescription() {
        return qualificationDescription;
    }

    public void setQualificationDescription(String qualificationDescription) {
        this.qualificationDescription = qualificationDescription;
    }

    @XmlTransient
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qualificationID != null ? qualificationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empqualification)) {
            return false;
        }
        Empqualification other = (Empqualification) object;
        if ((this.qualificationID == null && other.qualificationID != null) || (this.qualificationID != null && !this.qualificationID.equals(other.qualificationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Empqualification[ qualificationID=" + qualificationID + " ]";
    }
    
}
