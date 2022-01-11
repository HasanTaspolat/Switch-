package com.example.aswitch;

import android.os.Parcel;
import android.os.Parcelable;

public class Currency implements Parcelable {
    private String name;
    private String price;
    private String img;

    public Currency(String name, String price, String img) {
        this.name = name;
        this.price = price;
        this.img = img;
    }

    protected Currency(Parcel in) {
        name = in.readString();
        price = in.readString();
        img = in.readString();
    }

    public static final Creator<Currency> CREATOR = new Creator<Currency>() {
        @Override
        public Currency createFromParcel(Parcel in) {
            return new Currency(in);
        }

        @Override
        public Currency[] newArray(int size) {
            return new Currency[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(price);
        parcel.writeString(img);
    }
}
