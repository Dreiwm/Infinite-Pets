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
@Table(name = "empqualification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empqualification.findAll", query = "SELECT e FROM Empqualification e")
    , @NamedQuery(name = "Empqualification.findByEmpQualificationID", query = "SELECT e FROM Empqualification e WHERE e.empQualificationID = :empQualificationID")
    , @NamedQuery(name = "Empqualification.findByEmployeeID", query = "SELECT e FROM Empqualification e where e.employeeID = :employeeId")})
public class Empqualification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EmpQualificationID")
    private Integer empQualificationID;
    @JoinColumn(name = "EmployeeID", referencedColumnName = "EmployeeID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employeeID;
    @JoinColumn(name = "ServiceID", referencedColumnName = "ServiceID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Service serviceID;

    public Empqualification() {
    }

    public Empqualification(Integer empQualificationID) {
        this.empQualificationID = empQualificationID;
    }

    public Integer getEmpQualificationID() {
        return empQualificationID;
    }

    public void setEmpQualificationID(Integer empQualificationID) {
        this.empQualificationID = empQualificationID;
    }

    public Employee getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Employee employeeID) {
        this.employeeID = employeeID;
    }

    public Service getServiceID() {
        return serviceID;
    }

    public void setServiceID(Service serviceID) {
        this.serviceID = serviceID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empQualificationID != null ? empQualificationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empqualification)) {
            return false;
        }
        Empqualification other = (Empqualification) object;
        if ((this.empQualificationID == null && other.empQualificationID != null) || (this.empQualificationID != null && !this.empQualificationID.equals(other.empQualificationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Empqualification[ empQualificationID=" + empQualificationID + " ]";
    }
    
}
