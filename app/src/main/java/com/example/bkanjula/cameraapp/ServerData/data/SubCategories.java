package com.example.bkanjula.cameraapp.ServerData.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SubCategories implements Serializable {

    @SerializedName("Subcategoryid")
    @Expose
    private Integer subcategoryid;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Statustypeid")
    @Expose
    private Integer statustypeid;

    @SerializedName("RestaurantItems")
    @Expose
    private ArrayList<RestaurantItems> restaurantItems;

    public Integer getSubcategoryid() {
        return subcategoryid;
    }

    public void setSubcategoryid(Integer subcategoryid) {
        this.subcategoryid = subcategoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatustypeid() {
        return statustypeid;
    }

    public void setStatustypeid(Integer statustypeid) {
        this.statustypeid = statustypeid;
    }

    public ArrayList<RestaurantItems> getRestaurantItems() {
        return restaurantItems;
    }

    public void setRestaurantItems(ArrayList<RestaurantItems> restaurantItems) {
        this.restaurantItems = restaurantItems;
    }




}
