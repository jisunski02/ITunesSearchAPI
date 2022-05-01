package com.kumuph.ituneslist.DataModel;

public class AlbumDataModel {
    private String artWorkUrl100;
    private String collectionName;
    private String collectionViewUrl;

    public AlbumDataModel(String artworkurl100, String collectionname, String collectionviewurl){
        this.artWorkUrl100 = artworkurl100;
        this.collectionName = collectionname;
        this.collectionViewUrl = collectionviewurl;
    }

    public String getArtWorkUrl100() {
        return artWorkUrl100;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }
}
