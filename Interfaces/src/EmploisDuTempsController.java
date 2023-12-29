import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmploisDuTempsController {

    @FXML
    private TextField idCoursField;

    @FXML
    private TextField entryIdField;

    @FXML
    private TextField idEnseignantField;

    @FXML
    private TextField idSalleField;

    @FXML
    private TextField idFiliereField;

    @FXML
    private TextField jourField;

    @FXML
    private TextField heureDebutField;

    @FXML
    private TextField heureFinField;

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
            StackPane bar = FXMLLoader.load(getClass().getResource("view/EmploisDuTemps.fxml"));
            VBox addForm = FXMLLoader.load(getClass().getResource("view/AddFormEmploisDuTemps.fxml"));
            mainContentPane.getChildren().addAll(bar, addForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddSubmit() {
        String idCours = idCoursField.getText();
        String idEnseignant = idEnseignantField.getText();
        String idSalle = idSalleField.getText();
        String idFiliere = idFiliereField.getText();
        String jour = jourField.getText();
        String heureDebut = heureDebutField.getText();
        String heureFin = heureFinField.getText();
    
        if (idCours.isEmpty() || idEnseignant.isEmpty() || idSalle.isEmpty() ||
                idFiliere.isEmpty() || jour.isEmpty() || heureDebut.isEmpty() || heureFin.isEmpty()) {
            System.out.println("All fields must be filled.");
            return;
        }
    
        try {
            Connection connection = DatabaseConnection.connect();
            if (connection != null) {
                String insertQuery = "INSERT INTO EmploisDuTemps (ID_Cours, ID_Enseignant, ID_Salle, " +
                        "ID_Filiere, Jour, Heure_debut, Heure_fin) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, Integer.parseInt(idCours));
                    preparedStatement.setInt(2, Integer.parseInt(idEnseignant));
                    preparedStatement.setString(3, idSalle);
                    preparedStatement.setInt(4, Integer.parseInt(idFiliere));
                    preparedStatement.setString(5, jour);
                    preparedStatement.setString(6, heureDebut);
                    preparedStatement.setString(7, heureFin);
    
                    preparedStatement.executeUpdate();
                    System.out.println("Row inserted into EmploisDuTemps!");
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
            StackPane bar = FXMLLoader.load(getClass().getResource("view/EmploisDuTemps.fxml"));
            VBox updateForm = FXMLLoader.load(getClass().getResource("view/UpdateFormEmploisDuTemps.fxml"));
            mainContentPane.getChildren().addAll(bar, updateForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleUpdateSubmit() {
        String idCours = idCoursField.getText();
        String idEnseignant = idEnseignantField.getText();
        String idSalle = idSalleField.getText();
        String idFiliere = idFiliereField.getText();
        String jour = jourField.getText();
        String heureDebut = heureDebutField.getText();
        String heureFin = heureFinField.getText();
        String entryid = entryIdField.getText();
    
        if (idCours.isEmpty() || idEnseignant.isEmpty() || idSalle.isEmpty() ||
                idFiliere.isEmpty() || jour.isEmpty() || heureDebut.isEmpty() || heureFin.isEmpty()) {
            System.out.println("All fields must be filled.");
            return;
        }
    
        try {
            Connection connection = DatabaseConnection.connect();
            if (connection != null) {
                String updateQuery = "UPDATE EmploisDuTemps SET ID_Cours=?, ID_Enseignant=?, ID_Salle=?, " +
                        "ID_Filiere=?, Jour=?, Heure_debut=?, Heure_fin=? WHERE ID_EmploiDuTemps=?";
    
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setInt(1, Integer.parseInt(idCours));
                    preparedStatement.setInt(2, Integer.parseInt(idEnseignant));
                    preparedStatement.setString(3, idSalle);
                    preparedStatement.setInt(4, Integer.parseInt(idFiliere));
                    preparedStatement.setString(5, jour);
                    preparedStatement.setString(6, heureDebut);
                    preparedStatement.setString(7, heureFin);
    
                    // Assuming you have an ID for the entry you want to update
                    preparedStatement.setInt(8, Integer.parseInt(entryid));
    
                    preparedStatement.executeUpdate();
                    System.out.println("Row updated in EmploisDuTemps!");
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
            StackPane bar = FXMLLoader.load(getClass().getResource("view/EmploisDuTemps.fxml"));
            VBox removeForm = FXMLLoader.load(getClass().getResource("view/RemoveFormEmploisDuTemps.fxml"));
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
                String deleteQuery = "DELETE FROM EmploisDuTemps WHERE ID_EmploiDuTemps=?";
    
                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                    preparedStatement.setInt(1, Integer.parseInt(entryId));
    
                    preparedStatement.executeUpdate();
                    System.out.println("Row deleted from EmploisDuTemps!");
                }
    
                connection.close();
            }
    
        } catch (ClassNotFoundException | SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
    
}
