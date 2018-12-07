package ru.neurospb.notes.db.models.utilities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ru.neurospb.notes.db.local.LocalDBHelper;

import static ru.neurospb.notes.core.models.Folder.PATH_SPLITTER;
import static ru.neurospb.notes.db.local.LocalDBHelper.TABLE_THEMES;
import static ru.neurospb.notes.db.local.LocalDBHelper.THEMES_ID;
import static ru.neurospb.notes.db.local.LocalDBHelper.THEMES_NAME;
import static ru.neurospb.notes.db.local.LocalDBHelper.THEMES_PARENT_ID;

/**
 * Supplies utility methods for DB Theme model's data manipulation.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

class DButilityTheme {
    //CLASS METHODS
    /**
     * Converts parentId to path string.
     * METHOD DO NOT EXPECT: pid < 0 = wrong id.
     * METHOD DO NOT EXPECT: parent with given id have to exist in DB.
     * @param pid A parentId of given Theme
     * @return A String with path
     */
    static String pidToPath(int pid) {
        if (pid == 0) return null;

        StringBuilder path = new StringBuilder();
        SQLiteDatabase db = LocalDBHelper.getDB();
        do {
            Cursor data = db.query(
                    TABLE_THEMES,
                    new String[] {THEMES_NAME, THEMES_PARENT_ID},
                    THEMES_ID +" = ?",
                    new String[] {String.valueOf(pid)},
                    null, null,null);
            if (data != null) {
                if (data.moveToFirst()) {
                    path.insert(0, PATH_SPLITTER + data.getString(data.getColumnIndex(THEMES_NAME)));
                    pid = data.getInt(data.getColumnIndex(THEMES_PARENT_ID));
                }
                data.close();
            }
        } while (pid != 0);
        db.close();
        return path.toString();
    }
    /**
     * Converts path to parentId.
     * METHOD DO NOT EXPECT: path = "" OR "/"
     * @param path A String with path to given theme.
     * @return An int with parentId of given theme.     *
     * RETURNS -1 IF nothing found in DB = path does not exist.
     * RETURNS -2 IF db.query error
     */
    static int pathToPID(String path) {
        if (path == null) return 0;

        int p;
        List<String> names = new ArrayList<>();
        while (path.length() > 0) {
            p = path.lastIndexOf(PATH_SPLITTER);
            names.add(path.substring(p+1));
            path = path.substring(0, p);
        }

        int pid = 0;
        for (int i=names.size()-1; i >= 0; i--) {
            pid = getThemeId(names.get(i), pid);
            if (pid < 0) break;
        }
        return pid;
    }
    /**
     * Mines an id for given theme from DataBase by theme's name and parentId.
     * @param name A String with theme's name.
     * @param pid An int with theme's parentId.
     * @return An int with theme's id.
     * RETURN 0 IF theme is root.
     * RETURN -1 IF nothing found in DB = theme does not exist.
     * RETURN -2 IF db.query error
     * RETURN -3 IF Exception caught
     */
    static int getThemeId(String name, int pid) {
        if (name == null) {
            if (pid == 0) return 0;
            else return -1;
        }

        int id = -3;
        Cursor data;
        try {
            SQLiteDatabase db = LocalDBHelper.getDB();
            data = db.query(
                    TABLE_THEMES,
                    new String[] {THEMES_ID},
                    THEMES_NAME +" = ? and "+ THEMES_PARENT_ID +" = ?",
                    new String[] {name, String.valueOf(pid)},
                    null, null, null);
            if (data != null) {
                if (data.moveToFirst()) id = data.getInt(data.getColumnIndex(THEMES_ID));
                else id = -1;
                data.close();
            } else id = -2;
            db.close();
        } catch (Exception ignored){}
        return id;
    }
}