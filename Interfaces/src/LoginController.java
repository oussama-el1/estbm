import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        // Call the authentication method
        if (authenticateUser(email, password)) {
            // Successful login
            System.out.println("Login successful");

            // Close the login window
            closeLoginWindow();

            // Open the dashboard window
            openDashboardWindow(email);
        } else {
            // Failed login
            System.out.println("Login failed");
            showAlert("Login Failed", "Invalid email or password");
        }
    }

    private boolean authenticateUser(String email, String password) {
        // Check the database for the entered credentials
        String query = "SELECT * FROM administrators WHERE email = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeLoginWindow() {
        Stage stage = (Stage) emailField.getScene().getWindow();
        stage.close();
    }

    private void openDashboardWindow(String userEmail) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = loader.load();

            // Pass the user's email to the dashboard controller
            DashboardController dashboardController = loader.getController();
            dashboardController.initialize(userEmail);

            if (root == null) {
                System.out.println("FXML file not loaded. Check if Dashboard.fxml is in the correct location.");
                return;
            }

            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("Dashboard");
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception while loading Dashboard.fxml");
        }
    }

}
