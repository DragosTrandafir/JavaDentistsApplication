package repo;

import domain.Dentist;
import domain.Identifiable;

import java.util.ArrayList;

public class FakeRepository<ID,T extends Identifiable<ID>> implements IRepository<ID,T> {

    public Iterable<T> getAll(){
        ArrayList<Dentist> fixedDentistsCollection = new ArrayList<>();
        Dentist dentist1= new Dentist(124,"Dragos",41);
        Dentist dentist2= new Dentist(125,"Ion",46);
        fixedDentistsCollection.add(dentist1);
        fixedDentistsCollection.add(dentist2);
        return (Iterable<T>) fixedDentistsCollection;
    }



















    public void add(ID key, T value) {

    }

    public void delete(ID key) throws ExceptionRepository {

    }

    public void modify(ID key, T value){

    }
    public T findById(ID key) {
        //if(fakeObjects.containsKey(key))
            return null;
        //throw new NoSuchElementException();
    }

    @Override
    public String getNameOfDoctorWithMostAppointments() {
        return "";
    }

    @Override
    public ArrayList<ID> getIdsOfAppointmentsOfDoctor(String doctorName) {
        return null;
    }

    @Override
    public String toStringRepo(){
        return null;
    }
}

