package ma.enset.gestionconsultationdbcc.dao;

import ma.enset.gestionconsultationdbcc.entities.Consultation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsulationDao implements IconsultationDao{
    @Override
    public void Create(Consultation consultation) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO CONSULTATION(DESCRIPTIONS,DATE_Consultation,ID_PATIENT) " +
                "VALUES (?,?,?)");
        pstmt.setString(1,consultation.getDESCRIPTIONS());
        pstmt.setDate(2,consultation.getDATE_Consultation());
        pstmt.setLong(3,consultation.getPATIENT().getID_PATIENT());
        pstmt.executeUpdate();
    }

    @Override
    public void Update(Consultation consultation) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("UPDATE CONSULTATION SET CONSULTATION=?,DATE_Consultation=?,ID_PATIENT=? WHERE ID_consultation=?");
        pstmt.setString(1,consultation.getDESCRIPTIONS());
        pstmt.setDate(2,consultation.getDATE_Consultation());
        pstmt.setLong(3,consultation.getPATIENT().getID_PATIENT());
        pstmt.setLong(4,consultation.getID_consultation());
        pstmt.executeUpdate();

    }

    @Override
    public void Delete(Consultation consultation) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("DELETE FROM CONSULTATION WHERE ID_consultation=?");
        pstmt.setLong(1,consultation.getID_consultation());
        pstmt.executeUpdate();

    }

    @Override
    public List<Consultation> GetAll() throws SQLException {
        List<Consultation> consultations = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM CONSULTATION");
        ResultSet resultSet = pstmt.executeQuery();
        PatientDao patientDao = new PatientDao();
        while (resultSet.next()) {
            Consultation consultation = new Consultation();
            consultation.setID_consultation(resultSet.getLong("ID_consultation"));
            consultation.setDESCRIPTIONS(resultSet.getString("DESCRIPTIONS"));
            consultation.setDATE_Consultation(resultSet.getDate("DATE_Consultation"));
            consultation.setPATIENT(patientDao.GetById(resultSet.getLong("ID_PATIENT")));
            consultations.add(consultation);
        }

        return consultations;
    }

    @Override
    public Consultation GetById(Long id) throws SQLException {
        Consultation consultation = new Consultation();
        Connection connection = DbConnection.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM CONSULTATION WHERE ID_consultation=?");
        pstmt.setLong(1,id);
        ResultSet resultSet = pstmt.executeQuery();
        PatientDao patientDao = new PatientDao();
        while (resultSet.next()) {
            consultation.setDESCRIPTIONS(resultSet.getString("DESCRIPTIONS"));
            consultation.setDATE_Consultation(resultSet.getDate("DATE_Consultation"));
            consultation.setPATIENT(patientDao.GetById(resultSet.getLong("ID_PATIENT")));

        }

        return consultation;
    }
}
