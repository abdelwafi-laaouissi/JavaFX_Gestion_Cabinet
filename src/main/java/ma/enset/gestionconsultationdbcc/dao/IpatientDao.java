package ma.enset.gestionconsultationdbcc.dao;

import ma.enset.gestionconsultationdbcc.entities.Patient;

import java.util.List;

public interface IpatientDao extends Dao<Patient,Long>{
    List<Patient> findByQuery(String Query);
}
