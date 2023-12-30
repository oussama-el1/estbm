import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class NoteslistController {

    @FXML
    private TextField etudiantIdTextField;

    @FXML
    private TableView<Note> notesTable;

    @FXML
    private TableColumn<Note, Integer> idColumn;

    @FXML
    private TableColumn<Note, String> etudiantIdColumn;

    @FXML
    private TableColumn<Note, Integer> examenIdColumn;

    @FXML
    private TableColumn<Note, Double> noteColumn;

    public void initialize() {
        // Set cell value factories for each column
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        etudiantIdColumn.setCellValueFactory(new PropertyValueFactory<>("etudiantId"));
        examenIdColumn.setCellValueFactory(new PropertyValueFactory<>("examenId"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
    }

    @FXML
    private void showNotesForEtudiant() {
        notesTable.getItems().clear();

        try {
            // Get a database connection
            Connection connection = DatabaseConnection.connect();

            // Check if the connection is successful
            if (connection != null) {
                // SQL query to fetch notes for a specific student
                String selectQuery = "SELECT * FROM Notes WHERE ID_Etudiant = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                    preparedStatement.setString(1, etudiantIdTextField.getText());

                    // Execute the query and get the result set
                    ResultSet resultSet = preparedStatement.executeQuery();

                    // Populate the notesTable with data from the result set
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String etudiantId = resultSet.getString("ID_Etudiant");
                        int examenId = resultSet.getInt("ID_Examen");
                        double note = resultSet.getDouble("Note");

                        Note noteObj = new Note(id, etudiantId, examenId, note);
                        notesTable.getItems().add(noteObj);
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

    @FXML
    private void printAndDownloadTable() {
        // For demonstration purposes, I'll show a file chooser dialog
        Stage stage = (Stage) notesTable.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Table Data");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("CSV Files", "*.csv"));
        fileChooser.setInitialFileName("notes_table");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            saveTableDataToFile(file);
        }
    }

    private void saveTableDataToFile(File file) {
        try (FileWriter writer = new FileWriter(file)) {
            // Write header
            writer.write("ID,Etudiant ID,Examen ID,Note\n");

            // Write data
            for (Note note : notesTable.getItems()) {
                writer.write(note.getId() + "," + note.getEtudiantId() + "," + note.getExamenId() + "," + note.getNote() + "\n");
            }

            System.out.println("Table data saved to file: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
