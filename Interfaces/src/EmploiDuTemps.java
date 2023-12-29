import javafx.beans.property.*;

public class EmploiDuTemps {
    private final IntegerProperty idEmploiDuTemps;
    private final IntegerProperty idCours;
    private final IntegerProperty idEnseignant;
    private final StringProperty idSalle;
    private final IntegerProperty idFiliere;
    private final StringProperty jour;
    private final StringProperty heureDebut;
    private final StringProperty heureFin;
    private final StringProperty nomCours;
    private final StringProperty nomEnseignant;

    public EmploiDuTemps(int idEmploiDuTemps, int idCours, int idEnseignant, String idSalle, int idFiliere,
                        String jour, String heureDebut, String heureFin, String nomCours, String nomEnseignant) {
        this.idEmploiDuTemps = new SimpleIntegerProperty(idEmploiDuTemps);
        this.idCours = new SimpleIntegerProperty(idCours);
        this.idEnseignant = new SimpleIntegerProperty(idEnseignant);
        this.idSalle = new SimpleStringProperty(idSalle);
        this.idFiliere = new SimpleIntegerProperty(idFiliere);
        this.jour = new SimpleStringProperty(jour);
        this.heureDebut = new SimpleStringProperty(heureDebut);
        this.heureFin = new SimpleStringProperty(heureFin);
        this.nomCours = new SimpleStringProperty(nomCours);
        this.nomEnseignant = new SimpleStringProperty(nomEnseignant);
    }

    public int getIdEmploiDuTemps() {
        return idEmploiDuTemps.get();
    }

    public IntegerProperty getIdEmploiDuTempsProperty() {
        return idEmploiDuTemps;
    }

    public int getIdCours() {
        return idCours.get();
    }

    public IntegerProperty getIdCoursProperty() {
        return idCours;
    }

    public int getIdEnseignant() {
        return idEnseignant.get();
    }

    public IntegerProperty getIdEnseignantProperty() {
        return idEnseignant;
    }

    public String getIdSalle() {
        return idSalle.get();
    }

    public StringProperty getIdSalleProperty() {
        return idSalle;
    }

    public int getIdFiliere() {
        return idFiliere.get();
    }

    public IntegerProperty getIdFiliereProperty() {
        return idFiliere;
    }

    public String getJour() {
        return jour.get();
    }

    public StringProperty getJourProperty() {
        return jour;
    }

    public String getHeureDebut() {
        return heureDebut.get();
    }

    public StringProperty getHeureDebutProperty() {
        return heureDebut;
    }

    public String getHeureFin() {
        return heureFin.get();
    }

    public StringProperty getHeureFinProperty() {
        return heureFin;
    }

    public String getNomCours() {
        return nomCours.get();
    }

    public StringProperty getNomCoursProperty() {
        return nomCours;
    }

    public String getNomEnseignant() {
        return nomEnseignant.get();
    }

    public StringProperty getNomEnseignantProperty() {
        return nomEnseignant;
    }
}
