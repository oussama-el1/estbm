import javafx.beans.property.*;

public class Etudiant {
    private final StringProperty cin;
    private final StringProperty nom;
    private final StringProperty prenom;
    private final StringProperty email;
    private final StringProperty telephone;
    private final StringProperty filname;
    private final DoubleProperty notebac;


    public Etudiant(String cin, String nom, String prenom, String email, String telephone, double notebac, String filname) {
        this.cin = new SimpleStringProperty(cin);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.email = new SimpleStringProperty(email);
        this.telephone = new SimpleStringProperty(telephone);
        this.notebac = new SimpleDoubleProperty(notebac);
        this.filname = new SimpleStringProperty(filname);
    }

    public String getCin() {
        return cin.get();
    }

    public StringProperty getCinProperty() {
        return cin;
    }

    public String getfilname() {
        return filname.get();
    }

    public StringProperty getfilnameProperty() {
        return filname;
    }

    public String getNom() {
        return nom.get();
    }

    public StringProperty getNomProperty() {
        return nom;
    }

    public String getPrenom() {
        return prenom.get();
    }

    public StringProperty getPrenomProperty() {
        return prenom;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty getEmailProperty() {
        return email;
    }

    public String getTelephone() {
        return telephone.get();
    }

    public StringProperty getTelephoneProperty() {
        return telephone;
    }

    public double getNotebac() {
        return notebac.get();
    }

    public DoubleProperty getNotebacProperty() {
        return notebac;
    }
}
