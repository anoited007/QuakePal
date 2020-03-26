package com.alueducation.quakepal;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

public class Earthquake {
    private String title;
    private String description;
    private String link;
    private String category;
    private String geoCoordinates;
    private String location;
    private String depth;
    private float magnitude;
    // using String for Datetime because there won't be any special use for it aside displaying it back
    private String dateTime;


    public Earthquake(){

    }

    public Earthquake(String title, String description, String link, String category, String geoCoordinates, String location, String depth, float magnitude, String dateTime) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.category = category;
        this.geoCoordinates = geoCoordinates;
        this.location = location;
        this.depth = depth;
        this.magnitude = magnitude;
        this.dateTime = dateTime;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGeoCoordinates() {
        return geoCoordinates;
    }

    public void setGeoCoordinates(String geoCoordinates) {
        this.geoCoordinates = geoCoordinates;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public float getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(float magnitude) {
        this.magnitude = magnitude;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @NonNull
    @Override
    public String toString() {
        return "Earthquake{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", category='" + category + '\'' +
                ", geoCoordinates='" + geoCoordinates + '\'' +
                '}';
    }
}
