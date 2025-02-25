package ma.enset.gestionconsultationdbcc.dao;

import ma.enset.gestionconsultationdbcc.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDao implements IpatientDao{
    @Override
    public void Create(Patient patient) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("insert into patient(NOM,PRENOM,TEL,EMAIL)" +
                "values(?,?,?,?)");

        try {
            pstmt.setString(1,patient.getNOM());
            pstmt.setString(2,patient.getPRENOM());
            pstmt.setString(3,patient.getTEL());
            pstmt.setString(4,patient.getEMAIL());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void Update(Patient patient) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("UPDATE patient SET NOM=?,PRENOM=?,TEL=?,EMAIL=? WHERE ID_PATIENT=?");
        pstmt.setString(1,patient.getNOM());
        pstmt.setString(2,patient.getPRENOM());
        pstmt.setString(3,patient.getTEL());
        pstmt.setString(4,patient.getEMAIL());
        pstmt.setLong(5,patient.getID_PATIENT());
        pstmt.executeUpdate();
    }

    @Override
    public void Delete(Patient patient) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement pstmt =connection.prepareStatement("DELETE FROM patient WHERE ID_PATIENT=?");
        pstmt.setLong(1,patient.getID_PATIENT());
        pstmt.executeUpdate();
    }

    @Override
    public List<Patient> GetAll() throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM patient");
        ResultSet rs = pstm.executeQuery();
        List<Patient> patients = new ArrayList<>();
        while (rs.next()) {
            Patient patient = new Patient();
            patient.setID_PATIENT(rs.getLong("ID_PATIENT"));
            patient.setNOM(rs.getString("NOM"));
            patient.setPRENOM(rs.getString("PRENOM"));
            patient.setTEL(rs.getString("TEL"));
            patient.setEMAIL(rs.getString("EMAIL"));
            patients.add(patient);
        }

        return patients;
    }

    @Override
    public Patient GetById(Long id) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM patient WHERE  ID_PATIENT=?");
        pstm.setLong(1,id);
        ResultSet rs = pstm.executeQuery();
        Patient patient = new Patient();
        if (rs.next()) {
            patient.setID_PATIENT(rs.getLong("ID_PATIENT"));
            patient.setNOM(rs.getString("NOM"));
            patient.setPRENOM(rs.getString("PRENOM"));
            patient.setTEL(rs.getString("TEL"));
            patient.setEMAIL(rs.getString("EMAIL"));

        }
        return patient;
    }

    @Override
    public List<Patient> findByQuery(String query) {
        Connection connection = DbConnection.getConnection();
        List<Patient> patients = new ArrayList<>();
        try {
            PreparedStatement pstm  = connection.prepareStatement("SELECT * FROM patient WHERE  NOM LIKE ? OR PRENOM LIKE ?");
            pstm.setString(1,"%"+query+"%");
            pstm.setString(2,"%"+query+"%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setID_PATIENT(rs.getLong("ID_PATIENT"));
                patient.setNOM(rs.getString("NOM"));
                patient.setPRENOM(rs.getString("PRENOM"));
                patient.setTEL(rs.getString("TEL"));
                patient.setEMAIL(rs.getString("EMAIL"));
                patients.add(patient);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return patients;
    }
}
