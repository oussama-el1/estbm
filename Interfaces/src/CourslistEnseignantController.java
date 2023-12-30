import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CourslistEnseignantController {

    @FXML
    private TableView<CoursEnseignant> coursTable;

    @FXML
    private TableColumn<CoursEnseignant, Integer> idCoursColumn;

    @FXML
    private TableColumn<CoursEnseignant, String> nomCoursColumn;

    @FXML
    private TableColumn<CoursEnseignant, String> nomEnseignantColumn;

    public void initialize() {
        // Set cell value factories for each column
        idCoursColumn.setCellValueFactory(new PropertyValueFactory<>("idCours"));
        nomCoursColumn.setCellValueFactory(new PropertyValueFactory<>("nomCours"));
        nomEnseignantColumn.setCellValueFactory(new PropertyValueFactory<>("nomEnseignant"));

        // Fetch and display data
        fetchDataFromDatabase();
    }

    private void fetchDataFromDatabase() {
        try {
            // Get a database connection
            Connection connection = DatabaseConnection.connect();

            // Check if the connection is successful
            if (connection != null) {
                // SQL query to fetch data from the CoursEnseignant table
                String selectQuery = "SELECT CoursEnseignant.idCours, CoursEnseignant.nomCours, Enseignants.nom AS nomEnseignant " +
                                     "FROM CoursEnseignant " +
                                     "JOIN Enseignants ON CoursEnseignant.idEnseignant = Enseignants.id";

                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                    // Execute the query and get the result set
                    ResultSet resultSet = preparedStatement.executeQuery();

                    // Populate the coursTable with data from the result set
                    while (resultSet.next()) {
                        int idCours = resultSet.getInt("idCours");
                        String nomCours = resultSet.getString("nomCours");
                        String nomEnseignant = resultSet.getString("nomEnseignant");

                        CoursEnseignant cours = new CoursEnseignant(idCours, nomCours, nomEnseignant);
                        coursTable.getItems().add(cours);
                    }
                }

                // Close the database connection
                connection.close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions, show error messages, etc.
        }
    }
}
