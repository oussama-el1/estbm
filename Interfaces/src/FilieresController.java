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

public class FilieresController {

    @FXML
    private TextField nomFiliereField;

    @FXML
    private TextField idFil;

    @FXML
    private TextField responsableField;

    @FXML
    private TextField descriptionField;

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
            StackPane bar = FXMLLoader.load(getClass().getResource("view/Filieres.fxml"));
            VBox addForm = FXMLLoader.load(getClass().getResource("view/AddFormFilieres.fxml"));
            mainContentPane.getChildren().addAll(bar, addForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddSubmit() {
        String nomFiliere = nomFiliereField.getText();
        String responsableId = responsableField.getText();
        String description = descriptionField.getText();

        if (nomFiliere.isEmpty() || responsableId.isEmpty()) {
            System.out.println("The name of the academic department and responsible ID must be provided.");
            return;
        }

        try {
            Connection connection = DatabaseConnection.connect();
            if (connection != null) {
                String insertQuery = "INSERT INTO Filieres (nomFiliere, responsable, description) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, nomFiliere);
                    preparedStatement.setInt(2, Integer.parseInt(responsableId));
                    preparedStatement.setString(3, description);
                    preparedStatement.executeUpdate();
                    System.out.println("Row fil inserted!");
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
            StackPane bar = FXMLLoader.load(getClass().getResource("view/Filieres.fxml"));
            VBox addForm = FXMLLoader.load(getClass().getResource("view/UpdateFormFilieres.fxml"));
            mainContentPane.getChildren().addAll(bar, addForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUpdateSubmit() {
        String nomFiliere = nomFiliereField.getText();
        String responsableId = responsableField.getText();
        String description = descriptionField.getText();
        int idf;

        try {
            idf = Integer.parseInt(idFil.getText());
        } catch (NumberFormatException e) {
            // Handle the case where notebac is not a valid double
            System.out.println("Invalid id Filiere value.");
            return;
        }
        
        if (nomFiliere.isEmpty() || responsableId.isEmpty()) {
            System.out.println("The name of the academic department and responsible ID must be provided.");
            return;
        }

        try {
            Connection connection = DatabaseConnection.connect();
            if (connection != null) {
                String updateQuery = "UPDATE Filieres SET nomFiliere=?, responsable=?, description=? WHERE idFiliere=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, nomFiliere);
                    preparedStatement.setInt(2, Integer.parseInt(responsableId)); // Assuming responsibleId is an integer
                    preparedStatement.setString(3, description);
                    preparedStatement.setInt(4, idf);
                    preparedStatement.executeUpdate();
                    System.out.println("Row fil updated!");
                }

                connection.close();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRemove() {
        try {
            StackPane bar = FXMLLoader.load(getClass().getResource("view/Filieres.fxml"));
            VBox addForm = FXMLLoader.load(getClass().getResource("view/RemoveFilieres.fxml"));
            mainContentPane.getChildren().addAll(bar, addForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRemoveSubmit() {
        String nomFiliere = nomFiliereField.getText();
        String responsableId = responsableField.getText();

        if (nomFiliere.isEmpty() || responsableId.isEmpty()) {
            System.out.println("The name of the academic department and responsible ID must be provided.");
            return;
        }

        try {
            Connection connection = DatabaseConnection.connect();
            if (connection != null) {
                String deleteQuery = "DELETE FROM Filieres WHERE nomFiliere=? AND responsable=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                    preparedStatement.setString(1, nomFiliere);
                    preparedStatement.setInt(2, Integer.parseInt(responsableId)); // Assuming responsibleId is an integer
                    preparedStatement.executeUpdate();
                    System.out.println("Row fil deleted!");
                }

                connection.close();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Other methods as needed...
}
