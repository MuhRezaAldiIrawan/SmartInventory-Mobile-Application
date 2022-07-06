package com.example.inventoryapp.HelperClass;

public class StockModel {

    String infostock;
    String jmlitem;
    String itemname;
    int image;

    public StockModel(String infostock, String jmlitem, String itemname, int image) {
        this.infostock = infostock;
        this.jmlitem = jmlitem;
        this.itemname = itemname;
        this.image = image;
    }

    public String getInfostock() {
        return infostock;
    }

    public String getJmlitem() {
        return jmlitem;
    }

    public String getItemname() {
        return itemname;
    }

    public int getImage() {
        return image;
    }
}


