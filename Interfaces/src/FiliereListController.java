import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FiliereListController {

    @FXML
    private TableView<Filiere> filieresTable;

    @FXML
    private TableColumn<Filiere, Integer> idFiliereColumn;

    @FXML
    private TableColumn<Filiere, String> nomFiliereColumn;

    @FXML
    private TableColumn<Filiere, Integer> responsableColumn;

    @FXML
    private TableColumn<Filiere, String> nomEnseignantColumn;

    @FXML
    private TableColumn<Filiere, String> telephoneEnseignantColumn;

    @FXML
    private TableColumn<Filiere, String> descriptionColumn;

    public void initialize() {
        // Set cell value factories for each column
        idFiliereColumn.setCellValueFactory(cellData -> cellData.getValue().getIdFiliereProperty().asObject());
        nomFiliereColumn.setCellValueFactory(cellData -> cellData.getValue().getNomFiliereProperty());
        responsableColumn.setCellValueFactory(cellData -> cellData.getValue().getResponsableProperty().asObject());
        nomEnseignantColumn.setCellValueFactory(cellData -> cellData.getValue().getNomEnseignantProperty());
        telephoneEnseignantColumn.setCellValueFactory(cellData -> cellData.getValue().getTelephoneEnseignantProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getDescriptionProperty());

        fetchDataFromDatabase();
    }

    private void fetchDataFromDatabase() {
        try {
            // Get a database connection
            Connection connection = DatabaseConnection.connect();

            // Check if the connection is successful
            if (connection != null) {
                // SQL query to fetch data from the Filieres table with a join on Enseignants
                String selectQuery = "SELECT Filieres.*, Enseignants.nom AS nomEnseignant, Enseignants.telephone AS telephoneEnseignant " +
                                    "FROM Filieres " +
                                    "LEFT JOIN Enseignants ON Filieres.responsable = Enseignants.id";

                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                    // Execute the query and get the result set
                    ResultSet resultSet = preparedStatement.executeQuery();

                    // Populate the filieresTable with data from the result set
                    while (resultSet.next()) {
                        int idFiliere = resultSet.getInt("idFiliere");
                        String nomFiliere = resultSet.getString("nomFiliere");
                        int responsable = resultSet.getInt("responsable");
                        String nomEnseignant = resultSet.getString("nomEnseignant");
                        String telephoneEnseignant = resultSet.getString("telephoneEnseignant");
                        String description = resultSet.getString("description");

                        Filiere filiere = new Filiere(idFiliere, nomFiliere, responsable, nomEnseignant, telephoneEnseignant, description);
                        filieresTable.getItems().add(filiere);
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
