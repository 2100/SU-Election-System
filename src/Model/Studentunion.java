/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ahmed
 */
@Entity
@Table(name = "studentunion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Studentunion.findAll", query = "SELECT s FROM Studentunion s")
    , @NamedQuery(name = "Studentunion.findByGroupid", query = "SELECT s FROM Studentunion s WHERE s.groupid = :groupid")
    , @NamedQuery(name = "Studentunion.findByVotes", query = "SELECT s FROM Studentunion s WHERE s.votes = :votes")})
public class Studentunion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "groupid")
    private Integer groupid;
    @Column(name = "votes")
    private Integer votes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentunionGroupid")
    private List<Student> studentList;

    public Studentunion() {
    }

    public Studentunion(Integer groupid) {
        this.groupid = groupid;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    @XmlTransient
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupid != null ? groupid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Studentunion)) {
            return false;
        }
        Studentunion other = (Studentunion) object;
        if ((this.groupid == null && other.groupid != null) || (this.groupid != null && !this.groupid.equals(other.groupid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Studentunion[ groupid=" + groupid + " ]";
    }
    
}
