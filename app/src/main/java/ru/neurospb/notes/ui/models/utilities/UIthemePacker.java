package ru.neurospb.notes.ui.models.utilities;

import ru.neurospb.notes.core.gateways.Ipacking;
import ru.neurospb.notes.core.models.Folder;
import ru.neurospb.notes.ui.models.Theme;

/**
 * Packs UI layer's data object to Core layer's data object.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class UIthemePacker implements Ipacking<Folder, Theme> {
    //INTERFACE IMPLEMENTATION: Ipacking
    @Override
    public Folder pack(Theme theme) {
        Folder folder = new Folder();
        folder.setName(theme.getName());
        folder.setPath(theme.getPath());
        return folder;
    }
}