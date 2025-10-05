package com.aksoftwaresolution.wellpick.model;

public class MultipleItemList {

    private  String ItemId;
    private  String ItemTitle;
    private String ItemDescription;

    public MultipleItemList(String ItemId,String ItemTitle,String ItemDescription){
        this.ItemId=ItemId;
        this.ItemTitle=ItemTitle;
        this.ItemDescription=ItemDescription;

    }




    public String getItemId() {
        return ItemId;
    }

    public String getItemTitle() {
        return ItemTitle;
    }

    public String getItemDescription() {
        return ItemDescription;
    }
}
