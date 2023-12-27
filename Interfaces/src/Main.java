
import java.sql.Connection;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    Connection connection;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            connection = DatabaseConnection.connect();
            if (connection != null) {
                System.out.println("Connected to the database");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to connect to the database. Exiting...");
            System.exit(1);
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Login.fxml"));
        Parent root = loader.load();

        // Pass the connection to the controller
        LoginController loginController = loader.getController();
        loginController.setConnection(connection);

        primaryStage.setTitle("ESTBM - Login Page");
        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(getClass().getResource("style/styles.css").toExternalForm());
        primaryStage.getIcons().add(new javafx.scene.image.Image("resources/estbm.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        // Close the database connection when the application is closed
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
