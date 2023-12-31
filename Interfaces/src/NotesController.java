import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class NotesController {

    @FXML
    private TextField idEtudiantField;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button removeButton;

    @FXML
    private StackPane mainContentPane;

    @FXML
    private Label moyenneLabel;

    @FXML
    private TextField examenIdField;

    @FXML
    private TextField noteField;

    @FXML
    private Button submitButton;

    @FXML
    private void handleAdd() {
        try {
            StackPane bar = FXMLLoader.load(getClass().getResource("view/NotesForm.fxml"));
            VBox addForm = FXMLLoader.load(getClass().getResource("view/AddFormNote.fxml"));
            mainContentPane.getChildren().addAll(bar, addForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddSubmit() {
        String etudiantId = idEtudiantField.getText();
        String examenId = examenIdField.getText();
        String noteText = noteField.getText();
    
        if (etudiantId.isEmpty() || examenId.isEmpty() || noteText.isEmpty()) {
            System.out.println("All fields must be filled.");
            return;
        }
        double note;
        try {
            note = Double.parseDouble(noteText);
        } catch (NumberFormatException e) {
            System.out.println("Invalid note value.");
            return;
        }
    
        try {
            Connection connection = DatabaseConnection.connect();
            if (connection != null) {
                String insertQuery = "INSERT INTO Notes (ID_Etudiant, ID_Examen, Note) VALUES (?, ?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

                    preparedStatement.setString(1, etudiantId);
                    preparedStatement.setString(2, examenId);
                    preparedStatement.setDouble(3, note);

                    preparedStatement.executeUpdate();
                    System.out.println("Note inserted!");
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
            StackPane bar = FXMLLoader.load(getClass().getResource("view/NotesForm.fxml"));
            VBox addForm = FXMLLoader.load(getClass().getResource("view/UpdateNoteForm.fxml"));
            // Add the form to the main content pane
            mainContentPane.getChildren().addAll(bar, addForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUpdateMoyenne() {
        String studentId = idEtudiantField.getText();

        if (studentId.isEmpty()) {
            System.out.println("Student ID must be filled.");
            return;
        }
        try {
            Connection connection = DatabaseConnection.connect();

            if (connection != null) {
                String selectQuery = "SELECT Note FROM Notes WHERE ID_Etudiant = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                    preparedStatement.setString(1, studentId);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    double total = 0;
                    int count = 0;
                    while (resultSet.next()) {
                        double note = resultSet.getDouble("Note");
                        total += note;
                        count++;
                    }

                    double moyenne = (count > 0) ? total / count : 0;
                    moyenneLabel.setText("Moyenne for student " + studentId + ": " + moyenne);
                }
                connection.close();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
}
}
