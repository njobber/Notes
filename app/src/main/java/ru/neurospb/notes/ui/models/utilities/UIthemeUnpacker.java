package ru.neurospb.notes.ui.models.utilities;

import ru.neurospb.notes.core.gateways.Ipacking;
import ru.neurospb.notes.core.models.Folder;
import ru.neurospb.notes.ui.models.Theme;

/**
 * Unpacks Core layer's data object to UI layer's data object.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class UIthemeUnpacker implements Ipacking<Theme, Folder> {

    //INTERFACE IMPLEMENTATION: Ipacking
    @Override
    public Theme pack(Folder folder) {
        Theme theme = new Theme();
        theme.setName(folder.getName());
        theme.setPath(folder.getPath());
        return theme;
    }
}