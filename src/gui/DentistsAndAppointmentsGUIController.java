package gui;


import domain.*;
import gui.actions_domain.ActionAdd;
import gui.actions_domain.ActionRemove;
import gui.actions_domain.ActionUpdate;
import gui.undo_redo_stacks.ActionsStack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import service.ServiceAppointments;
import service.ServiceDentistAssignments;
import service.ServiceDentists;
import service.ServiceReports;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class DentistsAndAppointmentsGUIController {

    private ServiceDentists serviceDentists;
    private ServiceAppointments serviceAppointments;
    private ServiceDentistAssignments serviceDentistAssignments;
    private ServiceReports serviceReports;

    private ActionsStack<Integer,? extends Identifiable<Integer>> actionsStackAllEntities;
    //private ActionsStack<Integer,Appointment> actionsStackAppointments;

    ObservableList<Appointment> appointmentsObservableList;
    ObservableList<Dentist> dentistsObservableList;
    ObservableList<DentistAssignment> dentistAssignmentsObservableList;

    ObservableList<Object> appointmentsSpecialOptionsObservableList;

    ObservableList<Appointment> appointmentsReportsObservableList;
    ObservableList<Dentist> dentistsReportsObservableList;


    public DentistsAndAppointmentsGUIController(ServiceDentists serviceDentists, ServiceAppointments serviceAppointments
            , ServiceDentistAssignments serviceDentistAssignments, ServiceReports serviceReports) {
        this.serviceDentists = serviceDentists;
        this.serviceAppointments = serviceAppointments;
        this.serviceDentistAssignments = serviceDentistAssignments;
        this.serviceReports = serviceReports;
        this.actionsStackAllEntities = new ActionsStack<>();

    }

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button buttonAddAppointment;

    @FXML
    private Button buttonAddDentist;

    @FXML
    private Button buttonAddDentistAssignment;

    @FXML
    private Button buttonFindByIdAppointment;

    @FXML
    private Button buttonFindByIdDentist;

    @FXML
    private Button buttonFindByIdDentistAssignment;

    @FXML
    private Button buttonGetAllDentistsByAppointmentPatientNameSortedByAgeAscendingAndNameDescending;

    @FXML
    private Button buttonGetAppointmentsByDentistId;

    @FXML
    private Button buttonGetAppointmentsStartingAtHourAndAtCertainDate;

    @FXML
    private Button buttonGetDentistIdsWithAppointmentsAtCertainDate;

    @FXML
    private Button buttonGetDentistsHavingAppointmentAtHourSortedByName;

    @FXML
    private Button buttonGetIdAppointmentsOfDoctorName;

    @FXML
    private Button buttonGetNameDoctorWithMostAppointments;

    @FXML
    private Button buttonModifyAppointment;

    @FXML
    private Button buttonModifyDentist;

    @FXML
    private Button buttonModifyDentistAssignment;

    @FXML
    private Button buttonRemoveAppointment;

    @FXML
    private Button buttonRemoveDentist;

    @FXML
    private Button buttonRemoveDentistAssignment;

    @FXML
    private Button buttonRedo;

    @FXML
    private Button buttonUndo;

    @FXML
    private GridPane gridAppointmentsAddRemoveModifyFindById;

    @FXML
    private GridPane gridDentistAssignmentsAddRemoveModifyFindById;

    @FXML
    private GridPane gridDentistsAddRemoveModifyFindById;

    @FXML
    private Label labelDentistAssignmentConsultationCost;

    @FXML
    private Label labelAppointmentDate;

    @FXML
    private Label labelAppointmentDoctorName;

    @FXML
    private Label labelAppointmentHour;

    @FXML
    private Label labelAppointmentId;

    @FXML
    private Label labelAppointmentPatientName;

    @FXML
    private Label labelAppointmentsReportsTitle;

    @FXML
    private Label labelAppointmentsTitle;

    @FXML
    private Label labelDentistAge;

    @FXML
    private Label labelDentistAssignmentIdOfAppointment;

    @FXML
    private Label labelDentistAssignmentIdOfDentist;

    @FXML
    private Label labelDentistAssignmentIdOfDentistAssignment;

    @FXML
    private Label labelDentistAssignmentsTitle;

    @FXML
    private Label labelDentistId;

    @FXML
    private Label labelDentistName;

    @FXML
    private Label labelDentistsReportsTitle;

    @FXML
    private Label labelDentistsTitle;

    @FXML
    private Label labelReportsTitle;

    @FXML
    private Line lineSeparateAppointmentsFromReports;

    @FXML
    private Line lineSeparateDentistAssignmentsLeftFromReports;

    @FXML
    private Line lineSeparateDentistAssignmentsUpFromReports;

    @FXML
    private Line lineSeparateDentistsUpFromReports;

    @FXML
    private ListView<Appointment> listViewAppointments;

    @FXML
    private ListView<Appointment> listViewAppointmentsReports;

    @FXML
    private ListView<Object> listViewAppointmentsSpecialOptions;

    @FXML
    private ListView<DentistAssignment> listViewDentistAssignments;

    @FXML
    private ListView<Dentist> listViewDentists;

    @FXML
    private ListView<Dentist> listViewDentistsReports;

    @FXML
    private TextField textFieldAppointmentDate;

    @FXML
    private TextField textFieldAppointmentDoctorName;

    @FXML
    private TextField textFieldAppointmentHour;

    @FXML
    private TextField textFieldAppointmentId;

    @FXML
    private TextField textFieldAppointmentPatientName;

    @FXML
    private TextField textFieldDentistAge;

    @FXML
    private TextField textFieldDentistAssignmentIdOfAppointment;

    @FXML
    private TextField textFieldDentistAssignmentConsultationCost;

    @FXML
    private TextField textFieldDentistAssignmentIdOfDentist;

    @FXML
    private TextField textFieldDentistAssignmentIdOfDentistAssignment;

    @FXML
    private TextField textFieldDentistId;

    @FXML
    private TextField textFieldDentistName;

    // Assignments OPTIONS

    // initializing lists
    public void initializeOrResetAppointments() {
        appointmentsObservableList = FXCollections.observableArrayList();
        Iterable<Appointment> appointmentsFromService = this.serviceAppointments.getAll();
        for(Appointment appointment : appointmentsFromService) {
            appointmentsObservableList.add(appointment);
        }
        listViewAppointments.setItems(appointmentsObservableList);
    }


    public void initializeAppointmentsFromSpecialOptions() {
        appointmentsSpecialOptionsObservableList = FXCollections.observableArrayList();
        listViewAppointmentsSpecialOptions.setItems(appointmentsSpecialOptionsObservableList);
    }

    public void clearAppointmentsTextFields() {
        textFieldAppointmentId.clear();
        textFieldAppointmentPatientName.clear();
        textFieldAppointmentDoctorName.clear();
        textFieldAppointmentHour.clear();
        textFieldAppointmentDate.clear();
    }


    //actions

    @FXML
    void buttonAddAppointmentHandler(ActionEvent event) {
        try {
            Integer idAppointment = Integer.parseInt(textFieldAppointmentId.getText());
            String patientName = textFieldAppointmentPatientName.getText();
            String doctorName = textFieldAppointmentDoctorName.getText();
            String hour = textFieldAppointmentHour.getText();
            String date = textFieldAppointmentDate.getText();
            serviceAppointments.add(idAppointment, patientName, doctorName, hour, date);

            // undo_redo part
            Appointment appointmentForActionAdd = new Appointment(idAppointment, patientName, doctorName, hour, date);
            ActionAdd<Integer, Appointment> actionForAddingAppointment = new ActionAdd<>(appointmentForActionAdd, serviceAppointments.getRepository());
            actionsStackAllEntities.addAction(actionForAddingAppointment);
            // undo_redo part

            initializeOrResetAppointments();
            clearAppointmentsTextFields();
        }
        catch (RuntimeException e)
            {
                Alert addAppointmentErrorAlert = new Alert(Alert.AlertType.ERROR);
                addAppointmentErrorAlert.setContentText(e.getMessage());
                addAppointmentErrorAlert.showAndWait();
            }

    }

    @FXML
    void buttonRemoveAppointmentHandler(ActionEvent event) {
        try {
            Appointment selectedAppointment = listViewAppointments.getSelectionModel().getSelectedItem();
            serviceAppointments.delete(selectedAppointment.getId());

            // undo_redo part
            ActionRemove<Integer, Appointment> actionForRemovingAppointment = new ActionRemove<>(selectedAppointment,serviceAppointments.getRepository());
            actionsStackAllEntities.addAction(actionForRemovingAppointment);
            // undo_redo part


            initializeOrResetAppointments();
        } catch (RuntimeException e) {
            Alert deleteAppointmentErrorAlert = new Alert(Alert.AlertType.ERROR);
            deleteAppointmentErrorAlert.setContentText("Please select a valid appointment!");
            deleteAppointmentErrorAlert.showAndWait();
        }

    }

    @FXML
    void buttonModifyAppointmentHandler(ActionEvent event) {
        try {
            String patientName = textFieldAppointmentPatientName.getText();
            String doctorName = textFieldAppointmentDoctorName.getText();
            String hour = textFieldAppointmentHour.getText();
            String date = textFieldAppointmentDate.getText(); //new

            Appointment selectedAppointment = listViewAppointments.getSelectionModel().getSelectedItem(); //old

            serviceAppointments.modify(selectedAppointment.getId(),patientName,doctorName,hour,date);

            // undo_redo part
            Appointment newAppointmentForActionUpdate = new Appointment(selectedAppointment.getId(), patientName, doctorName, hour, date); //the id does not change
            ActionUpdate<Integer, Appointment> actionForUpdatingAppointment
                    = new ActionUpdate<>(serviceAppointments.getRepository(),selectedAppointment,newAppointmentForActionUpdate);
            actionsStackAllEntities.addAction(actionForUpdatingAppointment);
            // undo_redo part




            initializeOrResetAppointments();
            clearAppointmentsTextFields();
        } catch (RuntimeException e) {
            Alert modifyAppointmentErrorAlert = new Alert(Alert.AlertType.ERROR);
            modifyAppointmentErrorAlert.setContentText(e.getMessage());
            modifyAppointmentErrorAlert.showAndWait();
        }
    }

    @FXML
    void buttonFindByIdAppointmentHandler(ActionEvent event) {

        try {
            Integer idAppointment = Integer.parseInt(textFieldAppointmentId.getText());

            Alert findByIdAppointmentInformationAlert = new Alert(Alert.AlertType.INFORMATION);
            findByIdAppointmentInformationAlert.setContentText(serviceAppointments.findById(idAppointment).toString());
            findByIdAppointmentInformationAlert.showAndWait();

            clearAppointmentsTextFields();
        }catch (RuntimeException e) {
            Alert findByIdAppointmentErrorAlert = new Alert(Alert.AlertType.ERROR);
            findByIdAppointmentErrorAlert.setContentText(e.getMessage());
            findByIdAppointmentErrorAlert.showAndWait();
        }

    }

    @FXML
    void buttonGetIdsAppointmentsOfDoctorNameHandler(ActionEvent event) {
        initializeAppointmentsFromSpecialOptions();

        ArrayList<Integer> idsOfAppointmentsOfDoctorNameFromService
                = this.serviceAppointments.getIdsOfAppointmentsOfDoctor(textFieldAppointmentDoctorName.getText());
        appointmentsSpecialOptionsObservableList.addAll(idsOfAppointmentsOfDoctorNameFromService);
        listViewAppointmentsSpecialOptions.setItems(appointmentsSpecialOptionsObservableList);

        clearAppointmentsTextFields();
    }

    @FXML
    void buttonGetNameDoctorWithMostAppointmentsHandler(ActionEvent event) {
        initializeAppointmentsFromSpecialOptions();

        String namesOfDoctorsWithMostAppointmentsFromAppointmentsFromService
                = this.serviceAppointments.getNameOfDoctorWithMostAppointments();
        appointmentsSpecialOptionsObservableList.addAll(namesOfDoctorsWithMostAppointmentsFromAppointmentsFromService);
        listViewAppointmentsSpecialOptions.setItems(appointmentsSpecialOptionsObservableList);

        clearAppointmentsTextFields();

    }




    // Dentist Assignments Options
    public void initializeOrResetDentistAssignments() {
        dentistAssignmentsObservableList = FXCollections.observableArrayList();
        Iterable<DentistAssignment> dentistAssignmentsFromService = this.serviceDentistAssignments.getAll();
        for(DentistAssignment dentistAssignment : dentistAssignmentsFromService) {
            dentistAssignmentsObservableList.add(dentistAssignment);
        }
        listViewDentistAssignments.setItems(dentistAssignmentsObservableList);
    }

    public void clearDentistAssignmentsTextFields() {
        textFieldDentistAssignmentIdOfDentistAssignment.clear();
        textFieldDentistAssignmentIdOfAppointment.clear();
        textFieldDentistAssignmentIdOfDentist.clear();
        textFieldDentistAssignmentConsultationCost.clear();
    }


    //actions

    @FXML
    void buttonAddDentistAssignmentHandler(ActionEvent event) {
        try {
            Integer idDentistAssigment = Integer.parseInt(textFieldDentistAssignmentIdOfDentistAssignment.getText());
            Integer idAppointment = Integer.parseInt(textFieldDentistAssignmentIdOfAppointment.getText());
            Integer idDentist = Integer.parseInt(textFieldDentistAssignmentIdOfDentist.getText());
            float consultationCost = Float.parseFloat(textFieldDentistAssignmentConsultationCost.getText());
            serviceDentistAssignments.add(idDentistAssigment,serviceAppointments.findById(idAppointment),serviceDentists.findById(idDentist),consultationCost);

            // undo_redo part
            DentistAssignment dentistAssigmentForActionAdd
                    = new DentistAssignment(idDentistAssigment,serviceAppointments.findById(idAppointment),serviceDentists.findById(idDentist),consultationCost);
            ActionAdd<Integer, DentistAssignment> actionForAddingDentistAssignment = new ActionAdd<>(dentistAssigmentForActionAdd, serviceDentistAssignments.getRepository());
            actionsStackAllEntities.addAction(actionForAddingDentistAssignment);
            // undo_redo part

            initializeOrResetDentistAssignments();
            clearDentistAssignmentsTextFields();
        }
        catch (RuntimeException e)
        {
            Alert addDentistAssignmentErrorAlert = new Alert(Alert.AlertType.ERROR);
            addDentistAssignmentErrorAlert.setContentText(e.getMessage());
            addDentistAssignmentErrorAlert.showAndWait();
        }

    }

    @FXML
    void buttonRemoveDentistAssignmentHandler(ActionEvent event) {
        try {
            DentistAssignment selectedDentistAssignment = listViewDentistAssignments.getSelectionModel().getSelectedItem();
            serviceDentistAssignments.delete(selectedDentistAssignment.getId());

            // undo_redo part
            ActionRemove<Integer, DentistAssignment> actionForRemovingDentistAssignment
                    = new ActionRemove<>(selectedDentistAssignment,serviceDentistAssignments.getRepository());
            actionsStackAllEntities.addAction(actionForRemovingDentistAssignment);
            // undo_redo part

            initializeOrResetDentistAssignments();
        } catch (RuntimeException e) {
            Alert deleteDentistAssignmentErrorAlert = new Alert(Alert.AlertType.ERROR);
            deleteDentistAssignmentErrorAlert.setContentText("Please select a valid dentist assignment!");
            deleteDentistAssignmentErrorAlert.showAndWait();
        }



    }

    @FXML
    void buttonModifyDentistAssignmentHandler(ActionEvent event) {
        try {
            Integer idAppointment = Integer.parseInt(textFieldDentistAssignmentIdOfAppointment.getText());
            Integer idDentist = Integer.parseInt(textFieldDentistAssignmentIdOfDentist.getText());
            float consultationCost = Float.parseFloat(textFieldDentistAssignmentConsultationCost.getText());//new

            DentistAssignment selectedDentistAssignment = listViewDentistAssignments.getSelectionModel().getSelectedItem();//old

            serviceDentistAssignments.
                    modify(selectedDentistAssignment.getId(),serviceAppointments.findById(idAppointment),serviceDentists.findById(idDentist),consultationCost);


            // undo_redo part
            DentistAssignment newDentistAssignmentForActionUpdate
                    = new DentistAssignment(selectedDentistAssignment.getId(), serviceAppointments.findById(idAppointment),
                    serviceDentists.findById(idDentist),consultationCost); //the id does not change
            ActionUpdate<Integer, DentistAssignment> actionForUpdatingDentistAssignment
                    = new ActionUpdate<>(serviceDentistAssignments.getRepository(),selectedDentistAssignment,newDentistAssignmentForActionUpdate);
            actionsStackAllEntities.addAction(actionForUpdatingDentistAssignment);
            // undo_redo part


            initializeOrResetDentistAssignments();
            clearDentistAssignmentsTextFields();
        } catch (RuntimeException e) {
            Alert modifyDentistAssignmentErrorAlert = new Alert(Alert.AlertType.ERROR);
            modifyDentistAssignmentErrorAlert.setContentText(e.getMessage());
            modifyDentistAssignmentErrorAlert.showAndWait();
        }

    }

    @FXML
    void buttonFindByIdDentistAssignmentHandler(ActionEvent event) {
        try {
            Integer idDentistAssigment = Integer.parseInt(textFieldDentistAssignmentIdOfDentistAssignment.getText());

            Alert findByIdDentistAssignmentInformationAlert = new Alert(Alert.AlertType.INFORMATION);
            findByIdDentistAssignmentInformationAlert.setWidth(400);
            findByIdDentistAssignmentInformationAlert.setContentText(serviceDentistAssignments.findById(idDentistAssigment).toString());
            findByIdDentistAssignmentInformationAlert.showAndWait();

            clearAppointmentsTextFields();
        }catch (RuntimeException e) {
            Alert findByIdDentistAssignmentErrorAlert = new Alert(Alert.AlertType.ERROR);
            findByIdDentistAssignmentErrorAlert.setContentText(e.getMessage());
            findByIdDentistAssignmentErrorAlert.showAndWait();
        }

    }



    // Dentists Options
    public void initializeOrResetDentists() {
        dentistsObservableList = FXCollections.observableArrayList();
        Iterable<Dentist> dentistsFromService = this.serviceDentists.getAll();
        for(Dentist dentist : dentistsFromService) {
            dentistsObservableList.add(dentist);
        }
        listViewDentists.setItems(dentistsObservableList);
    }

    public void clearDentistsTextFields() {
        textFieldDentistId.clear();
        textFieldDentistName.clear();
        textFieldDentistAge.clear();
    }


    //actions

    @FXML
    void buttonAddDentistHandler(ActionEvent event) {
        try {
            Integer idDentist = Integer.parseInt(textFieldDentistId.getText());
            String nameDentist = textFieldDentistName.getText();
            Integer ageDentist = Integer.parseInt(textFieldDentistAge.getText());
            serviceDentists.add(idDentist,nameDentist,ageDentist);

            // undo_redo part
            Dentist dentistForActionAdd = new Dentist(idDentist,nameDentist,ageDentist);
            ActionAdd<Integer, Dentist> actionForAddingDentist = new ActionAdd<>(dentistForActionAdd, serviceDentists.getRepository());
            actionsStackAllEntities.addAction(actionForAddingDentist);
            // undo_redo part

            initializeOrResetDentists();
            clearDentistsTextFields();
        }
        catch (RuntimeException e)
        {
            Alert addDentistErrorAlert = new Alert(Alert.AlertType.ERROR);
            addDentistErrorAlert.setContentText(e.getMessage());
            addDentistErrorAlert.showAndWait();
        }

    }
    @FXML
    void buttonRemoveDentistHandler(ActionEvent event) {
        try {
            Dentist selectedDentist = listViewDentists.getSelectionModel().getSelectedItem();
            serviceDentists.delete(selectedDentist.getId());

            // undo_redo part
            ActionRemove<Integer, Dentist> actionForRemovingDentist
                    = new ActionRemove<>(selectedDentist,serviceDentists.getRepository());
            actionsStackAllEntities.addAction(actionForRemovingDentist);
            // undo_redo part

            initializeOrResetDentists();
        } catch (RuntimeException e) {
            Alert deleteDentistErrorAlert = new Alert(Alert.AlertType.ERROR);
            deleteDentistErrorAlert.setContentText("Please select a valid dentist!");
            deleteDentistErrorAlert.showAndWait();
        }

    }

    @FXML
    void buttonModifyDentistHandler(ActionEvent event) {
        try {
            String nameDentist = textFieldDentistName.getText();
            Integer ageDentist = Integer.parseInt(textFieldDentistAge.getText());

            Dentist selectedDentist = listViewDentists.getSelectionModel().getSelectedItem();

            serviceDentists.modify(selectedDentist.getId(),nameDentist,ageDentist);

            // undo_redo part
            Dentist newDentistForActionUpdate
                    = new Dentist(selectedDentist.getId(),nameDentist,ageDentist); //the id does not change
            ActionUpdate<Integer, Dentist> actionForUpdatingDentist
                    = new ActionUpdate<>(serviceDentists.getRepository(),selectedDentist,newDentistForActionUpdate);
            actionsStackAllEntities.addAction(actionForUpdatingDentist);
            // undo_redo part

            initializeOrResetDentists();
            clearDentistsTextFields();
        } catch (RuntimeException e) {
            Alert modifyDentistErrorAlert = new Alert(Alert.AlertType.ERROR);
            modifyDentistErrorAlert.setContentText(e.getMessage());
            modifyDentistErrorAlert.showAndWait();
        }

    }



    @FXML
    void buttonFindByIdDentistHandler(ActionEvent event) {
        try {
            Integer idDentist = Integer.parseInt(textFieldDentistId.getText());

            Alert findByIdDentistInformationAlert = new Alert(Alert.AlertType.INFORMATION);
            findByIdDentistInformationAlert.setContentText(serviceDentists.findById(idDentist).toString());
            findByIdDentistInformationAlert.showAndWait();

            clearDentistsTextFields();
        }catch (RuntimeException e) {
            Alert findByIdDentistErrorAlert = new Alert(Alert.AlertType.ERROR);
            findByIdDentistErrorAlert.setContentText(e.getMessage());
            findByIdDentistErrorAlert.showAndWait();
        }

    }




    // REPORTS OPTIONS

    // Appointments Reports Options
    public void initializeAppointmentsFromReports() {
        appointmentsReportsObservableList = FXCollections.observableArrayList();
        listViewAppointmentsReports.setItems(appointmentsReportsObservableList);
    }


    //actions
    @FXML
    void buttonGetAppointmentsByDentistIdHandler(ActionEvent event) {

        try {
            Integer dentistIdGetAppointmentsByDentistIdReports = Integer.parseInt(textFieldDentistId.getText());

            initializeAppointmentsFromReports();

            List<Appointment> appointmentsByDentistIdFromService
                    = this.serviceReports.getAllAppointmentsByDentistId(dentistIdGetAppointmentsByDentistIdReports);
            appointmentsReportsObservableList.addAll(appointmentsByDentistIdFromService);
            listViewAppointmentsReports.setItems(appointmentsReportsObservableList);

            clearDentistsTextFields();
        }catch (RuntimeException e) {
            Alert getAppointmentsByDentistIdErrorAlert = new Alert(Alert.AlertType.ERROR);
            getAppointmentsByDentistIdErrorAlert.setContentText(e.getMessage());
            getAppointmentsByDentistIdErrorAlert.showAndWait();
        }

    }

    @FXML
    void buttonGetAppointmentsStartingAtHourAndAtCertainDateHandler(ActionEvent event) {

        String startingHourAppointmentReports = textFieldAppointmentHour.getText();
        String startingDateAppointmentReports = textFieldAppointmentDate.getText();

        initializeAppointmentsFromReports();

        List<Appointment> appointmentsStartingAtHourAndAtCertainDateFromService
                = this.serviceReports.getAppointmentsStartingAtHourAndAtCertainDate(startingHourAppointmentReports,startingDateAppointmentReports);
        appointmentsReportsObservableList.addAll(appointmentsStartingAtHourAndAtCertainDateFromService);
        listViewAppointmentsReports.setItems(appointmentsReportsObservableList);

        clearAppointmentsTextFields();
    }



    // Dentists Reports Options
    public void initializeDentistsFromReports() {
        dentistsReportsObservableList = FXCollections.observableArrayList();
        listViewDentistsReports.setItems(dentistsReportsObservableList);
    }

    //actions

    @FXML
    void buttonGetDentistIdsWithAppointmentsAtCertainDateHandler(ActionEvent event) { // alert

        try {
            String dateAppointment = textFieldAppointmentDate.getText();

            Alert getDentistIdsWithAppointmentsAtCertainDateInformationAlert = new Alert(Alert.AlertType.INFORMATION);
            getDentistIdsWithAppointmentsAtCertainDateInformationAlert.setContentText(
                    serviceReports.getDentistIdsWithAppointmentsAtCertainDate(dateAppointment).toString());
            getDentistIdsWithAppointmentsAtCertainDateInformationAlert.showAndWait();

            clearAppointmentsTextFields();
        }catch (RuntimeException e) {
            Alert getDentistIdsWithAppointmentsAtCertainDateErrorAlert = new Alert(Alert.AlertType.ERROR);
            getDentistIdsWithAppointmentsAtCertainDateErrorAlert.setContentText(e.getMessage());
            getDentistIdsWithAppointmentsAtCertainDateErrorAlert.showAndWait();
        }
    }

    @FXML
    void buttonGetDentistsHavingAppointmentAtHourSortedByNameHandler(ActionEvent event) {
        String hourOfAppointmentDentistsReports = textFieldAppointmentHour.getText();

        initializeDentistsFromReports();

        List<Dentist> dentistsHavingAppointmentAtHourSortedByNameFromService
                = this.serviceReports.getDentistsHavingAppointmentAtHourSortedByName(hourOfAppointmentDentistsReports);
        dentistsReportsObservableList.addAll(dentistsHavingAppointmentAtHourSortedByNameFromService);
        listViewDentistsReports.setItems(dentistsReportsObservableList);

        clearAppointmentsTextFields();
    }
    @FXML
    void buttonGetAllDentistsByAppointmentPatientNameSortedByAgeAscendingAndNameDescendingHandler(ActionEvent event) {
        String appointmentPatientNameDentistsReports = textFieldAppointmentPatientName.getText();

        initializeDentistsFromReports();

        List<Dentist> dentistsByAppointmentPatientNameSortedByAgeAscendingAndNameDescendingFromService
                = this.serviceReports.getAllDentistsByAppointmentPatientNameSortedByAgeAscendingAndNameDescending(appointmentPatientNameDentistsReports);
        dentistsReportsObservableList.addAll(dentistsByAppointmentPatientNameSortedByAgeAscendingAndNameDescendingFromService);
        listViewDentistsReports.setItems(dentistsReportsObservableList);

        clearAppointmentsTextFields();

    }



    //redo
    @FXML
    void buttonRedoHandler(ActionEvent event) {
        actionsStackAllEntities.redo();
        initializeOrResetAppointments();
        initializeOrResetDentistAssignments();
        initializeOrResetDentists();
    }

    //undo
    @FXML
    void buttonUndoHandler(ActionEvent event) {
        actionsStackAllEntities.undo();
        initializeOrResetAppointments();
        initializeOrResetDentistAssignments();
        initializeOrResetDentists();
    }








    public void initialize(){
        initializeOrResetAppointments();
        initializeAppointmentsFromSpecialOptions();

        initializeOrResetDentistAssignments();

        initializeOrResetDentists();


    }
}