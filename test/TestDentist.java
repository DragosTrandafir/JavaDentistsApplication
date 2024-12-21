import domain.Dentist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDentist {
    private Dentist dentist;
    private Dentist dentistOther;

    @BeforeEach
    public void setUpDentistObjects_initializeDentistsForTests_noErrors() {
       dentist = new Dentist(1,"Dragos Trandafir",40);
       dentistOther = new Dentist(1,"Dragos Trandafir",40);
    }

    @Test
    public void testGetName_correctNameStringRepresentation_noErrors() {
        Assertions.assertEquals("Dragos Trandafir",dentist.getName());
    }

    @Test
    public void testGetAge_correctAgeNumberRepresentation_noErrors() {
        Assertions.assertEquals(40,dentist.getAge());
    }

    @Test
    public void testGetId_correctIdNumberRepresentation_noErrors() {
        Assertions.assertEquals(1,dentist.getId());
    }

    @Test
    public void testSetId_validSetIdInputParameterValue_noErrors() {
        Assertions.assertDoesNotThrow(()->dentist.setId(10));
    }

    @Test
    public void testToString_correctDentistStringRepresentation_noErrors() {
        Assertions.assertEquals("Dentist{" + "id=1"
                + ", name='Dragos Trandafir" + '\'' + ", age=40" + '}',dentist.toString());
    }

    @Test
    public void testEquals_dentistsSameParametersValues_noErrors() {
        Assertions.assertTrue(dentist.equals(dentistOther));
    }


    @Test
    public void testHashCode_exactlySameHashCodeOfDentists_noErrors() {
        Assertions.assertEquals(dentist.hashCode(),dentistOther.hashCode());
    }


}
