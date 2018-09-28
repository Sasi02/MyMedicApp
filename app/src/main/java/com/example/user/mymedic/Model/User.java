package com.example.user.mymedic.Model;

import java.util.Date;

public class User {
    int id;
    String firstName;
    String lastName;
    String initials;
    Date dob;
    String phone;
    String gender;
    String bloodGroup;
    String genDiseases;
    String allergies;
    String operations;



    public User(){}

    public User(String fname, String lname){
        this.firstName = fname;
        this.lastName = lname;
    }

    public User(int id, String firstName, String lastName, String initials, Date dob,
                String phone, String gender, String bloodGroup, String genDiseases,
                String allergies, String operations) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.initials = initials;
        this.dob = dob;
        this.phone = phone;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.genDiseases = genDiseases;
        this.allergies = allergies;
        this.operations = operations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        gender = gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getGenDiseases() {
        return genDiseases;
    }

    public void setGenDiseases(String genDiseases) {
        this.genDiseases = genDiseases;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }
}
