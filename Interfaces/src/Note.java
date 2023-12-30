public class Note {
    private int id;
    private String etudiantId;
    private int examenId;
    private double note;


    public Note(int id, String etudiantId, int examenId, double note) {
        this.id = id;
        this.etudiantId = etudiantId;
        this.examenId = examenId;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public String getEtudiantId() {
        return etudiantId;
    }

    public int getExamenId() {
        return examenId;
    }

    public double getNote() {
        return note;
    }
}
