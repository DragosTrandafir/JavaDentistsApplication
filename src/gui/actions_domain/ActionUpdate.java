package gui.actions_domain;

import domain.Identifiable;
import repo.ExceptionRepository;
import repo.IRepository;

public class ActionUpdate<ID,T extends Identifiable<ID>> implements IAction<ID,T> {
    private IRepository<ID, T> repository;
    private T oldElement;
    private T newElement;

    public ActionUpdate(IRepository<ID, T> repository, T oldElement, T newElement) {
        this.repository = repository;
        this.oldElement = oldElement;
        this.newElement = newElement;
    }

    @Override
    public void executeUndo() {
        try {
            ID keyOldElement = oldElement.getId(); //does not matter - old or new - the id remains the same
            repository.modify(keyOldElement, oldElement);
        } catch (ExceptionRepository e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void executeRedo() {
        try {
             ID keyOldElement = oldElement.getId(); //does not matter - old or new - the id remains the same
             repository.modify(keyOldElement, newElement);
        } catch (ExceptionRepository e) {
            throw new RuntimeException(e);
        }
    }
}
