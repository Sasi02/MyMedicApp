package com.example.user.mymedic.Model;

public class DrugScheduleHour {

    private int id;
    private String method;
    private double noOfPills;
    private double duration;
    private int prescriptionId;
    private int drugId;

    public DrugScheduleHour() {}

    public DrugScheduleHour(int id, String method, double noOfPills, double duration, int prescriptionId, int drugId) {
        this.id = id;
        this.method = method;
        this.noOfPills = noOfPills;
        this.duration = duration;
        this.prescriptionId = prescriptionId;
        this.drugId = drugId;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public double getNoOfPills() {
        return noOfPills;
    }

    public void setNoOfPills(double noOfPills) {
        this.noOfPills = noOfPills;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
