package be.wannes.listener;


import com.intellij.openapi.command.undo.UndoManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.TextEditor;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StopScrollingListener extends KeyAdapter {

    public static final int KEY_CODE_Z = 90;
    private boolean scrollListenerEnabled = false;
    private ScrollListener scrollListener;
    private FileEditor fileEditor;
    private UndoManager undoManager;

    public StopScrollingListener(FileEditor fileEditor, ScrollListener scrollListener) {
        this.fileEditor = fileEditor;
        this.scrollListener = scrollListener;
        undoManager = UndoManager.getGlobalInstance();

        enableScrollListener();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            disableScrollListener();
        }
    }

    private void enableScrollListener() {
        if (!scrollListenerEnabled) {
            System.out.println("enable scroll listener");
            Editor editor = ((TextEditor) fileEditor).getEditor();
            editor.getContentComponent().addMouseWheelListener(scrollListener);
        }
        scrollListenerEnabled = true;
    }

    private void disableScrollListener() {
        if (scrollListenerEnabled) {
            System.out.println("disable scroll listener");
            Editor editor = ((TextEditor) fileEditor).getEditor();
            editor.getContentComponent().removeMouseWheelListener(scrollListener);
        }
        scrollListenerEnabled = false;
    }

    private boolean isCtrlZ(KeyEvent e) {
        return e.getModifiers() == ActionEvent.CTRL_MASK && e.getKeyCode() == KEY_CODE_Z;
    }

    private boolean isCtrlShiftZ(KeyEvent e) {
        return e.getModifiers() == ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK && e.getKeyCode() == KEY_CODE_Z;
    }
}
