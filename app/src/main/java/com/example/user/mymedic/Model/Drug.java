package com.example.user.mymedic.Model;

public class Drug {

    private int id;
    private String manufacturer;
    private String name;
    private double dosage;

    public Drug(){}

    public Drug(int id, String manufacturer, String name, double dosage){
        this.id = id;
        this.manufacturer = manufacturer;
        this.name = name;
        this.dosage = dosage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }
}
