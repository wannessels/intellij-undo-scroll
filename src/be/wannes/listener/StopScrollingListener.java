package be.wannes.listener;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.TextEditor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StopScrollingListener extends KeyAdapter {

    private boolean scrollListenerEnabled = false;
    private ScrollListener scrollListener;
    private FileEditor fileEditor;

    public StopScrollingListener(FileEditor fileEditor, ScrollListener scrollListener) {
        this.fileEditor = fileEditor;
        this.scrollListener = scrollListener;

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
            Editor editor = ((TextEditor) fileEditor).getEditor();
            editor.getContentComponent().addMouseWheelListener(scrollListener);
        }
        scrollListenerEnabled = true;
    }

    private void disableScrollListener() {
        if (scrollListenerEnabled) {
            Editor editor = ((TextEditor) fileEditor).getEditor();
            editor.getContentComponent().removeMouseWheelListener(scrollListener);
        }
        scrollListenerEnabled = false;
    }
}
