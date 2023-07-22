package com.example.myapplication;

public class Products {

    private int product_ID;
    private String name;
    private String price;
    private byte[]image;

    public Products(int product_ID, byte[] image, String name, String price) {
        this.product_ID = product_ID;
        this.image = image;
        this.name = name;
        this.price = price;
    }




    public int getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(int product_ID) {
        this.product_ID = product_ID;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
