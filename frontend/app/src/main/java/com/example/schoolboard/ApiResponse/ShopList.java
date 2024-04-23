package com.example.schoolboard.ApiResponse;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopList implements Parcelable {
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

    @SerializedName("cost")
    @Expose
    private int cost;

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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public ShopList(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.content = in.readString();
        this.image = in.readString();
        this.cost = in.readInt();
    }

    public void writeToParcel(@NonNull Parcel parcel, int i){
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(content);
        parcel.writeString(image);
        parcel.writeInt(cost);
    }

    public int describeContents() {
        return 0;
    }
    public static final Parcelable.Creator<ShopList> CREATOR = new Parcelable.Creator<ShopList>() {
        public ShopList createFromParcel(Parcel in) {
            return new ShopList(in);
        }

        public ShopList[] newArray(int size) {
            return new ShopList[size];
        }
    };
}
