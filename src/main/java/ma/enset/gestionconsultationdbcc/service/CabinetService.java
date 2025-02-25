package ma.enset.gestionconsultationdbcc.service;

import ma.enset.gestionconsultationdbcc.dao.ConsulationDao;
import ma.enset.gestionconsultationdbcc.dao.IconsultationDao;
import ma.enset.gestionconsultationdbcc.dao.IpatientDao;
import ma.enset.gestionconsultationdbcc.dao.PatientDao;
import ma.enset.gestionconsultationdbcc.entities.Consultation;
import ma.enset.gestionconsultationdbcc.entities.Patient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CabinetService implements ICabinetService{
    private IpatientDao patientDao;
    private IconsultationDao consulationDao;

    public CabinetService(IpatientDao patientDao, IconsultationDao consulationDao) {
        this.patientDao = patientDao;
        this.consulationDao = consulationDao;
    }

    @Override
    public void addPatient(Patient patient) {
        try {
            patientDao.Create(patient);
        }
        catch (Exception e) {
             e.printStackTrace();
        }

    }

    @Override
    public void deletePatient(Patient patient) {
      try {
          patientDao.Delete(patient);
      }
      catch (Exception e) {
          e.printStackTrace();
      }
    }

    @Override
    public void updatePatient(Patient patient) {
        try {
            patientDao.Update(patient);
        }
        catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public List<Patient> getALLPatients() {
        List<Patient> patients =null;
        try {
            patients = patientDao.GetAll();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public Patient getPatientByID(long id) {
        Patient patient = null;
        try {
            patient = patientDao.GetById(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return patient;
    }

    @Override
    public List<Patient> searchPatientByQuery(String query) {
        List<Patient> patients =null;
        try {
            patients = patientDao.findByQuery(query);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public void addConsultation(Consultation consultation){
        try {
            consulationDao.Create(consultation);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteConsultation(Consultation consultation) {
        try {
            consulationDao.Delete(consultation);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateConsultation(Consultation consultation) {
        try {
            consulationDao.Update(consultation);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Consultation> getALLConsultation() {
        List<Consultation> consultations =null;
        try {
            consultations = consulationDao.GetAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consultations;
    }

    @Override
    public Consultation getConsultationByID(long id) {
        Consultation consultation = null;
        try {
            consultation = consulationDao.GetById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consultation;
    }
}
