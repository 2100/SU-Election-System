/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Model.Audit;
import Model.Student;
import Model.Studentunion;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Ahmed
 */
public class Result {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EntityManager em;
        em = Persistence.createEntityManagerFactory("ProjectPU").createEntityManager();
        em.getTransaction().begin();

        List<Studentunion> groups = em.createNamedQuery("Studentunion.findAll").getResultList();
        List<Audit> audits = em.createNamedQuery("Audit.findAll").getResultList();
        
        for(Studentunion ue : groups){
            ArrayList<Student> students = (ArrayList<Student>) ue.getStudentList();
           for(Student s : students){
               System.out.println("Group "+ ue.getGroupid() + " got " + ue.getVotes() + " votes contains the following students: "+s.getFname() + " " + s.getMidname());
           }
        }
        
        System.out.println("And the Audits");
        
       for(Audit a : audits){
           System.out.println(a.getStudentId().getFname() + " " + a.getStudentId().getMidname() + " got " + a.getVotes() + " votes" );
       }

    }

}
