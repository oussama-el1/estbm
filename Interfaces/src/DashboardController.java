import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label welcomeLabel;

    // You can add more UI elements and functionality as needed

    public void initialize(String userEmail) {
        // Initialize the dashboard with user-specific information
        welcomeLabel.setText("Welcome, " + userEmail + "!");
    }
}
