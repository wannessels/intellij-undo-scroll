package be.wannes.action;

import be.wannes.listener.StopScrollingListener;
import be.wannes.listener.ScrollListener;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;

public class UndoScrollAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Editor editor = e.getData(CommonDataKeys.EDITOR_EVEN_IF_INACTIVE);
        Project project = e.getData(CommonDataKeys.PROJECT);
        FileEditor[] selectedEditors = FileEditorManager.getInstance(project).getSelectedEditors();
        if (selectedEditors.length > 0) {
            FileEditor fileEditor = selectedEditors[0];
            final ScrollListener scrollListener = new ScrollListener(project,fileEditor);
            StopScrollingListener stopScrollingListener = new StopScrollingListener(fileEditor, scrollListener);
            editor.getContentComponent().addKeyListener(stopScrollingListener);
        }




    }
}
