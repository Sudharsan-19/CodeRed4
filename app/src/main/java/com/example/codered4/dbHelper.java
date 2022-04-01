package com.example.codered4;

import android.os.Parcel;
import android.os.Parcelable;

public class dbHelper implements Parcelable{

    String UName;
    String UAge;
    String USex;
    String UBlood_Group;
    String UEmail;
    String UmobNumber;
    String UworkLoc;
    String UhomeLoc;


    private dbHelper(){

    }

    public dbHelper(String UName, String UAge, String USex, String UBlood_Group, String UEmail, String umobNumber, String uworkLoc, String uhomeLoc) {
        this.UName = UName;
        this.UAge = UAge;
        this.USex = USex;
        this.UBlood_Group = UBlood_Group;
        this.UEmail = UEmail;
        UmobNumber = umobNumber;
        UworkLoc = uworkLoc;
        UhomeLoc = uhomeLoc;
    }

    protected dbHelper(Parcel in) {
        UName = in.readString();
        UAge = in.readString();
        USex = in.readString();
        UBlood_Group = in.readString();
        UEmail = in.readString();
        UmobNumber = in.readString();
        UworkLoc = in.readString();
        UhomeLoc = in.readString();
    }

    public static final Creator<dbHelper> CREATOR = new Creator<dbHelper>() {
        @Override
        public dbHelper createFromParcel(Parcel in) {
            return new dbHelper(in);
        }

        @Override
        public dbHelper[] newArray(int size) {
            return new dbHelper[size];
        }
    };

    public String getUName() {
        return UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public String getUAge() {
        return UAge;
    }

    public void setUAge(String UAge) {
        this.UAge = UAge;
    }

    public String getUSex() {
        return USex;
    }

    public void setUSex(String USex) {
        this.USex = USex;
    }

    public String getUBlood_Group() {
        return UBlood_Group;
    }

    public void setUBlood_Group(String UBlood_Group) {
        this.UBlood_Group = UBlood_Group;
    }

    public String getUEmail() {
        return UEmail;
    }

    public void setUEmail(String UEmail) {
        this.UEmail = UEmail;
    }

    public String getUmobNumber() {
        return UmobNumber;
    }

    public void setUmobNumber(String umobNumber) {
        UmobNumber = umobNumber;
    }

    public String getUworkLoc() {
        return UworkLoc;
    }

    public void setUworkLoc(String uworkLoc) {
        UworkLoc = uworkLoc;
    }

    public String getUhomeLoc() {
        return UhomeLoc;
    }

    public void setUhomeLoc(String uhomeLoc) {
        UhomeLoc = uhomeLoc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(UName);
        parcel.writeString(UAge);
        parcel.writeString(USex);
        parcel.writeString(UBlood_Group);
        parcel.writeString(UEmail);
        parcel.writeString(UmobNumber);
        parcel.writeString(UworkLoc);
        parcel.writeString(UhomeLoc);
    }
}
