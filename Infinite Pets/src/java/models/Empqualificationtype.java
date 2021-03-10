/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author BTran
 */
@Entity
@Table(name = "empqualificationtype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empqualificationtype.findAll", query = "SELECT e FROM Empqualificationtype e")
    , @NamedQuery(name = "Empqualificationtype.findByQualificationTypeID", query = "SELECT e FROM Empqualificationtype e WHERE e.qualificationTypeID = :qualificationTypeID")
    , @NamedQuery(name = "Empqualificationtype.findByQualificationName", query = "SELECT e FROM Empqualificationtype e WHERE e.qualificationName = :qualificationName")
    , @NamedQuery(name = "Empqualificationtype.findByQualificationDescription", query = "SELECT e FROM Empqualificationtype e WHERE e.qualificationDescription = :qualificationDescription")})
public class Empqualificationtype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "QualificationTypeID")
    private Integer qualificationTypeID;
    @Basic(optional = false)
    @Column(name = "QualificationName")
    private String qualificationName;
    @Basic(optional = false)
    @Column(name = "QualificationDescription")
    private String qualificationDescription;
    @ManyToMany(mappedBy = "empqualificationtypeList", fetch = FetchType.EAGER)
    private List<Employee> employeeList;

    public Empqualificationtype() {
    }

    public Empqualificationtype(Integer qualificationTypeID) {
        this.qualificationTypeID = qualificationTypeID;
    }

    public Empqualificationtype(Integer qualificationTypeID, String qualificationName, String qualificationDescription) {
        this.qualificationTypeID = qualificationTypeID;
        this.qualificationName = qualificationName;
        this.qualificationDescription = qualificationDescription;
    }

    public Integer getQualificationTypeID() {
        return qualificationTypeID;
    }

    public void setQualificationTypeID(Integer qualificationTypeID) {
        this.qualificationTypeID = qualificationTypeID;
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
        hash += (qualificationTypeID != null ? qualificationTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empqualificationtype)) {
            return false;
        }
        Empqualificationtype other = (Empqualificationtype) object;
        if ((this.qualificationTypeID == null && other.qualificationTypeID != null) || (this.qualificationTypeID != null && !this.qualificationTypeID.equals(other.qualificationTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Empqualificationtype[ qualificationTypeID=" + qualificationTypeID + " ]";
    }
    
}
