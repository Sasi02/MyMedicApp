package com.example.user.mymedic.Model;

import java.util.Date;

public class Prescription {
    private int id;
    private String name;
    private String docName;
    private String disease;
    private String discription;
    private Date startDate;
    private Date endDate;
    private int userId;

    public Prescription(){}

    public Prescription(int id, String name, String docName, String disease, String discription,
                        Date startDate, Date endDate, int userId) {
        this.id = id;
        this.name = name;
        this.docName = docName;
        this.disease = disease;
        this.discription = discription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
