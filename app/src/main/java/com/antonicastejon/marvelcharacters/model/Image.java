package com.antonicastejon.marvelcharacters.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class Image implements Parcelable {
    private String path;
    private String extension;

    protected Image(Parcel in) {
        path = in.readString();
        extension = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(path);
        parcel.writeString(extension);
    }
}
