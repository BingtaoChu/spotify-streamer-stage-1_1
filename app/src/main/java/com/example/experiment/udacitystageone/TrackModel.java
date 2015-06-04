package com.example.experiment.udacitystageone;

import android.os.Parcel;
import android.os.Parcelable;

import kaaes.spotify.webapi.android.models.Track;

/**
 * Created by experiment on 6/3/15.
 */
public class TrackModel implements Parcelable {
    public static final Parcelable.Creator<TrackModel> CREATOR = new Parcelable.Creator<TrackModel>() {
    public TrackModel createFromParcel(Parcel source) {
        return new TrackModel(source);
    }

    public TrackModel[] newArray(int size) {
        return new TrackModel[size];
    }};


    private String txtSongName;
    private String txtAlbumName;
    private String imgUrl;

    public TrackModel() {

    }
    public TrackModel(Parcel in) {
        readFromParcel(in);
    }

    public void setTxtSongName(String txtSongName) {
        this.txtSongName = txtSongName;
    }

    public void setTxtAlbumName(String txtAlbumName) {
        this.txtAlbumName = txtAlbumName;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTxtSongName() {

        return txtSongName;
    }

    public String getTxtAlbumName() {
        return txtAlbumName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public TrackModel( String txtSongName, String txtAlbumName, String imgUrl ) {
        this.txtAlbumName = txtAlbumName;
        this.txtSongName = txtSongName;
        this.imgUrl = imgUrl;

    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int ignored) {
        dest.writeValue(txtAlbumName);
        dest.writeValue(txtSongName);
        dest.writeValue(imgUrl);
    }
    private void readFromParcel(Parcel in)
    {
        this.txtSongName = in.readString();
        this.txtAlbumName = in.readString();
        this.imgUrl = in.readString();
    }
}
