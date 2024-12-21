package domain;


public interface Validator<T extends Identifiable> {
    public void validate(T identifiable);
}
