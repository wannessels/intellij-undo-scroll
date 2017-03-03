package be.wannes.listener;

import com.intellij.openapi.command.undo.UndoManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.project.Project;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;

public class ScrollListener implements MouseWheelListener {

    private final UndoManager undoManager;
    private FileEditor editor;

    public ScrollListener(Project project, FileEditor editor) {
        this.editor = editor;
        this.undoManager = UndoManager.getInstance(project);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        if (e.getWheelRotation() < 0) {
            System.out.println("undoAvailable " + undoManager.isUndoAvailable(editor));
            if (undoManager.isUndoAvailable(editor)) {
                undoManager.undo(editor);
            }
        } else {
            System.out.println("redoAvailable " + undoManager.isRedoAvailable(editor));
            if (undoManager.isRedoAvailable(editor)) {
                undoManager.redo(editor);
            }
        }

    }
}
