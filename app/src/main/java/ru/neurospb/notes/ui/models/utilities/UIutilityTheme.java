package ru.neurospb.notes.ui.models.utilities;

import ru.neurospb.notes.ui.models.Theme;

import static ru.neurospb.notes.core.models.Folder.PATH_SPLITTER;
import static ru.neurospb.notes.ui.presenters.toolbar.IToolbar.Presenter.MODE_ROOT;
import static ru.neurospb.notes.ui.presenters.toolbar.IToolbar.Presenter.MODE_THEME;

/**
 * Supplies utility methods for UI Theme model's data manipulation.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class UIutilityTheme {
    //CLASS METHODS
    /**
     * Mines parent Theme from given one.
     * @param childTheme A Theme to mine from.
     * @return A parent Theme.
     */
    public static Theme getParent(Theme childTheme){
        Theme parentTheme = new Theme();
        String childPath = childTheme.getPath();
        if (childPath != null) {
            int p = childPath.lastIndexOf(PATH_SPLITTER);
            if (p > 0) parentTheme.setPath(childPath.substring(0, p));
            else parentTheme.setPath(null);
            parentTheme.setName(childPath.substring(p+1));
        }
        return parentTheme;
    }
    /**
     * Converts given parent Theme to a child's Theme.path.
     * @param parentTheme A Theme to convert.
     * @return A String value of child's Theme.path.
     */
    public static String getChildPath(Theme parentTheme) {
        String childPath;
        if (parentTheme.getName() == null)
            childPath = null;
        else if (parentTheme.getPath() != null)
            childPath = parentTheme.getPath() + PATH_SPLITTER + parentTheme.getName();
        else
            childPath = PATH_SPLITTER + parentTheme.getName();
        return childPath;
    }
    /**
     * Obtains toolbar's mode by given Theme.
     * @param theme A Theme to mine from.
     * @return An exact int value of toolbar's mode.
     */
    public static int getToolbarMode(Theme theme) {
        if (theme.getPath() == null) return MODE_ROOT;
        else return MODE_THEME;
    }
}