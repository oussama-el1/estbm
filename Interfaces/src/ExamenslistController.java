import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ExamenslistController {

    @FXML
    private TableView<Examen> examensTable;

    @FXML
    private TableColumn<Examen, Integer> idExamenColumn;

    @FXML
    private TableColumn<Examen, String> dateExamenColumn;

    @FXML
    private TableColumn<Examen, String> nomCoursColumn;

    @FXML
    private TableColumn<Examen, String> nomFiliereColumn;

    public void initialize() {
        // Set cell value factories for each column
        idExamenColumn.setCellValueFactory(new PropertyValueFactory<>("idExamen"));
        dateExamenColumn.setCellValueFactory(new PropertyValueFactory<>("dateExamen"));
        nomCoursColumn.setCellValueFactory(new PropertyValueFactory<>("nomCours"));
        nomFiliereColumn.setCellValueFactory(new PropertyValueFactory<>("nomFiliere"));

        // Fetch and display data
        fetchDataFromDatabase();
    }

    private void fetchDataFromDatabase() {
        try {
            // Get a database connection
            Connection connection = DatabaseConnection.connect();

            // Check if the connection is successful
            if (connection != null) {
                // SQL query to fetch data from the Examens table, joining with CoursEnseignant table
                String selectQuery = "SELECT Examens.ID_Examen, Examens.Date_Examen, CoursEnseignant.nomCours, Examens.Nom_Filiere " +
                                     "FROM Examens " +
                                     "JOIN CoursEnseignant ON Examens.ID_Cours = CoursEnseignant.idCours";

                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                    // Execute the query and get the result set
                    ResultSet resultSet = preparedStatement.executeQuery();

                    // Populate the examensTable with data from the result set
                    while (resultSet.next()) {
                        int idExamen = resultSet.getInt("ID_Examen");
                        String dateExamen = resultSet.getString("Date_Examen");
                        String nomCours = resultSet.getString("nomCours");
                        String nomFiliere = resultSet.getString("Nom_Filiere");

                        Examen examen = new Examen(idExamen, dateExamen, nomCours, nomFiliere);
                        examensTable.getItems().add(examen);
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
