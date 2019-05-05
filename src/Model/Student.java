/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ahmed
 */
@Entity
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
    , @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.id = :id")
    , @NamedQuery(name = "Student.findByFname", query = "SELECT s FROM Student s WHERE s.fname = :fname")
    , @NamedQuery(name = "Student.findByMidname", query = "SELECT s FROM Student s WHERE s.midname = :midname")
    , @NamedQuery(name = "Student.findByLname", query = "SELECT s FROM Student s WHERE s.lname = :lname")
    , @NamedQuery(name = "Student.findByDepartment", query = "SELECT s FROM Student s WHERE s.department = :department")
    , @NamedQuery(name = "Student.findByYear", query = "SELECT s FROM Student s WHERE s.year = :year")
    , @NamedQuery(name = "Student.findByVotedsu", query = "SELECT s FROM Student s WHERE s.votedsu = :votedsu")
    , @NamedQuery(name = "Student.findByVotedaudit", query = "SELECT s FROM Student s WHERE s.votedaudit = :votedaudit")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fname")
    private String fname;
    @Column(name = "midname")
    private String midname;
    @Column(name = "lname")
    private String lname;
    @Column(name = "department")
    private String department;
    @Column(name = "year")
    private String year;
    @Column(name = "votedsu")
    private Short votedsu;
    @Column(name = "votedaudit")
    private Short votedaudit;
    @JoinColumn(name = "studentunion_groupid", referencedColumnName = "groupid")
    @ManyToOne(optional = false)
    private Studentunion studentunionGroupid;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "studentId")
    private Audit audit;

    public Student() {
    }

    public Student(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMidname() {
        return midname;
    }

    public void setMidname(String midname) {
        this.midname = midname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Short getVotedsu() {
        return votedsu;
    }

    public void setVotedsu(Short votedsu) {
        this.votedsu = votedsu;
    }

    public Short getVotedaudit() {
        return votedaudit;
    }

    public void setVotedaudit(Short votedaudit) {
        this.votedaudit = votedaudit;
    }

    public Studentunion getStudentunionGroupid() {
        return studentunionGroupid;
    }

    public void setStudentunionGroupid(Studentunion studentunionGroupid) {
        this.studentunionGroupid = studentunionGroupid;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Student[ id=" + id + " ]";
    }
    
}
