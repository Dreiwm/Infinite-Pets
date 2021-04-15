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
 * @author Riley
 */
@Entity
@Table(name = "appointmentService")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppointmentService.findAll", query = "SELECT a FROM AppointmentService a"),
    @NamedQuery(name = "AppointmentService.findByAppServID", query = "SELECT a FROM AppointmentService a WHERE a.appServID = :appServID")})
public class AppointmentService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AppServID")
    private Integer appServID;
    @JoinColumn(name = "AppointmentID", referencedColumnName = "AppointmentID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Appointment appointmentID;
    @JoinColumn(name = "PetID", referencedColumnName = "PetID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Pet petID;
    @JoinColumn(name = "ServiceID", referencedColumnName = "ServiceID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Service serviceID;

    public AppointmentService() {
    }

    public AppointmentService(Integer appServID) {
        this.appServID = appServID;
    }

    public Integer getAppServID() {
        return appServID;
    }

    public void setAppServID(Integer appServID) {
        this.appServID = appServID;
    }

    public Appointment getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(Appointment appointmentID) {
        this.appointmentID = appointmentID;
    }

    public Pet getPetID() {
        return petID;
    }

    public void setPetID(Pet petID) {
        this.petID = petID;
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
        hash += (appServID != null ? appServID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppointmentService)) {
            return false;
        }
        AppointmentService other = (AppointmentService) object;
        if ((this.appServID == null && other.appServID != null) || (this.appServID != null && !this.appServID.equals(other.appServID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.AppointmentService[ appServID=" + appServID + " ]";
    }
    
}
