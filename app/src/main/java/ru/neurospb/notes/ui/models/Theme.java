package ru.neurospb.notes.ui.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * A Theme model. UI layer.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */
public class Theme implements Serializable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("path")
    @Expose
    private String path;

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