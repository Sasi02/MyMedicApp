package com.example.user.mymedic.Model;

public class Drug {
    int id;
    String manufacturer;
    String name;
    double dosage;

    public Drug(){}

    public Drug(int id, String manufacturer, String name, double dosage){
        this.id = id;
        this.manufacturer = manufacturer;
        this.name = name;
        this.dosage = dosage;
    }
}
