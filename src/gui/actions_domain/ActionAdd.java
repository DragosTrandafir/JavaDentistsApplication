package gui.actions_domain;

import domain.Identifiable;
import repo.ExceptionRepository;
import repo.IRepository;

public class ActionAdd<ID,T extends Identifiable<ID>> implements IAction<ID,T> {
    private IRepository<ID, T> repository;
    private T addedElement;

    public ActionAdd(T addedElement, IRepository<ID, T> repository) {
        this.addedElement = addedElement;
        this.repository = repository;
    }

    @Override
    public void executeUndo() {
        try {
            ID keyAddedElement = addedElement.getId();
            repository.delete(keyAddedElement);
        } catch (ExceptionRepository e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void executeRedo() {
        try {
            ID keyAddedElement = addedElement.getId();
            repository.add(keyAddedElement, addedElement);
        } catch (ExceptionRepository e) {
            throw new RuntimeException(e);
        }
    }
}
