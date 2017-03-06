package be.wannes.action;

import com.intellij.openapi.command.undo.UndoManager;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;

public class RedoScrollAction extends AbstractDoScrollAction {

    @Override
    protected void doAction(Project project) {
        FileEditor[] selectedEditors = FileEditorManager.getInstance(project).getSelectedEditors();
        UndoManager undoManager = UndoManager.getInstance(project);
        for (FileEditor selectedEditor : selectedEditors) {
            if (undoManager.isRedoAvailable(selectedEditor)) {
                undoManager.redo(selectedEditor);
                break;
            }
        }
    }
}
