package ma.enset.gestionconsultationdbcc.service;

import ma.enset.gestionconsultationdbcc.entities.Consultation;
import ma.enset.gestionconsultationdbcc.entities.Patient;

import java.sql.SQLException;
import java.util.List;

public interface ICabinetService {


    void addPatient(Patient patient);
    void deletePatient(Patient patient);
    void updatePatient(Patient patient);
    List<Patient> getALLPatients();
    Patient getPatientByID(long id);
    List<Patient> searchPatientByQuery(String query);


    void addConsultation(Consultation consultation) throws SQLException;
    void deleteConsultation(Consultation consultation);
    void updateConsultation(Consultation consultation);
    List<Consultation> getALLConsultation();
    Consultation getConsultationByID(long id);

}
