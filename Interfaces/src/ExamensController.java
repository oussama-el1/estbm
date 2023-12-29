import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class ExamensController {

    @FXML
    private TextField idCoursField;

    @FXML
    private TextField entryIdField;

    @FXML
    private DatePicker dateExamenPicker;

    @FXML
    private TextField nomFiliereField;

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
    private StackPane mainContentPane;

    @FXML
    private void handleAdd() {
        try {
            StackPane bar = FXMLLoader.load(getClass().getResource("view/Examens.fxml"));
            VBox addForm = FXMLLoader.load(getClass().getResource("view/AddFormExamens.fxml"));
            mainContentPane.getChildren().addAll(bar, addForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

@FXML
private void handleAddSubmit() {
    String idCours = idCoursField.getText();
    String nomFiliere = nomFiliereField.getText();
    LocalDate dateExamen = dateExamenPicker.getValue(); // Retrieve the selected date

    if (idCours.isEmpty() || nomFiliere.isEmpty() || dateExamen == null) {
        System.out.println("All fields must be filled.");
        return;
    }

    try {
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            String insertQuery = "INSERT INTO Examens (ID_Cours, Date_Examen, Nom_Filiere) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, Integer.parseInt(idCours));
                preparedStatement.setDate(2, Date.valueOf(dateExamen));
                preparedStatement.setString(3, nomFiliere);
                preparedStatement.executeUpdate();
                System.out.println("Row inserted into Examens!");
            }

            connection.close();
        }

    } catch (ClassNotFoundException | SQLException | NumberFormatException e) {
        e.printStackTrace();
    }
}


    @FXML
    private void handleUpdate() {
        try {
            StackPane bar = FXMLLoader.load(getClass().getResource("view/Examens.fxml"));
            VBox updateForm = FXMLLoader.load(getClass().getResource("view/UpdateFormExamens.fxml"));
            mainContentPane.getChildren().addAll(bar, updateForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUpdateSubmit() {
        String idCours = idCoursField.getText();
        String nomFiliere = nomFiliereField.getText();
        LocalDate dateExamen = dateExamenPicker.getValue();
        String entryId = entryIdField.getText();
    
        if (idCours.isEmpty() || nomFiliere.isEmpty() || dateExamen == null || entryId.isEmpty()) {
            System.out.println("All fields must be filled.");
            return;
        }
    
        try {
            Connection connection = DatabaseConnection.connect();
            if (connection != null) {
                String updateQuery = "UPDATE Examens SET ID_Cours=?, Date_Examen=?, Nom_Filiere=? WHERE ID_Examen=?";
    
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setInt(1, Integer.parseInt(idCours));
                    preparedStatement.setDate(2, Date.valueOf(dateExamen));
                    preparedStatement.setString(3, nomFiliere);
                    preparedStatement.setInt(4, Integer.parseInt(entryId));
    
                    preparedStatement.executeUpdate();
                    System.out.println("Row updated in Examens!");
                }
    
                connection.close();
            }
    
        } catch (ClassNotFoundException | SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
    

    @FXML
    private void handleRemove() {
        try {
            StackPane bar = FXMLLoader.load(getClass().getResource("view/Examens.fxml"));
            VBox removeForm = FXMLLoader.load(getClass().getResource("view/RemoveFormExamens.fxml"));
            mainContentPane.getChildren().addAll(bar, removeForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRemoveSubmit() {
        String entryId = entryIdField.getText();
    
        if (entryId.isEmpty()) {
            System.out.println("Entry ID must be filled.");
            return;
        }
    
        try {
            Connection connection = DatabaseConnection.connect();
            if (connection != null) {
                String deleteQuery = "DELETE FROM Examens WHERE ID_Examen=?";
    
                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                    preparedStatement.setInt(1, Integer.parseInt(entryId));
    
                    preparedStatement.executeUpdate();
                    System.out.println("Row deleted from Examens!");
                }
    
                connection.close();
            }
    
        } catch (ClassNotFoundException | SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
    
}
