package be.wannes.listener;

import com.intellij.openapi.command.undo.UndoManager;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.project.Project;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ScrollListener implements MouseWheelListener {

    private final UndoManager undoManager;
    private FileEditor fileEditor;

    public ScrollListener(Project project, FileEditor fileEditor) {
        this.fileEditor = fileEditor;
        this.undoManager = UndoManager.getInstance(project);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
        if (isScrollingDown(mouseWheelEvent)) {
            if (undoManager.isUndoAvailable(fileEditor)) {
                undoManager.undo(fileEditor);
            }
        } else {
            if (undoManager.isRedoAvailable(fileEditor)) {
                undoManager.redo(fileEditor);
            }
        }
    }

    private boolean isScrollingDown(MouseWheelEvent e) {
        return e.getWheelRotation() > 0;
    }
}
