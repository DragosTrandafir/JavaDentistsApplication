package repo;

import domain.Identifiable;

import java.util.ArrayList;
import java.util.HashMap;

// generic repository for any type of objects ,structured as a hashmap
public class MemoryRepository<ID,T extends Identifiable<ID>> implements IRepository<ID, T> {
    protected HashMap<ID,T> objects = new HashMap<>();

    @Override
    public void add(ID key, T value) throws ExceptionRepository {
        // add objects with a certain key only if it doesnt exist already
        if(!objects.containsKey(key))
            objects.put(key,value);
        else throw new ExceptionRepository("Id"+ key+" already exists");
    }
    public void delete(ID key) throws ExceptionRepository {
        if(objects.containsKey(key))
            objects.remove(key);
        else throw new ExceptionRepository("Id"+ key+" does not exist");
    }
    public void modify(ID key, T value) throws ExceptionRepository {
        if(objects.containsKey(key))
            objects.put(key,value);
        else throw new ExceptionRepository("Id"+ key+" does not exist. Choose another id");
    }
    public T findById(ID key) throws ExceptionRepository {
        if(objects.containsKey(key))
            return objects.get(key);
        else throw new ExceptionRepository("Id"+ key+" does not exist. No object found");
    }
    public Iterable<T> getAll() {
            return objects.values();
    }

    @Override
    public String toStringRepo() {
        String repositoryStringRepresentation ="";
        for(T object : objects.values())
            repositoryStringRepresentation = repositoryStringRepresentation + object.toString() + "\n";
        return repositoryStringRepresentation;
    }

    @Override
    public String getNameOfDoctorWithMostAppointments()  {
        return "";
    }

    @Override
    public ArrayList<ID> getIdsOfAppointmentsOfDoctor(String doctorName) {
        return null;
    }
}