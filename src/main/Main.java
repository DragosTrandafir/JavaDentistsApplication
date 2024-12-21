package main;

import domain.Appointment;
import domain.Dentist;
import domain.DentistAssignment;
import filter.FilterAppointmentsByHour;
import filter.FilterDentistsByAge;
import repo.*;
import service.ServiceAppointments;
import service.ServiceDentistAssignments;
import service.ServiceDentists;
import service.ServiceReports;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        IRepository<Integer, Dentist> dentistsRepository = null;
        IRepository<Integer, Appointment> appointmentsRepository = null;
        IRepository<Integer, DentistAssignment> dentistAssignmentsRepository = new DentistAssignmentsRepository();

        FileReader fileReader = null; // settings.properties file
        try {
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
            } else {
                appointmentsRepository = new AppointmentsRepository();
                dentistsRepository = new DentistsRepository();
                System.out.println("empty");
            }

            ServiceDentists serviceDentists = new ServiceDentists(dentistsRepository);
            ServiceAppointments serviceAppointments = new ServiceAppointments(appointmentsRepository);
            ServiceDentistAssignments serviceDentistAssignments = new ServiceDentistAssignments(dentistAssignmentsRepository);
            ServiceReports serviceReports = new ServiceReports(dentistsRepository, appointmentsRepository, dentistAssignmentsRepository);


            // filtered appointments
            //FilteredRepository<Integer,Appointment> filteredAppointmentsRepository= new FilteredRepository<Integer,Appointment>(new FilterByDoctorName("Doctor0"));// decide here what filter with value you want to impose
            FilteredRepository<Integer, Appointment> filteredAppointmentsRepository
                    = new FilteredRepository<Integer, Appointment>(new FilterAppointmentsByHour("08:00", "16:00"));// decide here what filter with value you want to impose

            ServiceAppointments serviceFilteredAppointments = new ServiceAppointments(filteredAppointmentsRepository);

            serviceFilteredAppointments.add(124, "Dragos Trandafir", "Andrei Ionescu", "07:00", "12.12.2004");
            serviceFilteredAppointments.add(340, "Patient0", "Doctor0", "10:00", "10.10.2010");
            serviceFilteredAppointments.add(342, "Patient1", "Doctor1", "10:00", "13.10.2010");
            serviceFilteredAppointments.add(343, "Patient2", "Doctor2", "11:00", "10.1.2010");
            serviceFilteredAppointments.add(344, "Patient3", "Doctor0", "17:00", "8.7.2010");


            // filtered dentists
            FilteredRepository<Integer, Dentist> filteredDentistsRepository = new FilteredRepository<Integer, Dentist>(new FilterDentistsByAge(40));
            //FilteredRepository<Integer,Dentist> filteredDentistsRepository = new FilteredRepository<Integer,Dentist>(new FilterDentistsByDentistName("Doctor0"));

            ServiceDentists serviceFilteredDentists = new ServiceDentists(filteredDentistsRepository);

            serviceFilteredDentists.add(6, "Dragos", 34);
            serviceFilteredDentists.add(7, "Doctor0", 36);
            serviceFilteredDentists.add(3, "Doctor1", 40);
            serviceFilteredDentists.add(4, "Andrei Ionescu", 59);
            serviceFilteredDentists.add(5, "Dragos2", 44);

            main.UserInterface userInterface = new UserInterface(serviceAppointments, serviceDentists, serviceReports
                    , serviceDentistAssignments, serviceFilteredAppointments, serviceFilteredDentists);
            userInterface.run();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ExceptionRepository | IOException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
