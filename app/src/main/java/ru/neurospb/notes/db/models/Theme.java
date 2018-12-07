package ru.neurospb.notes.db.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * A Theme model. DB layer.
 *
 * @author Alexey Utovka
 * @version alpha1
 * @since alpha1
 */

public class Theme implements IdbModel{
    //id > 0 -> OK
    //id = -1 -> NEW: theme does not exist
    //id = -2,-3 -> DB error
    @SerializedName("_id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    //pid >= 0 -> OK
    //pid = -1 -> ERROR: path does not exist
    //pid = -2 -> DB error
    @SerializedName("pid")
    @Expose
    private int parentId;

    //GETTERS / SETTERS
    //id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    //name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    //parentId
    public int getParentId() {
        return parentId;
    }
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}