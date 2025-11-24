package com.example.restpos.models;

public class Product {
    private int id;
    private String code;
    private String nameAr;
    private String nameEn;
    private int categoryId;
    private double price;
    private String printerGroup;
    private boolean active;

    public Product(int id, String code, String nameAr, String nameEn, int categoryId, double price, String printerGroup, boolean active) {
        this.id = id;
        this.code = code;
        this.nameAr = nameAr;
        this.nameEn = nameEn;
        this.categoryId = categoryId;
        this.price = price;
        this.printerGroup = printerGroup;
        this.active = active;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPrinterGroup() {
        return printerGroup;
    }

    public void setPrinterGroup(String printerGroup) {
        this.printerGroup = printerGroup;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}