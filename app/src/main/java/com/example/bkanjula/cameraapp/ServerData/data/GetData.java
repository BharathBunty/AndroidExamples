package com.example.bkanjula.cameraapp.ServerData.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class GetData implements Serializable {

    @SerializedName("Restaurantid")
    @Expose
    private Integer restaurantid;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Statustypeid")
    @Expose
    private Integer statustypeid;

    public ArrayList<Categories> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Categories> categories) {
        this.categories = categories;
    }

    @SerializedName("Categories")
    @Expose
    private ArrayList<Categories> categories;

    public Integer getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(Integer restaurantid) {
        this.restaurantid = restaurantid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStatustypeid() {
        return statustypeid;
    }

    public void setStatustypeid(Integer statustypeid) {
        this.statustypeid = statustypeid;
    }


}
