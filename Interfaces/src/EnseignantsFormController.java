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

public class EnseignantsFormController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField telField;

    @FXML
    private TextField emailField;

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

    // Initialize method or other methods for handling functionality

    @FXML
    private void handleAdd() {
        // Load the AddForm.fxml dynamically
        try {
            StackPane bar = FXMLLoader.load(getClass().getResource("view/Etudiants.fxml"));
            VBox addForm = FXMLLoader.load(getClass().getResource("view/AddForm.fxml"));
            // Add the form to the main content pane
            mainContentPane.getChildren().addAll(bar, addForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleaddSubmit() {
        // Retrieve data from text fields
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String tel = telField.getText();
        String email = emailField.getText();

            // Check if any of the fields is empty
            if (nom.isEmpty() || prenom.isEmpty() || tel.isEmpty() || email.isEmpty()) {
                // Show an alert or message to inform the user that all fields must be filled
                System.out.println("All fields must be filled.");
                return; // Don't proceed with the database insertion
    }

        try {
            // Get a database connection using the DatabaseConnection class
            Connection connection = DatabaseConnection.connect();
                if (connection != null) {
                // SQL query to insert data into Enseignants table
                    String insertQuery = "INSERT INTO Enseignants (nom, prenom, email, telephone) VALUES (?, ?, ?, ?)";
                // Create a prepared statement
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    // Set parameters for the query
                    preparedStatement.setString(1, nom);
                    preparedStatement.setString(2, prenom);
                    preparedStatement.setString(3, email);
                    preparedStatement.setString(4, tel);
                    // Execute the query
                    preparedStatement.executeUpdate();
                    System.out.println("row inserted !");
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
            StackPane bar = FXMLLoader.load(getClass().getResource("view/Etudiants.fxml"));
            VBox addForm = FXMLLoader.load(getClass().getResource("view/updateForm.fxml"));
            // Add the form to the main content pane
            mainContentPane.getChildren().addAll(bar, addForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleupSubmit() {
        // Retrieve data from text fields
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String tel = telField.getText();
        String email = emailField.getText();
    
        // Check if any of the fields is empty
        if (nom.isEmpty() || prenom.isEmpty() || tel.isEmpty() || email.isEmpty()) {
            // Show an alert or message to inform the user that all fields must be filled
            System.out.println("All fields must be filled.");
            return; // Don't proceed with the database update
        }
    
        try {
            // Get a database connection using the DatabaseConnection class
            Connection connection = DatabaseConnection.connect();
    
            if (connection != null) {
                // SQL query to update data in the Enseignants table
                String updateQuery = "UPDATE Enseignants SET nom=?, prenom=?, email=?, telephone=? WHERE nom =? AND prenom =? ";
    
                // Create a prepared statement
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    // Set parameters for the query
                    preparedStatement.setString(1, nom);
                    preparedStatement.setString(2, prenom);
                    preparedStatement.setString(3, email);
                    preparedStatement.setString(4, tel);
                    preparedStatement.setString(5, nom);
                    preparedStatement.setString(6, prenom);
    
                    // Assuming you have an "id" variable representing the Enseignant's ID
                    //preparedStatement.setInt(5, id);
                    // Execute the query
                    preparedStatement.executeUpdate();
                }
    
                // Close the database connection
                connection.close();
    
                // Optionally, you can show a success message or perform other actions
                System.out.println("Enseignant updated successfully.");
            }
    
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions, show error messages, etc.
        }
    }

    @FXML
    private void handleRemove() {
        // Handle remove operation
        try {
            StackPane bar = FXMLLoader.load(getClass().getResource("view/Etudiants.fxml"));
            VBox addForm = FXMLLoader.load(getClass().getResource("view/remove.fxml"));
            // Add the form to the main content pane
            mainContentPane.getChildren().addAll(bar, addForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlermSubmit() {
        // Retrieve data from text fields
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String tel = telField.getText();
        String email = emailField.getText();
    
        // Check if any of the fields is empty
        if (nom.isEmpty() || prenom.isEmpty() || tel.isEmpty() || email.isEmpty()) {
            // Show an alert or message to inform the user that all fields must be filled
            System.out.println("All fields must be filled.");
            return; // Don't proceed with the database update
        }
    
        try {
            // Get a database connection using the DatabaseConnection class
            Connection connection = DatabaseConnection.connect();
    
            if (connection != null) {
                // SQL query to update data in the Enseignants table
                String updateQuery = "DELETE FROM Enseignants WHERE nom=? AND prenom=? AND email=? AND telephone=?";
    
                // Create a prepared statement
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    // Set parameters for the query
                    preparedStatement.setString(1, nom);
                    preparedStatement.setString(2, prenom);
                    preparedStatement.setString(3, email);
                    preparedStatement.setString(4, tel);
    
                    // Assuming you have an "id" variable representing the Enseignant's ID
                    //preparedStatement.setInt(5, id);
                    // Execute the query
                    preparedStatement.executeUpdate();
                }
    
                // Close the database connection
                connection.close();
    
                // Optionally, you can show a success message or perform other actions
                System.out.println("Enseignant deleted successfully.");
            }
    
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions, show error messages, etc.
        }
    }
}
