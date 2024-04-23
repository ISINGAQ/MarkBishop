package com.example.schoolboard.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Roles {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("description")
    @Expose
    private String description;
}
