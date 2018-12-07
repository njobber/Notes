package ru.neurospb.notes.db.models.utilities;

import ru.neurospb.notes.core.gateways.Ipacking;
import ru.neurospb.notes.core.models.Folder;
import ru.neurospb.notes.db.models.Theme;

/**
 * Unpacks Core layer data object to DB layer data object.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class DBthemeUnpacker implements Ipacking<Theme, Folder> {

    //INTERFACE IMPLEMENTATION: Ipacking
    @Override
    public Theme pack(Folder folder) {
        Theme theme = new Theme();
        theme.setName(folder.getName());
        theme.setParentId(DButilityTheme.pathToPID(folder.getPath()));
        theme.setId(DButilityTheme.getThemeId(theme.getName(), theme.getParentId()));
        return theme;
    }
}