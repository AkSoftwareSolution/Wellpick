package com.aksoftwaresolution.wellpick.model;

public class SubCategory {
    int id;
    String name;
    String image_url;
    String premiumId;
    String premiumName;
    String premiumImages;
    boolean premium;

    //add subCategory item and chack by constructor
    public SubCategory(int id, String name, String image_url) {
        this.id = id;
        this.name = name;
        this.image_url = image_url;
        this.premium = false;
    }
    //add subCategory premium item and chack by constructor is premium or not
    public SubCategory(String premiumId, String premiumName, String premiumImages, boolean premium) {
        this.premiumId =premiumId;
        this.premiumName = premiumName;
        this.premiumImages = premiumImages;
        this.premium = premium;
    }
    //get by PremiumId to use getter
    public String getPremiumId() {
        return premiumId;
    }
    //get by PremiumName to use getter
    public String getPremiumName() {
        return premiumName;
    }
    //get by PremiumImages to use getter
    public String getPremiumImages() {
        return premiumImages;
    }
    //get by getImage_url to use getter
    public String getImage_url() {
        return image_url;
    }
    //get by getId to use getter
    public int getId() {
        return id;
    }
    //get by getName to use getter
    public String getName() {
        return name;
    }
    //chack  by isPremium to use getter
    public boolean isPremium() {
        return premium;
    }
    //get by setPremium to use getter
    public void setPremium(boolean premium) {
        this.premium = premium;
    }
}
