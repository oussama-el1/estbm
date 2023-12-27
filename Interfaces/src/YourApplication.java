import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class YourApplication extends Application {

    private Connection connection;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException {
        // Connect to the database
        connection = DatabaseConnection.connect();
        // Check if the connection is successful
        if (connection == null) {
            System.err.println("Failed to connect to the database. Exiting...");
            System.exit(1);
        }
        // Set up the login scene
        Scene loginScene = createLoginScene(primaryStage);
        // Set the primary stage
        primaryStage.setTitle("Login");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private Scene createLoginScene(Stage primaryStage) {
        StackPane loginPane = new StackPane();
        loginPane.setAlignment(Pos.CENTER);
        loginPane.setMinSize(400, 300);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> handleLogin(usernameField.getText(), passwordField.getText(), primaryStage));

        loginPane.getChildren().addAll(usernameField, passwordField, loginButton);

        return new Scene(loginPane, 400, 300);
    }

    private void handleLogin(String username, String password, Stage primaryStage) {
        // Check the database for the entered credentials
        String query = "SELECT * FROM administrators WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Successful login
                System.out.println("Login successful");
                showDashboard(primaryStage);
            } else {
                // Failed login
                System.out.println("Login failed");
                showAlert("Login Failed", "Invalid username or password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showDashboard(Stage primaryStage) {
        StackPane dashboardPane = new StackPane();
        dashboardPane.setAlignment(Pos.CENTER);
        dashboardPane.setMinSize(400, 300);

        Label dashboardLabel = new Label("Dashboard");
        dashboardPane.getChildren().add(dashboardLabel);

        Scene dashboardScene = new Scene(dashboardPane, 400, 300);
        primaryStage.setScene(dashboardScene);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}