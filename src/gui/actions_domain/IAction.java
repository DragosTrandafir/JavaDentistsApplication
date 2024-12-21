package gui.actions_domain;

import domain.Identifiable;

public interface IAction<ID,T extends Identifiable<ID>> {
    public void executeUndo();
    public void executeRedo();
}