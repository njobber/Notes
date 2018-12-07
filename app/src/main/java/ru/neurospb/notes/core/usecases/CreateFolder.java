package ru.neurospb.notes.core.usecases;

import ru.neurospb.notes.core.gateways.Icore2db;
import ru.neurospb.notes.core.gateways.Icore2ui;
import ru.neurospb.notes.core.models.Folder;
import ru.neurospb.notes.core.usecases.base.AbstractUseCase;

/**
 * Creates new Folder (saves it in database).
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class CreateFolder extends AbstractUseCase {
    private Icore2db.Entity<Folder> dbEntity;
    private Icore2ui.Events<Folder> uiEvents;
    private Folder folder2create;

    //CONSTRUCTOR
    public CreateFolder(Folder folder2create,
                        Icore2db.Entity<Folder> dbEntity,
                        Icore2ui.Events<Folder> uiEvents) {
        super();
        this.folder2create = folder2create;
        this.dbEntity = dbEntity;
        this.uiEvents = uiEvents;
    }

    //INTERFACE IMPLEMENTATION: IUseCase.
    @Override
    public void run() {
        if (dbEntity.create(folder2create))
            getThreadUI().post(new Runnable() {
                @Override
                public void run() {
                    uiEvents.onActionComplete(folder2create);
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