package filter;

import domain.Appointment;

// this implements AbstractFilter superclass, specifically checks if the appointment that we want to add has  a certain NAME
public class FilterAppointmentsByDentistName implements AbstractFilter<Appointment> {

    private String doctorName; // private attribute and constructor specific for this class
    public FilterAppointmentsByDentistName(String doctorName){
        this.doctorName=doctorName;
    }

    @Override
    public boolean accept(Appointment appointment) { //checking the condition -> True/False
        return doctorName.equals(appointment.getDoctorName());
    }
}
