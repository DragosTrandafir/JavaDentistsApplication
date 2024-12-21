package gui;

import domain.Appointment;
import domain.Dentist;
import domain.DentistAssignment;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import repo.*;
import service.ServiceAppointments;
import service.ServiceDentistAssignments;
import service.ServiceDentists;
import service.ServiceReports;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class DentistsAndAppointmentsGUI extends Application {
    public ArrayList<IRepository> getAllLoadedRepositories() throws IOException, ExceptionRepository {
        IRepository<Integer, Dentist> dentistsRepository = null;
        IRepository<Integer, Appointment> appointmentsRepository = null;
        IRepository<Integer, DentistAssignment> dentistAssignmentsRepository = new DentistAssignmentsRepository();

        FileReader fileReader = null; // settings.properties file
        fileReader = new FileReader("D:\\MAP\\a5-2024-DragosTrandafir\\src\\main\\settings.properties");

        Properties properties = new Properties();
        properties.load(fileReader);

        String type = (String) properties.getProperty("type");
        System.out.println(type);

        String isAppointmentsIncluded = (String) properties.getProperty("appointments");
        System.out.println(isAppointmentsIncluded);

        String isDentistsIncluded = (String) properties.getProperty("dentists");
        System.out.println(isDentistsIncluded);


        if (type.equals("text")) {
            String appointmentsPathText = (String) properties.getProperty("appointmentsPathText");
            System.out.println(appointmentsPathText);

            String dentistsPathText = properties.getProperty("dentistsPathText");
            System.out.println(dentistsPathText);

            if (isAppointmentsIncluded.equals("yes"))
                appointmentsRepository = new AppointmentsTextFileRepository(appointmentsPathText);
            else
                appointmentsRepository = new AppointmentsRepository();

            if (isDentistsIncluded.equals("yes")) {
                dentistsRepository = new DentistsTextFileRepository(dentistsPathText);
            } else
                dentistsRepository = new DentistsRepository();
        } else if (type.equals("binary")) {

            String appointmentsPathBinary = (String) properties.getProperty("appointmentsPathBinary");
            System.out.println(appointmentsPathBinary);

            String dentistsPathBinary = properties.getProperty("dentistsPathBinary");
            System.out.println(dentistsPathBinary);

            if (isAppointmentsIncluded.equals("yes"))
                appointmentsRepository = new BinaryFileAppointmentsRepository(appointmentsPathBinary);
            else
                appointmentsRepository = new AppointmentsRepository();

            if (isDentistsIncluded.equals("yes")) {
                dentistsRepository = new BinaryFileDentistsRepository(dentistsPathBinary);
            } else
                dentistsRepository = new DentistsRepository();
        } else if (type.equals("database")) {
            String appointmentsPathDataBase = (String) properties.getProperty("appointmentsPathDataBase");
            System.out.println(appointmentsPathDataBase);

            String dentistsPathDataBase = properties.getProperty("dentistsPathDataBase");
            System.out.println(dentistsPathDataBase);

            if (isAppointmentsIncluded.equals("yes"))
                appointmentsRepository = new AppointmentsDBRepository(appointmentsPathDataBase);
            else
                appointmentsRepository = new AppointmentsRepository();
            if (isDentistsIncluded.equals("yes"))
                dentistsRepository = new DentistsDBRepository(dentistsPathDataBase);
            else
                dentistsRepository = new DentistsRepository();


            // add some objects in the assigmentsRepository

            if (isAppointmentsIncluded.equals("yes") && isDentistsIncluded.equals("yes")) {
                DentistAssignment dentistAssignment1
                        = new DentistAssignment(1, appointmentsRepository.findById(1), dentistsRepository.findById(12), 300);
                DentistAssignment dentistAssignment2
                        = new DentistAssignment(2, appointmentsRepository.findById(2), dentistsRepository.findById(12), 350);
                DentistAssignment dentistAssignment3
                        = new DentistAssignment(3, appointmentsRepository.findById(3), dentistsRepository.findById(13), 300);
                DentistAssignment dentistAssignment4
                        = new DentistAssignment(4, appointmentsRepository.findById(4), dentistsRepository.findById(12), 370);
                DentistAssignment dentistAssignment5
                        = new DentistAssignment(5, appointmentsRepository.findById(5), dentistsRepository.findById(13), 380);

                dentistAssignmentsRepository.add(dentistAssignment1.getId(), dentistAssignment1);
                dentistAssignmentsRepository.add(dentistAssignment2.getId(), dentistAssignment2);
                dentistAssignmentsRepository.add(dentistAssignment3.getId(), dentistAssignment3);
                dentistAssignmentsRepository.add(dentistAssignment4.getId(), dentistAssignment4);
                dentistAssignmentsRepository.add(dentistAssignment5.getId(), dentistAssignment5);
            }


        } else if (type.equals("json")) {
            String appointmentsPathJson = (String) properties.getProperty("appointmentsPathJson");
            System.out.println(appointmentsPathJson);

            String dentistsPathJson = properties.getProperty("dentistsPathJson");
            System.out.println(dentistsPathJson);

            if (isAppointmentsIncluded.equals("yes"))
                appointmentsRepository = new AppointmentsJsonRepository(appointmentsPathJson);
            else
                appointmentsRepository = new AppointmentsRepository();

            if (isDentistsIncluded.equals("yes")) {
                dentistsRepository = new DentistsJsonRepository(dentistsPathJson);
            } else
                dentistsRepository = new DentistsRepository();
        } else if (type.equals("xml")) {
            String appointmentsPathXml = (String) properties.getProperty("appointmentsPathXml");
            System.out.println(appointmentsPathXml);

            String dentistsPathXml = properties.getProperty("dentistsPathXml");
            System.out.println(dentistsPathXml);

            if (isAppointmentsIncluded.equals("yes"))
                appointmentsRepository = new AppointmentsXmlRepository(appointmentsPathXml);
            else
                appointmentsRepository = new AppointmentsRepository();

            if (isDentistsIncluded.equals("yes")) {
                dentistsRepository = new DentistsXmlRepository(dentistsPathXml);
            } else
                dentistsRepository = new DentistsRepository();
            }
        else {
                appointmentsRepository = new AppointmentsRepository();
                dentistsRepository = new DentistsRepository();
                System.out.println("empty");
            }

        ArrayList<IRepository> loadedRepositories = new ArrayList<>();
        loadedRepositories.add(dentistsRepository);
        loadedRepositories.add(appointmentsRepository);
        loadedRepositories.add(dentistAssignmentsRepository);
        return loadedRepositories;
        }


    @Override
    public void start(Stage stage) throws Exception {

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/DentistsAndAppointmentsGUI.fxml"));

            ArrayList<IRepository> allLoadedRepositories = getAllLoadedRepositories();

            ServiceDentists serviceDentists = new ServiceDentists(allLoadedRepositories.get(0));
            ServiceAppointments serviceAppointments = new ServiceAppointments(allLoadedRepositories.get(1));
            ServiceDentistAssignments serviceDentistAssignments = new ServiceDentistAssignments(allLoadedRepositories.get(2));
            ServiceReports serviceReports = new ServiceReports(allLoadedRepositories.get(0),allLoadedRepositories.get(1),allLoadedRepositories.get(2));

            DentistsAndAppointmentsGUIController controller = new DentistsAndAppointmentsGUIController(serviceDentists,serviceAppointments, serviceDentistAssignments,serviceReports);

            loader.setController(controller);
            Scene scene = new Scene(loader.load(), 950, 800);
            stage.setTitle("GUI for Doctors App");
            stage.setScene(scene);
            stage.show();
        } catch (ExceptionRepository | IOException | RuntimeException e) {
            Alert wrongApplicationInitializationErrorAlert = new Alert(Alert.AlertType.ERROR);
            wrongApplicationInitializationErrorAlert.setContentText(e.getMessage());
            wrongApplicationInitializationErrorAlert.showAndWait();
            stage.show();
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}
