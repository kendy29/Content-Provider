package com.ptcintadamai.costomerapp;

import android.os.Parcel;
import android.os.Parcelable;

public class note implements Parcelable {
    private int id;
    private String title;
    private String description;
    private String date;

    public note(int id, String title, String description, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.date);
    }
    public note() {
    }
    private note(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.date = in.readString();
    }
    public static final Creator<note> CREATOR = new Creator<note>() {
        @Override
        public note createFromParcel(Parcel source) {
            return new note(source);
        }
        @Override
        public note[] newArray(int size) {
            return new note[size];
        }
    };
}
