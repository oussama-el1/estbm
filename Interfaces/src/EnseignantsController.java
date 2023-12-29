import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EnseignantsController {

    @FXML
    private TableView<Enseignant> enseignantsTable;

    @FXML
    private TableColumn<Enseignant, Integer> idColumn;

    @FXML
    private TableColumn<Enseignant, String> nomColumn;

    @FXML
    private TableColumn<Enseignant, String> prenomColumn;

    @FXML
    private TableColumn<Enseignant, String> emailColumn;

    @FXML
    private TableColumn<Enseignant, String> telephoneColumn;

    public void initialize() {
        // Set cell value factories for each column
        idColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().getNomProperty());
        prenomColumn.setCellValueFactory(cellData -> cellData.getValue().getPrenomProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
        telephoneColumn.setCellValueFactory(cellData -> cellData.getValue().getTelephoneProperty());

        fetchDataFromDatabase();
    }

    private void fetchDataFromDatabase() {
        try {
            // Get a database connection
            Connection connection = DatabaseConnection.connect();

            // Check if the connection is successful
            if (connection != null) {
                // SQL query to fetch data from the Enseignants table
                String selectQuery = "SELECT * FROM Enseignants";

                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                    // Execute the query and get the result set
                    ResultSet resultSet = preparedStatement.executeQuery();

                    // Populate the enseignantsTable with data from the result set
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nom = resultSet.getString("nom");
                        String prenom = resultSet.getString("prenom");
                        String email = resultSet.getString("email");
                        String telephone = resultSet.getString("telephone");

                        Enseignant enseignant = new Enseignant(id, nom, prenom, email, telephone);
                        enseignantsTable.getItems().add(enseignant);
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
