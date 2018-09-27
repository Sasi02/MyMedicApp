package com.example.user.mymedic.Model;

public class DrugScheduleHour {

    int id;
    String method;
    double noOfPills;
    double duration;

    public DrugScheduleHour() {}

    public DrugScheduleHour(int id, String method, double noOfPills, double duration) {
        this.id = id;
        this.method = method;
        this.noOfPills = noOfPills;
        this.duration = duration;
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
