package service;

import domain.Appointment;
import domain.Dentist;
import domain.DentistValidator;
import repo.ExceptionRepository;
import repo.IRepository;

public class ServiceDentists {
    private IRepository<Integer,Dentist> dentists;

    public ServiceDentists(IRepository<Integer,Dentist> dentists) {
        this.dentists = dentists;
    }

    public void add(int id, String name, int age) {

        try{
            DentistValidator validatorDentists = new DentistValidator();

            Dentist dentist= new Dentist(id,name,age);
            validatorDentists.validate(dentist);

            dentists.add(id,dentist);
        }catch(ExceptionRepository | IllegalArgumentException e){
            throw new RuntimeException(e);
        }
    }
    public void delete(int id) {
        try{
            dentists.delete(id);
        }catch(ExceptionRepository e){
            throw new RuntimeException(e);
        }
    }
    public void modify(int id_whereToModify, String name, int age){

        try{
            DentistValidator validatorDentists = new DentistValidator();

            Dentist dentist= new Dentist(id_whereToModify,name,age);
            validatorDentists.validate(dentist);

            dentists.modify(id_whereToModify,dentist);
        }catch(IllegalArgumentException | ExceptionRepository e){
            throw new RuntimeException(e);
        }
    }
    public Dentist findById(int id) {
        try{
        return dentists.findById(id);
        }catch(ExceptionRepository e){
            throw new RuntimeException(e);
        }
    }
    public Iterable<Dentist> getAll(){
                return dentists.getAll();

        }
    public IRepository<Integer, Dentist> getRepository() {
        return this.dentists;
    }

    public String toStringRepo(){
        return dentists.toStringRepo();
    }
}
