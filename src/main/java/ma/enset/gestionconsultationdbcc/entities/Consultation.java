package ma.enset.gestionconsultationdbcc.entities;

import java.sql.Date;

public class Consultation {
    private long ID_consultation;
    private String DESCRIPTIONS;
    private Date DATE_Consultation;
    private Patient PATIENT;

    public Consultation() {
    }

    public Consultation(String DESCRIPTIONS, Date DATE_Consultation, Patient PATIENT) {
        this.DESCRIPTIONS = DESCRIPTIONS;
        this.DATE_Consultation = DATE_Consultation;
        this.PATIENT = PATIENT;
    }

    public long getID_consultation() {
        return ID_consultation;
    }

    public void setID_consultation(long ID_consultation) {
        this.ID_consultation = ID_consultation;
    }

    public String getDESCRIPTIONS() {
        return DESCRIPTIONS;
    }

    public void setDESCRIPTIONS(String DESCRIPTIONS) {
        this.DESCRIPTIONS = DESCRIPTIONS;
    }

    public Date getDATE_Consultation() {
        return DATE_Consultation;
    }

    public void setDATE_Consultation(Date DATE_Consultation) {
        this.DATE_Consultation = DATE_Consultation;
    }

    public Patient getPATIENT() {
        return PATIENT;
    }

    public void setPATIENT(Patient PATIENT) {
        this.PATIENT = PATIENT;
    }
}
