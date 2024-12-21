package gui.actions_domain;

import domain.Identifiable;
import repo.ExceptionRepository;
import repo.IRepository;

public class ActionRemove<ID,T extends Identifiable<ID>> implements IAction<ID,T> {
    private IRepository<ID, T> repository;
    private T removedElement;

    public ActionRemove(T removedElement, IRepository<ID, T> repository) {
        this.removedElement = removedElement;
        this.repository = repository;
    }

    @Override
    public void executeUndo() {
        try {
            ID keyRemovedElement = removedElement.getId();
            repository.add(keyRemovedElement, removedElement);
        } catch (ExceptionRepository e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void executeRedo() {
        try {
            ID keyRemovedElement = removedElement.getId();
            repository.delete(keyRemovedElement);
        } catch (ExceptionRepository e) {
            throw new RuntimeException(e);
        }
    }
}
