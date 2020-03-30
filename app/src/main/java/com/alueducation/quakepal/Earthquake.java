
/*
 * Copyright (c) 2020.
 * Name: Emmanuel Sackey
 * Matric: S1719015
 * Programme: Bsc(Hons) Computing
 *
 */

package com.alueducation.quakepal;

import androidx.annotation.NonNull;

import java.util.Comparator;

public class Earthquake implements Comparable<Earthquake> {
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

    @Override
    public int compareTo(Earthquake o) {
        return (int)this.magnitude - (int)o.getMagnitude();
    }


    class SortByDepth implements Comparator<Earthquake>{
        @Override
        public int compare(Earthquake o1, Earthquake o2) {
            return 0;
        }
    }
}
