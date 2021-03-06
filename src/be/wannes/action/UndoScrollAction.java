package be.wannes.action;

import com.intellij.openapi.command.undo.UndoManager;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;

public class UndoScrollAction extends AbstractDoScrollAction {

    @Override
    protected void doAction(Project project) {
        FileEditor[] selectedEditors = FileEditorManager.getInstance(project).getSelectedEditors();
        UndoManager undoManager = UndoManager.getInstance(project);
        for (FileEditor selectedEditor : selectedEditors) {
            if (undoManager.isUndoAvailable(selectedEditor)) {
                undoManager.undo(selectedEditor);
                break;
            }
        }
    }
}
