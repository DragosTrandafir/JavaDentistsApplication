package repo;
import domain.Identifiable;

import java.util.ArrayList;

public interface IRepository<ID,T extends Identifiable<ID>> {
    public void add(ID key, T value) throws ExceptionRepository;
    public void delete(ID key) throws ExceptionRepository;
    public void modify(ID key, T value) throws ExceptionRepository;
    public T findById(ID key) throws ExceptionRepository;
    public Iterable<T> getAll() ;

    public String toStringRepo();

    String getNameOfDoctorWithMostAppointments() ;

    ArrayList<ID> getIdsOfAppointmentsOfDoctor(String doctorName) ;
}
