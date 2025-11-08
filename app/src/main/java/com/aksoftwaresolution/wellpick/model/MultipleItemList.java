package com.aksoftwaresolution.wellpick.model;

public class MultipleItemList {

    private  String popularId;
    private  String popularTitle;
    private String popularImages;
    private int premiumId;
    private String premiumTitle;
    private String premiumImages;

    public MultipleItemList(String popularId,String popularTitle,String popularImages){
        this.popularId=popularId;
        this.popularTitle=popularTitle;
        this.popularImages=popularImages;

    }
 public MultipleItemList(int premiumId,String premiumTitle,String premiumImages){
        this.premiumId=premiumId;
        this.premiumTitle=premiumTitle;
        this.premiumImages=premiumImages;

 }


    public String getPopularId() {
        return popularId;
    }

    public String getPopularTitle() {
        return popularTitle;
    }

    public String getPopularImages() {
        return popularImages;
    }

    public int getPremiumId() {
        return premiumId;
    }

    public String getPremiumTitle() {
        return premiumTitle;
    }

    public String getPremiumImages() {
        return premiumImages;
    }
}
