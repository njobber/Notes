package ru.neurospb.notes.db.models.utilities;

import ru.neurospb.notes.core.gateways.Ipacking;
import ru.neurospb.notes.core.models.Folder;
import ru.neurospb.notes.db.models.Theme;

/**
 * Packs DB layer's data object to Core layer's data object.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class DBthemePacker implements Ipacking<Folder, Theme> {

    //INTERFACE IMPLEMENTATION: Ipacking
    @Override
    public Folder pack(Theme theme) {
        Folder folder = new Folder();
        folder.setName(theme.getName());
        folder.setPath(DButilityTheme.pidToPath(theme.getParentId()));
        return folder;
    }
}