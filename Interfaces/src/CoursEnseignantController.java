import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CoursEnseignantController {

    @FXML
    private TextField nomCoursField;

    @FXML
    private TextField idCours;

    @FXML
    private TextField idEnseignantField;

    @FXML
    private Button submitButton;

    @FXML
    private Label titleLabel;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button removeButton;

    @FXML
    private StackPane mainContentPane; // Assuming you have a StackPane for the main content

    @FXML
    private void handleAdd() {
        try {
            StackPane bar = FXMLLoader.load(getClass().getResource("view/CoursEnseignant.fxml"));
            VBox addForm = FXMLLoader.load(getClass().getResource("view/AddFormCoursEnseignant.fxml"));
            mainContentPane.getChildren().addAll(bar, addForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleaddSubmit() {
        String nomCours = nomCoursField.getText();
        String idEnseignant = idEnseignantField.getText();

        if (nomCours.isEmpty() || idEnseignant.isEmpty()) {
            System.out.println("All fields must be filled.");
            return;
        }

        try {
            Connection connection = DatabaseConnection.connect();
            if (connection != null) {
                String insertQuery = "INSERT INTO CoursEnseignant (nomCours, idEnseignant) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, nomCours);
                    preparedStatement.setString(2, idEnseignant);
                    preparedStatement.executeUpdate();
                    System.out.println("Row inserted!");
                }

                connection.close();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUpdate() {
        try {
            StackPane bar = FXMLLoader.load(getClass().getResource("view/CoursEnseignant.fxml"));
            VBox addForm = FXMLLoader.load(getClass().getResource("view/updateFormCoursEnseignant.fxml"));
            mainContentPane.getChildren().addAll(bar, addForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleupSubmit() {
        String nomCours = nomCoursField.getText();
        String idEnseignant = idEnseignantField.getText();
        String idC = idCours.getText();

        if (nomCours.isEmpty() || idEnseignant.isEmpty() || idC.isEmpty()) {
            System.out.println("All fields must be filled.");
            return;
        }

        try {
            Connection connection = DatabaseConnection.connect();

            if (connection != null) {
                String updateQuery = "UPDATE CoursEnseignant SET nomCours=?, idEnseignant =? WHERE idCours =?  ";
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, nomCours);
                    preparedStatement.setString(2, idEnseignant);
                    preparedStatement.setString(3, idC);
                    preparedStatement.executeUpdate();
                }

                connection.close();

                System.out.println("CoursEnseignant updated successfully.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRemove() {
        try {
            StackPane bar = FXMLLoader.load(getClass().getResource("view/CoursEnseignant.fxml"));
            VBox addForm = FXMLLoader.load(getClass().getResource("view/removeCoursEnseignant.fxml"));
            mainContentPane.getChildren().addAll(bar, addForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlermSubmit() {
        String idC = idCours.getText();

        if (idC.isEmpty()) {
            System.out.println("All fields must be filled.");
            return;
        }

        try {
            Connection connection = DatabaseConnection.connect();

            if (connection != null) {
                String removeQuery = "DELETE FROM CoursEnseignant WHERE idCours=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(removeQuery)) {
                    preparedStatement.setString(1, idC);
                    preparedStatement.executeUpdate();
                }

                connection.close();

                System.out.println("CoursEnseignant deleted successfully.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
