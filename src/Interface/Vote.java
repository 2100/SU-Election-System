/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Model.Audit;
import Model.Student;
import Model.Studentunion;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.persistence.EntityManager;

/**
 *
 * @author Ahmed
 */
public class Vote extends Application {

    int counteraudit = 0;
    EntityManager em;
    Student s;
    int voteGrp1;
    int voteGrp2;
    int voteAbs;
    int voteA1;
    int voteA2;
    int voteA3;
    CheckBox audit1;
    CheckBox audit2;
    CheckBox audit3;
    RadioButton grp1;
    RadioButton grp2;
    RadioButton abs;

    public Vote(EntityManager em, Student s) {
        this.em = em;
        this.s = s;
    }

    
    
    Stage primaryStage;
    
    

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(50));
        Button btn = new Button("Submit");
        btn.setOnAction(new ButtonHandle());

        VBox top = new VBox();
        GridPane bottom = new GridPane();

        bottom.setPadding(
                new Insets(11.5, 12.5, 13.5, 14.5));
        bottom.setHgap(
                50);
        bottom.setVgap(
                20);

        audit1 = new CheckBox("Name 1");
        audit2 = new CheckBox("Name 2");
        audit3 = new CheckBox("Name 3");
        audit1.setOnAction(new CheckBoxHandle());
        audit2.setOnAction(new CheckBoxHandle());
        audit3.setOnAction(new CheckBoxHandle());

        GridPane center = new GridPane();

        center.setPadding(
                new Insets(11.5, 12.5, 13.5, 14.5));
        center.setHgap(
                50);
        center.setVgap(
                5.5);

        ToggleGroup tg = new ToggleGroup();
         grp1 = new RadioButton("Group 1");
         grp2 = new RadioButton("Group 2");
         abs = new RadioButton("Abstain");

        grp1.setToggleGroup(tg);
        grp2.setToggleGroup(tg);
        abs.setToggleGroup(tg);

        Label title = new Label("Choose 1 group and 2 audits");
        Label ue = new Label("Student Union");

        top.setAlignment(Pos.CENTER);
        top.setSpacing(
                20);
        top.getChildren()
                .add(title);
        top.getChildren()
                .add(ue);

        center.add(new ImageView(), 0, 0);
        center.add(new ImageView(), 0, 1);
        center.add(new ImageView(), 0, 2);
        center.add(grp1, 0, 3);
        center.add(new ImageView(), 1, 0);
        center.add(new ImageView(), 1, 1);
        center.add(new ImageView(), 1, 2);
        center.add(grp2, 1, 3);
        center.add(abs, 2, 3);
        bottom.add(new Label("Audits"), 1, 0);
        bottom.add(new ImageView(), 0, 1);
        bottom.add(audit1,0, 2);
        bottom.add(new ImageView(), 1, 1);
        bottom.add(audit2,1, 2);
        bottom.add(new ImageView(), 2, 1);
        bottom.add(audit3,2, 2);
        bottom.add(btn,2, 3);

        root.setCenter(center);
        root.setTop(top);
        root.setBottom(bottom);
        Scene scene = new Scene(root, 600, 600);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    class CheckBoxHandle implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            if (e.getSource().equals(audit1)) {
                if (counteraudit == 2 && audit1.isSelected()) {
                    audit1.setSelected(false);
                    return;
                }
                if (audit1.isSelected()) {
                    counteraudit++;
                } else {
                    counteraudit--;
                }
            } else if (e.getSource().equals(audit2)) {
                if (counteraudit == 2 && audit2.isSelected()) {
                    audit2.setSelected(false);
                    return;
                }
                if (audit2.isSelected()) {
                    counteraudit++;
                } else {
                    counteraudit--;
                }
            } else {
                if (counteraudit == 2 && audit3.isSelected()) {
                    audit3.setSelected(false);
                    return;
                }
                if (audit3.isSelected()) {
                    counteraudit++;
                } else {
                    counteraudit--;
                }
            }
        }
    }
    
    class ButtonHandle implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            
            short v = 1;
        if (abs.isSelected()) {
            Studentunion grp = (Studentunion) em.createNamedQuery("Studentunion.findByGroupid").setParameter("groupid", 11).getSingleResult();
            voteAbs = grp.getVotes();
            voteAbs++;
            s.setVotedsu(v);
            grp.setVotes(voteAbs);
            em.persist(grp);
        }
        if (grp1.isSelected()) {
            Studentunion grp = (Studentunion) em.createNamedQuery("Studentunion.findByGroupid").setParameter("groupid", 1).getSingleResult();
            voteGrp1 = grp.getVotes();
            voteGrp1++;
            
            s.setVotedsu(v);
            grp.setVotes(voteGrp1);
            em.persist(grp);
        }
        if (grp2.isSelected()) {
            Studentunion grp = (Studentunion) em.createNamedQuery("Studentunion.findByGroupid").setParameter("groupid", 2).getSingleResult();
            voteGrp2 = grp.getVotes();
            voteGrp2++;
            s.setVotedsu(v);
            grp.setVotes(voteGrp2);
            em.persist(grp);
        }
        if (audit1.isSelected()) {
            Audit a = (Audit) em.createNamedQuery("Audit.findById").setParameter("id", 1).getSingleResult();
            voteA1 = a.getVotes();
            voteA1++;
            s.setVotedaudit(v);
            a.setVotes(voteA1);
            em.persist(a);
        }
        
        if (audit2.isSelected()) {
            Audit a = (Audit) em.createNamedQuery("Audit.findById").setParameter("id", 2).getSingleResult();
            voteA2 = a.getVotes();
            voteA2++;
            s.setVotedaudit(v);
            a.setVotes(voteA2);
            em.persist(a);
        }
        
        if (audit3.isSelected()) {
            Audit a = (Audit) em.createNamedQuery("Audit.findById").setParameter("id", 3).getSingleResult();
            voteA3 = a.getVotes();
            voteA3++;
            s.setVotedaudit(v);
            a.setVotes(voteA3);
            em.persist(a);
        }
        em.persist(s);
        em.getTransaction().commit();
        em.close();
            
        }
        
    }
}
