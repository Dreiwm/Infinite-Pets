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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Riley
 */
@Entity
@Table(name = "gallery")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gallery.findAll", query = "SELECT g FROM Gallery g"),
    @NamedQuery(name = "Gallery.findByImageID", query = "SELECT g FROM Gallery g WHERE g.imageID = :imageID"),
    @NamedQuery(name = "Gallery.findByImageSubtitle", query = "SELECT g FROM Gallery g WHERE g.imageSubtitle = :imageSubtitle"),
    @NamedQuery(name = "Gallery.findByImagePath", query = "SELECT g FROM Gallery g WHERE g.imagePath = :imagePath"),
    @NamedQuery(name = "Gallery.findByShow", query = "SELECT g FROM Gallery g WHERE g.show = :show"),
    @NamedQuery(name = "Gallery.findByFeatured", query = "SELECT g FROM Gallery g WHERE g.featured = :featured")})
public class Gallery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ImageID")
    private Integer imageID;
    @Column(name = "ImageSubtitle")
    private String imageSubtitle;
    @Column(name = "ImagePath")
    private String imagePath;
    @Basic(optional = false)
    @Column(name = "Show")
    private boolean show;
    @Basic(optional = false)
    @Column(name = "Featured")
    private boolean featured;

    public Gallery() {
    }

    public Gallery(Integer imageID) {
        this.imageID = imageID;
    }

    public Gallery(Integer imageID, boolean show, boolean featured) {
        this.imageID = imageID;
        this.show = show;
        this.featured = featured;
    }

    public Integer getImageID() {
        return imageID;
    }

    public void setImageID(Integer imageID) {
        this.imageID = imageID;
    }

    public String getImageSubtitle() {
        return imageSubtitle;
    }

    public void setImageSubtitle(String imageSubtitle) {
        this.imageSubtitle = imageSubtitle;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean getShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean getFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (imageID != null ? imageID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gallery)) {
            return false;
        }
        Gallery other = (Gallery) object;
        if ((this.imageID == null && other.imageID != null) || (this.imageID != null && !this.imageID.equals(other.imageID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Gallery[ imageID=" + imageID + " ]";
    }
    
}
