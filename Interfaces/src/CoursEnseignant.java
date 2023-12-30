public class CoursEnseignant {
    private int idCours;
    private String nomCours;
    private String nomEnseignant;

    // Constructor, getters, and setters

    public CoursEnseignant(int idCours, String nomCours, String nomEnseignant) {
        this.idCours = idCours;
        this.nomCours = nomCours;
        this.nomEnseignant = nomEnseignant;
    }

    public int getIdCours() {
        return idCours;
    }

    public String getNomCours() {
        return nomCours;
    }

    public String getNomEnseignant() {
        return nomEnseignant;
    }
}
