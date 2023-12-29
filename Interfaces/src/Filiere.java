import javafx.beans.property.*;

public class Filiere {
    private final IntegerProperty idFiliere;
    private final StringProperty nomFiliere;
    private final IntegerProperty responsable;
    private final StringProperty nomEnseignant;
    private final StringProperty telephoneEnseignant;
    private final StringProperty description;

    public Filiere(int idFiliere, String nomFiliere, int responsable, String nomEnseignant, String telephoneEnseignant, String description) {
        this.idFiliere = new SimpleIntegerProperty(idFiliere);
        this.nomFiliere = new SimpleStringProperty(nomFiliere);
        this.responsable = new SimpleIntegerProperty(responsable);
        this.nomEnseignant = new SimpleStringProperty(nomEnseignant);
        this.telephoneEnseignant = new SimpleStringProperty(telephoneEnseignant);
        this.description = new SimpleStringProperty(description);
    }

    public int getIdFiliere() {
        return idFiliere.get();
    }

    public IntegerProperty getIdFiliereProperty() {
        return idFiliere;
    }

    public String getNomFiliere() {
        return nomFiliere.get();
    }

    public StringProperty getNomFiliereProperty() {
        return nomFiliere;
    }

    public int getResponsable() {
        return responsable.get();
    }

    public IntegerProperty getResponsableProperty() {
        return responsable;
    }

    public String getNomEnseignant() {
        return nomEnseignant.get();
    }

    public StringProperty getNomEnseignantProperty() {
        return nomEnseignant;
    }

    public String getTelephoneEnseignant() {
        return telephoneEnseignant.get();
    }

    public StringProperty getTelephoneEnseignantProperty() {
        return telephoneEnseignant;
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty getDescriptionProperty() {
        return description;
    }
}
