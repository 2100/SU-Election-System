/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Model.Audit;
import Model.Student;
import Model.Studentunion;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Ahmed
 */
public class Result extends Application {

    EntityManager em;
    Button btnAudit = new Button("Find Audit Results");
    ComboBox groups = new ComboBox();
    TextField name1 = new TextField();
    TextField name2 = new TextField();
    TextField name3 = new TextField();
    TextField votesSU = new TextField();
    TextField audit1 = new TextField();
    TextField audit2 = new TextField();
    TextField audit3 = new TextField();

    @Override
    public void start(Stage primaryStage) {

        em = Persistence.createEntityManagerFactory("ProjectPU").createEntityManager();
        em.getTransaction().begin();

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(50));
        name1.setEditable(false);
        name2.setEditable(false);
        name3.setEditable(false);
        votesSU.setEditable(false);
        audit1.setEditable(false);
        audit2.setEditable(false);
        audit3.setEditable(false);

        GridPane center = new GridPane();
        GridPane bottom = new GridPane();

        center.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        center.setAlignment(Pos.CENTER);
        center.setHgap(10);
        center.setVgap(10);

        bottom.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        bottom.setAlignment(Pos.CENTER_RIGHT);
        bottom.setHgap(10);
        bottom.setVgap(10);

        groups.setPromptText("Groups");
        groups.getItems().add("Group 1");
        groups.getItems().add("Group 2");
        groups.getItems().add("Abstains");
        center.add(new Label("Student Union Results"), 0, 0);
        center.add(groups, 0, 1);
        center.add(name1, 0, 2);
        center.add(name2, 0, 3);
        center.add(name3, 0, 4);
        center.add(votesSU, 0, 5);
        groups.setOnAction(new ComboBoxHandler());

        bottom.add(new Label("Audits Results"), 0, 0);
        bottom.add(audit1, 0, 1, 3, 1);
        bottom.add(audit2, 0, 2, 3, 1);
        bottom.add(audit3, 0, 3, 3, 1);
        bottom.add(btnAudit, 1, 4);
        btnAudit.setOnAction(new ButtonHandler());

        root.setCenter(center);
        root.setBottom(bottom);
        Scene scene = new Scene(root, 600, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Results");
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    class ComboBoxHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            String str = groups.getSelectionModel().getSelectedItem().toString();

            List<Studentunion> allGroups = em.createNamedQuery("Studentunion.findAll").getResultList();

            for (Studentunion g : allGroups) {
                int id = g.getGroupid();
                if (str.equalsIgnoreCase("Group 1") && (id == 1)) {
                    List<Student> students = g.getStudentList();

                    name1.setText(students.get(0).getFname() + " " + students.get(0).getMidname());
                    name2.setText(students.get(1).getFname() + " " + students.get(1).getMidname());
                    name3.setText(students.get(2).getFname() + " " + students.get(2).getMidname());
                    votesSU.setText(g.getVotes().toString() + " Votes");
                } else if (str.equalsIgnoreCase("Group 2") && (id == 2)) {
                    List<Student> students = g.getStudentList();

                    name1.setText(students.get(0).getFname() + " " + students.get(0).getMidname());
                    name2.setText(students.get(1).getFname() + " " + students.get(1).getMidname());
                    name3.setText(students.get(2).getFname() + " " + students.get(2).getMidname());
                    votesSU.setText(g.getVotes().toString() + " Votes");
                } else if (str.equalsIgnoreCase("Abstains")) {
                    name1.setText(g.getVotes().toString() + " Abstain Votes");
                    name2.clear();
                    name3.clear();
                    votesSU.clear();

                }
            }
            
        }
    }

    class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            List<Audit> allAudits = em.createNamedQuery("Audit.findAll").getResultList();
            audit1.setText(allAudits.get(0).getStudentId().getFname() + " " + allAudits.get(0).getStudentId().getMidname() + " got " + allAudits.get(0).getVotes().toString() + " Votes");
            audit2.setText(allAudits.get(1).getStudentId().getFname() + " " + allAudits.get(1).getStudentId().getMidname() + " got " + allAudits.get(1).getVotes().toString() + " Votes");
            audit3.setText(allAudits.get(2).getStudentId().getFname() + " " + allAudits.get(2).getStudentId().getMidname() + " got " + allAudits.get(2).getVotes().toString() + " Votes");
            
        }
        
    }
}
