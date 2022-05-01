package com.kumuph.ituneslist.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(indices = {@Index(value = {"collectionId", "trackId"}, unique = true)}) //this will avoid duplicate insert on our Table

public class ITunesListRoom implements Serializable {

    //Creating rows for room database, this is where we save the data from ITunes search API
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "trackName") //
    String trackname;

    @ColumnInfo(name = "artworkUrl100") //
    String artworkurl100;

    @ColumnInfo(name = "collectionPrice") //
    float collectionprice;

    @ColumnInfo(name = "primarygenrename") //
    String primarygenrename;


    @ColumnInfo(name = "longDescription") //
    String longdescription;


    @ColumnInfo(name = "collectionId") //
    int colectionId;


    @ColumnInfo(name = "trackId") //
    int trackId;

    //Instantiate Getters and Setters for Room Database


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrackname() {
        return trackname;
    }

    public void setTrackname(String trackname) {
        this.trackname = trackname;
    }

    public String getArtworkurl100() {
        return artworkurl100;
    }

    public void setArtworkurl100(String artworkurl100) {
        this.artworkurl100 = artworkurl100;
    }

    public float getCollectionprice() {
        return collectionprice;
    }

    public void setCollectionprice(float collectionprice) {
        this.collectionprice = collectionprice;
    }

    public String getPrimarygenrename() {
        return primarygenrename;
    }

    public void setPrimarygenrename(String primarygenrename) {
        this.primarygenrename = primarygenrename;
    }

    public String getLongdescription() {
        return longdescription;
    }

    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    public int getColectionId() {
        return colectionId;
    }

    public void setColectionId(int colectionId) {
        this.colectionId = colectionId;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }
}
