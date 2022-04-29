package com.kumuph.ituneslist.DataModel;

public class ITunesListDataModel {
    //Data Model for ITunes Search API
    private String wrapperType;
    private String kind;
    private int collectionId;
    private int trackId;
    private String artistName;
    private String collectionName;
    private String trackName;
    private String collectionCensoredName;
    private String trackCensoredName;
    private int collectionArtistId;
    private String collectionArtistViewUrl;
    private String collectionViewUrl;
    private String trackViewUrl;
    private String previewUrl;
    private String artWorkUrl30;
    private String artWorkUrl60;
    private String artWorkUrl100;
    private float collectionPrice;
    private float trackPrice;
    private float trackRentalPrice;
    private float collectionHdPrice;
    private float trackHdPrice;
    private float trackHdRentalPrice;
    private String releaseDate;
    private String collectionExplicitness;
    private String trackExplicitness;
    private int discCount;
    private int discNumber;
    private int trackCount;
    private int trackNumber;
    private int trackTimeMillis;
    private String country;
    private String currency;
    private String primaryGenreName;
    private String contentAdvisoryRating;
    private String shortDescription;
    private String longDescription;
    private boolean hasITunesExtras;

    //Initialize ITunes Search API Constructor and its Parameters
    public ITunesListDataModel(
            String wrapperType,
            String kind,
            int collectionId,
            int trackId,
            String artistName,
            String collectionName,
            String trackName,
            String collectionCensoredName,
            String trackCensoredName,
            int collectionArtistId,
            String collectionArtistViewUrl,
            String collectionViewUrl,
            String trackViewUrl,
            String previewUrl,
            String artWorkUrl30,
            String artWorkUrl60,
            String artWorkUrl100,
            float collectionPrice,
            float trackPrice,
            float trackRentalPrice,
            float collectionHdPrice,
            float trackHdPrice,
            float trackHdRentalPrice,
            String releaseDate,
            String collectionExplicitness,
            String trackExplicitness,
            int discCount,
            int discNumber,
            int trackCount,
            int trackNumber,
            int trackTimeMillis,
            String country,
            String currency,
            String primaryGenreName,
            String contentAdvisoryRating,
            String shortDescription,
            String longDescription,
            boolean hasITunesExtras
            )
    {
            this.wrapperType = wrapperType;
            this.kind = kind;
            this.collectionId = collectionId;
            this.trackId = trackId;
            this.artistName = artistName;
            this.collectionName = collectionName;
            this.trackName = trackName;
            this.collectionCensoredName = collectionCensoredName;
            this.trackCensoredName = trackCensoredName;
            this.collectionArtistId = collectionArtistId;
            this.collectionArtistViewUrl = collectionArtistViewUrl;
            this.collectionViewUrl = collectionViewUrl;
            this.trackViewUrl = trackViewUrl;
            this.previewUrl = previewUrl;
            this.artWorkUrl30 = artWorkUrl30;
            this.artWorkUrl60 = artWorkUrl60;
            this.artWorkUrl100 = artWorkUrl100;
            this.collectionPrice = collectionPrice;
            this.trackPrice = trackPrice;
            this.trackRentalPrice = trackRentalPrice;
            this.collectionHdPrice = collectionHdPrice;
            this.trackHdPrice = trackHdPrice;
            this.trackHdRentalPrice = trackHdRentalPrice;
            this.releaseDate = releaseDate;
            this.collectionExplicitness = collectionExplicitness;
            this.trackExplicitness = trackExplicitness;
            this.discCount = discCount;
            this.discNumber = discNumber;
            this.trackCount = trackCount;
            this.trackNumber = trackNumber;
            this.trackTimeMillis = trackTimeMillis;
            this.country = country;
            this.currency = currency;
            this.primaryGenreName = primaryGenreName;
            this.contentAdvisoryRating = contentAdvisoryRating;
            this.shortDescription = shortDescription;
            this.longDescription = longDescription;
            this.hasITunesExtras = hasITunesExtras;
    }

    //Instantiate Getters and Setters


    public String getWrapperType() {
        return wrapperType;
    }

    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
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

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getCollectionCensoredName() {
        return collectionCensoredName;
    }

    public void setCollectionCensoredName(String collectionCensoredName) {
        this.collectionCensoredName = collectionCensoredName;
    }

    public String getTrackCensoredName() {
        return trackCensoredName;
    }

    public void setTrackCensoredName(String trackCensoredName) {
        this.trackCensoredName = trackCensoredName;
    }

    public int getCollectionArtistId() {
        return collectionArtistId;
    }

    public void setCollectionArtistId(int collectionArtistId) {
        this.collectionArtistId = collectionArtistId;
    }

    public String getCollectionArtistViewUrl() {
        return collectionArtistViewUrl;
    }

    public void setCollectionArtistViewUrl(String collectionArtistViewUrl) {
        this.collectionArtistViewUrl = collectionArtistViewUrl;
    }

    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    public void setCollectionViewUrl(String collectionViewUrl) {
        this.collectionViewUrl = collectionViewUrl;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getArtWorkUrl30() {
        return artWorkUrl30;
    }

    public void setArtWorkUrl30(String artWorkUrl30) {
        this.artWorkUrl30 = artWorkUrl30;
    }

    public String getArtWorkUrl60() {
        return artWorkUrl60;
    }

    public void setArtWorkUrl60(String artWorkUrl60) {
        this.artWorkUrl60 = artWorkUrl60;
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

    public float getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(float trackPrice) {
        this.trackPrice = trackPrice;
    }

    public float getTrackRentalPrice() {
        return trackRentalPrice;
    }

    public void setTrackRentalPrice(float trackRentalPrice) {
        this.trackRentalPrice = trackRentalPrice;
    }

    public float getCollectionHdPrice() {
        return collectionHdPrice;
    }

    public void setCollectionHdPrice(float collectionHdPrice) {
        this.collectionHdPrice = collectionHdPrice;
    }

    public float getTrackHdPrice() {
        return trackHdPrice;
    }

    public void setTrackHdPrice(float trackHdPrice) {
        this.trackHdPrice = trackHdPrice;
    }

    public float getTrackHdRentalPrice() {
        return trackHdRentalPrice;
    }

    public void setTrackHdRentalPrice(float trackHdRentalPrice) {
        this.trackHdRentalPrice = trackHdRentalPrice;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCollectionExplicitness() {
        return collectionExplicitness;
    }

    public void setCollectionExplicitness(String collectionExplicitness) {
        this.collectionExplicitness = collectionExplicitness;
    }

    public String getTrackExplicitness() {
        return trackExplicitness;
    }

    public void setTrackExplicitness(String trackExplicitness) {
        this.trackExplicitness = trackExplicitness;
    }

    public int getDiscCount() {
        return discCount;
    }

    public void setDiscCount(int discCount) {
        this.discCount = discCount;
    }

    public int getDiscNumber() {
        return discNumber;
    }

    public void setDiscNumber(int discNumber) {
        this.discNumber = discNumber;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public int getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public void setTrackTimeMillis(int trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
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

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    public String getContentAdvisoryRating() {
        return contentAdvisoryRating;
    }

    public void setContentAdvisoryRating(String contentAdvisoryRating) {
        this.contentAdvisoryRating = contentAdvisoryRating;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public boolean isHasITunesExtras() {
        return hasITunesExtras;
    }

    public void setHasITunesExtras(boolean hasITunesExtras) {
        this.hasITunesExtras = hasITunesExtras;
    }
}
