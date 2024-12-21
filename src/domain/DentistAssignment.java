package domain;

public class DentistAssignment implements Identifiable<Integer>{

    // Private attributes
    private int id;
    private Appointment appointment;
    private Dentist dentist;
    private float consultationCost; //highly depends on the dentist

    public DentistAssignment(int id, Appointment appointment, Dentist dentist, float consultationCost) {
        this.id = id;
        this.appointment = appointment;
        this.dentist = dentist;
        this.consultationCost = consultationCost;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public float getConsultationCost() {
        return consultationCost;
    }

    public void setConsultationCost(float consultationCost) {
        this.consultationCost = consultationCost;
    }

    @Override
    public String toString() {
        return "DentistAssignment{" +
                "id=" + id +
                ", appointment=" + appointment +
                ", dentist=" + dentist +
                ", consultationCost=" + consultationCost +
                '}';
    }
}