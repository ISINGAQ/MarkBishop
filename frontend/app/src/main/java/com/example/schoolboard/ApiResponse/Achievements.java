package com.example.schoolboard.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Achievements {
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("file")
    @Expose
    private String file;
    @SerializedName("dateOfObtained")
    @Expose
    private String dateOfObtained;
    @SerializedName("isInternal")
    @Expose
    private boolean isInternal;
    @SerializedName("direction")
    @Expose
    private String direction;

    @SerializedName("userId")
    @Expose
    private int userId;
    @SerializedName("achievementGradeId")
    @Expose
    private int achievementGradeId;
    @SerializedName("achievementTypeId")
    @Expose
    private int achievementTypeId;

    public AchievementGrade achievementGrade;

    public AchievementType achievementType;

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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDateOfObtained() {
        return dateOfObtained;
    }

    public void setDateOfObtained(String dateOfObtained) {
        this.dateOfObtained = dateOfObtained;
    }

    public boolean isInternal() {
        return isInternal;
    }

    public void setInternal(boolean internal) {
        isInternal = internal;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAchievementGradeId() {
        return achievementGradeId;
    }

    public void setAchievementGradeId(int achievementGradeId) {
        this.achievementGradeId = achievementGradeId;
    }

    public int getAchievementTypeId() {
        return achievementTypeId;
    }

    public void setAchievementTypeId(int achievementTypeId) {
        this.achievementTypeId = achievementTypeId;
    }

    public AchievementGrade getAchievementGrade() {
        return achievementGrade;
    }

    public void setAchievementGrade(AchievementGrade achievementGrade) {
        this.achievementGrade = achievementGrade;
    }

    public AchievementType getAchievementType() {
        return achievementType;
    }

    public void setAchievementType(AchievementType achievementType) {
        this.achievementType = achievementType;
    }
}
