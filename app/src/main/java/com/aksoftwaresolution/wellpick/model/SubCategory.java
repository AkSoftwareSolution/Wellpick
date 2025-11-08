package com.aksoftwaresolution.wellpick.model;

public class SubCategory {
     int id;
     String name,image_url;



    public SubCategory(int id, String name,String image_url) {
        this.id = id;
        this.name = name;
        this.image_url=image_url;
    }


    public String getImage_url() {
        return image_url;
    }

    public int getId() { return id; }
    public String getName() { return name; }
}
