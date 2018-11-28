package com.example.bkanjula.cameraapp.ServerData.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Categories implements Serializable {

    @SerializedName("Categoryid")
    @Expose
    private Integer categoryid;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Statustypeid")
    @Expose
    private Integer statustypeid;

    @SerializedName("SubCategories")
    @Expose
    private ArrayList<SubCategories> subcategories;

    public ArrayList<SubCategories> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(ArrayList<SubCategories> subcategories) {
        this.subcategories = subcategories;
    }



    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
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



}
