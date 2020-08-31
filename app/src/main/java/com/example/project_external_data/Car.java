package com.example.project_external_data;

public class Car {

    private int id;
    private String modle;
    private String color;
    private String discraption;
    private String image;
    private double distansePerLetter;


    public Car(int id, String modle, String color, String discraption, String image, double distansePerLetter) {
        this.id = id;
        this.modle = modle;
        this.color = color;
        this.discraption = discraption;
        this.image=image;
        this.distansePerLetter = distansePerLetter;
    }

    public Car(String modle, String color, String discraption, String image, double distansePerLetter) {
        this.modle = modle;
        this.color = color;
        this.discraption = discraption;
        this.image = image;
        this.distansePerLetter = distansePerLetter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModle() {
        return modle;
    }

    public void setModle(String modle) {
        this.modle = modle;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDiscraption() {
        return discraption;
    }

    public void setDiscraption(String discraption) {
        this.discraption = discraption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getDistansePerLetter() {
        return distansePerLetter;
    }

    public void setDistansePerLetter(double distansePerLetter) {
        this.distansePerLetter = distansePerLetter;
    }
}
