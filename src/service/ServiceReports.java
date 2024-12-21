package service;

import domain.Appointment;
import domain.Dentist;
import domain.DentistAssignment;
import repo.DentistAssignmentsRepository;
import repo.IRepository;

import java.util.ArrayList;
import java.util.List;

public class ServiceReports {

    private IRepository<Integer, Dentist> dentistsRepository;
    private IRepository<Integer, Appointment> appointmentsRepository;
    private IRepository<Integer, DentistAssignment> dentistAssignmentsRepository;

    public ServiceReports(IRepository<Integer, Dentist> dentistsRepository,
                          IRepository<Integer, Appointment> appointmentsRepository,
                          IRepository<Integer, DentistAssignment> dentistAssignmentsRepository) {

        this.dentistsRepository = dentistsRepository;
        this.appointmentsRepository = appointmentsRepository;
        this.dentistAssignmentsRepository = dentistAssignmentsRepository;
    }

    public ServiceReports(IRepository<Integer, Dentist> dentistsRepository,
                          IRepository<Integer, Appointment> appointmentsRepository,
                          DentistAssignmentsRepository dentistAssignmentsRepository) {
    }

    public Iterable<Dentist> getDentists()  {
        return dentistsRepository.getAll();
    }
    public Iterable<Appointment> getAppointments()  {
        return appointmentsRepository.getAll();
    }
    public Iterable<DentistAssignment> getAssignments()  {
        return dentistAssignmentsRepository.getAll();
    }

    public List<Appointment> getAllAppointmentsByDentistId(Integer dentistId) {
        List<DentistAssignment> assignments = new ArrayList<>();
        for(DentistAssignment dentistAssignment: dentistAssignmentsRepository.getAll())
            assignments.add(dentistAssignment);

        List<Appointment> appointmentsHavingDentistId = assignments.stream()
                .filter(assignment -> assignment.getDentist().getId().equals(dentistId))
                .map(assignment -> assignment.getAppointment())
                .toList();
        return appointmentsHavingDentistId;
    }

    public List<Dentist>
    getAllDentistsByAppointmentPatientNameSortedByAgeAscendingAndNameDescending(String patientName) {

        List<DentistAssignment> assignments = new ArrayList<>();
        for(DentistAssignment dentistAssignment: dentistAssignmentsRepository.getAll())
            assignments.add(dentistAssignment);

        List<Dentist> dentistsByAppointmentPatientNameSortedByAgeAscendingAndNameDescending
                = assignments.stream()
                .filter(assignment -> assignment.getAppointment().getPatientName().equals(patientName))
                .map(assignment -> assignment.getDentist())
                .sorted((dentist1, dentist2)-> dentist2.getName().compareToIgnoreCase(dentist1.getName()))
                .sorted((dentist1, dentist2)-> Integer.compare(dentist1.getAge(),dentist2.getAge()))
                .toList();
        return dentistsByAppointmentPatientNameSortedByAgeAscendingAndNameDescending;
    }
    public List<Dentist> getDentistsHavingAppointmentAtHourSortedByName(String hour) {
        List<DentistAssignment> assignments = new ArrayList<>();
        for(DentistAssignment dentistAssignment: dentistAssignmentsRepository.getAll())
            assignments.add(dentistAssignment);

        List<Dentist> dentistsHavingAppointmentAtHourSortedByName
                = assignments.stream()
                .filter(assignment -> assignment.getAppointment().getHour().equals(hour))
                .map(assignment-> assignment.getDentist())
                .sorted((dentist1,dentist2)-> dentist1.getName().compareToIgnoreCase(dentist2.getName()))
                .toList();
        return dentistsHavingAppointmentAtHourSortedByName;

    }

    public List<Integer> getDentistIdsWithAppointmentsAtCertainDate(String date) {
        List<DentistAssignment> assignments = new ArrayList<>();
        for(DentistAssignment dentistAssignment: dentistAssignmentsRepository.getAll())
            assignments.add(dentistAssignment);

        List<Integer> dentistIdsWithAppointmentsAtCertainDate = assignments.stream()
                .filter(assignment -> assignment.getAppointment().getDate().equals(date))
                .map(assignment -> assignment.getDentist().getId())
                .toList();
        return dentistIdsWithAppointmentsAtCertainDate;
    }

    public List<Appointment> getAppointmentsStartingAtHourAndAtCertainDate(String hour, String certainDate) {
        List<Appointment> appointments = new ArrayList<>();
        for(Appointment appointment: appointmentsRepository.getAll())
            appointments.add(appointment);

        List<Appointment> appointmentsStartingAtHourAndAtCertainDate =  appointments.stream()
                .filter(appointment -> appointment.getDate().equals(certainDate))
                .filter(appointment -> appointment.getHour().equals(hour))
                .toList();
        return appointmentsStartingAtHourAndAtCertainDate;
    }

}
