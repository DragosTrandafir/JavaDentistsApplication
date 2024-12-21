package domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;
import java.util.Objects;

public class Appointment implements Identifiable<Integer>,Serializable
{
    //private attributes
    @JacksonXmlProperty(localName = "Id")
    private int id;

    @JacksonXmlProperty(localName = "PatientName")
    private String patientName;

    @JacksonXmlProperty(localName = "DoctorName")
    private String doctorName;

    @JacksonXmlProperty(localName = "Hour")
    private String hour;

    @JacksonXmlProperty(localName = "Date")
    private String date;

    //constructor
    public Appointment(){}

    public Appointment(int id, String patientName, String doctorName, String hour, String date){
        this.id=id;

        this.patientName = patientName;

        this.doctorName = doctorName;

        this.hour = hour;
        this.date = date;
    }


    //getters
    public String getPatientName(){
        return patientName;
    }
    public String getDoctorName(){
        return doctorName;
    }
    public String getHour(){
        return hour;
    }
    public String getDate(){
        return date;
    }

    //setters
    public void setPatientName(String patientName){
        this.patientName = patientName;
    }
    public void setDoctorName(String doctorName){
        this.doctorName = doctorName;
    }
    public void setHour(String hour){
        this.hour = hour;
    }
    public void setDate(String date){
        this.date = date;
    }

    @Override
    public void setId(Integer id) {   //use Integer, not int
        this.id=id;                   // it replaces ID and it represents the bigger object of int in the compliler
    }
    public Integer getId(){
        return id;
    }

    // checking if 2 objects are egual -> same data values & same bucket using hashCode
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Appointment that = (Appointment) object;
        return id == that.id && Objects.equals(patientName, that.patientName) && Objects.equals(doctorName, that.doctorName) && Objects.equals(hour, that.hour) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientName, doctorName, hour, date);
    }

    public String toString(){
        return "Appointment [id=" + id + ", patientName=" + patientName + ", doctorName="+ doctorName + ", hour=" + hour + ", date=" + date + "]";
    }
}
