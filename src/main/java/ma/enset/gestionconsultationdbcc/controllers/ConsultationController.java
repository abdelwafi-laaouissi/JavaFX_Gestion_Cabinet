package ma.enset.gestionconsultationdbcc.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.gestionconsultationdbcc.dao.ConsulationDao;
import ma.enset.gestionconsultationdbcc.dao.PatientDao;
import ma.enset.gestionconsultationdbcc.entities.Consultation;
import ma.enset.gestionconsultationdbcc.entities.Patient;
import ma.enset.gestionconsultationdbcc.service.CabinetService;
import ma.enset.gestionconsultationdbcc.service.ICabinetService;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConsultationController implements Initializable {



    @FXML private  DatePicker dateConsultation;
    @FXML private  ComboBox<Patient> comboPatient;
    @FXML private TextArea texteFieldDescription;
    @FXML private TableView<Consultation> tableConsultation;
    @FXML private TableColumn<Consultation,Long> columnID;
    @FXML private TableColumn<Consultation,DateCell> columnDateConsultation;
    @FXML private TableColumn<Consultation,String> columnDescription;
    @FXML private TableColumn<Consultation,Patient> columnPatient;

    private ICabinetService cabinetService;
    private ObservableList<Patient> patients = FXCollections.observableArrayList();
    private ObservableList<Consultation> consultations = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cabinetService =new CabinetService(new PatientDao(),new ConsulationDao());
        comboPatient.setItems(patients);
        patients.setAll(cabinetService.getALLPatients());
        columnID.setCellValueFactory(new PropertyValueFactory<>("ID_consultation"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("DESCRIPTIONS"));
        columnDateConsultation.setCellValueFactory(new PropertyValueFactory<>("DATE_Consultation"));
        columnPatient.setCellValueFactory(new PropertyValueFactory<>("PATIENT"));
        tableConsultation.setItems(consultations);
        tableConsultation.getSelectionModel().selectedItemProperty().addListener((observable, oldConsultations ,newConsultations) -> {
            if (newConsultations != null) {
                dateConsultation.setUserData(newConsultations.getDATE_Consultation());
                dateConsultation.setValue(newConsultations.getDATE_Consultation().toLocalDate());
                comboPatient.setUserData(newConsultations.getPATIENT());
                texteFieldDescription.setText(newConsultations.getDESCRIPTIONS());
                comboPatient.getSelectionModel().select(newConsultations.getPATIENT());
            }
        });
        loadConsultation();
    }
    public void addConsultation(){
        Consultation consultation = new Consultation();
        consultation.setDESCRIPTIONS(texteFieldDescription.getText());
        consultation.setDATE_Consultation(Date.valueOf(dateConsultation.getValue()));
        consultation.setPATIENT(comboPatient.getSelectionModel().getSelectedItem());
        try {
            cabinetService.addConsultation(consultation);
            messageInfo("Consultation added");
            vider_champs();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadConsultation();
    }
    public void loadConsultation(){
        consultations.setAll(cabinetService.getALLConsultation());
    }
    public void deleteConsultation(){
        Consultation consultation = tableConsultation.getSelectionModel().getSelectedItem();
        if (consultation != null) {
            cabinetService.deleteConsultation(consultation);
            messageInfo("Consultation deleted");
            vider_champs();
            loadConsultation();
        }else {
            messageInfo("Consultation not found");
        }
    }
    public void messageInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void vider_champs(){
        texteFieldDescription.setText("");
        dateConsultation.setValue(null);
        comboPatient.getSelectionModel().select(null);

    }
}
