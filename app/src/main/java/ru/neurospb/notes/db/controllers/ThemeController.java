package ru.neurospb.notes.db.controllers;

import ru.neurospb.notes.core.gateways.Icore2db;
import ru.neurospb.notes.core.gateways.Ipacking;
import ru.neurospb.notes.core.models.Folder;
import ru.neurospb.notes.db.local.LocalThemeOperator;
import ru.neurospb.notes.db.models.Theme;
import ru.neurospb.notes.db.models.utilities.DBthemeUnpacker;

/**
 * Implements REPOSITORY PATTERN of DB layer.
 * Works with Folder ENTITY (Theme MODEL)
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class ThemeController implements Icore2db.Entity<Folder> {
    private IbasicModelOperator modelOperator;
    private Ipacking<Theme, Folder> modelUnpacker;

    //CONSTRUCTOR
    public ThemeController() {
        modelOperator = new LocalThemeOperator();
        modelUnpacker = new DBthemeUnpacker();
    }

    //INTERFACE IMPLEMENTATION: Icore2db.Entity
    @Override
    public boolean create(Folder data) {
        return modelOperator.put(modelUnpacker.pack(data));
    }
    @Override
    public boolean update(Folder oldVersionOfData, Folder newVersionOfData) {
        Theme updatedTheme = modelUnpacker.pack(newVersionOfData);
        if (modelUnpacker.pack(oldVersionOfData).getId() == -1)
            return false;
        updatedTheme.setId(modelUnpacker.pack(oldVersionOfData).getId());
        return modelOperator.post(updatedTheme);
    }
    @Override
    public boolean delete(Folder data) {
        return modelOperator.delete(modelUnpacker.pack(data));
    }
}