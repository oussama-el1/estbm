public class Examen {
    private int idExamen;
    private String dateExamen;
    private String nomCours;
    private String nomFiliere;

    // Constructor, getters, and setters

    public Examen(int idExamen, String dateExamen, String nomCours, String nomFiliere) {
        this.idExamen = idExamen;
        this.dateExamen = dateExamen;
        this.nomCours = nomCours;
        this.nomFiliere = nomFiliere;
    }

    public int getIdExamen() {
        return idExamen;
    }

    public String getDateExamen() {
        return dateExamen;
    }

    public String getNomCours() {
        return nomCours;
    }

    public String getNomFiliere() {
        return nomFiliere;
    }
}
