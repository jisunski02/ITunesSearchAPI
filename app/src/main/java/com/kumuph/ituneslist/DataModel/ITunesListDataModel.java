package com.kumuph.ituneslist.DataModel;

public class ITunesListDataModel {
    //Data Model for ITunes Search API
    private String trackName;
    private String artWorkUrl100;
    private float collectionPrice;
    private String primaryGenreName;
    private String longDescription;
    private int collectionId;
    private int trackId;
    //Initialize ITunes Search API Constructor and its Parameters
    public ITunesListDataModel(
            String trackName,
            String artWorkUrl100,
            float collectionPrice,
            String primaryGenreName,
            String longDescription,
            int collectionId,
            int trackId
            )
    {

            this.trackName = trackName;
            this.artWorkUrl100 = artWorkUrl100;
            this.collectionPrice = collectionPrice;
            this.primaryGenreName = primaryGenreName;
            this.longDescription = longDescription;
            this.collectionId = collectionId;
            this.trackId = trackId;
    }

    //Instantiate Getters and Setters

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtWorkUrl100() {
        return artWorkUrl100;
    }

    public void setArtWorkUrl100(String artWorkUrl100) {
        this.artWorkUrl100 = artWorkUrl100;
    }

    public float getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(float collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }
}
