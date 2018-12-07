package ru.neurospb.notes.core.usecases;

import ru.neurospb.notes.core.gateways.Icore2db;
import ru.neurospb.notes.core.gateways.Icore2ui;
import ru.neurospb.notes.core.models.Folder;
import ru.neurospb.notes.core.usecases.base.AbstractUseCase;

/**
 * Deletes existing Folder (deletes it in database).
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class DeleteFolder extends AbstractUseCase {
    private Icore2db.Entity<Folder> dbEntity;
    private Icore2ui.Events<Folder> uiEvents;
    private Folder folder2delete;

    //CONSTRUCTOR
    public DeleteFolder(Folder folder2delete,
                        Icore2db.Entity<Folder> dbEntity,
                        Icore2ui.Events<Folder> uiEvents) {
        super();
        this.folder2delete = folder2delete;
        this.dbEntity = dbEntity;
        this.uiEvents = uiEvents;
    }

    //INTERFACE IMPLEMENTATION: IUseCase.
    @Override
    public void run() {
        if (dbEntity.delete(folder2delete))
            getThreadUI().post(new Runnable() {
                @Override
                public void run() {
                    uiEvents.onActionComplete(null);
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