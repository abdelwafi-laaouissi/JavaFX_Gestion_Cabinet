package ma.enset.gestionconsultationdbcc.controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import ma.enset.gestionconsultationdbcc.dao.ConsulationDao;
import ma.enset.gestionconsultationdbcc.dao.PatientDao;
import ma.enset.gestionconsultationdbcc.entities.Patient;
import ma.enset.gestionconsultationdbcc.service.CabinetService;
import ma.enset.gestionconsultationdbcc.service.ICabinetService;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class PatientController implements Initializable {


    @FXML private TextField textfieldNom;
    @FXML private TextField textfieldPrenom;
    @FXML private TextField textfieldTel;
    @FXML private TextField textfieldEmail;
    @FXML private TextField textfieldSearch;
    @FXML private TableView<Patient> tablePatients;
    @FXML private TableColumn<Patient,Long> columnID;
    @FXML private TableColumn<Patient,String> columnNOM;
    @FXML private TableColumn<Patient,String> columnPRENOM;
    @FXML private TableColumn<Patient,String> columnTEL;
    @FXML private TableColumn<Patient,String> columnEMAIL;

    private ICabinetService cabinetService;
    private Patient selectedPatient;
    private ObservableList<Patient> patients = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cabinetService = new CabinetService(new PatientDao(),new ConsulationDao());
        columnID.setCellValueFactory(new PropertyValueFactory<>("ID_PATIENT"));
        columnNOM.setCellValueFactory(new PropertyValueFactory<>("NOM"));
        columnPRENOM.setCellValueFactory(new PropertyValueFactory<>("PRENOM"));
        columnTEL.setCellValueFactory(new PropertyValueFactory<>("TEL"));
        columnEMAIL.setCellValueFactory(new PropertyValueFactory<>("EMAIL"));
        tablePatients.setItems(patients);
        textfieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
           // System.out.println(oldValue+" --> "+newValue);
            patients.setAll(cabinetService.searchPatientByQuery(newValue));
        });
        tablePatients.getSelectionModel().selectedItemProperty().addListener((observable, oldPatient, newPatient) -> {
            if (newPatient != null) {
                textfieldNom.setText(newPatient.getNOM());
                textfieldPrenom.setText(newPatient.getPRENOM());
                textfieldTel.setText(newPatient.getTEL());
                textfieldEmail.setText(newPatient.getEMAIL());
                selectedPatient =newPatient;
            }
        });
        loadPatients();

    }
    public void addPatient() {
        Patient patient = new Patient();
        patient.setNOM(textfieldNom.getText());
        patient.setPRENOM(textfieldPrenom.getText());
        patient.setTEL(textfieldTel.getText());
        patient.setEMAIL(textfieldEmail.getText());
        cabinetService.addPatient(patient);
        messageInfo("Patient added");
        loadPatients();
    }
    public void deletePatient() {
        Patient patient = tablePatients.getSelectionModel().getSelectedItem();
        if (patient != null) {
            String nom = patient.getNOM();
            cabinetService.deletePatient(patient);
            messageInfo("Patient " + nom + " deleted successfully");
        }
        else {
            messageInfo("No Patient Selected");
        }

        loadPatients();
    }
    public void loadPatients() {
        vider_champs();
        patients.setAll(cabinetService.getALLPatients());
    }
    public void messageInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void updatePatient() {
        selectedPatient.setNOM(textfieldNom.getText());
        selectedPatient.setPRENOM(textfieldPrenom.getText());
        selectedPatient.setTEL(textfieldTel.getText());
        selectedPatient.setEMAIL(textfieldEmail.getText());
        cabinetService.updatePatient(selectedPatient);
        messageInfo("Patient " + selectedPatient.getNOM() + " updated successfully");
        loadPatients();
    }
    public void vider_champs(){
        textfieldNom.setText("");
        textfieldPrenom.setText("");
        textfieldTel.setText("");
        textfieldEmail.setText("");
        textfieldSearch.setText("");
    }
}
