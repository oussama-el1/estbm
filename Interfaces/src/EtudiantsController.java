import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EtudiantsController {

    @FXML
    private TableView<Etudiant> etudiantsTable;

    @FXML
    private TableColumn<Etudiant, String> cinColumn;

    @FXML
    private TableColumn<Etudiant, String> nomColumn;

    @FXML
    private TableColumn<Etudiant, String> prenomColumn;

    @FXML
    private TableColumn<Etudiant, String> emailColumn;

    @FXML
    private TableColumn<Etudiant, String> telephoneColumn;

    @FXML
    private TableColumn<Etudiant, String> Filname;

    @FXML
    private TableColumn<Etudiant, Double> notebacColumn;

    public void initialize() {
        // Set cell value factories for each column
        cinColumn.setCellValueFactory(cellData -> cellData.getValue().getCinProperty());
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().getNomProperty());
        prenomColumn.setCellValueFactory(cellData -> cellData.getValue().getPrenomProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
        telephoneColumn.setCellValueFactory(cellData -> cellData.getValue().getTelephoneProperty());
        notebacColumn.setCellValueFactory(cellData -> cellData.getValue().getNotebacProperty().asObject());
        Filname.setCellValueFactory(cellData -> cellData.getValue().getfilnameProperty());
        fetchDataFromDatabase();
    }

        private void fetchDataFromDatabase() {
                try {
                    
                    Connection connection = DatabaseConnection.connect();

                    // Check if the connection is successful
                    if (connection != null) {
                        // SQL query to fetch data from the Etudiants table
                        String selectQuery = "SELECT * FROM Etudiants";

                        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                            // Execute the query and get the result set
                            ResultSet resultSet = preparedStatement.executeQuery();

                            while (resultSet.next()) {
                                String cin = resultSet.getString("cin");
                                String nom = resultSet.getString("nom");
                                String prenom = resultSet.getString("prenom");
                                String email = resultSet.getString("email");
                                String telephone = resultSet.getString("telephone");
                                double notebac = resultSet.getDouble("notebac");
                                String filname = resultSet.getString("nomFiliere");

                                Etudiant etudiant = new Etudiant(cin, nom, prenom, email, telephone, notebac, filname);
                                etudiantsTable.getItems().add(etudiant);
                            }
                        }

                        connection.close();
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
}
