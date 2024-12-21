package main;

import domain.Appointment;
import domain.Dentist;
import service.ServiceAppointments;
import service.ServiceDentistAssignments;
import service.ServiceDentists;
import service.ServiceReports;
import test.ServiceTests;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private ServiceAppointments serviceAppointments;
    private ServiceDentists serviceDentists;
    private ServiceReports serviceReports;
    private ServiceDentistAssignments serviceDentistAssignments;
    private ServiceAppointments serviceFilteredAppointments;
    private ServiceDentists serviceFilteredDentists;

    public UserInterface(ServiceAppointments serviceAppointments, ServiceDentists serviceDentists, ServiceReports serviceReports
            , ServiceDentistAssignments serviceDentistAssignments, ServiceAppointments serviceFilteredAppointments, ServiceDentists serviceFilteredDentists) {
        this.serviceAppointments = serviceAppointments;
        this.serviceDentists = serviceDentists;
        this.serviceReports = serviceReports;
        this.serviceDentistAssignments = serviceDentistAssignments;
        this.serviceFilteredAppointments = serviceFilteredAppointments;
        this.serviceFilteredDentists = serviceFilteredDentists;

    }
    // constants
    static final int DISPLAY_ALL_OPTION_APPOINTMENTS = 1;
    static final int ADD_APPOINTMENT_OPTION = 2;
    static final int MODIFY_APPOINTMENT_AT_ID_OPTION = 3;
    static final int REMOVE_APPOINTMENT__AT_ID_OPTION = 4;
    static final int FIND_APPOINTMENT_BY_ID = 5;
    static final int GET_IDS_OF_APPOINTMENTS_OF_DOCTOR_OPTION = 6;
    static final int GET_DOCTOR_NAME_MOST_APPOINTMENTS_OPTION = 7;

    static final int DISPLAY_ALL_OPTION_FILTER_APPOINTMENTS = 8;
    static final int ADD_APPOINTMENT_OPTION_FILTER = 9;
    static final int MODIFY_APPOINTMENT_AT_ID_OPTION_FILTER = 10;
    static final int REMOVE_APPOINTMENT_AT_ID_OPTION_FILTER = 11;


    static final int DISPLAY_ALL_OPTION_DENTISTS = 12;
    static final int ADD_DENTIST_OPTION = 13;
    static final int MODIFY_DENTIST_AT_ID_OPTION = 14;
    static final int REMOVE_DENTIST_AT_ID_OPTION = 15;
    static final int FIND_DENTIST_BY_ID = 16;

    static final int DISPLAY_ALL_OPTION_FILTER_DENTISTS = 17;
    static final int ADD_DENTIST_OPTION_FILTER = 18;
    static final int MODIFY_DENTIST_AT_ID_OPTION_FILTER = 19;
    static final int REMOVE_DENTIST_AT_ID_OPTION_FILTER = 20;

    static final int DISPLAY_ALL_OPTION_DENTISTS_ASSIGNMENTS = 21;
    static final int ADD_DENTIST_ASSIGNMENT_OPTION = 22;
    static final int MODIFY_DENTIST_ASSIGNMENT_AT_ID_OPTION = 23;
    static final int REMOVE_DENTIST_ASSIGNMENT_AT_ID_OPTION = 24;
    static final int FIND_DENTIST_ASSIGNMENT_BY_ID = 25;

    static final int GET_APPOINTMENTS_BY_DENTIST_ID_OPTION = 26;
    static final int GET_ALL_DENTISTS_BY_APPOINTMENT_PATIENT_NAME_SORTED_BY_AGE_ASCENDING_AND_NAME_DESCENDING_OPTION = 27;
    static final int GET_DENTISTS_HAVING_APPOINTMENT_AT_HOUR_SORTED_BY_NAME_OPTION = 28;
    static final int GET_DENTIST_IDS_WITH_APPOINTMENTS_AT_CERTAIN_DATE_OPTION = 29;
    static final int GET_APPOINTMENTS_STARTING_AT_HOUR_AND_AT_CERTAIN_DATE_OPTION = 30;

//    static final int DISPLAY_ALL_OPTION_APPOINTMENTS_JSON = 31;
//    static final int ADD_APPOINTMENT_OPTION_JSON = 32;
//    static final int MODIFY_APPOINTMENT_AT_ID_OPTION_JSON = 32;
//    static final int REMOVE_APPOINTMENT__AT_ID_OPTION_JSON = 34;
//
//    static final int DISPLAY_ALL_OPTION_DENTISTS_JSON = 35;
//    static final int ADD_DENTIST_OPTION_JSON = 36;
//    static final int MODIFY_DENTIST_AT_ID_OPTION_JSON = 37;
//    static final int REMOVE_DENTIST_AT_ID_OPTION_JSON = 38;

    static final int EXIT_OPTION = 0;


    //methods
    public static String menuAppointments() {
        return "\nMENU APPOINTMENTS\n" +
                DISPLAY_ALL_OPTION_APPOINTMENTS + ".Print appointments\n" +
                ADD_APPOINTMENT_OPTION + ".Add appointment\n" + MODIFY_APPOINTMENT_AT_ID_OPTION + ".Modify at id " +
                "\n" + REMOVE_APPOINTMENT__AT_ID_OPTION + ".Remove appointment\n" +
                FIND_APPOINTMENT_BY_ID + ".Find appointment by id\n" +
                GET_IDS_OF_APPOINTMENTS_OF_DOCTOR_OPTION + ".Get the ids of the appointments of a certain doctor" +
                "\n" + GET_DOCTOR_NAME_MOST_APPOINTMENTS_OPTION + ".Get the name of the doctor with the most appointments" +
                "\n" + "\nMENU FILTERED APPOINTMENTS\n" + DISPLAY_ALL_OPTION_FILTER_APPOINTMENTS
                + ".Print appointments\n" + ADD_APPOINTMENT_OPTION_FILTER + ".Add appointment\n"
                + MODIFY_APPOINTMENT_AT_ID_OPTION_FILTER + ".Modify at id"
                + "\n" + REMOVE_APPOINTMENT_AT_ID_OPTION_FILTER + ".Remove appointment\n" + "\n";
    }

    public static String menuDentists() {
        return "MENU DENTISTS\n" + DISPLAY_ALL_OPTION_DENTISTS + ".Print dentists\n" + ADD_DENTIST_OPTION
                + ".Add dentist\n" + MODIFY_DENTIST_AT_ID_OPTION + ".Modify at id " +
                "\n" + REMOVE_DENTIST_AT_ID_OPTION + ".Remove dentist\n" +
                FIND_DENTIST_BY_ID + ".Find dentist by id\n" +
                "\n" + "MENU FILTERED DENTISTS\n" + DISPLAY_ALL_OPTION_FILTER_DENTISTS + ".Print dentists\n"
                + ADD_DENTIST_OPTION_FILTER + ".Add dentist\n" + MODIFY_DENTIST_AT_ID_OPTION_FILTER + ".Modify at id"
                + "\n" + REMOVE_DENTIST_AT_ID_OPTION_FILTER + ".Remove dentist\n"
                + "\n";
    }

    public static String menuAssignmentsAndReports() {
        return "MENU ASSIGNMENTS\n"
                + DISPLAY_ALL_OPTION_DENTISTS_ASSIGNMENTS + ".Print dentist assignments\n"
                + ADD_DENTIST_ASSIGNMENT_OPTION + ".Add dentist assignment\n"
                + MODIFY_DENTIST_ASSIGNMENT_AT_ID_OPTION + ".Modify assignment at id " +
                "\n" + REMOVE_DENTIST_ASSIGNMENT_AT_ID_OPTION + ".Remove dentist assignment\n" +
                FIND_DENTIST_ASSIGNMENT_BY_ID + ".Find dentist assignment by id\n\n" +
                "MENU REPORTS\n"
                + GET_APPOINTMENTS_BY_DENTIST_ID_OPTION + ".Get appointments by dentist id\n"
                + GET_ALL_DENTISTS_BY_APPOINTMENT_PATIENT_NAME_SORTED_BY_AGE_ASCENDING_AND_NAME_DESCENDING_OPTION
                + ".Get all dentists by appointment patient name sorted by age ascending and name descending\n"
                + GET_DENTISTS_HAVING_APPOINTMENT_AT_HOUR_SORTED_BY_NAME_OPTION
                + ".Get dentists having appointment at hour sorted by name\n"
                + GET_DENTIST_IDS_WITH_APPOINTMENTS_AT_CERTAIN_DATE_OPTION
                + ".Get dentists ids having appointment at certain date\n"
                + GET_APPOINTMENTS_STARTING_AT_HOUR_AND_AT_CERTAIN_DATE_OPTION
                + ".Get appointments starting at hour and at certain date\n"
                + EXIT_OPTION + ".Exit all\n\n" +
                "\n";
    }


    public static int readInteger() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt())
            return scanner.nextInt();
        return 0;
    }

    public static String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static float readFloat() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextFloat())
            return scanner.nextFloat();
        return 0;
    }
    public static void testFakeRepository_correctTests_NoError(){
        ServiceTests testFakeRepoCustom = new ServiceTests();
        testFakeRepoCustom.testGetAll_correctSize_NoError();
        System.out.println("Custom fakeRepository tests executed!\n");
        //testFakeRepoCustom.testGetAll_EmptyCollection_ThrowsException();
    }


    void run() {

        testFakeRepository_correctTests_NoError();

            boolean exit = false;
            while (!exit) {
                System.out.println(menuAppointments());
                System.out.println(menuDentists());
                System.out.println(menuAssignmentsAndReports());
                System.out.print("\nOption chosen: ");
                int option = readInteger();

                switch (option) {
                    //normal options APPOINTMENTS
                    case DISPLAY_ALL_OPTION_APPOINTMENTS:
                        System.out.println(serviceAppointments.toStringRepo());
                        break;
                    case ADD_APPOINTMENT_OPTION:
                        System.out.println("Id:");
                        int id_add_appointment = readInteger();

                        System.out.println("Patient name:");
                        String patientName_add_appointment = readString();

                        System.out.println("Doctor name:");
                        String doctorName_add_appointment = readString();

                        System.out.println("Hour:");
                        String hour_add_appointment = readString();

                        System.out.println("Date:");
                        String date_add_appointment = readString();


                        try {
                            serviceAppointments.add(id_add_appointment, patientName_add_appointment, doctorName_add_appointment
                                    , hour_add_appointment, date_add_appointment);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case MODIFY_APPOINTMENT_AT_ID_OPTION:
                        System.out.println("Modify at this id:");
                        int idWhereToModify = readInteger();


                        System.out.println("Patient name:");
                        String patientName_modify_appointment = readString();

                        System.out.println("Doctor name:");
                        String doctorName_modify_appointment = readString();

                        System.out.println("Hour:");
                        String hour_modify_appointment = readString();

                        System.out.println("Date:");
                        String date_modify_appointment = readString();

                        try {
                            serviceAppointments.modify(idWhereToModify, patientName_modify_appointment, doctorName_modify_appointment,
                                    hour_modify_appointment, date_modify_appointment);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case REMOVE_APPOINTMENT__AT_ID_OPTION:
                        System.out.println("Remove at this id:");
                        int idWhereToRemove = readInteger();

                        try {
                            serviceAppointments.delete(idWhereToRemove);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case FIND_APPOINTMENT_BY_ID:
                        System.out.println("Id:");
                        int id_find_appointment = readInteger();

                        try {
                            System.out.println(serviceAppointments.findById(id_find_appointment));
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case GET_IDS_OF_APPOINTMENTS_OF_DOCTOR_OPTION:
                        System.out.println("Doctor name:");
                        String doctorName_get_ids_of_appointments_of_doctor = readString();

                        System.out.println(serviceAppointments.getIdsOfAppointmentsOfDoctor(doctorName_get_ids_of_appointments_of_doctor));
                        break;
                    case GET_DOCTOR_NAME_MOST_APPOINTMENTS_OPTION:
                        System.out.println(serviceAppointments.getNameOfDoctorWithMostAppointments());
                        break;
                    case EXIT_OPTION:
                        exit = true;
                        break;

                    // Filter options APPOINTMENTS
                    case DISPLAY_ALL_OPTION_FILTER_APPOINTMENTS:
                        System.out.println(serviceFilteredAppointments.toStringRepo());
                        break;
                    case ADD_APPOINTMENT_OPTION_FILTER:
                        System.out.println("Id:");
                        int id_add_appointment_filtered = readInteger();

                        System.out.println("Patient name:");
                        String patientName_add_appointment_filtered = readString();

                        System.out.println("Doctor name:");
                        String doctorName_add_appointment_filtered = readString();

                        System.out.println("Hour:");
                        String hour_add_appointment_filtered = readString();

                        System.out.println("Date:");
                        String date_add_appointment_filtered = readString();

                        try {
                            serviceFilteredAppointments.add(id_add_appointment_filtered, patientName_add_appointment_filtered, doctorName_add_appointment_filtered
                                    , hour_add_appointment_filtered, date_add_appointment_filtered);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    case MODIFY_APPOINTMENT_AT_ID_OPTION_FILTER:
                        System.out.println("Modify at this id:");
                        int idWhereToModify_filtered = readInteger();


                        System.out.println("Patient name:");
                        String patientName_modify_appointment_filtered = readString();

                        System.out.println("Doctor name:");
                        String doctorName_modify_appointment_filtered = readString();

                        System.out.println("Hour:");
                        String hour_modify_appointment_filtered = readString();

                        System.out.println("Date:");
                        String date_modify_appointment_filtered = readString();

                        try {
                            serviceFilteredAppointments.modify(idWhereToModify_filtered, patientName_modify_appointment_filtered,
                                    doctorName_modify_appointment_filtered, hour_modify_appointment_filtered, date_modify_appointment_filtered);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }


                        break;
                    case REMOVE_APPOINTMENT_AT_ID_OPTION_FILTER:
                        System.out.println("Remove at this id:");
                        int idWhereToRemove_filtered = readInteger();

                        try {
                            serviceFilteredAppointments.delete(idWhereToRemove_filtered);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;




                    //normal options DENTISTS
                    case DISPLAY_ALL_OPTION_DENTISTS:
                        System.out.println(serviceDentists.toStringRepo());
                        break;
                    case ADD_DENTIST_OPTION:
                        System.out.println("Id:");
                        int id_add_dentist = readInteger();

                        System.out.println("Name:");
                        String name_add_dentists = readString();

                        System.out.println("Age:");
                        int age_add_dentist = readInteger();


                        try {
                            serviceDentists.add(id_add_dentist, name_add_dentists, age_add_dentist);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case MODIFY_DENTIST_AT_ID_OPTION:
                        System.out.println("Modify at this id:");
                        int idWhereToModify_dentists = readInteger();

                        System.out.println("Name:");
                        String name_modify_dentists = readString();

                        System.out.println("Age:");
                        int age_modify_dentist = readInteger();


                        try {
                            serviceDentists.modify(idWhereToModify_dentists, name_modify_dentists, age_modify_dentist);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case REMOVE_DENTIST_AT_ID_OPTION:
                        System.out.println("Remove at this id:");
                        int idWhereToRemove_dentists = readInteger();

                        try {
                            serviceDentists.delete(idWhereToRemove_dentists);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case FIND_DENTIST_BY_ID:
                        System.out.println("Id:");
                        int id_find_dentist = readInteger();

                        try {
                            System.out.println(serviceDentists.findById(id_find_dentist));
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    // Filter options DENTISTS
                    case DISPLAY_ALL_OPTION_FILTER_DENTISTS:
                        System.out.println(serviceFilteredDentists.toStringRepo());
                        break;
                    case ADD_DENTIST_OPTION_FILTER:
                        System.out.println("Id:");
                        int id_add_dentist_filtered = readInteger();

                        System.out.println("Name:");
                        String name_add_dentists_filtered = readString();

                        System.out.println("Age:");
                        int age_add_dentist_filtered = readInteger();

                        try {
                            serviceFilteredDentists.add(id_add_dentist_filtered, name_add_dentists_filtered, age_add_dentist_filtered);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case MODIFY_DENTIST_AT_ID_OPTION_FILTER:
                        System.out.println("Modify at this id:");
                        int idWhereToModify_dentist_filtered = readInteger();

                        System.out.println("Name:");
                        String name_modify_dentists_filtered = readString();

                        System.out.println("Age:");
                        int age_modify_dentist_filtered = readInteger();

                        try {
                            serviceFilteredDentists.modify(idWhereToModify_dentist_filtered, name_modify_dentists_filtered, age_modify_dentist_filtered);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case REMOVE_DENTIST_AT_ID_OPTION_FILTER:
                        System.out.println("Remove at this id:");
                        int idWhereToRemove_dentist_filtered = readInteger();

                        try {
                            serviceFilteredDentists.delete(idWhereToRemove_dentist_filtered);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;


                    // ASSIGNMENTS OPERATIONS
                    case DISPLAY_ALL_OPTION_DENTISTS_ASSIGNMENTS:
                        System.out.println(serviceDentistAssignments.toStringRepo());
                        break;
                    case ADD_DENTIST_ASSIGNMENT_OPTION:
                        // int id, Appointment appointment
                        System.out.println("Id assignment:");
                        int id_assignment_add_assignment = readInteger();

                        System.out.println("Id appointment:");
                        int id_appointment_add_assignment = readInteger();


                        //Dentist dentist,float consultationCost_add_assignment
                        System.out.println("Id dentist:");
                        int id_dentist_add_assignment = readInteger();


                        System.out.println("Consultation cost:");
                        float consultationCost_add_assignment = readFloat();

                        try {
                            Appointment appointment_add_assignment = serviceAppointments.findById(id_appointment_add_assignment);
                            Dentist dentist_add_assignment = serviceDentists.findById(id_dentist_add_assignment);
                            serviceDentistAssignments.add(id_assignment_add_assignment, appointment_add_assignment, dentist_add_assignment, consultationCost_add_assignment);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case MODIFY_DENTIST_ASSIGNMENT_AT_ID_OPTION:
                        // int id, Appointment appointment
                        System.out.println("Id assignment:");
                        int id_assignment_modify_assignment = readInteger();

                        System.out.println("Id appointment:");
                        int id_appointment_modify_assignment = readInteger();


                        //Dentist dentist,float consultationCost_add_assignment
                        System.out.println("Id dentist:");
                        int id_dentist_modify_assignment = readInteger();


                        System.out.println("Consultation cost:");
                        float consultationCost_modify_assignment = readFloat();


                        try {
                            Appointment appointment_modify_assignment = serviceAppointments.findById(id_appointment_modify_assignment);
                            Dentist dentist_modify_assignment = serviceDentists.findById(id_dentist_modify_assignment);

                            serviceDentistAssignments.modify(id_assignment_modify_assignment, appointment_modify_assignment, dentist_modify_assignment
                                    , consultationCost_modify_assignment);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case REMOVE_DENTIST_ASSIGNMENT_AT_ID_OPTION:
                        System.out.println("Remove assigment at this id:");
                        int idWhereToRemove_assigment = readInteger();

                        try {
                            serviceDentistAssignments.delete(idWhereToRemove_assigment);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case FIND_DENTIST_ASSIGNMENT_BY_ID:
                        System.out.println("Id:");
                        int id_find_dentist_assignment = readInteger();

                        try {
                            System.out.println(serviceDentistAssignments.findById(id_find_dentist_assignment));
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    // REPORTS operations
                    case GET_APPOINTMENTS_BY_DENTIST_ID_OPTION:
                        System.out.println("Dentist id:");
                        int id_for_which_get_appointments = readInteger();

                        List<Appointment> appointmentsHavingDentistId = serviceReports.getAllAppointmentsByDentistId(id_for_which_get_appointments);
                        for (Appointment appointment : appointmentsHavingDentistId)
                            System.out.println(appointment.toString());
                        break;
                    case GET_ALL_DENTISTS_BY_APPOINTMENT_PATIENT_NAME_SORTED_BY_AGE_ASCENDING_AND_NAME_DESCENDING_OPTION:
                        System.out.println("Appointment PATIENT NAME:");
                        String appointment_patient_name_get_all_dentists_by_appointment_patient_name_sorted_by_age_ascending_and_name_descending = readString();

                        List<Dentist> dentistsByAppointmentPatientNameSortedByAgeAscendingAndNameDescending
                                = serviceReports.getAllDentistsByAppointmentPatientNameSortedByAgeAscendingAndNameDescending
                                (appointment_patient_name_get_all_dentists_by_appointment_patient_name_sorted_by_age_ascending_and_name_descending);
                        for (Dentist dentist : dentistsByAppointmentPatientNameSortedByAgeAscendingAndNameDescending)
                            System.out.println(dentist.toString());
                        break;
                    case GET_DENTISTS_HAVING_APPOINTMENT_AT_HOUR_SORTED_BY_NAME_OPTION:
                        System.out.println("Appointment hour:");
                        String hour_get_dentists_having_appointment_at_hour_sorted_by_name = readString();

                        List<Dentist> dentistsHavingAppointmentAtHourSortedByName
                                = serviceReports.getDentistsHavingAppointmentAtHourSortedByName(hour_get_dentists_having_appointment_at_hour_sorted_by_name);
                        for (Dentist dentist : dentistsHavingAppointmentAtHourSortedByName)
                            System.out.println(dentist.toString());
                        break;
                    case GET_DENTIST_IDS_WITH_APPOINTMENTS_AT_CERTAIN_DATE_OPTION:
                        System.out.println("Appointment date:");
                        String date_get_dentists_with_appointments_at_certain_date = readString();

                        List<Integer> dentistIdsWithAppointmentsAtCertainDate
                                = serviceReports.getDentistIdsWithAppointmentsAtCertainDate(date_get_dentists_with_appointments_at_certain_date);
                        for (Integer dentistId : dentistIdsWithAppointmentsAtCertainDate)
                            System.out.println(dentistId.toString());
                        break;
                    case GET_APPOINTMENTS_STARTING_AT_HOUR_AND_AT_CERTAIN_DATE_OPTION:
                        System.out.println("Appointment date:");
                        String date_get_appointments_starting_at_hour_and_at_certain_date = readString();

                        System.out.println("Appointment starting at hour:");
                        String hour_get_appointments_starting_at_hour_and_at_certain_date = readString();

                        List<Appointment> appointmentsStartingAtHourAndAtCertainDate
                                = serviceReports.getAppointmentsStartingAtHourAndAtCertainDate(hour_get_appointments_starting_at_hour_and_at_certain_date
                                , date_get_appointments_starting_at_hour_and_at_certain_date);
                        for (Appointment appointment : appointmentsStartingAtHourAndAtCertainDate)
                            System.out.println(appointment.toString());
                        break;


                }
            }
    }
}
