package service;

import domain.Appointment;
import domain.AppointmentValidator;
import repo.ExceptionRepository;
import repo.IRepository;

import java.util.ArrayList;

public class ServiceAppointments {
    private IRepository<Integer, Appointment> appointments;

    public ServiceAppointments(IRepository<Integer, Appointment> appointments) {
        this.appointments = appointments;
    }

    public void add(int id,  String patientName,  String doctorName,  String hour, String date){

        try {
            AppointmentValidator validatorAppointments = new AppointmentValidator();

            Appointment appointment = new Appointment(id, patientName, doctorName, hour, date);
            validatorAppointments.validate(appointment);

            appointments.add(id, appointment);
        }catch(IllegalArgumentException | ExceptionRepository e){
            throw new RuntimeException(e);
        }
    }
    public void delete(int id){
        try {
            appointments.delete(id);
        }catch(ExceptionRepository e){
        throw new RuntimeException(e);
    }
    }
    public void modify(int id_whereToModify, String patientName,  String doctorName,  String hour, String date){
        try{
            AppointmentValidator validatorAppointments = new AppointmentValidator();

            Appointment appointment = new Appointment(id_whereToModify, patientName, doctorName, hour, date);
            validatorAppointments.validate(appointment);

            appointments.modify(id_whereToModify, appointment);
        }catch(IllegalArgumentException | ExceptionRepository e){
            throw new RuntimeException(e);
        }

    }
    public Appointment findById(int id){
        try {
            return appointments.findById(id);
        }catch(ExceptionRepository e){
            throw new RuntimeException(e);

        }

    }
    public Iterable<Appointment> getAll(){

            return appointments.getAll();

    }
    public ArrayList<Integer> getIdsOfAppointmentsOfDoctor(String doctorName) {

        return appointments.getIdsOfAppointmentsOfDoctor(doctorName);

    }
    public String getNameOfDoctorWithMostAppointments() {

        return appointments.getNameOfDoctorWithMostAppointments();

    }
    public IRepository<Integer, Appointment> getRepository() {
        return this.appointments;
    }
    public String toStringRepo(){
        return appointments.toStringRepo();
    }
}
