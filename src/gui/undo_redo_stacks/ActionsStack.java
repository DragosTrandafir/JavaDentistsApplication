package gui.undo_redo_stacks;

import gui.actions_domain.IAction;
import domain.Identifiable;
import javafx.scene.control.Alert;

import java.util.Stack;

public class ActionsStack<ID, T extends Identifiable<ID>> {
    private Stack < IAction<Integer, ? extends Identifiable<Integer>> > stackUndoActions= new Stack<>();
    private Stack < IAction<Integer, ? extends Identifiable<Integer>> > stackRedoActions= new Stack<>();

    public void addAction(IAction<Integer, ? extends Identifiable<Integer>> action) {
        stackUndoActions.push( action);
        stackRedoActions.clear();
    }

    public void undo(){
        if(!stackUndoActions.isEmpty()) {
            IAction<Integer, ? extends Identifiable<Integer>> lastPerformedAction_UndoStack = stackUndoActions.pop();

            try {
                lastPerformedAction_UndoStack.executeUndo();
            }catch(RuntimeException e) {
                Alert executeUndoErrorAlert = new Alert(Alert.AlertType.ERROR);
                executeUndoErrorAlert.setContentText(e.getMessage());
                executeUndoErrorAlert.showAndWait();
            }

            stackRedoActions.push(lastPerformedAction_UndoStack);
        }else{
            Alert executeUndoErrorAlert = new Alert(Alert.AlertType.WARNING);
            executeUndoErrorAlert.setContentText("Nothing to undo");
            executeUndoErrorAlert.showAndWait();
        }
    }
    public void redo(){
        if(!stackRedoActions.isEmpty()) {
            IAction<Integer, ? extends Identifiable<Integer>> lastPerformedAction_RedoStack = stackRedoActions.pop();

            try{
            lastPerformedAction_RedoStack.executeRedo();
            }catch(RuntimeException e) {
                Alert executeRedoErrorAlert = new Alert(Alert.AlertType.ERROR);
                executeRedoErrorAlert.setContentText(e.getMessage());
                executeRedoErrorAlert.showAndWait();
            }

            stackUndoActions.push(lastPerformedAction_RedoStack);
        }else{
            Alert executeUndoErrorAlert = new Alert(Alert.AlertType.WARNING);
            executeUndoErrorAlert.setContentText("Nothing to redo");
            executeUndoErrorAlert.showAndWait();
        }
    }
}
