package ru.neurospb.notes.core.models;

/**
 * A Folder entity.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class Folder {
    private String name, path;
    public static final char PATH_SPLITTER = '/';

    //GETTERS / SETTERS
    //name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    //path
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}