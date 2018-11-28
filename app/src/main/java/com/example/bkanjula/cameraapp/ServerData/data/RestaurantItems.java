package com.example.bkanjula.cameraapp.ServerData.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RestaurantItems implements Serializable {



    @SerializedName("Restitemid")
    @Expose
    private Integer restitemid;

    @SerializedName("Cost")
    @Expose
    private String cost;

    @SerializedName("Itemname")
    @Expose
    private String itemname;

    public Integer getRestitemid() {
        return restitemid;
    }

    public void setRestitemid(Integer restitemid) {
        this.restitemid = restitemid;
    }


    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    @SerializedName("Description")
    @Expose
    private String description;

    @SerializedName("Imagepath")
    @Expose
    private String imagepath;

}