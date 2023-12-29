import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EmploiDuTempsController {

    @FXML
    private TableView<EmploiDuTemps> emploiDuTempsTable;

    @FXML
    private TableColumn<EmploiDuTemps, Integer> idEmploiDuTempsColumn;

    @FXML
    private TableColumn<EmploiDuTemps, String> jourColumn;

    @FXML
    private TableColumn<EmploiDuTemps, String> heureDebutColumn;

    @FXML
    private TableColumn<EmploiDuTemps, String> heureFinColumn;

    @FXML
    private TableColumn<EmploiDuTemps, String> nomCoursColumn;

    @FXML
    private TableColumn<EmploiDuTemps, String> nomEnseignantColumn;

    @FXML
    private TableColumn<EmploiDuTemps, String> salleColumn;

    @FXML
    private TextField filiereNameTextField;

    public void initialize() {
        // Set cell value factories for each column
        jourColumn.setCellValueFactory(cellData -> cellData.getValue().getJourProperty());
        heureDebutColumn.setCellValueFactory(cellData -> cellData.getValue().getHeureDebutProperty());
        heureFinColumn.setCellValueFactory(cellData -> cellData.getValue().getHeureFinProperty());
        nomCoursColumn.setCellValueFactory(cellData -> cellData.getValue().getNomCoursProperty());
        nomEnseignantColumn.setCellValueFactory(cellData -> cellData.getValue().getNomEnseignantProperty());
        salleColumn.setCellValueFactory(cellData -> cellData.getValue().getIdSalleProperty());

        // Fetch and display data for a specific filiere (you need to pass the filiere name to this method)
        fetchDataFromDatabase("SampleFiliere"); // Replace "SampleFiliere" with the actual filiere name
    }

    @FXML
    private void showEmploiDuTemps() {
        emploiDuTempsTable.getItems().clear(); // Clear existing data

        try {
            String filiereName = filiereNameTextField.getText();

            if (!filiereName.isEmpty()) {
                fetchDataFromDatabase(filiereName);
            } else {
                // Handle empty filiere name
                System.out.println("Filiere Name cannot be empty");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions as needed
        }
    }

    private void fetchDataFromDatabase(String filiereName) {
        try {
            // Get a database connection
            Connection connection = DatabaseConnection.connect();

            // Check if the connection is successful
            if (connection != null) {
                // SQL query to fetch emploi du temps data for a specific filiere
                String selectQuery = "SELECT EmploisDuTemps.*, CoursEnseignant.nomCours, Enseignants.nom AS nomEnseignant "
                        +
                        "FROM EmploisDuTemps " +
                        "JOIN CoursEnseignant ON EmploisDuTemps.ID_Cours = CoursEnseignant.idCours " +
                        "JOIN Enseignants ON EmploisDuTemps.ID_Enseignant = Enseignants.id " +
                        "JOIN Filieres ON EmploisDuTemps.ID_Filiere = Filieres.idFiliere " +
                        "WHERE Filieres.nomFiliere = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                    preparedStatement.setString(1, filiereName);

                    // Execute the query and get the result set
                    ResultSet resultSet = preparedStatement.executeQuery();

                    // Populate the emploiDuTempsTable with data from the result set
                    while (resultSet.next()) {
                        int idEmploiDuTemps = resultSet.getInt("ID_EmploiDuTemps");
                        // Extract other columns similarly

                        EmploiDuTemps emploiDuTemps = new EmploiDuTemps(
                                idEmploiDuTemps,
                                resultSet.getInt("ID_Cours"),
                                resultSet.getInt("ID_Enseignant"),
                                resultSet.getString("ID_Salle"),
                                resultSet.getInt("ID_Filiere"),
                                resultSet.getString("Jour"),
                                resultSet.getString("Heure_debut"),
                                resultSet.getString("Heure_fin"),
                                resultSet.getString("nomCours"),
                                resultSet.getString("nomEnseignant"));
                        emploiDuTempsTable.getItems().add(emploiDuTemps);
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
