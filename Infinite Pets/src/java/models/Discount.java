/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "discount")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Discount.findAll", query = "SELECT d FROM Discount d")
    , @NamedQuery(name = "Discount.findByDiscountID", query = "SELECT d FROM Discount d WHERE d.discountID = :discountID")
    , @NamedQuery(name = "Discount.findByDiscount", query = "SELECT d FROM Discount d WHERE d.discount = :discount")
    , @NamedQuery(name = "Discount.findByDiscountType", query = "SELECT d FROM Discount d WHERE d.discountType = :discountType")})
public class Discount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DiscountID")
    private Integer discountID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "Discount")
    private BigDecimal discount;
    @Basic(optional = false)
    @Column(name = "DiscountType")
    private Character discountType;
    @JoinColumn(name = "PromoID", referencedColumnName = "PromoID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Promotion promoID;
    @JoinColumn(name = "ServiceID", referencedColumnName = "ServiceID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Service serviceID;

    public Discount() {
    }

    public Discount(Integer discountID) {
        this.discountID = discountID;
    }

    public Discount(Integer discountID, BigDecimal discount, Character discountType) {
        this.discountID = discountID;
        this.discount = discount;
        this.discountType = discountType;
    }

    public Integer getDiscountID() {
        return discountID;
    }

    public void setDiscountID(Integer discountID) {
        this.discountID = discountID;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Character getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Character discountType) {
        this.discountType = discountType;
    }

    public Promotion getPromoID() {
        return promoID;
    }

    public void setPromoID(Promotion promoID) {
        this.promoID = promoID;
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
        hash += (discountID != null ? discountID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Discount)) {
            return false;
        }
        Discount other = (Discount) object;
        if ((this.discountID == null && other.discountID != null) || (this.discountID != null && !this.discountID.equals(other.discountID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Discount[ discountID=" + discountID + " ]";
    }
    
}
