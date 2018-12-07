package ru.neurospb.notes.db.local;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import ru.neurospb.notes.ui.views.StartPoint;

/**
 * Basic setup of local DataBase(SQLite).
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class LocalDBHelper extends SQLiteOpenHelper {
    //CONSTANTS
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "notes";
    //{THEMES}
    public static final String TABLE_THEMES = "themes";
    public static final String THEMES_ID = "_id";
    public static final String THEMES_NAME = "name";
    public static final String THEMES_PARENT_ID = "pid";

    //CONSTRUCTOR
    private LocalDBHelper() {
        super(StartPoint.getActivity(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    //IMPLEMENTATION: SQLiteOpenHelper
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_THEMES +" ("
                + THEMES_ID +" integer primary key autoincrement,"
                + THEMES_NAME +" text,"
                + THEMES_PARENT_ID +" integer);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    //CLASS METHODS
    /**
     * Gets an instance of SQLite Data Base.
     * @return An instance of SQLiteDatabase
     * @throws SQLiteException if the database cannot be opened for writing
     */
    public static SQLiteDatabase getDB() throws SQLiteException {
        return new LocalDBHelper().getWritableDatabase();
    }
}