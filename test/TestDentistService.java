import domain.Dentist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repo.AppointmentsRepository;
import repo.DentistsRepository;
import repo.ExceptionRepository;
import service.ServiceAppointments;
import service.ServiceDentists;


public class TestDentistService {
    private ServiceDentists serviceDentists;

    @BeforeEach
    void setUpDentistsService_initializeServiceForAllTests_noException() {
        serviceDentists= new ServiceDentists(new DentistsRepository());
        try {
            serviceDentists.add(6, "Dragos", 34);
            serviceDentists.add(7, "Mihai", 35);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddDentist_IdDoesNotExistYet_noException() {
        try {
            serviceDentists.add(2, "Ion", 41);
            Iterable<Dentist> dentists = serviceDentists.getAll();

            int size=0;
            for(Dentist dentist : dentists) {
                size++;
            }
            Assertions.assertEquals(size,3);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testAddDentist_IdAlreadyExists_catchesException() {
        try {
            serviceDentists.add(2, "Mihai", 41);
        } catch (Exception e) {
            Assertions.assertEquals(e.getMessage(), "Id6 already exists");
        }
    }

    @Test
    public void testRemoveDentist_IdExists_noException() {

        try {
            serviceDentists.delete(6);
            Iterable<Dentist> dentists = serviceDentists.getAll();

            int size=0;
            for(Dentist dentist : dentists) {
                size++;
            }
            Assertions.assertEquals(size,1);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testRemoveDentist_IdDoesNotExist_catchesException() {
        try {
            serviceDentists.delete(1);
        } catch (Exception e) {
            Assertions.assertEquals(e.getMessage(), "repo.ExceptionRepository: Id1 does not exist");
        }
    }

    @Test
    public void testModifyDentist_IdExists_noException() {
        try {
            serviceDentists.modify(6, "Ion", 41);
            Iterable<Dentist> dentists = serviceDentists.getAll();

            Assertions.assertEquals(dentists.toString(),"[Dentist{id=6, name='Ion', age=41}, Dentist{id=7, name='Mihai', age=35}]");

        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testModifyDentist_IdDoesNotExist_catchesException() {
        try {
            serviceDentists.modify(90, "Ion", 41);
        } catch (Exception e) {
            Assertions.assertEquals(e.getMessage(), "repo.ExceptionRepository: Id90 does not exist. Choose another id");
        }
    }

    @Test
    public void testFindDentistById_IdExists_noException() {
        try {
            Assertions.assertEquals(serviceDentists.findById(6),new Dentist(6, "Dragos", 34));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void testFindDentistById_IdDoesNotExist_catchesException() {
        try {
            serviceDentists.findById(90);
        } catch (Exception e) {
            Assertions.assertEquals(e.getMessage(), "repo.ExceptionRepository: Id90 does not exist. No object found");
        }
    }

    @Test
    public void testToStringService_correctDentistsStringRepresentation_noErrors() {
        Assertions.assertEquals(serviceDentists.toStringRepo(),"Dentist{id=6, name='Dragos', age=34}\nDentist{id=7, name='Mihai', age=35}\n");
    }

}
