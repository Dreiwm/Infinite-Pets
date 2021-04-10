/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BTran
 */
@Entity
@Table(name = "appointment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a")
    , @NamedQuery(name = "Appointment.findByAppointmentID", query = "SELECT a FROM Appointment a WHERE a.appointmentID = :appointmentID")
    , @NamedQuery(name = "Appointment.findByAppointmentDate", query = "SELECT a FROM Appointment a WHERE a.appointmentDate = :appointmentDate")
    , @NamedQuery(name = "Appointment.findByEndDate", query = "SELECT a FROM Appointment a WHERE a.endDate = :endDate")
    , @NamedQuery(name = "Appointment.findByAppointmentTime", query = "SELECT a FROM Appointment a WHERE a.appointmentTime = :appointmentTime")
    , @NamedQuery(name = "Appointment.findByConfirmed", query = "SELECT a FROM Appointment a WHERE a.confirmed = :confirmed")
    , @NamedQuery(name = "Appointment.findByPaid", query = "SELECT a FROM Appointment a WHERE a.paid = :paid")
    , @NamedQuery(name = "Appointment.findByActive", query = "SELECT a FROM Appointment a WHERE a.active = :active")})
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AppointmentID")
    private Integer appointmentID;
    @Basic(optional = false)
    @Column(name = "AppointmentDate")
    @Temporal(TemporalType.DATE)
    private Date appointmentDate;
    @Column(name = "EndDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "AppointmentTime")
    @Temporal(TemporalType.TIME)
    private Date appointmentTime;
    @Basic(optional = false)
    @Column(name = "Confirmed")
    private boolean confirmed;
    @Basic(optional = false)
    @Column(name = "Paid")
    private boolean paid;
    @Basic(optional = false)
    @Column(name = "Active")
    private boolean active;
    @JoinColumn(name = "ClientID", referencedColumnName = "UserId")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Account clientID;
    @JoinColumn(name = "EmployeeID", referencedColumnName = "EmployeeID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Employee employeeID;
    @JoinColumn(name = "PetID", referencedColumnName = "PetID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Pet petID;
    @JoinColumn(name = "ServiceID", referencedColumnName = "ServiceID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Service serviceID;

    public Appointment() {
    }

    public Appointment(Integer appointmentID) {
        this.appointmentID = appointmentID;
    }

    public Appointment(Integer appointmentID, Date appointmentDate, boolean confirmed, boolean paid, boolean active) {
        this.appointmentID = appointmentID;
        this.appointmentDate = appointmentDate;
        this.confirmed = confirmed;
        this.paid = paid;
        this.active = active;
    }

    public Integer getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(Integer appointmentID) {
        this.appointmentID = appointmentID;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean getPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Account getClientID() {
        return clientID;
    }

    public void setClientID(Account clientID) {
        this.clientID = clientID;
    }

    public Employee getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Employee employeeID) {
        this.employeeID = employeeID;
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
        hash += (appointmentID != null ? appointmentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.appointmentID == null && other.appointmentID != null) || (this.appointmentID != null && !this.appointmentID.equals(other.appointmentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Appointment[ appointmentID=" + appointmentID + " ]";
    }
    
}
