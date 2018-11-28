package com.example.bkanjula.cameraapp.LoginApp;

public class EmployeeDetails {

    String name;
    String Id;
    String email;
    String image;
    int position;

    public String getKeyRowid() {
        return keyRowid;
    }

    public void setKeyRowid(String keyRowid) {
        this.keyRowid = keyRowid;
    }

    String keyRowid;

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    String plot;
    String street;
    String area;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
