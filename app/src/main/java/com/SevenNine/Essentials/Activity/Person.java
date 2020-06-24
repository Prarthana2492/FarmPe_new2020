package com.SevenNine.Essentials.Activity;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class Person implements ClusterItem {

    private final LatLng mPosition;
    private String name;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;


   public Person(double lat, double lng, String name, String id) {
        mPosition = new LatLng(lat, lng);
        this.name = name;this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getSnippet() {
        return id;
    }
}