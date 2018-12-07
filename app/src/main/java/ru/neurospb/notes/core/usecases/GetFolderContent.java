package ru.neurospb.notes.core.usecases;

import java.util.ArrayList;
import java.util.List;

import ru.neurospb.notes.core.gateways.Icore2db;
import ru.neurospb.notes.core.gateways.Icore2ui;
import ru.neurospb.notes.core.models.Folder;
import ru.neurospb.notes.core.usecases.base.AbstractUseCase;

/**
 * Gets a folder's content (folders).
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class GetFolderContent extends AbstractUseCase {
    private Icore2db.Listing<List<Folder>> dbListing;
    private Icore2ui.Data<List<Folder>> uiData;
    private Folder parentFolder;
    private List<Folder> folders = new ArrayList<>();

    //CONSTRUCTOR
    public GetFolderContent(Folder parentFolder,
                            Icore2ui.Data<List<Folder>> uiData,
                            Icore2db.Listing<List<Folder>> dbListing) {
        super();
        this.parentFolder = parentFolder;
        this.uiData = uiData;
        this.dbListing = dbListing;
    }

    //INTERFACE IMPLEMENTATION: IUseCase.
    @Override
    public void run() {
        folders = dbListing.get(parentFolder);
        getThreadUI().post(new Runnable() {
            @Override
            public void run() {
                uiData.onDataLoad(folders);
            }
        });
    }
}