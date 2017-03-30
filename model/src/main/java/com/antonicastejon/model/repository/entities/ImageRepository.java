package com.antonicastejon.model.repository.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class ImageRepository implements Parcelable {
    private String path;
    private String extension;

    public ImageRepository(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    protected ImageRepository(Parcel in) {
        path = in.readString();
        extension = in.readString();
    }

    public static final Creator<ImageRepository> CREATOR = new Creator<ImageRepository>() {
        @Override
        public ImageRepository createFromParcel(Parcel in) {
            return new ImageRepository(in);
        }

        @Override
        public ImageRepository[] newArray(int size) {
            return new ImageRepository[size];
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
