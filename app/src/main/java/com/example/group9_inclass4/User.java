package com.example.group9_inclass4;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{
    String name;
    String email;
    int id;
    String department;

    public User(String name, String email, int id, String department){
        this.name = name;
        this.email = email;
        this.id = id;
        this.department = department;
    }

    public User() {
    }

    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
        id = in.readInt();
        department = in.readString();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.email);
        parcel.writeInt(this.id);
        parcel.writeString(this.department);
    }
}
