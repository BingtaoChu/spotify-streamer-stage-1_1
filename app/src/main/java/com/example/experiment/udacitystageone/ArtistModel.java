package com.example.experiment.udacitystageone;

import android.os.Parcel;
import android.os.Parcelable;

import kaaes.spotify.webapi.android.models.Artist;

/**
 * Created by experiment on 6/3/15.
 */
public class ArtistModel {
    public static final Parcelable.Creator<TrackModel> CREATOR = new Parcelable.Creator<TrackModel>() {
        public TrackModel createFromParcel(Parcel source) {
            return new TrackModel(source);
        }

        public TrackModel[] newArray(int size) {
            return new TrackModel[size];
        }};


    private String artistName;
    private String artistID;
    private String artistImg;

    public ArtistModel() {

    }
    public ArtistModel(Parcel in) {
        readFromParcel(in);
    }

    public ArtistModel( String artistName, String artistID, String artistImg ) {
        this.artistName = artistName;
        this.artistID = artistID;
        this.artistImg = artistImg;

    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int ignored) {
        dest.writeValue(artistName);
        dest.writeValue(artistID);
        dest.writeValue(artistImg);
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public void setArtistImg(String artistImg) {
        this.artistImg = artistImg;
    }

    public String getArtistImg() {

        return artistImg;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtistID() {
        return artistID;
    }

    private void readFromParcel(Parcel in)
    {
        this.artistName = in.readString();
        this.artistID = in.readString();
        this.artistImg = in.readString();
    }
}
