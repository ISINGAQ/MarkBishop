package com.example.schoolboard.ApiResponse;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event implements Parcelable {
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("statement")
    @Expose
    private String statement;
    @SerializedName("dateOfEvent")
    @Expose
    private String dateOfEvent;

    @SerializedName("organization")
    @Expose
    private String organization;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("gradeStart")
    @Expose
    private int gradeStart;

    @SerializedName("gradeEnd")
    @Expose
    private int gradeEnd;
    @SerializedName("length")
    @Expose
    private String length;
    @SerializedName("isInternal")
    @Expose
    private boolean isInternal;

    @SerializedName("userId")
    @Expose
    private int userId;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getDateOfEvent() {
        return dateOfEvent;
    }

    public void setDateOfEvent(String dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGradeStart() {
        return gradeStart;
    }

    public void setGradeStart(int gradeStart) {
        this.gradeStart = gradeStart;
    }

    public int getGradeEnd() {
        return gradeEnd;
    }

    public void setGradeEnd(int gradeEnd) {
        this.gradeEnd = gradeEnd;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public boolean isInternal() {
        return isInternal;
    }

    public void setInternal(boolean internal) {
        isInternal = internal;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Event(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.content = in.readString();
        this.image = in.readString();
        this.statement = in.readString();
        this.dateOfEvent = in.readString();
        this.gradeStart = in.readInt();
        this.gradeEnd = in.readInt();
        this.address = in.readString();
        this.length = in.readString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            this.isInternal = in.readBoolean();
        }
        this.organization = in.readString();
    }

    public void writeToParcel(@NonNull Parcel parcel, int i){
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(content);
        parcel.writeString(image);
        parcel.writeString(statement);
        parcel.writeString(dateOfEvent);
        parcel.writeInt(gradeStart);
        parcel.writeInt(gradeEnd);
        parcel.writeString(address);
        parcel.writeString(length);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            parcel.writeBoolean(isInternal);
        }
        parcel.writeString(organization);
    }

    public int describeContents() {
        return 0;
    }
    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}
