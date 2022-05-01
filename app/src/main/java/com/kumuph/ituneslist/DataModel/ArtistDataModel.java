package com.kumuph.ituneslist.DataModel;

public class ArtistDataModel {
    //Data Model for ITunes Artist Search API
    private String artistName;
    private String artistType;
    private String artistLinkUrl;

    //Initialize ITunes Artist Search API Constructor and its Parameters
    public ArtistDataModel(String artistname, String artistType, String artistlinkurl){
        this.artistName =  artistname;
        this.artistType = artistType;
        this.artistLinkUrl = artistlinkurl;
    }


    //Instantiate Getters and Setters

    public String getArtistName() {
        return artistName;
    }

    public String getArtistType() {
        return artistType;
    }

    public String getArtistLinkUrl() {
        return artistLinkUrl;
    }
}
