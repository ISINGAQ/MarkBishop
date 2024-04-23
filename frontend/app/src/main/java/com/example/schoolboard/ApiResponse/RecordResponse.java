package com.example.schoolboard.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecordResponse {
    @SerializedName("userId")
    @Expose
    private static int id;
    @SerializedName("eventId")
    @Expose
    private static int eventId;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        RecordResponse.id = id;
    }

    public static int getEventId() {
        return eventId;
    }

    public static void setEventId(int eventId) {
        RecordResponse.eventId = eventId;
    }
}
