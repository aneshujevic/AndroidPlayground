package com.example.intentsexample;

import android.os.Parcel;
import android.os.Parcelable;

public class TravellingMessageParcelable implements Parcelable {
    private String from;
    private String to;
    private String date;
    private String text;

    public  TravellingMessageParcelable() {

    }

    private TravellingMessageParcelable(Parcel in) {
        from = in.readString();
        to = in.readString();
        date = in.readString();
        text = in.readString();
    }

    public static final Creator<TravellingMessageParcelable> CREATOR = new Creator<TravellingMessageParcelable>() {
        @Override
        public TravellingMessageParcelable createFromParcel(Parcel in) {
            return new TravellingMessageParcelable(in);
        }

        @Override
        public TravellingMessageParcelable[] newArray(int size) {
            return new TravellingMessageParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(from);
        dest.writeString(to);
        dest.writeString(date);
        dest.writeString(text);
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
}
