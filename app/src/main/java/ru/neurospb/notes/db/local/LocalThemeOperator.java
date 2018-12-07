package ru.neurospb.notes.db.local;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import ru.neurospb.notes.db.controllers.IbasicModelOperator;
import ru.neurospb.notes.db.models.IdbModel;
import ru.neurospb.notes.db.models.Theme;

import static ru.neurospb.notes.db.local.LocalDBHelper.TABLE_THEMES;
import static ru.neurospb.notes.db.local.LocalDBHelper.THEMES_ID;
import static ru.neurospb.notes.db.local.LocalDBHelper.THEMES_NAME;
import static ru.neurospb.notes.db.local.LocalDBHelper.THEMES_PARENT_ID;

/**
 * Supports basic operations for Theme model.
 * Works with local SQLite data storage.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class LocalThemeOperator implements IbasicModelOperator {

    //INTERFACE IMPLEMENTATION: IbasicModelOperator
    @Override
    public boolean put(IdbModel model) {
        Theme theme = (Theme) model;

        if (theme.getId() == -1 && theme.getParentId() >= 0) {
            ContentValues newData = new ContentValues();
            newData.put(THEMES_NAME, theme.getName());
            newData.put(THEMES_PARENT_ID, theme.getParentId());
            SQLiteDatabase db = LocalDBHelper.getDB();
            db.insert(TABLE_THEMES, null, newData);
            db.close();
            return true;
        } else {
            //theme is not unique -> exists in DB
            //theme is nonsense.
            //ERROR: wrong path
            //ERROR: DB error
            return false;
        }
    }
    @Override
    public boolean post(IdbModel model) {
        Theme theme = (Theme) model;

        if (theme.getParentId() >= 0 && theme.getId() > 0) {
            ContentValues newData = new ContentValues();
            newData.put(THEMES_NAME, theme.getName());
            newData.put(THEMES_PARENT_ID, theme.getParentId());
            SQLiteDatabase db = LocalDBHelper.getDB();
            db.update(TABLE_THEMES,
                    newData,
                    THEMES_ID +" = ?",
                    new String[] {String.valueOf(theme.getId())});
            db.close();
            return true;
        } else return false;
            //ERROR: wrong path. pid<0
            //ERROR: DB error. pid<0
            //ERROR: old theme.name is unique OR wrong(root) OR error/exception. id<1
    }
    @Override
    public boolean delete(IdbModel model) {
        Theme theme = (Theme) model;

        if (theme.getParentId() >= 0 && theme.getId() > 0) {
            SQLiteDatabase db = LocalDBHelper.getDB();
            db.delete(TABLE_THEMES,
                    THEMES_ID +" = ? OR "+ THEMES_PARENT_ID+" = ?",
                    new String[] {String.valueOf(theme.getId()),
                                  String.valueOf(theme.getId())
                    });
            db.close();
            return true;
        } else return false;
    }
}