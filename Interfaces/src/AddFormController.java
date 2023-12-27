// AddFormController.java
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddFormController {

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
    private void handleSubmit() {
        // Handle the logic for adding an enseignant
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String tel = telField.getText();
        String email = emailField.getText();
        // Perform necessary actions (e.g., add to the database)
        System.out.println("Adding enseignant: " + nom + " " + prenom + " " + tel + " " + email);
    }
}
