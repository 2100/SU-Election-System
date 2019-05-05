/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Model.Student;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Ahmed
 */
public class Login extends Application {

    private Object btn;
    EntityManager em;
    TextField FnameTextField;
    TextField FatherTextField;
    TextField LastNameTextField;
    PasswordField pwBox;
    String str;

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Student Login:");
        scenetitle.setId("welcome-text");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        Label FirstName = new Label("First Name:");
        grid.add(FirstName, 0, 1);
        FnameTextField = new TextField();
        grid.add(FnameTextField, 1, 1);
        Label FatherName = new Label("Father's Name:");
        grid.add(FatherName, 0, 2);
        FatherTextField = new TextField();
        grid.add(FatherTextField, 1, 2);
        Label LastName = new Label("Third Name:");
        grid.add(LastName, 0, 3);
        LastNameTextField = new TextField();
        grid.add(LastNameTextField, 1, 3);
        Label pw = new Label("Id:");
        grid.add(pw, 0, 4);
        pwBox = new PasswordField();
        grid.add(pwBox, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 11);
        ComboBox comboBox = new ComboBox();
        comboBox.setPromptText("Department");
        comboBox.getItems().add("Tic");
        comboBox.getItems().add("Pec");
        comboBox.getItems().add("Archi");
        comboBox.getItems().add("Gestion");
        comboBox.getItems().add("Langue");
        grid.add(comboBox, 1, 7);

        ComboBox YearBox = new ComboBox();
        YearBox.setPromptText("Year");
        YearBox.getItems().add("1st Year");
        YearBox.getItems().add("2nd Year");
        YearBox.getItems().add("3rd Year");
        YearBox.getItems().add("4th Year");
        YearBox.getItems().add("5th Year");
        grid.add(YearBox, 1, 8);
        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                str = comboBox.getSelectionModel().getSelectedItem().toString();
                if (str.equals("Gestion") || str.equals("Langue")) {
                    MenuItem m = new MenuItem();
                    YearBox.getItems().clear();
                    YearBox.getItems().addAll("1st Year", "2nd Year", "3rd Year", "4th Year");
                } else {
                    YearBox.getItems().clear();
                    YearBox.getItems().addAll("1st Year", "2nd Year", "3rd Year", "4th Year", "5th Year");
                }
            }
        });
        Button btn = new Button("Submit");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 10);

        btn.setOnAction(new Buttonhandler());

        Scene scene = new Scene(grid, 600, 600);
        primaryStage.setTitle("Student Login");
        primaryStage.setScene(scene);
        //scene.getStylesheets().add(javafxelectionnew.Audit.class.getResource("Style.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    class Buttonhandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            String fname = FnameTextField.getText();
            String midname = FatherTextField.getText();
            String lname = LastNameTextField.getText();
            em = Persistence.createEntityManagerFactory("ProjectPU").createEntityManager();
            em.getTransaction().begin();

            List<Student> students = em.createNamedQuery("Student.findByFname").setParameter("fname", fname).getResultList();
            for (Student s : students) {

                if (s.getMidname().equalsIgnoreCase(midname) && s.getLname().equalsIgnoreCase(lname) && s.getDepartment().equalsIgnoreCase(str)) {
                    System.out.println("found");

                }

            }
        }
    }

}
