package ru.neurospb.notes.db.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ru.neurospb.notes.db.controllers.IthemeContentOperator;
import ru.neurospb.notes.db.models.IdbModel;
import ru.neurospb.notes.db.models.Theme;

import static ru.neurospb.notes.db.local.LocalDBHelper.TABLE_THEMES;
import static ru.neurospb.notes.db.local.LocalDBHelper.THEMES_ID;
import static ru.neurospb.notes.db.local.LocalDBHelper.THEMES_NAME;
import static ru.neurospb.notes.db.local.LocalDBHelper.THEMES_PARENT_ID;

/**
 * Supports operations with given Theme content (subthemes).
 * Works with local SQLite data storage.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class LocalThemeContentOperator implements IthemeContentOperator {

    //INTERFACE IMPLEMENTATION: IthemeContentOperator
    @Override
    public List<IdbModel> getChildrenList(Theme parentTheme) {
        List<IdbModel> children = new ArrayList<>();
        if (parentTheme.getParentId() >= 0 && parentTheme.getId() >= 0) { // path & theme found in DB
            SQLiteDatabase db = LocalDBHelper.getDB();
            Cursor data = db.query(
                    TABLE_THEMES,
                    new String[] {THEMES_NAME, THEMES_ID},
                    THEMES_PARENT_ID +" = ?",
                    new String[] {String.valueOf(parentTheme.getId())},
                    null, null, null);
            if (data != null && data.moveToFirst()) {
                do {
                    Theme theme = new Theme();
                    theme.setId(data.getInt(data.getColumnIndex(THEMES_ID)));
                    theme.setParentId(parentTheme.getId());
                    theme.setName(data.getString(data.getColumnIndex(THEMES_NAME)));
                    children.add(theme);
                } while (data.moveToNext());
            }
            if (data != null) data.close();
            db.close();
        }
        return children;
    }
}