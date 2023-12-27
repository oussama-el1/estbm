

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class DashboardController {

    @FXML
    private ListView<String> navListView;

    @FXML
    private StackPane mainContentPane;

    private boolean isInitialized = false;

    public void initialize() {
        // Check if already initialized
        if (!isInitialized) {
            // Initialize the navigation bar
            navListView.getItems().addAll("Enseignant", "Emplois de Temps", "Etudiants", "Filiere", "Salles", "Cours", "Examens", "Notes");
            navListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            // Set default content
            // Mark as initialized
            isInitialized = true;
        }
    }

    @FXML
    private void handleNavItemSelection() {
        // Handle item selection from the navigation bar
        String selectedSection = navListView.getSelectionModel().getSelectedItem();
        if (selectedSection != null) {
            // Update the main content based on the selected section
            switch (selectedSection) {
                case "Enseignant":
                    showEnseignantsForm();
                    break;
                // Add other cases for different sections
                default:
                    System.out.println("Not implemented yet");
                    break;
            }
        }
    }

    private void showEnseignantsForm() {
        // Load EnseignantsForm.fxml dynamically
        try {
            mainContentPane.getChildren().clear(); // Clear existing content
            mainContentPane.getChildren().add(FXMLLoader.load(getClass().getResource("view/EnseignantsForm.fxml.")));
        } catch (IOException e) {
            e.printStackTrace();
            mainContentPane.getChildren().setAll(new Label("Error loading Enseignants Form"));
        }
    }
}
