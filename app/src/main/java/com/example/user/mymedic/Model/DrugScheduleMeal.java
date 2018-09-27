package com.example.user.mymedic.Model;

public class DrugScheduleMeal {

    private int id;
    private String method;
    private double noOfPills;
    private String selectedMeal;
    private String beforeOrAfter;
    private int prescriptionId;
    private int drugId;

    public DrugScheduleMeal() {}

    public DrugScheduleMeal(int id, String method, double noOfPills, String selectedMeal, String beforeOrAfter,
                            int prescriptionId, int drugId) {
        this.id = id;
        this.method = method;
        this.noOfPills = noOfPills;
        this.selectedMeal = selectedMeal;
        this.beforeOrAfter = beforeOrAfter;
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

    public String getSelectedMeal() {
        return selectedMeal;
    }

    public void setSelectedMeal(String selectedMeal) {
        this.selectedMeal = selectedMeal;
    }

    public String getBeforeOrAfter() {
        return beforeOrAfter;
    }

    public void setBeforeOrAfter(String beforeOrAfter) {
        this.beforeOrAfter = beforeOrAfter;
    }
}
