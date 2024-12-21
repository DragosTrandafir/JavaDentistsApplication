package domain;

public class DentistValidator implements Validator<Dentist> {

    @Override
    public void validate(Dentist dentist) {
        if (dentist.getId() <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer.");
        }
        if (dentist.getName() == null || dentist.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
    }
}
