package ru.neurospb.notes.db.controllers;

import java.util.ArrayList;
import java.util.List;

import ru.neurospb.notes.core.gateways.Icore2db;
import ru.neurospb.notes.core.models.Folder;
import ru.neurospb.notes.db.local.LocalThemeContentOperator;
import ru.neurospb.notes.db.models.IdbModel;
import ru.neurospb.notes.db.models.Theme;
import ru.neurospb.notes.db.models.utilities.DBthemePacker;
import ru.neurospb.notes.db.models.utilities.DBthemeUnpacker;

/**
 * Implements REPOSITORY PATTERN of DB layer.
 * Works with Folder's content (subfolders/subthemes)
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class ThemeContentController implements Icore2db.Listing<List<Folder>> {

    //INTERFACE IMPLEMENTATION: Icore2db.Listing
    @Override
    public List<Folder> get(Folder parent) {
        IthemeContentOperator contentOperator = new LocalThemeContentOperator();
        List<IdbModel> childModels = contentOperator.getChildrenList(new DBthemeUnpacker().pack(parent));
        List<Folder> subfolders = new ArrayList<>();
        DBthemePacker themePacker = new DBthemePacker();
        for (IdbModel model: childModels) subfolders.add(themePacker.pack((Theme) model));
        return subfolders;
    }
}