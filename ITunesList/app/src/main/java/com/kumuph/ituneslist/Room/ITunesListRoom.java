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

    @ColumnInfo(name = "wrapperType")
    String wrappertype;

    @ColumnInfo(name = "kind")
    String kind;

    @ColumnInfo(name = "collectionId")
    int collectionid;

    @ColumnInfo(name = "trackId")
    int trackid;

    @ColumnInfo(name = "artistName")
    String artistname;

    @ColumnInfo(name = "collectionName")
    String collectionname;

    @ColumnInfo(name = "trackName")
    String trackname;

    @ColumnInfo(name = "collectionCensoredName")
    String collectioncensoredname;

    @ColumnInfo(name = "trackCensoredName")
    String trackcensoredname;

    @ColumnInfo(name = "collectionArtistId")
    int collectionartistid;

    @ColumnInfo(name = "collectionArtistViewUrl")
    String collectionartistviewurl;

    @ColumnInfo(name = "collectionViewUrl")
    String collectionviewurl;

    @ColumnInfo(name = "trackViewUrl")
    String trackviewurl;

    @ColumnInfo(name = "previewUrl")
    String previewurl;

    @ColumnInfo(name = "artworkurlmini")
    String artworkurl30;

    @ColumnInfo(name = "artworkurlsmall")
    String artworkurl60;

    @ColumnInfo(name = "artworkUrlbig")
    String artworkurl100;

    @ColumnInfo(name = "collectionPrice")
    float collectionprice;

    @ColumnInfo(name = "trackprice")
    float trackprice;

    @ColumnInfo(name = "trackRentalPrice")
    float trackrentalprice;

    @ColumnInfo(name = "collectionHdPrice")
    float collectionhdprice;

    @ColumnInfo(name = "trackHdPrice")
    float trackhdprice;

    @ColumnInfo(name = "trackHdRentalPrice")
    float trackhdrentalprice;

    @ColumnInfo(name = "releasedate")
    String releasedate;

    @ColumnInfo(name = "collectionexplicitness")
    String collectionexplicitness;

    @ColumnInfo(name = "trackExplicitness")
    String trackexplicitness;

    @ColumnInfo(name = "disccount")
    int disccount;

    @ColumnInfo(name = "discnumber")
    int discnumber;

    @ColumnInfo(name = "trackcount")
    int trackcount;

    @ColumnInfo(name = "tracknumber")
    int tracknumber;

    @ColumnInfo(name = "tracktimemillis")
    int tracktimemillis;

    @ColumnInfo(name = "country")
    String country;

    @ColumnInfo(name = "currency")
    String currency;

    @ColumnInfo(name = "primarygenrename")
    String primarygenrename;

    @ColumnInfo(name = "contentadvisoryrating")
    String contentadvisoryrating;

    @ColumnInfo(name = "shortDescription")
    String shortdescription;

    @ColumnInfo(name = "longDescription")
    String longdescription;

    @ColumnInfo(name = "hasITunesExtras")
    boolean hasitunesextras;

    //Instantiate Getters and Setters for Room Database


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWrappertype() {
        return wrappertype;
    }

    public void setWrappertype(String wrappertype) {
        this.wrappertype = wrappertype;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getCollectionid() {
        return collectionid;
    }

    public void setCollectionid(int collectionid) {
        this.collectionid = collectionid;
    }

    public int getTrackid() {
        return trackid;
    }

    public void setTrackid(int trackid) {
        this.trackid = trackid;
    }

    public String getArtistname() {
        return artistname;
    }

    public void setArtistname(String artistname) {
        this.artistname = artistname;
    }

    public String getCollectionname() {
        return collectionname;
    }

    public void setCollectionname(String collectionname) {
        this.collectionname = collectionname;
    }

    public String getTrackname() {
        return trackname;
    }

    public void setTrackname(String trackname) {
        this.trackname = trackname;
    }

    public String getCollectioncensoredname() {
        return collectioncensoredname;
    }

    public void setCollectioncensoredname(String collectioncensoredname) {
        this.collectioncensoredname = collectioncensoredname;
    }

    public String getTrackcensoredname() {
        return trackcensoredname;
    }

    public void setTrackcensoredname(String trackcensoredname) {
        this.trackcensoredname = trackcensoredname;
    }

    public int getCollectionartistid() {
        return collectionartistid;
    }

    public void setCollectionartistid(int collectionartistid) {
        this.collectionartistid = collectionartistid;
    }

    public String getCollectionartistviewurl() {
        return collectionartistviewurl;
    }

    public void setCollectionartistviewurl(String collectionartistviewurl) {
        this.collectionartistviewurl = collectionartistviewurl;
    }

    public String getCollectionviewurl() {
        return collectionviewurl;
    }

    public void setCollectionviewurl(String collectionviewurl) {
        this.collectionviewurl = collectionviewurl;
    }

    public String getTrackviewurl() {
        return trackviewurl;
    }

    public void setTrackviewurl(String trackviewurl) {
        this.trackviewurl = trackviewurl;
    }

    public String getPreviewurl() {
        return previewurl;
    }

    public void setPreviewurl(String previewurl) {
        this.previewurl = previewurl;
    }

    public String getArtworkurl30() {
        return artworkurl30;
    }

    public void setArtworkurl30(String artworkurl30) {
        this.artworkurl30 = artworkurl30;
    }

    public String getArtworkurl60() {
        return artworkurl60;
    }

    public void setArtworkurl60(String artworkurl60) {
        this.artworkurl60 = artworkurl60;
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

    public float getTrackprice() {
        return trackprice;
    }

    public void setTrackprice(float trackprice) {
        this.trackprice = trackprice;
    }

    public float getTrackrentalprice() {
        return trackrentalprice;
    }

    public void setTrackrentalprice(float trackrentalprice) {
        this.trackrentalprice = trackrentalprice;
    }

    public float getCollectionhdprice() {
        return collectionhdprice;
    }

    public void setCollectionhdprice(float collectionhdprice) {
        this.collectionhdprice = collectionhdprice;
    }

    public float getTrackhdprice() {
        return trackhdprice;
    }

    public void setTrackhdprice(float trackhdprice) {
        this.trackhdprice = trackhdprice;
    }

    public float getTrackhdrentalprice() {
        return trackhdrentalprice;
    }

    public void setTrackhdrentalprice(float trackhdrentalprice) {
        this.trackhdrentalprice = trackhdrentalprice;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public String getCollectionexplicitness() {
        return collectionexplicitness;
    }

    public void setCollectionexplicitness(String collectionexplicitness) {
        this.collectionexplicitness = collectionexplicitness;
    }

    public String getTrackexplicitness() {
        return trackexplicitness;
    }

    public void setTrackexplicitness(String trackexplicitness) {
        this.trackexplicitness = trackexplicitness;
    }

    public int getDisccount() {
        return disccount;
    }

    public void setDisccount(int disccount) {
        this.disccount = disccount;
    }

    public int getDiscnumber() {
        return discnumber;
    }

    public void setDiscnumber(int discnumber) {
        this.discnumber = discnumber;
    }

    public int getTrackcount() {
        return trackcount;
    }

    public void setTrackcount(int trackcount) {
        this.trackcount = trackcount;
    }

    public int getTracknumber() {
        return tracknumber;
    }

    public void setTracknumber(int tracknumber) {
        this.tracknumber = tracknumber;
    }

    public int getTracktimemillis() {
        return tracktimemillis;
    }

    public void setTracktimemillis(int tracktimemillis) {
        this.tracktimemillis = tracktimemillis;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrimarygenrename() {
        return primarygenrename;
    }

    public void setPrimarygenrename(String primarygenrename) {
        this.primarygenrename = primarygenrename;
    }

    public String getContentadvisoryrating() {
        return contentadvisoryrating;
    }

    public void setContentadvisoryrating(String contentadvisoryrating) {
        this.contentadvisoryrating = contentadvisoryrating;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public String getLongdescription() {
        return longdescription;
    }

    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    public boolean isHasitunesextras() {
        return hasitunesextras;
    }

    public void setHasitunesextras(boolean hasitunesextras) {
        this.hasitunesextras = hasitunesextras;
    }
}
