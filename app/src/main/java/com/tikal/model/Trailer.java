package com.tikal.model;


import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by Elad
 */

@Parcel
public class Trailer {

    String id;
    String key;
    String name;

    @ParcelConstructor
    public Trailer(String id, String key, String name) {
        this.id = id;
        this.key = key;
        this.name = name;
    }


    public Trailer() {
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

}
