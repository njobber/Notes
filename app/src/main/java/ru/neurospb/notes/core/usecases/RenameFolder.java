package ru.neurospb.notes.core.usecases;

import ru.neurospb.notes.core.gateways.Icore2db;
import ru.neurospb.notes.core.gateways.Icore2ui;
import ru.neurospb.notes.core.models.Folder;
import ru.neurospb.notes.core.usecases.base.AbstractUseCase;

/**
 * Renames existing Folder (updates it in database).
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class RenameFolder extends AbstractUseCase {
    private Icore2db.Entity<Folder> dbEntity;
    private Icore2ui.Events<Folder> uiEvents;
    private Folder folder2rename, newVersionFolder;

    //CONSTRUCTOR
    public RenameFolder(Folder folder2rename,
                        String newFolderName,
                        Icore2db.Entity<Folder> dbEntity,
                        Icore2ui.Events<Folder> uiEvents) {
        super();
        this.folder2rename = folder2rename;
        newVersionFolder = new Folder();
        newVersionFolder.setPath(folder2rename.getPath());
        newVersionFolder.setName(newFolderName);
        this.dbEntity = dbEntity;
        this.uiEvents = uiEvents;
    }

    //INTERFACE IMPLEMENTATION: IUseCase.
    @Override
    public void run() {
        if (dbEntity.update(folder2rename, newVersionFolder))
            getThreadUI().post(new Runnable() {
                @Override
                public void run() {
                    uiEvents.onActionComplete(newVersionFolder);
                }
            });
        else
            getThreadUI().post(new Runnable() {
                @Override
                public void run() {
                    uiEvents.onError();
                }
            });
    }
}