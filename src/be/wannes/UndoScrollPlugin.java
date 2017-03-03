package be.wannes;

import com.intellij.openapi.actionSystem.IdeActions;
import com.intellij.openapi.actionSystem.Shortcut;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.keymap.Keymap;
import com.intellij.openapi.keymap.impl.KeymapManagerImpl;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;


public class UndoScrollPlugin implements ProjectComponent {

    private Project project;

    public UndoScrollPlugin(Project project) {
        this.project = project;
    }

    @Override
    public void projectOpened() {

    }

    @Override
    public void projectClosed() {

    }

    @Override
    public void initComponent() {
        Keymap keyMap = getKeyMap();
        replaceDefaultAction(keyMap, IdeActions.ACTION_UNDO);
        replaceDefaultAction(keyMap, IdeActions.ACTION_REDO);
    }

    private void replaceDefaultAction(Keymap keyMap, String action) {
        Shortcut[] shortcuts = keyMap.getShortcuts(action);
        for (Shortcut shortcut : shortcuts) {
            keyMap.removeShortcut(action, shortcut);
            keyMap.addShortcut("UndoScrollAction", shortcut);
        }
    }

    private Keymap getKeyMap() {
        Keymap keymap = KeymapManagerImpl.getInstance().getActiveKeymap().getParent();
        if (keymap == null) {
            keymap = KeymapManagerImpl.getInstance().getActiveKeymap();
        }
        return keymap;
    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return "undo-scroll";
    }
}
