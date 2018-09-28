package com.example.user.mymedic.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class User implements Parcelable {
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
    String email;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    protected User(Parcel in) {
        id = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        initials = in.readString();
        long tmpDob = in.readLong();
        dob = tmpDob != -1 ? new Date(tmpDob) : null;
        phone = in.readString();
        gender = in.readString();
        bloodGroup = in.readString();
        genDiseases = in.readString();
        allergies = in.readString();
        operations = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(initials);
        dest.writeLong(dob != null ? dob.getTime() : -1L);
        dest.writeString(phone);
        dest.writeString(gender);
        dest.writeString(bloodGroup);
        dest.writeString(genDiseases);
        dest.writeString(allergies);
        dest.writeString(operations);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
