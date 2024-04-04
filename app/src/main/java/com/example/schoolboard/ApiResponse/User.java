package com.example.schoolboard.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User {
    @SerializedName("id")
    @Expose
    public static int id;
    @SerializedName("name")
    @Expose
    private static String name;
    @SerializedName("surname")
    @Expose
    private static String surname;
    @SerializedName("patronymic")
    @Expose
    private static String patronymic;
    @SerializedName("dateOfBirth")
    @Expose
    private static String dateOfBirth;
    @SerializedName("school")
    @Expose
    private static String school;
    @SerializedName("grade")
    @Expose
    private static int grade;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        User.id = id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        User.name = name;
    }

    public static String getSurname() {
        return surname;
    }

    public static void setSurname(String surname) {
        User.surname = surname;
    }

    public static String getPatronymic() {
        return patronymic;
    }

    public static void setPatronymic(String patronymic) {
        User.patronymic = patronymic;
    }

    public static String getDateOfBirth() {
        return dateOfBirth;
    }

    public static void setDateOfBirth(String dateOfBirth) {
        User.dateOfBirth = dateOfBirth;
    }

    public static String getSchool() {
        return school;
    }

    public static void setSchool(String school) {
        User.school = school;
    }

    public static int getGrade() {
        return grade;
    }

    public static void setGrade(int grade) {
        User.grade = grade;
    }

    public static int getOverallPoints() {
        return overallPoints;
    }

    public static void setOverallPoints(int overallPoints) {
        User.overallPoints = overallPoints;
    }

    public static int getCurrentPoints() {
        return currentPoints;
    }

    public static void setCurrentPoints(int currentPoints) {
        User.currentPoints = currentPoints;
    }

    @SerializedName("overallPoints")
    @Expose
    private static int overallPoints;
    @SerializedName("currentPoints")
    @Expose
    private static int currentPoints;
    @SerializedName("image")
    @Expose
    private static String image;

    public static String getImage() {
        return image;
    }

    public static void setImage(String image) {
        User.image = image;
    }

    public static ArrayList<Roles> roles;


}
