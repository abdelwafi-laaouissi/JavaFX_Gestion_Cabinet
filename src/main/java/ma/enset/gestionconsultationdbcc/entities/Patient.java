package ma.enset.gestionconsultationdbcc.entities;

import java.util.List;

public class Patient {
    private Long ID_PATIENT;
    private String NOM;
    private String PRENOM;
    private String TEL;
    private String EMAIL;
    private List<Consultation> consultations;

    public Patient() {
    }

    public Patient(String NOM, String PRENOM, String TEL, String EMAIL) {
        this.NOM = NOM;
        this.PRENOM = PRENOM;
        this.TEL = TEL;
        this.EMAIL = EMAIL;
    }

    public long getID_PATIENT() {
        return ID_PATIENT;
    }

    public void setID_PATIENT(long ID_PATIENT) {
        this.ID_PATIENT = ID_PATIENT;
    }

    public String getNOM() {
        return NOM;
    }

    public void setNOM(String NOM) {
        this.NOM = NOM;
    }

    public String getPRENOM() {
        return PRENOM;
    }

    public void setPRENOM(String PRENOM) {
        this.PRENOM = PRENOM;
    }

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    @Override
    public String toString() {
        return NOM + " " + PRENOM;

    }
}
