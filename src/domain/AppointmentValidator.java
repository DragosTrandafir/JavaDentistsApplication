package domain;

public class AppointmentValidator implements Validator<Appointment> {
    @Override
    public void validate(Appointment appointment) {
        if (appointment.getId() <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer.");
        }
        if (appointment.getPatientName() == null || appointment.getPatientName().trim().isEmpty()) {
            throw new IllegalArgumentException("Patient name cannot be null or empty.");
        }
        if (appointment.getDoctorName()== null || appointment.getDoctorName().trim().isEmpty()) {
            throw new IllegalArgumentException("Doctor name cannot be null or empty.");
        }
    }
}
