package com.lcz.utilslib.Phone;

import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by lcz on 2016/9/28.
 * 联系人实体类
 */

public class PhoneDto implements Parcelable{
    private String name;        //联系人姓名
    private String telPhone;    //电话号码

    protected PhoneDto(Parcel in) {
        name = in.readString();
        telPhone = in.readString();
    }


    public static final Creator<PhoneDto> CREATOR = new Creator<PhoneDto>() {
        @Override
        public PhoneDto createFromParcel(Parcel in) {
            return new PhoneDto(in);
        }

        @Override
        public PhoneDto[] newArray(int size) {
            return new PhoneDto[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public PhoneDto() {
    }

    public PhoneDto(String name, String telPhone) {
        this.name = name;
        this.telPhone = telPhone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(telPhone);
    }
}
