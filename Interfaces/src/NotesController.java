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

    // Initialize method or other methods for handling functionality

    @FXML
    private void handleAdd() {
        // Load the AddForm.fxml dynamically
        try {
            StackPane bar = FXMLLoader.load(getClass().getResource("view/NotesForm.fxml"));
            VBox addForm = FXMLLoader.load(getClass().getResource("view/AddFormNote.fxml"));
            // Add the form to the main content pane
            mainContentPane.getChildren().addAll(bar, addForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddSubmit() {
        // Retrieve data from text fields
        String etudiantId = idEtudiantField.getText();
        String examenId = examenIdField.getText();
        String noteText = noteField.getText();
    
        // Validate input
        if (etudiantId.isEmpty() || examenId.isEmpty() || noteText.isEmpty()) {
            // Show an alert or message to inform the user that all fields must be filled
            System.out.println("All fields must be filled.");
            return; // Don't proceed with the database insertion
        }
    
        // Parse note as a double
        double note;
        try {
            note = Double.parseDouble(noteText);
        } catch (NumberFormatException e) {
            // Handle the case where note is not a valid double
            System.out.println("Invalid note value.");
            return;
        }
    
        try {
            // Get a database connection using the DatabaseConnection class
            Connection connection = DatabaseConnection.connect();
            if (connection != null) {
                // SQL query to insert data into Notes table
                String insertQuery = "INSERT INTO Notes (ID_Etudiant, ID_Examen, Note) VALUES (?, ?, ?)";
                // Create a prepared statement
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    // Set parameters for the query
                    preparedStatement.setString(1, etudiantId);
                    preparedStatement.setString(2, examenId);
                    preparedStatement.setDouble(3, note);
    
                    // Execute the query
                    preparedStatement.executeUpdate();
                    System.out.println("Note inserted!");
                }
    
                // Close the database connection
                connection.close();
                // Optionally, you can show a success message or perform other actions
            }
    
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions, show error messages, etc.
        }
    }

    @FXML
    private void handleUpdate() {
        // Handle update operation
        try {
            StackPane bar = FXMLLoader.load(getClass().getResource("view/NotesForm.fxml"));
            VBox addForm = FXMLLoader.load(getClass().getResource("view/updateNoteForm.fxml"));
            // Add the form to the main content pane
            mainContentPane.getChildren().addAll(bar, addForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUpdateMoyenne() {
        // Retrieve student ID (CIN) from the text field
        String studentId = idEtudiantField.getText();

        if (studentId.isEmpty()) {
            System.out.println("Student ID must be filled.");
            return;
        }

        try {
            // Get a database connection using the DatabaseConnection class
            Connection connection = DatabaseConnection.connect();

            if (connection != null) {
                // SQL query to fetch exam scores for the given student ID
                String selectQuery = "SELECT Note FROM Notes WHERE ID_Etudiant = ?";

                // Create a prepared statement
                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                    // Set parameters for the query
                    preparedStatement.setString(1, studentId);

                    // Execute the query and get the result set
                    ResultSet resultSet = preparedStatement.executeQuery();

                    // Calculate moyenne from the result set
                    double total = 0;
                    int count = 0;
                    while (resultSet.next()) {
                        double note = resultSet.getDouble("Note");
                        total += note;
                        count++;
                    }

                    double moyenne = (count > 0) ? total / count : 0;

                    // Display the moyenne in the Label
                    moyenneLabel.setText("Moyenne for student " + studentId + ": " + moyenne);
                }

                // Close the database connection
                connection.close();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions, show error messages, etc.
        }
}
}
