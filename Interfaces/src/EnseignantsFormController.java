import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class EnseignantsFormController {

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
            StackPane bar = FXMLLoader.load(getClass().getResource("view/EnseignantsForm.fxml"));
            VBox addForm = FXMLLoader.load(getClass().getResource("view/AddForm.fxml"));
            // Add the form to the main content pane
            mainContentPane.getChildren().setAll(bar, addForm);
        } catch (IOException e) {
            System.out.println("eroor here");
        }
    }
    

    @FXML
    private void handleUpdate() {
        // Handle update operation
    }

    @FXML
    private void handleRemove() {
        // Handle remove operation
    }
}
