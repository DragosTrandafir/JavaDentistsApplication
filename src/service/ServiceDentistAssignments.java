package service;

import domain.Appointment;
import domain.Dentist;
import domain.DentistAssignment;
import repo.ExceptionRepository;
import repo.IRepository;

public class ServiceDentistAssignments {
    private IRepository<Integer, DentistAssignment> dentistAssignments;

    public ServiceDentistAssignments(IRepository<Integer, DentistAssignment> dentistAssignments) {
        this.dentistAssignments = dentistAssignments;
    }

    public void add(int id, Appointment appointment, Dentist dentist, float consultationCost) //highly depends on the dentist) {
    {
        try{
            DentistAssignment dentistAssignment=new DentistAssignment(id,appointment,dentist,consultationCost);
            dentistAssignments.add(id,dentistAssignment);
        }catch(ExceptionRepository | IllegalArgumentException e){
            throw new RuntimeException(e);
        }
    }
    public void delete(int id) {
        try{
            dentistAssignments.delete(id);
        }catch(ExceptionRepository e){
            throw new RuntimeException(e);
        }
    }
    public void modify(int id_whereToModify, Appointment appointment,Dentist dentist,float consultationCost){

        try{
            DentistAssignment dentistAssignment=new DentistAssignment(id_whereToModify,appointment,dentist,consultationCost);
            dentistAssignments.modify(id_whereToModify,dentistAssignment);
        }catch(IllegalArgumentException | ExceptionRepository e){
            throw new RuntimeException(e);
        }
    }
    public DentistAssignment findById(int id) {
        try{
            return dentistAssignments.findById(id);
        }catch(ExceptionRepository e){
            throw new RuntimeException(e);
        }
    }
    public Iterable<DentistAssignment> getAll(){
        return dentistAssignments.getAll();

    }
    public IRepository<Integer, DentistAssignment> getRepository() {
        return this.dentistAssignments;
    }

    public String toStringRepo(){
        return dentistAssignments.toStringRepo();
    }
}
