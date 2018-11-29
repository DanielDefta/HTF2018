package com.example.danieldefta.htf.models;

public class Supply {

    private String name;
    private int lat;
    private int lng;
    private String image;
    private String author;
    private String destined_users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLng() {
        return lng;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDestined_users() {
        return destined_users;
    }

    public void setDestined_users(String destined_users) {
        this.destined_users = destined_users;
    }

    public Supply(){

    }
}
