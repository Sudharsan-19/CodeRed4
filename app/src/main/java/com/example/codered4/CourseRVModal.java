package com.example.codered4;


import android.os.Parcel;
import android.os.Parcelable;

public class CourseRVModal implements Parcelable {
    private String NameOfRecipient;
    private String aadharNumber;
    private String dateOfBirth;
    private String mobileNumber;
    private String bloodGroup;
    private String unitNeeded;
    private String NameofHospital;
    private String hospitalAddress;
    private String Reason_of_the_need;

    private CourseRVModal(){}

    public CourseRVModal(String nameOfRecipient, String aadharNumber, String dateOfBirth, String mobileNumber, String bloodGroup, String unitNeeded, String nameofHospital, String hospitalAddress, String reason_of_the_need) {
        this.NameOfRecipient = nameOfRecipient;
        this.aadharNumber = aadharNumber;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.bloodGroup = bloodGroup;
        this.unitNeeded = unitNeeded;
        this.NameofHospital = nameofHospital;
        this.hospitalAddress = hospitalAddress;
        this.Reason_of_the_need = reason_of_the_need;
    }

    protected CourseRVModal(Parcel in) {
        NameOfRecipient = in.readString();
        aadharNumber = in.readString();
        dateOfBirth = in.readString();
        mobileNumber = in.readString();
        bloodGroup = in.readString();
        unitNeeded = in.readString();
        NameofHospital = in.readString();
        hospitalAddress = in.readString();
        Reason_of_the_need = in.readString();
    }

    public static final Creator<CourseRVModal> CREATOR = new Creator<CourseRVModal>() {
        @Override
        public CourseRVModal createFromParcel(Parcel in) {
            return new CourseRVModal(in);
        }

        @Override
        public CourseRVModal[] newArray(int size) {
            return new CourseRVModal[size];
        }
    };

    public String getNameOfRecipient() {
        return NameOfRecipient;
    }

    public void setNameOfRecipient(String nameOfRecipient) {
        NameOfRecipient = nameOfRecipient;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getUnitNeeded() {
        return unitNeeded;
    }

    public void setUnitNeeded(String unitNeeded) {
        this.unitNeeded = unitNeeded;
    }

    public String getNameofHospital() {
        return NameofHospital;
    }

    public void setNameofHospital(String nameofHospital) {
        NameofHospital = nameofHospital;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getReason_of_the_need() {
        return Reason_of_the_need;
    }

    public void setReason_of_the_need(String reason_of_the_need) {
        Reason_of_the_need = reason_of_the_need;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(NameOfRecipient);
        parcel.writeString(aadharNumber);
        parcel.writeString(dateOfBirth);
        parcel.writeString(mobileNumber);
        parcel.writeString(bloodGroup);
        parcel.writeString(unitNeeded);
        parcel.writeString(NameofHospital);
        parcel.writeString(hospitalAddress);
        parcel.writeString(Reason_of_the_need);
    }
}
