

import domain.Dentist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repo.DentistsRepository;
import repo.ExceptionRepository;

public class TestDentistRepository {
    private DentistsRepository dentistRepository;

    @BeforeEach
    public void setUpDentistsRepository_initializeRepositoryForAllTests_noException() {
        dentistRepository = new DentistsRepository();
        try {
            dentistRepository.add(6, new Dentist(6, "Dragos", 34));
            dentistRepository.add(7, new Dentist(7, "Mihai", 35));
        } catch (ExceptionRepository e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddDentist_IdDoesNotExistYet_noException() {
        try {
            dentistRepository.add(2, new Dentist(2, "Ion", 41));
            Iterable<Dentist> dentists = dentistRepository.getAll();

            int size=0;
            for(Dentist dentist : dentists) {
                size++;
            }
            Assertions.assertEquals(size,3);
        }catch (ExceptionRepository e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void testAddDentist_IdAlreadyExists_catchesException() {
        try {
            dentistRepository.add(6, new Dentist(2, "Mihai", 41));
        } catch (ExceptionRepository e) {
            Assertions.assertEquals(e.getMessage(), "Id6 already exists");
        }
    }

    @Test
    public void testRemoveDentist_IdExists_noException() {

        try {
            dentistRepository.delete(6);
            Iterable<Dentist> dentists = dentistRepository.getAll();

            int size=0;
            for(Dentist dentist : dentists) {
                size++;
            }
            Assertions.assertEquals(size,1);
        }catch (ExceptionRepository e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testRemoveDentist_IdDoesNotExist_catchesException() {
        try {
            dentistRepository.delete(1);
        } catch (ExceptionRepository e) {
            Assertions.assertEquals(e.getMessage(), "Id1 does not exist");
        }
    }

    @Test
    public void testModifyDentist_IdExists_noException() {
        try {
            dentistRepository.modify(6, new Dentist(6, "Ion", 41));
            Iterable<Dentist> dentists = dentistRepository.getAll();

            Assertions.assertEquals(dentists.toString(),"[Dentist{id=6, name='Ion', age=41}, Dentist{id=7, name='Mihai', age=35}]");

        }catch (ExceptionRepository e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void testModifyDentist_IdDoesNotExist_catchesException() {
        try {
            dentistRepository.modify(90, new Dentist(6, "Ion", 41));
        } catch (ExceptionRepository e) {
            Assertions.assertEquals(e.getMessage(), "Id90 does not exist. Choose another id");
        }
    }

    @Test
    public void testFindDentistById_IdExists_noException() {
        try {
            Assertions.assertEquals(dentistRepository.findById(6),new Dentist(6, "Dragos", 34));
        }catch (ExceptionRepository e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void testFindDentistById_IdDoesNotExist_catchesException() {
        try {
            dentistRepository.findById(90);
        } catch (ExceptionRepository e) {
            Assertions.assertEquals(e.getMessage(), "Id90 does not exist. No object found");
        }
    }

    @Test
    public void testToStringRepo_correctDentistsStringRepresentation_noErrors(){
        Assertions.assertEquals(dentistRepository.toStringRepo(),"Dentist{id=6, name='Dragos', age=34}\nDentist{id=7, name='Mihai', age=35}\n");
    }

    @Test
    public void testGetDoctorMostAppointments_defaultMemoryRepoImplementation_noErrors(){
        Assertions.assertEquals(dentistRepository.getNameOfDoctorWithMostAppointments(),"");
    }

    @Test
    public void testGetAppointmentsOfDoctor_defaultMemoryRepoImplementation_noErrors() {
        Assertions.assertNull(dentistRepository.getIdsOfAppointmentsOfDoctor("Dragos"));
    }

}
