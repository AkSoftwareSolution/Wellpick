package com.aksoftwaresolution.wellpick.model;

public class User {
    private String id;
    private String name;
    private String encodeImage;
    private String description;

    public User(String id, String name, String encodeImage,String description) {
        this.id = id;
        this.name = name;
        this.encodeImage = encodeImage;
        this.description = description;

    }




    public String getName() { return name; }

    public String getdescription() { return description; }

    public String getId() {
        return id;
    }

    public String getEncodeImage() {
        return encodeImage;
    }


}
