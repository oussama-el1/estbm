

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
            navListView.getItems().addAll("Enseignant", "Emplois de Temps", "Etudiants", "Filiere", "Cours", "Examens", "Notes", "List Etudients", "List Enseignant",
                                            "List Filiere", "Emplois List");
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
                case "Etudiants":
                    showEtudiants();
                    break;
                case "Cours":
                    showCours();
                    break;
                case "Filiere":
                    showFil();
                    break;
                case "Emplois de Temps":
                    showEmp();
                    break;
                case "Examens":
                    showExam();
                    break;
                case "Notes":
                    showNote();
                    break;
                case "List Etudients":
                    showlistEtudients();
                    break;
                case "List Enseignant":
                    showlistEnseignant();
                    break;
                case "List Filiere":
                    showListFiliere();
                    break;
                case "Emplois List":
                    showEmploiList();
                    break;
                default:
                    System.out.println("Not implemented yet");
                    break;
            }
        }
    }

    private void showEnseignantsForm() {
        try {
            mainContentPane.getChildren().clear(); // Clear existing content
            mainContentPane.getChildren().add(FXMLLoader.load(getClass().getResource("view/EnseignantsForm.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            mainContentPane.getChildren().setAll(new Label("Error loading Enseignants Form"));
        }
    }

    private void showEtudiants() {
        try {
            mainContentPane.getChildren().clear(); // Clear existing content
            mainContentPane.getChildren().add(FXMLLoader.load(getClass().getResource("view/Etudiants.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            mainContentPane.getChildren().setAll(new Label("Error loading studets Form"));
        }
    }

    private void showCours() {
        try {
            mainContentPane.getChildren().clear(); // Clear existing content
            mainContentPane.getChildren().add(FXMLLoader.load(getClass().getResource("view/CoursEnseignant.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            mainContentPane.getChildren().setAll(new Label("Error loading cours Form"));
        }
    }

    private void showFil() {
        try {
            mainContentPane.getChildren().clear(); // Clear existing content
            mainContentPane.getChildren().add(FXMLLoader.load(getClass().getResource("view/Filieres.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            mainContentPane.getChildren().setAll(new Label("Error loading cours Form"));
        }
    }

    private void showEmp() {
        try {
            mainContentPane.getChildren().clear(); // Clear existing content
            mainContentPane.getChildren().add(FXMLLoader.load(getClass().getResource("view/EmploisDuTemps.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            mainContentPane.getChildren().setAll(new Label("Error loading cours Form"));
        }
    }

    private void showExam() {
        // Load EnseignantsForm.fxml dynamically
        try {
            mainContentPane.getChildren().clear(); // Clear existing content
            mainContentPane.getChildren().add(FXMLLoader.load(getClass().getResource("view/Examens.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            mainContentPane.getChildren().setAll(new Label("Error loading cours Form"));
        }
    }

    private void showNote() {
        // Load EnseignantsForm.fxml dynamically
        try {
            mainContentPane.getChildren().clear(); // Clear existing content
            mainContentPane.getChildren().add(FXMLLoader.load(getClass().getResource("view/NotesForm.fxml")));
        } catch (IOException e) {
            mainContentPane.getChildren().setAll(new Label("Error loading cours Form"));
        }
    }

    private void showlistEtudients() {
        // Load EnseignantsForm.fxml dynamically
        try {
            mainContentPane.getChildren().clear(); // Clear existing content
            mainContentPane.getChildren().add(FXMLLoader.load(getClass().getResource("view/EtudiantsView.fxml")));
        } catch (IOException e) {
            mainContentPane.getChildren().setAll(new Label("Error loading cours Form"));
        }
    }

    private void showlistEnseignant() {
        // Load EnseignantsForm.fxml dynamically
        try {
            mainContentPane.getChildren().clear(); // Clear existing content
            mainContentPane.getChildren().add(FXMLLoader.load(getClass().getResource("view/Enseignants.fxml")));
        } catch (IOException e) {
            mainContentPane.getChildren().setAll(new Label("Error loading cours Form"));
        }
    }

    private void showListFiliere() {
        // Load EnseignantsForm.fxml dynamically
        try {
            mainContentPane.getChildren().clear(); // Clear existing content
            mainContentPane.getChildren().add(FXMLLoader.load(getClass().getResource("view/FilieresList.fxml")));
        } catch (IOException e) {
            mainContentPane.getChildren().setAll(new Label("Error loading cours Form"));
        }
    }

    private void showEmploiList() {
        // Load EnseignantsForm.fxml dynamically
        try {
            mainContentPane.getChildren().clear(); // Clear existing content
            mainContentPane.getChildren().add(FXMLLoader.load(getClass().getResource("view/EmploiDuTemps.fxml")));
        } catch (IOException e) {
            mainContentPane.getChildren().setAll(new Label("Error loading cours Form"));
        }
    }
}
